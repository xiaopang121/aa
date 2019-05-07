package com.insthub.cat.android.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.fui.widget.CustomViewPager;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.CollectListData;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.CollectServiceAdapter;
import com.insthub.cat.android.ui.adatper.CollectStoreAdapter;
import com.insthub.cat.android.ui.adatper.CommonFragmentAdatper;
import com.insthub.cat.android.ui.extend.PullToRefreshRecyclerViewExtends;
import com.insthub.cat.android.ui.fragment.CollectFragment.CollectServiceFragment;
import com.insthub.cat.android.ui.fragment.CollectFragment.CollectStoreFragment;
import com.insthub.cat.android.ui.fragment.LabelFragment.LabelFragment;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 收藏界面
 * Created by linux on 2017/6/28.
 */

public class CollectActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View{


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.tl)
    TabLayout tl;

    @Bind(R.id.viewpager)
    CustomViewPager vitePager;

    private CommonFragmentAdatper mGuidePagerAdapter;
    private String[] titles;

    private List<String> tabs = new ArrayList<>();
    public static final int MOVABLE_COUNT = 5;

    private int tabCount;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_collect;
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
        commonTitleBar.setTitle("我的收藏");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);


        titles = new String[]{getString(R.string.menu_shop_has_withdraw), getString(R.string.menu_shop_no_withdraw)};
        mGuidePagerAdapter = new CommonFragmentAdatper(getSupportFragmentManager());

        tabs.clear();

        tabs.add("店铺");
        tabs.add("服务");

        mGuidePagerAdapter.addFragment(CollectStoreFragment.newInstance(),"店铺");

        mGuidePagerAdapter.addFragment(CollectServiceFragment.newInstance(),"服务");
        tabCount= tabs.size();
        vitePager.setOffscreenPageLimit(3);
        vitePager.setAdapter(mGuidePagerAdapter);
        vitePager.setVisibility(View.VISIBLE);
        initTabLayout();

    }

    private void initTabLayout() {
        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        tl.setTabMode(tabCount <= MOVABLE_COUNT ? TabLayout.MODE_FIXED : TabLayout.MODE_SCROLLABLE);
        //指示条的颜色
        tl.setSelectedTabIndicatorColor(getResources().getColor(R.color.yellow_color_pressed));
        tl.setSelectedTabIndicatorHeight((int) getResources().getDimension(R.dimen.view_margin_2));
        //关联tabLayout和ViewPager,两者的选择和滑动状态会相互影响
        tl.setupWithViewPager(vitePager);
        //自定义标签布局
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = tl.getTabAt(i);
            TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tabview_main, tl, false);
            tv.setText(tabs.get(i));
            tab.setCustomView(tv);
        }
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


}
