package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.fui.widget.CustomViewPager;
import com.common.android.futils.ScreenInfo;
import com.insthub.cat.android.R;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.CommonFragmentAdatper;
import com.insthub.cat.android.ui.fragment.HistoryFragment.IncomeFragment;
import com.insthub.cat.android.ui.fragment.HistoryFragment.ReceiverFragment;
import com.insthub.cat.android.ui.fragment.HistoryFragment.WithdrawFragment;
import com.viewpagerindicator.UnderlinePageIndicator;

import butterknife.Bind;


/**
 * 历史记录
 * Created by linux on 2017/6/28.
 */

public class HistoryActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.viewpager)
    CustomViewPager vitePager;

    @Bind(R.id.linepageIndicator)
    UnderlinePageIndicator linepageIndicator;
    @Bind(R.id.menu_get)
    RadioButton menuGet;
    @Bind(R.id.menu_income)
    RadioButton menuIncome;
    @Bind(R.id.menu_withdraw)
    RadioButton menuWithdraw;
    @Bind(R.id.menu_categrary)
    RadioGroup menuGroup;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_history;
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
        commonTitleBar.setTitle(R.string.title_history_record);
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        setupViewPager(vitePager);

    }


    private void setupViewPager(ViewPager viewPager) {
        CommonFragmentAdatper adapter = new CommonFragmentAdatper(getSupportFragmentManager());
        adapter.addFragment(IncomeFragment.newInstance(0), this.getString(R.string.title_dailingqu));
        adapter.addFragment(IncomeFragment.newInstance(1), this.getString(R.string.title_record));
        adapter.addFragment(IncomeFragment.newInstance(2), this.getString(R.string.title_width));
        viewPager.setAdapter(adapter);
        linepageIndicator.setViewPager(vitePager);
        linepageIndicator.setFades(false);
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

        menuGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.menu_get:
                        vitePager.setCurrentItem(0);
                        break;
                    case R.id.menu_income:
                        vitePager.setCurrentItem(1);
                        break;
                    case R.id.menu_withdraw:
                        vitePager.setCurrentItem(2);
                        break;
                }
            }
        });
    }


    @Override
    protected void bindData() {
        super.bindData();


    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {

    }

    @Override
    public void showError(String msg,int code) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


}
