package com.insthub.cat.android.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.fui.widget.CustomViewPager;
import com.common.android.futils.ScreenInfo;
import com.insthub.cat.android.R;
import com.insthub.cat.android.helper.LegalUserHelper;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.CommonFragmentAdatper;
import com.insthub.cat.android.ui.fragment.InnovateFragment.InnovateFragment;
import com.insthub.cat.android.ui.fragment.LabelFragment.LabelFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * User:macbook
 * DATE:2018/10/31 09:09
 * Desc:${DESC}
 */
public class InnovateListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {

    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.viewpager)
    CustomViewPager vitePager;

    @Bind(R.id.tl)
    TabLayout tl;

    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    private CommonFragmentAdatper mGuidePagerAdapter;
    private String[] titles;

    private List<String> tabs = new ArrayList<>();
    public static final int MOVABLE_COUNT = 5;

    private int tabCount;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_innovate;
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
        commonTitleBar.setTitle("创星集");

        //commonTitleBar.setRighButtonTitle(R.string.str_innovate_right_button);

        commonTitleBar.setLeftImageMenu(R.drawable.ic_back_white_24dp);

        initTabLayout();
    }


    private void initTabLayout() {

        titles =getResources().getStringArray(R.array.innovate_titles);
        mGuidePagerAdapter = new CommonFragmentAdatper(getSupportFragmentManager());


        for(int x=0;x<titles.length;x++)
        {

            tabs.add(titles[x]);
            mGuidePagerAdapter.addFragment(InnovateFragment.newInstance(x),titles[x]);
        }

        tabCount= tabs.size();
        vitePager.setOffscreenPageLimit(3);
        vitePager.setAdapter(mGuidePagerAdapter);
        vitePager.setVisibility(View.VISIBLE);
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

        commonTitleBar.btRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LegalUserHelper.isLegalUserStatus(getActivity()))
                {
                    startActivityForResult(InnovatePublishActivity.class,300);
                }
            }
        });





    }

    @Override
    public void showSuccess(Object object) {

    }

    @Override
    public void showError(String msg, int code) {

    }
}
