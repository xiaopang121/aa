package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.fui.widget.CustomViewPager;
import com.common.android.futils.ScreenInfo;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.GuidePagerAdapter;
import com.insthub.cat.android.ui.fragment.CommentFragment.CommentFragment;
import com.insthub.cat.android.ui.fragment.OrderFragment.OrderFragment;
import com.viewpagerindicator.UnderlinePageIndicator;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 评论列表
 * Created by linux on 2017/6/28.
 */

public class CommentListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


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

    @Bind(R.id.user_photo)
    RoundedImageView userPhoto;

    @Bind(R.id.tv_username)
    TextView tvUsername;


    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    private GuidePagerAdapter mGuidePagerAdapter;
    private String[] titles;


    private int type ;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_comment;
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

        type = getIntent().getIntExtra(ConstantsKeys.KEY_DATA,0);
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);


        switch (type)
        {
            case 0:
                commonTitleBar.setTitle("我的评论");
                menuGroup.check(R.id.menu_has_process);
                break;
            case 1:
                commonTitleBar.setTitle("我的订单");
                menuGroup.check(R.id.menu_no_process);
                break;
        }


        titles = new String[]{getString(R.string.title_menu_comment), getString(R.string.title_menu_order)};
        mGuidePagerAdapter = new GuidePagerAdapter(getSupportFragmentManager(), titles);
        fragmentArrayList.add(CommentFragment.newInstance());
        fragmentArrayList.add(OrderFragment.newInstance());
        mGuidePagerAdapter.addAllItems(fragmentArrayList);
        vitePager.setOffscreenPageLimit(3);
        vitePager.setAdapter(mGuidePagerAdapter);

        linepageIndicator.setViewPager(vitePager);
        linepageIndicator.setFades(false);

        vitePager.setCurrentItem(type);



        RequestOptions requestOptions2 = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                ;
        Glide.with(getContext()).asBitmap()
                .load(CacheManager.getInstance().getUserInfo().getData().getHead_image())
                .apply(requestOptions2)
                .into(userPhoto);

        tvUsername.setText(CacheManager.getInstance().getUserInfo().getData().getUser_name());

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
                        commonTitleBar.setTitle("我的订单");
                        vitePager.setCurrentItem(1);
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
                        commonTitleBar.setTitle("我的评论");
                        break;
                    case 1:
                        commonTitleBar.setTitle("我的订单");
                        break;
                }

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


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        setSwipeBackEnable(false);
    }
}
