package com.insthub.cat.android.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.City;
import com.insthub.cat.android.ui.adatper.BidPriceAdapter;
import com.insthub.cat.android.ui.adatper.PopupwindowAdapter;
import com.insthub.cat.android.ui.viewholder.DashlineItemDivider;
import com.insthub.cat.android.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:macbook
 * DATE:2018/11/2 21:12
 * Desc:${DESC}
 */
public class SelectCommonWindow extends PopupWindow {



    private View view;


    public RecyclerView recyclerview;

    private ArrayList<City>  dataList = new ArrayList<>();
    private PopupwindowAdapter mPopupwindowAdapter;

    private SelectCityCallback mSelectCityCallback;

    public SelectCommonWindow(Context context,SelectCityCallback callback)
    {

        mSelectCityCallback = callback;
        int height = ScreenUtils.getScreenHeight(context);
        setHeight(height/3);
        view = LayoutInflater.from(context).inflate(R.layout.common_popupwindow,null);
        setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        setOutsideTouchable(true);

        recyclerview = view.findViewById(R.id.listView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mPopupwindowAdapter = new PopupwindowAdapter(context, dataList);
        recyclerview.setAdapter(mPopupwindowAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_1));
        recyclerview.scrollToPosition(0);

        setContentView(view);


        bindEvent();
    }



    private void bindEvent()
    {
        mPopupwindowAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                if(mSelectCityCallback!=null)
                {
                    mSelectCityCallback.onSelectCity(dataList.get(position).getName());
                }
                dismiss();
            }
        });
    }



    public void setData(List<City> data)
    {
        dataList.clear();
        dataList.addAll(data);
        mPopupwindowAdapter.notifyDataSetChanged();
    }





    public void  show(View view)
    {

        setWidth(view.getWidth());
        showAsDropDown(view, 0, 0);
    }



    public interface  SelectCityCallback
    {
        public void onSelectCity(String city);
    }

}
