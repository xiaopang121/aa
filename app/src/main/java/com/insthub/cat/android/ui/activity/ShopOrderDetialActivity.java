package com.insthub.cat.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.android.ftheme.widgets.TintTextView;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.OrderDetialData;
import com.insthub.cat.android.module2.ShopOrderListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import butterknife.Bind;


/**
 * 订单详情
 * Created by linux on 2017/6/28.
 */

public class ShopOrderDetialActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.field_order_client)
    TextView fieldOrderClient;

    @Bind(R.id.field_order_call)
    TextView fieldOrderCall;

    @Bind(R.id.rll_select_category)
    LinearLayout rllSelectCategory;

    @Bind(R.id.field_order_categary)
    TextView fieldOrderCategary;

    @Bind(R.id.field_order_money)
    TextView fieldOrderMoney;

    @Bind(R.id.lly_goodness)
    LinearLayout llyGoodness;

    @Bind(R.id.field_order_date)
    TextView fieldOrderDate;

    @Bind(R.id.tintTextView)
    TintTextView tintTextView;

    @Bind(R.id.field_order_other)
    TextView fieldOrderOther;

    @Bind(R.id.field_order_amount)
    TextView fieldOrderAmount;

    @Bind(R.id.field_order_num)
    TextView fieldOrderNum;

    @Bind(R.id.field_order_contract)
    TextView fieldOrderContract;

    @Bind(R.id.field_order_paytype)
    TextView fieldOrderPaytype;

    @Bind(R.id.field_order_createtime)
    TextView fieldOrderCreatetime;
    @Bind(R.id.tv_pay_state)
    TintTextView tvPayState;


    private ShopOrderListData.DataBean.ListBean dataBaean;

    private OrderDetialData mOrderDetialData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shop_order_detial;
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
        commonTitleBar.setTitle("订单详情");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);

        dataBaean = (ShopOrderListData.DataBean.ListBean) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);


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


        rllSelectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(ShopCategoryActivity.class, 200);
            }
        });


    }


    @Override
    protected void bindData() {
        super.bindData();
        mPresenter.getOrderDetial(CacheManager.getInstance().getToken().getData().getUser_id(), CacheManager.getInstance().getToken().getData().getToken(), dataBaean.getOrder_id());

    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {

        dismissLoadDialog();
        if (object instanceof OrderDetialData) {
            mOrderDetialData = (OrderDetialData) object;
            refreshData();
        }

    }

    @Override
    public void showError(String msg, int code) {

        dismissLoadDialog();
        ToastUtil.show(this, msg);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    public void refreshData() {
        if (mOrderDetialData != null) {
            fieldOrderClient.setText(mOrderDetialData.getData().getUser_name());

            fieldOrderCategary.setText(mOrderDetialData.getData().getService_content());

            fieldOrderCall.setText(mOrderDetialData.getData().getPhone());

            fieldOrderMoney.setText("¥" + mOrderDetialData.getData().getService_price());

            fieldOrderDate.setText(mOrderDetialData.getData().getService_time());

            fieldOrderOther.setText(mOrderDetialData.getData().getRemarks());


            switch (mOrderDetialData.getData().getState()) {
                case 0:

                    tvPayState.setVisibility(View.GONE);
                    fieldOrderAmount.setText("未付款");
                    break;
                case 1:
                    tvPayState.setVisibility(View.VISIBLE);
                    fieldOrderAmount.setText("付款成功");
                    break;

                case 2:
                    tvPayState.setVisibility(View.GONE);
                    fieldOrderAmount.setText("已确认付款给商家");
                    break;
            }


            fieldOrderNum.setText(mOrderDetialData.getData().getOrder_id());

         //   fieldOrderContract.setText();

            fieldOrderCreatetime.setText(mOrderDetialData.getData().getCreate_time());
        }
    }


}
