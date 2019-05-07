package com.insthub.cat.android.ui.fragment.DiscoverFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.fui.widget.CustomViewPager;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.event.LocationUpdateEvent;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.activity.SelectCityActivity;
import com.insthub.cat.android.ui.activity.ShopSearchActivity;
import com.insthub.cat.android.ui.adatper.CommonFragmentAdatper;
import com.insthub.cat.android.ui.adatper.GuidePagerAdapter;
import com.insthub.cat.android.ui.fragment.LabelFragment.LabelFragment;
import com.insthub.cat.android.ui.fragment.ShopFinishWithdrawFragment.ShopFinishWithdrawFragment;
import com.insthub.cat.android.ui.fragment.ShopReadyWithdrowFragment.ShopNoWithdrawFragment;
import com.insthub.cat.android.utils.LocationUtils;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.UnderlinePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by linux on 2017/6/28.
 */

public class DiscoverFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.bt_left)
    TextView tvLocaiton;


    @Bind(R.id.tv_search)
    TextView tvSearchView;


    @Bind(R.id.bt_right)
    ImageView ivService;

    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;

    @Bind(R.id.viewpager)
    CustomViewPager vitePager;

//    @Bind(R.id.linepageIndicator)
//    UnderlinePageIndicator linepageIndicator;
//
//
//    @Bind(R.id.titlepageindicator)
//    TabPageIndicator mTitlePageIndicator;
//
     @Bind(R.id.tl)
     TabLayout tl;


    @Bind(R.id.root_view)
    LinearLayout rootView;


    private String city;
    private DiscoverLabelData mDiscoverLabelData;


    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    private CommonFragmentAdatper mGuidePagerAdapter;
    private String[] titles;

    private List<String> tabs = new ArrayList<>();
    public static final int MOVABLE_COUNT = 5;

    private int tabCount;

    public static DiscoverFragment newInstance() {
        DiscoverFragment mainFragment = new DiscoverFragment();
        Bundle bundle = new Bundle();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected int bindColorPrimary() {
        return 0;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void bindViewById() {
        super.bindViewById();
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = rootView.getLayoutParams();
        lp.height = lp.height + statubar;
        rootView.setPadding(0, statubar, 0, 0);
        rootView.setLayoutParams(lp);
        rootView.invalidate();
        BDLocationManager.getInstance().addCallback(mCityNameStatus);
        mPresenter.getDiscoverLabel();

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
        subscribLocationEvent();

        tvLocaiton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SelectCityActivity.class);
            }
        });



        tvSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(ShopSearchActivity.class);
            }
        });
    }




    @Override
    protected void bindData() {
        super.bindData();

        if(!TextUtils.isEmpty(city))
        {
            tvLocaiton.setText(city);

        }else
        {
            tvLocaiton.setText("定位");
        }



    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void showSuccess(Object object) {


        if(object instanceof DiscoverLabelData)
        {
            mDiscoverLabelData = (DiscoverLabelData)object;
            updateFragment();
        }
    }



    private void updateFragment()
    {


        if(mDiscoverLabelData!=null)
        {
            titles = new String[]{getString(R.string.menu_shop_has_withdraw), getString(R.string.menu_shop_no_withdraw)};
            mGuidePagerAdapter = new CommonFragmentAdatper(getChildFragmentManager());
            for(int x=0;x<mDiscoverLabelData.getData().getLabel_lv1().size();x++)
            {
                if(mDiscoverLabelData.getData().getLabel_lv1().get(x).getLable_name().equals("为您推荐"))
                {
                    continue;
                }
                tabs.add(mDiscoverLabelData.getData().getLabel_lv1().get(x).getLable_name());
                mGuidePagerAdapter.addFragment(LabelFragment.newInstance(mDiscoverLabelData.getData().getLabel_lv1().get(x)),mDiscoverLabelData.getData().getLabel_lv1().get(x).getLable_name());
            }

            tabCount= tabs.size();
            vitePager.setOffscreenPageLimit(3);
            vitePager.setAdapter(mGuidePagerAdapter);
            vitePager.setVisibility(View.VISIBLE);
            initTabLayout();
        }




    }







    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(),msg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }






    /**
     * 定位回调
     */
    private LocationUtils.CityNameStatus mCityNameStatus = new LocationUtils.CityNameStatus() {

        @Override
        public void detecting() {

        }

        @Override
        public void update(String parmaCity, BDLocation aMapLocation,BDLocation location2) {

            if (!TextUtils.isEmpty(parmaCity)) {
                city = parmaCity;
                tvLocaiton.setText(parmaCity);

            } else {
                tvLocaiton.setText("未知");
            }
        }
    };



    /**
     * 定位更新
     */
    private void subscribLocationEvent() {
        Subscription subscription = RxBusManager.getInstance()
                .toObservable(LocationUpdateEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LocationUpdateEvent>() {
                    @Override
                    public void call(LocationUpdateEvent event) {

                        tvLocaiton.setText(BDLocationManager.getInstance().getCurLocation().getCountry());

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        throwable.printStackTrace();
                    }
                });
        RxBusManager.getInstance().addSubscription(this, subscription);
    }
}
