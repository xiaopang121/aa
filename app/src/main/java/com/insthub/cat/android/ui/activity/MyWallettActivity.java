package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.insthub.cat.android.R;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.utils.DialogUtils;

import butterknife.Bind;


/**
 * 钱包
 * Created by linux on 2017/6/28.
 */

public class MyWallettActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.rll_history_record)
    RelativeLayout rllHistoryRecord;

    @Bind(R.id.rll_withdraw)
    RelativeLayout rllWithdraw;

    @Bind(R.id.tv_money_title)
    TextView tvMoneyTitle;


    @Bind(R.id.tv_money)
    TextView tvMoney;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_wallet;
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
        commonTitleBar.setTitle(R.string.title_wallet);
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


        rllHistoryRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(HistoryActivity.class);
            }
        });


        rllWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(CacheManager.getInstance().getUserInfo().getData().getBalance()<10)
                {
                    DialogUtils.showWithdrawDialog(getContext()).show();

                }else
                {
                    startActivity(WithdrawActivity.class);
                }

            }
        });


    }


    @Override
    protected void bindData() {
        super.bindData();

        tvMoney.setText("￥"+CacheManager.getInstance().getUserInfo().getData().getBalance());

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


}
