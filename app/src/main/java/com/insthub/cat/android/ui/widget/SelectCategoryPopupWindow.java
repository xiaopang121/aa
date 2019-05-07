package com.insthub.cat.android.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.manager.DBManager;
import com.insthub.cat.android.module.LabelListData;
import com.insthub.cat.android.ui.adatper.PopupwindowLabel1Adapter;
import com.insthub.cat.android.ui.adatper.PopupwindowLabel2Adapter;
import com.insthub.cat.android.utils.ScreenUtils;

import java.util.ArrayList;

/**
 * User:macbook
 * DATE:2018/11/2 21:12
 * Desc:${DESC}
 */
public class SelectCategoryPopupWindow extends PopupWindow {



    private View view;

    public TextView rvCategoryFirst;
    public RecyclerView recyclerview;
    private ArrayList<LabelListData.DataBean.LabelLv1Bean> dataList = new ArrayList<>();
    private PopupwindowLabel1Adapter mPopupwindowAdapter;



    public TextView tvCategorySecond;

    public RecyclerView recyclerSecond;
    private ArrayList<LabelListData.DataBean.LabelLv1Bean.LabelLv2Bean>  dataChildList = new ArrayList<>();
    private PopupwindowLabel2Adapter mPopupwindowLabel2Adapter;


    private Context context;


    DBManager mDBManager;

    private LabelListData mLabelListData;

    private int label1Postion=-1,label2Positon=-1;




    public SelectCategoryPopupWindow(Context context)
    {

        this.context = context;
        mDBManager = new DBManager(context);
        int height = ScreenUtils.getScreenHeight(context);
        setHeight(height/10*4);
        view = LayoutInflater.from(context).inflate(R.layout.view_my_drop_down_expanded,null);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
        rvCategoryFirst = view.findViewById(R.id.tv_category_one);
        tvCategorySecond = view.findViewById(R.id.tv_category_two);
        recyclerview = view.findViewById(R.id.listView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mPopupwindowAdapter = new PopupwindowLabel1Adapter(context, dataList);
        recyclerview.setAdapter(mPopupwindowAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_1));
        recyclerview.scrollToPosition(0);


        recyclerSecond = view.findViewById(R.id.listView2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerSecond.setLayoutManager(layoutManager2);
        recyclerSecond.setHasFixedSize(true);
        mPopupwindowLabel2Adapter = new PopupwindowLabel2Adapter(context, dataChildList);
        recyclerSecond.setAdapter(mPopupwindowLabel2Adapter);
        recyclerSecond.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_1));
        recyclerSecond.scrollToPosition(0);

        bindEvent();

        setContentView(view);

    }


    public void setData(LabelListData param,int label1Postion,int label2Positon)
    {
        this.mLabelListData=param;
        if(label1Postion>=0)
        {
            this.label1Postion=label1Postion;
            rvCategoryFirst.setText(mLabelListData.getData().getLabel_lv1().get(label1Postion).getLable_name());


        }
        if(dataList.isEmpty())
        {
            dataList.addAll(mLabelListData.getData().getLabel_lv1());


        }
        mPopupwindowAdapter.notifyDataSetChanged();
        recyclerview.setVisibility(View.VISIBLE);

        if(label1Postion>=0&&label2Positon>=0)
        {
            this.label2Positon = label2Positon;
            tvCategorySecond.setText(mLabelListData.getData().getLabel_lv1().get(label1Postion).getLabel_lv2().get(label2Positon).getLable_name());
        }

    }


    public int getLabel1Postion()
    {
        return label1Postion;
    }

    public int getLabel2Position()
    {
        return label2Positon;
    }





    private void bindEvent()
    {

        rvCategoryFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(dataList.isEmpty())
                {
                    dataList.addAll(mLabelListData.getData().getLabel_lv1());


                }
                mPopupwindowAdapter.notifyDataSetChanged();
                recyclerview.setVisibility(View.VISIBLE);
            }
        });


        tvCategorySecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(label1Postion==-1)
                {
                    ToastUtil.show(context,"请选择主分类");
                    return;
                }


                dataChildList.clear();
                dataChildList.addAll(mLabelListData.getData().getLabel_lv1().get(label1Postion).getLabel_lv2());
                mPopupwindowLabel2Adapter.notifyDataSetChanged();
                recyclerSecond.setVisibility(View.VISIBLE);

            }
        });



        mPopupwindowAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position!= label1Postion)
                {
                    label1Postion = position;
                    label2Positon =-1;
                }
                rvCategoryFirst.setText(mLabelListData.getData().getLabel_lv1().get(label1Postion).getLable_name());

                recyclerview.setVisibility(View.GONE);
            }
        });



        mPopupwindowLabel2Adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                label2Positon = position;
                tvCategorySecond.setText(mLabelListData.getData().getLabel_lv1().get(label1Postion).getLabel_lv2().get(label2Positon).getLable_name());
                recyclerSecond.setVisibility(View.GONE);

                dismiss();

            }
        });
    }




    public void  show(View view)
    {

        setWidth(view.getWidth());
        showAsDropDown(view, 0, 0);
    }



    public interface  SelectLabelCallback
    {
        public void onSelectLabelV1(int label1Position);
        public void onSelectLabelV2( int label2Position);

    }


}
