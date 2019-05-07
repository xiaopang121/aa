package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.insthub.cat.android.ui.fragment.ActionFragment.ActionFragment1;
import com.insthub.cat.android.ui.fragment.ActionFragment.ActionFragment2;
import com.insthub.cat.android.ui.fragment.ActionFragment.ActionFragment3;
import com.insthub.cat.android.ui.fragment.ActionFragment.ActionFragment4;
import com.insthub.cat.android.ui.fragment.LabelFragment.LabelFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 关于软件
 * Created by linux on 2017/6/28.
 */

public class SetActionActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.tl)
    TabLayout tl;

    @Bind(R.id.viewpager)
    CustomViewPager vitePager;

    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    private CommonFragmentAdatper mGuidePagerAdapter;
    private String[]  titles = new String[]{"砍价","秒杀","团购","幸运抽奖"}; ;

    private List<String> tabs = new ArrayList<>();
    public static final int MOVABLE_COUNT = 5;

    private int tabCount=4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_action_manager;
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
        commonTitleBar.setTitle("店铺活动设置");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        updateFragment();
    }

    private void updateFragment()
    {

        mGuidePagerAdapter = new CommonFragmentAdatper(getSupportFragmentManager());
        for(int x=0;x<titles.length;x++)
        {

            tabs.add(titles[x]);

        }

        mGuidePagerAdapter.addFragment(ActionFragment1.newInstance(),titles[0]);
        mGuidePagerAdapter.addFragment(ActionFragment2.newInstance(),titles[1]);
        mGuidePagerAdapter.addFragment(ActionFragment3.newInstance(),titles[2]);
        mGuidePagerAdapter.addFragment(ActionFragment4.newInstance(),titles[3]);
        tabCount= tabs.size();
        vitePager.setOffscreenPageLimit(4);
        vitePager.setAdapter(mGuidePagerAdapter);
        vitePager.setVisibility(View.VISIBLE);

        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        tl.setTabMode( TabLayout.MODE_FIXED);
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


        LinearLayout linearLayout = (LinearLayout) tl.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.item_divider_v_black));
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


}
