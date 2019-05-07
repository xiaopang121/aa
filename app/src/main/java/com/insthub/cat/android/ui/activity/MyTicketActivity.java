package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.GuidePagerAdapter;
import com.insthub.cat.android.ui.fragment.TicketFragment.TicketFragment;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.UnderlinePageIndicator;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 我的发票
 * Created by linux on 2017/6/28.
 */

public class MyTicketActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.vite_pager)
    ViewPager vitePager;
    @Bind(R.id.tabPageIndicator)
    TabPageIndicator tabPageIndicator;
    @Bind(R.id.linepageIndicator)
    UnderlinePageIndicator linepageIndicator;



    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    private GuidePagerAdapter mGuidePagerAdapter;
    private String [] titles = new String[]{"全部","本季","本月"};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_ticket;
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
        commonTitleBar.setTitle("我的发票");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);


        mGuidePagerAdapter = new GuidePagerAdapter(getSupportFragmentManager(),titles);
        fragmentArrayList.add(TicketFragment.newInstance(0));
        fragmentArrayList.add(TicketFragment.newInstance(1));
        fragmentArrayList.add(TicketFragment.newInstance(2));
        mGuidePagerAdapter.addAllItems(fragmentArrayList);
        vitePager.setOffscreenPageLimit(3);
        vitePager.setAdapter(mGuidePagerAdapter);

        linepageIndicator.setViewPager(vitePager);
        linepageIndicator.setFades(false);
        tabPageIndicator.setViewPager(vitePager);
        tabPageIndicator.setOnPageChangeListener(linepageIndicator);

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


        linepageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
        dismissLoadDialog();
        ToastUtil.show(getActivity(),msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


}
