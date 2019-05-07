package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.insthub.cat.android.R;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import butterknife.Bind;


/**
 * 会员服务
 * Created by linux on 2017/6/28.
 */

public class MemberServiceActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.tv_title1)
    TextView tvTitle1;


    @Bind(R.id.ic_service_money)
    TextView icServiceMoney;

    @Bind(R.id.ic_service_bond2333)
    TextView icServiceBond2333;

    @Bind(R.id.item_save_time)
    TextView tvSaveTime;

    @Bind(R.id.tv_title2)
    TextView tvTitle2;

    @Bind(R.id.ic_service_bond)
    TextView icServiceBond;

    @Bind(R.id.ic_service_bond2)
    TextView icServiceBond2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_member_service;
    }

    @Override
    protected int bindColorPrimary() {
        return 0;
    }


    @Override
    protected void bindPresenter() {
        super.bindPresenter();
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void bindViewById() {
        super.bindViewById();
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        commonTitleBar.setTitle("会员服务");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);

    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        commonTitleBar.ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        icServiceBond2333.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(MemberRechargeActivity.class,100);
            }
        });

        icServiceBond2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(MemberRechargeActivity.class,100);
            }
        });



    }


    @Override
    protected void bindData() {
        super.bindData();


        icServiceMoney.setText("¥"+ CacheManager.getInstance().getUserInfo().getData().getSave_money()+"元/年");

        tvSaveTime.setText( CacheManager.getInstance().getUserInfo().getData().getService_fee_time());


        icServiceBond.setText("¥"+CacheManager.getInstance().getUserInfo().getData().getService_money());


        long servieFeeTime = 0;

        if(!TextUtils.isEmpty(CacheManager.getInstance().getUserInfo().getData().getService_fee_time()))
        {
            servieFeeTime= TimeUtils.parserTime(CacheManager.getInstance().getUserInfo().getData().getService_fee_time(),TimeUtils.FROAMTE_YMHMS);
        }
        if(CacheManager.getInstance().getUserInfo().getData().getService_fee()==0|| servieFeeTime<System.currentTimeMillis())
        {
            icServiceBond2333.setVisibility(View.VISIBLE);
        }else
        {

            icServiceBond2333.setVisibility(View.GONE);
        }


        //判断是否到期  是否已经缴费
        if(CacheManager.getInstance().getUserInfo().getData().getIs_save()==1 )
        {
            icServiceBond2.setVisibility(View.GONE);
        }else
        {

            icServiceBond2.setVisibility(View.VISIBLE);
        }




    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {

    }

    @Override
    public void showError(String msg, int code) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100 && resultCode== Activity.RESULT_OK)
        {
            finish();
        }
    }
}
