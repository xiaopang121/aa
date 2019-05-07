package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.fui.widget.CustomViewPager;
import com.common.android.futils.ScreenInfo;
import com.insthub.cat.android.R;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.CommonFragmentAdatper;
import com.insthub.cat.android.ui.fragment.PublishFragment.MyBidFragment;
import com.insthub.cat.android.ui.fragment.PublishFragment.MyTenderFragment;
import com.insthub.cat.android.ui.fragment.PublishFragment.MyZhengjiFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:macbook
 * DATE:2018/10/31 09:09
 * Desc:${DESC}
 */
public class AllPublishListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {

    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.viewpager)
    CustomViewPager vitePager;
    @Bind(R.id.rb_jingjia)
    RadioButton rbJingjia;
    @Bind(R.id.rb_zhaobiao)
    RadioButton rbZhaobiao;
    @Bind(R.id.rb_zhengjie)
    RadioButton rbZhengjie;

    @Bind(R.id.radio_group)
    RadioGroup  radioGroup;
    private int page;


    private CommonFragmentAdatper mGuidePagerAdapter;

    String[] titles;


    @Override
    protected int getLayoutResId() {
        return R.layout.acitivity_all_publish_list;
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
        commonTitleBar.setTitle("我的发布");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        titles = new String[]{"竞价", "招标", "创新集"};
        mGuidePagerAdapter = new CommonFragmentAdatper(getSupportFragmentManager());
        mGuidePagerAdapter.addFragment(MyBidFragment.newInstance(), titles[0]);
        mGuidePagerAdapter.addFragment(MyTenderFragment.newInstance(), titles[1]);
        mGuidePagerAdapter.addFragment(MyZhengjiFragment.newInstance(), titles[2]);


        vitePager.setOffscreenPageLimit(3);
        vitePager.setAdapter(mGuidePagerAdapter);
        vitePager.setCurrentItem(0);
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


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.rb_jingjia:
                        vitePager.setCurrentItem(0);
                        break;

                    case R.id.rb_zhaobiao:
                        vitePager.setCurrentItem(1);
                        break;

                    case R.id.rb_zhengjie:
                        vitePager.setCurrentItem(2);
                        break;
                }
            }
        });



        vitePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        radioGroup.check(R.id.rb_jingjia);
                        break;

                    case 1:
                        radioGroup.check(R.id.rb_zhaobiao);
                        break;

                    case 2:
                        radioGroup.check(R.id.rb_zhengjie);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void showSuccess(Object object) {

    }

    @Override
    public void showError(String msg, int code) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
