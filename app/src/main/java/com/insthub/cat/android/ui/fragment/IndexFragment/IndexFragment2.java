package com.insthub.cat.android.ui.fragment.IndexFragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.transformer.TransitionEffect;
import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.event.ActiveUserEvent;
import com.insthub.cat.android.event.LocationUpdateEvent;
import com.insthub.cat.android.helper.LegalUserHelper;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.module.InvoiceSchemeListData;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.activity.HomeButtomActivity;
import com.insthub.cat.android.ui.activity.InfomationListActivity;
import com.insthub.cat.android.ui.activity.MyTicketActivity;
import com.insthub.cat.android.ui.activity.SelectCityActivity;
import com.insthub.cat.android.ui.activity.ShopDetialActivity;
import com.insthub.cat.android.ui.activity.ShopSearchActivity;
import com.insthub.cat.android.ui.activity.TaitouListActivity;
import com.insthub.cat.android.ui.activity.ThemeActionActivity;
import com.insthub.cat.android.ui.activity.XiaojingpushouActivity;
import com.insthub.cat.android.ui.adatper.LocalImgAdapter;
import com.insthub.cat.android.ui.adatper.TodayThemeAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.ui.widget.MarqueTextView;
import com.insthub.cat.android.ui.widget.MarqueeView;
import com.insthub.cat.android.utils.LocationUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import karics.library.zxing.android.CaptureActivity;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
//import karics.library.zxing.android.CaptureActivity;

/**
 * 首页
 * Created by linux on 2017/6/28.
 */

public class IndexFragment2 extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View, LocalImgAdapter.BannerCallback {

    @Bind(R.id.bt_left)
    TextView tvLocaiton;


    @Bind(R.id.tv_search)
    TextView tvSearchView;


    @Bind(R.id.bt_right)
    ImageView ivService;

    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;

    @Bind(R.id.tv_menu_taitou)
    TextView tvMenuTaitou;

    @Bind(R.id.tv_menu_qrc)
    TextView tvMenuQrc;

    @Bind(R.id.tv_menu_piao)
    TextView tvMenuPiao;

    @Bind(R.id.tv_menu_bushou)
    TextView tvBuShou;

    @Bind(R.id.banners)
    LMBanners mLBanners;


    @Bind(R.id.rll_theme)
    RecyclerView mRecyclerView;


    @Bind(R.id.marqueeView)
    MarqueeView mMarqueeview;

    @Bind(R.id.root_view)
    LinearLayout rootView;



    private List<CharSequence> marqueeStringlist = new ArrayList<>();

    private ArrayList<HomeData.DataBean.ThemeListBean> todayThemeList = new ArrayList<>();


    private TodayThemeAdapter mTodayThemeAdapter;

    private ArrayList<HomeData.DataBean.BannerListBean> bannerList = new ArrayList<>();


    private String city;

    @Bind(R.id.iv_quan)
    ImageView ivInvoiceScheme;


    private HomeData mHomeData;




    @Bind(R.id.rll_action_data)
    RelativeLayout rllActionData;

    @Bind(R.id.action_head)
    RoundedImageView ivActionHead;

    @Bind(R.id.tv_action_content)
    MarqueTextView textView;

    //卷皮
    private  InvoiceSchemeListData mInvoiceSchemeListData;

    public static IndexFragment2 newInstance() {
        IndexFragment2 mainFragment = new IndexFragment2();
        return mainFragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_index2;
    }


    @Override
    public void initPresenter() {
        super.initPresenter();
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



        bannerList.add(new HomeData.DataBean.BannerListBean());
        mLBanners.isGuide(false);//是否为引导页
        mLBanners.setAutoPlay(true);//自动播放
        mLBanners.setVertical(false);//是否垂直播放
        mLBanners.setScrollDurtion(2000);//两页切换时间
        mLBanners.setCanLoop(true);//循环播放
        mLBanners.setSelectIndicatorRes(R.drawable.guide_indicator_select);//选中的原点
        mLBanners.setUnSelectUnIndicatorRes(R.drawable.guide_indicator_unselect);//未选中的原点
        //若自定义原点到底部的距离,默认20,必须在setIndicatorWidth之前调用
        mLBanners.setIndicatorBottomPadding(10);
        mLBanners.setIndicatorWidth(5);//原点默认为5dp
        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Alpha);//选中喜欢的样式
//        mLBanners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));//自定义样式
        mLBanners.setDurtion(3000);//轮播切换时间
        mLBanners.showIndicatorLayout();//显示原点
        mLBanners.setIndicatorPosition(LMBanners.IndicaTorPosition.BOTTOM_MID);//设置原点显示位置
        //本地用法


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, false, R.drawable.item_divider_black));
        mRecyclerView.scrollToPosition(0);
        mTodayThemeAdapter = new TodayThemeAdapter(getActivity(), todayThemeList);
        mRecyclerView.setAdapter(mTodayThemeAdapter);
        mLBanners.setAdapter(new LocalImgAdapter(getActivity(), this), bannerList);
        BDLocationManager.getInstance().addCallback(mCityNameStatus);


    }



    @Override
    public void onResume() {
        super.onResume();
          mLBanners.startImageTimerTask();
    }

    @Override
    public void onPause() {
        super.onPause();
         mLBanners.stopImageTimerTask();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLBanners != null) {
            mLBanners.clearImageTimerTask();
        }

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


    /**
     * banner点击事件
     *
     * @param position
     */
    public void onClickItem(int position) {

//        Bundle bundle  = new Bundle();
//        bundle.putSerializable(ConstantsKeys.KEY_DATA,new DiscoverStoreData.DataBean.ListBean(bannerList.get(position).getStore_id()));
//        startActivity(ShopDetialActivity.class,bundle);
    }


    @Override
    protected void bindEvent() {
        super.bindEvent();

        subscribActionUserEvent();

        subscribLocationEvent();

        tvLocaiton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(SelectCityActivity.class);
            }
        });


        tvSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(ShopSearchActivity.class);
            }
        });
