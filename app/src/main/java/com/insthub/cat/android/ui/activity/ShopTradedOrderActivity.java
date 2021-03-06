package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.fui.widget.CustomViewPager;
import com.common.android.futils.ScreenInfo;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.GuidePagerAdapter;
import com.insthub.cat.android.ui.fragment.CommentFragment.CommentFragment;
import com.insthub.cat.android.ui.fragment.OrderFragment.OrderFragment;
import com.insthub.cat.android.ui.fragment.ShopFinishWithdrawFragment.ShopFinishWithdrawFragment;
import com.insthub.cat.android.ui.fragment.ShopReadyWithdrowFragment.ShopNoWithdrawFragment;
import com.viewpagerindicator.UnderlinePageIndicator;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 交易完成的
 * Created by linux on 2017/6/28.
 */

public class ShopTradedOrderActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.viewpager)
    CustomViewPager vitePager;

    @Bind(R.id.linepageIndicator)
    UnderlinePageIndicator linepageIndicator;


    @Bind(R.id.menu_has_process)
    RadioButton menuComment;

    @Bind(R.id.menu_no_process)
    RadioButton menuPrder;

    @Bind(R.id.menu_categrary)
    RadioGroup menuGroup;


    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    private GuidePagerAdapter mGuidePagerAdapter;
    private String[] titles;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shop_traded;
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
        commonTitleBar.setTitle(R.string.title_withdraw_record);
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);

        titles = new String[]{getString(R.string.menu_shop_has_withdraw), getString(R.string.menu_shop_no_withdraw)};
        mGuidePagerAdapter = new GuidePagerAdapter(getSupportFragmentManager(), titles);
        fragmentArrayList.add(ShopFinishWithdrawFragment.newInstance(0));
        fragmentArrayList.add(ShopFinishWithdrawFragment.newInstance(1));
        mGuidePagerAdapter.addAllItems(fragmentArrayList);
        vitePager.setOffscreenPageLimit(3);
        vitePager.setAdapter(mGuidePagerAdapter);

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
                switch (i) {
                    case R.id.menu_has_process:
                        vitePager.setCurrentItem(0);
                        break;
                    case R.id.menu_no_process:
                        vitePager.setCurrentItem(1);
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

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
