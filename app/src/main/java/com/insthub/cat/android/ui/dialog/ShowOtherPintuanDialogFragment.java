package com.insthub.cat.android.ui.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.fragment.BaseDialogFragment;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.module2.PintuanListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.PintuanAdatper;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by linux on 2018/8/8.
 */

public class ShowOtherPintuanDialogFragment extends BaseDialogFragment<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.recycleview)
    RecyclerView recyclerview;

    @Bind(R.id.ic_close)
    ImageView ivClose;


    private DismissCallback callback;


    private ArrayList<PintuanListData.DataBean.ListBean> dataList = new ArrayList<>();

     private PintuanAdatper mProductAdapter;

     private String product_id;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme_AppCompat_Dialog_Alert_Translate2);
//        Dialog dialog = getDialog();
//        if (dialog != null) {
//            DisplayMetrics dm = new DisplayMetrics();
//            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), (int) (dm.widthPixels * 0.6));
//        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.9), (int) (dm.heightPixels * 0.6));
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        product_id=String.valueOf(getArguments().getInt(ConstantsKeys.KEY_DATA)) ;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mProductAdapter = new PintuanAdatper(getContext(), dataList);
        recyclerview.setAdapter(mProductAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, false, R.drawable.item_divider_black_1));
        bindEvent();
        mPresenter.getPintuanList(product_id);


    }


    private void  bindEvent()
    {



        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        mProductAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                dismiss();

                if(callback!=null)
                {
                    callback.dismiss();
                }


            }
        });
    }



    public void setDismissCallback(DismissCallback param)
    {

        callback = param;
    }



    @Override
    public void showSuccess(Object object) {
        if(object instanceof PintuanListData)
        {
            PintuanListData data = (PintuanListData)object;

            dataList.clear();
            dataList.addAll(data.getData().getList());
            mProductAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void showError(String msg, int code) {

    }

    @Override
    public void bindPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_pintuanlist;
    }




    public  interface DismissCallback
    {
        public void dismiss();
    }



}