//

        mLBanners.setOnStartListener(new LMBanners.onStartListener() {
            @Override
            public void startOpen() {
                //回调跳转的逻辑


            }
        });
//


        tvMenuTaitou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(LegalUserHelper.isLegalUserStatus(getActivity()))
                {
                    startActivity(TaitouListActivity.class);
                }

            }
        });

        tvMenuQrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(LegalUserHelper.isLegalUserStatus(getActivity()))
                {
                    startActivity(CaptureActivity.class);

                   // startActivity(PullListActivity.class);
                }

            }
        });


        tvMenuPiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(LegalUserHelper.isLegalUserStatus(getActivity()))
                {
                    startActivity(MyTicketActivity.class);
                }

            }
        });


        tvBuShou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LegalUserHelper.isLegalUserStatus(getActivity()))
                {
                    startActivity(XiaojingpushouActivity.class);
                }

            }
        });



        //点击卷皮
        ivInvoiceScheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(LegalUserHelper.isLegalUserStatus(getActivity()))
                {
                    if(mHomeData!=null && mHomeData.getData()!=null && mHomeData.getData().getButtom()!=null)
                    {
                        Bundle bundle  = new Bundle();
                        bundle.putSerializable(ConstantsKeys.KEY_DATA, mHomeData.getData().getButtom().getUrl());
                        startActivity(HomeButtomActivity.class,bundle);

                    }

                }


            }
        });



        mMarqueeview.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {


                startActivity(InfomationListActivity.class);
            }

        });



        mTodayThemeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if(position>=todayThemeList.size())
                {
                   return;
                }
//                Bundle bundle  = new Bundle();
//                bundle.putSerializable(ConstantsKeys.KEY_DATA,new DiscoverStoreData.DataBean.ListBean( todayThemeList.get(position).getStore_id()));
//                startActivity(ShopDetialActivity.class,bundle);


                Bundle bundle  = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,todayThemeList.get(position));
                startActivity(ThemeActionActivity.class,bundle);
            }
        });

    }




    @Override
    protected int bindColorPrimary() {
        return 0;
    }


    @Override
    public void showSuccess(Object object) {


        if(object instanceof HomeData)
        {


            try
            {
                mHomeData = (HomeData)object;
                bannerList.clear();
                bannerList.addAll(mHomeData.getData().getBanner_list());
                if(bannerList.size()==0)
                {
                    bannerList.add(new HomeData.DataBean.BannerListBean());
                }
                mLBanners.setAdapter(new LocalImgAdapter(getActivity(), this), bannerList);



                marqueeStringlist.clear();
                mMarqueeview.removeAllViews();
                for(int x=0;x<mHomeData.getData().getNews_list().size();x++)
                {
                    marqueeStringlist.add(mHomeData.getData().getNews_list().get(x).getTitle());
                }
                mMarqueeview.startWithList(marqueeStringlist);

                todayThemeList.clear();
                todayThemeList.addAll(mHomeData.getData().getTheme_list());
                mTodayThemeAdapter.notifyDataSetChanged();

//
//
//
//            Glide.with(getActivity())
//                    .load(mHomeData.getData().getButtom().getImage())
//                    .error(R.drawable.ic_index_plan)
//                    .placeholder(R.drawable.ic_index_plan)
//                    .into(ivInvoiceScheme);
//


                RequestOptions requestOptions = new RequestOptions()
                        .placeholder(R.drawable.ic_index_plan)
                        .error(R.drawable.ic_index_plan)
                        ;
                Glide.with(getContext().getApplicationContext()).asBitmap()
                        .load(mHomeData.getData().getButtom().getImage())
                        .apply(requestOptions)
                        .into(ivInvoiceScheme);
            }catch (RuntimeException e)
            {
                e.printStackTrace();
            }



        }


    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(), msg);
    }






    /**
     * 定位回调
     */
    private LocationUtils.CityNameStatus mCityNameStatus = new LocationUtils.CityNameStatus() {

        @Override
        public void detecting() {

        }

        @Override
        public void update(String parmaCity, BDLocation aMapLocation,BDLocation location) {

            if (!TextUtils.isEmpty(parmaCity)) {

                city = parmaCity;
                tvLocaiton.setText(parmaCity);



                if(mHomeData==null)
                {

                    mPresenter.getHomeData(location.getLatitude(),location.getLongitude());
                }





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



    /**
     * 用户活动信息
     */
    private void subscribActionUserEvent() {
        Subscription subscription = RxBusManager.getInstance()
                .toObservable(ActiveUserEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ActiveUserEvent>() {
                    @Override
                    public void call(ActiveUserEvent event) {


                        rllActionData.setVisibility(View.VISIBLE);



                        RequestOptions requestOptions = new RequestOptions()
                                .placeholder(R.drawable.ic_default_head)
                                .error(R.drawable.ic_default_head)
                                .circleCrop()
                                ;
                        Glide.with(getContext().getApplicationContext()).asBitmap()
                                .load(event.getHead_img())
                                .apply(requestOptions)
                                .into(ivActionHead);


                        textView.setText(event.getContent());
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

