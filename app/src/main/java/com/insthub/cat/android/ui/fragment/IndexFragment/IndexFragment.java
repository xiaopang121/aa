package com.insthub.cat.android.ui.fragment.IndexFragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.transformer.TransitionEffect;
import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.flog.KLog;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.event.ActiveUserEvent;
import com.insthub.cat.android.event.HongbaoEvent;
import com.insthub.cat.android.event.LocationUpdateEvent;
import com.insthub.cat.android.helper.FistBindPhoneCallback;
import com.insthub.cat.android.helper.HomeActivityCallback;
import com.insthub.cat.android.helper.LegalUserHelper;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.module2.BannerListData;
import com.insthub.cat.android.module.InfoListData;
import com.insthub.cat.android.module.InvoiceSchemeListData;
import com.insthub.cat.android.module.TodayThemeListData;
import com.insthub.cat.android.module2.BindPhoneData;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.module2.LoginTokenData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.activity.BidPriceListActivity;
import com.insthub.cat.android.ui.activity.HombaoActivity;
import com.insthub.cat.android.ui.activity.HomeActivityShareActivity;
import com.insthub.cat.android.ui.activity.HomeButtomActivity;
import com.insthub.cat.android.ui.activity.ITDataActivity;
import com.insthub.cat.android.ui.activity.IndexMenuMoreActivity;
import com.insthub.cat.android.ui.activity.IndexTuiguangActivity;
import com.insthub.cat.android.ui.activity.InfomationListActivity;
import com.insthub.cat.android.ui.activity.InnovateListActivity;
import com.insthub.cat.android.ui.activity.MyTicketActivity;
import com.insthub.cat.android.ui.activity.PullListActivity;
import com.insthub.cat.android.ui.activity.RenqiDetialActivity;
import com.insthub.cat.android.ui.activity.SelectCityActivity;
import com.insthub.cat.android.ui.activity.SelectTaitouActivity;
import com.insthub.cat.android.ui.activity.ShareActivity;
import com.insthub.cat.android.ui.activity.ShopDetialActivity;
import com.insthub.cat.android.ui.activity.ShopSearchActivity;
import com.insthub.cat.android.ui.activity.TaitouListActivity;
import com.insthub.cat.android.ui.activity.TenderListActivity;
import com.insthub.cat.android.ui.activity.ThemeActionActivity;
import com.insthub.cat.android.ui.activity.WxLoginActivity;
import com.insthub.cat.android.ui.activity.XiaojingpushouActivity;
import com.insthub.cat.android.ui.adatper.HomeJingxuanAdapter;
import com.insthub.cat.android.ui.adatper.HomeRenqiAdapter;
import com.insthub.cat.android.ui.adatper.IndexMenuAdapter;
import com.insthub.cat.android.ui.adatper.LocalImgAdapter;
import com.insthub.cat.android.ui.adatper.TodayThemeAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.ui.widget.MarqueTextView;
import com.insthub.cat.android.ui.widget.MarqueeView;
import com.insthub.cat.android.ui.widget.SpacesItemDecoration;
import com.insthub.cat.android.utils.Constant;
import com.insthub.cat.android.utils.DialogUtils;
import com.insthub.cat.android.utils.LocationUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.lang.ref.WeakReference;
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

public class IndexFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View, LocalImgAdapter.BannerCallback {

    @Bind(R.id.bt_left)
    TextView tvLocaiton;

    @Bind(R.id.tv_search)
    TextView tvSearchView;


    @Bind(R.id.bt_right)
    ImageView ivService;

    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;



    //首页菜单
    @Bind(R.id.rv_index_menu)
    RecyclerView rvIndexMenu;
    private IndexMenuAdapter mIndexMenuAdapter;
    private ArrayList<HomeData.DataBean.ServiceTypeListBean> mIndexMenuList = new ArrayList<>();



     //人气
    @Bind(R.id.rv_renqi)
    RecyclerView rvRenqiView;
    private HomeRenqiAdapter mHomeRenqiAdapter;
    private ArrayList<HomeData.DataBean.RenqiListBean> mIndexRenqiList = new ArrayList<>();



    @Bind(R.id.rv_jinxuan)
    RecyclerView rvJingxuanView;
    private HomeJingxuanAdapter mHomeJingxuanAdapter;
    private ArrayList<HomeData.DataBean.JingxuanListBean> mIndexJingxuanList = new ArrayList<>();





    @Bind(R.id.banners)
    LMBanners mLBanners;

    @Bind(R.id.root_view)
    LinearLayout rootView;

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


    private CustomShareListener mShareListener;


    private Dialog dialog;

    private BDLocation mBDLocation;

    public static IndexFragment newInstance() {
        IndexFragment mainFragment = new IndexFragment();
        return mainFragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_index;
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
        mLBanners.setScrollDurtion(3000);//两页切换时间
        mLBanners.setCanLoop(true);//循环播放
        mLBanners.setSelectIndicatorRes(R.drawable.guide_indicator_select);//选中的原点
        mLBanners.setUnSelectUnIndicatorRes(R.drawable.guide_indicator_unselect);//未选中的原点
        //若自定义原点到底部的距离,默认20,必须在setIndicatorWidth之前调用
        mLBanners.setIndicatorBottomPadding(10);
        mLBanners.setIndicatorWidth(5);//原点默认为5dp
        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Alpha);//选中喜欢的样式
//        mLBanners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));//自定义样式
        mLBanners.setDurtion(4000);//轮播切换时间
        mLBanners.showIndicatorLayout();//显示原点
        mLBanners.setIndicatorPosition(LMBanners.IndicaTorPosition.BOTTOM_MID);//设置原点显示位置
        mLBanners.setAdapter(new LocalImgAdapter(getActivity(), this), bannerList);


        //首页菜单
        GridLayoutManager partnetGridLayoutManager = new GridLayoutManager(getActivity(), 4);
        SpacesItemDecoration partnetDecoration = new SpacesItemDecoration(UIUtil.dpToPx(getResources(), 5));
        rvIndexMenu.setLayoutManager(partnetGridLayoutManager);
        rvIndexMenu.setHasFixedSize(true);
        mIndexMenuAdapter = new IndexMenuAdapter(getActivity(), mIndexMenuList);
        rvIndexMenu.setAdapter(mIndexMenuAdapter);
        rvIndexMenu.addItemDecoration(partnetDecoration);
        rvIndexMenu.scrollToPosition(0);


        //人气
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(UIUtil.dpToPx(getResources(), 5));
        rvRenqiView.setLayoutManager(layoutManager);
        rvRenqiView.setHasFixedSize(true);
        mHomeRenqiAdapter = new HomeRenqiAdapter(getActivity(), mIndexRenqiList);
        rvRenqiView.setAdapter(mHomeRenqiAdapter);
        rvRenqiView.addItemDecoration(itemDecoration);
        rvRenqiView.scrollToPosition(0);

        //精选
        GridLayoutManager jxlayoutManager = new GridLayoutManager(getActivity(), 2);
        SpacesItemDecoration jx2layoutManager = new SpacesItemDecoration(UIUtil.dpToPx(getResources(), 12));
        rvJingxuanView.setLayoutManager(jxlayoutManager);
        rvJingxuanView.setHasFixedSize(true);
        mHomeJingxuanAdapter = new HomeJingxuanAdapter(getActivity(), mIndexJingxuanList);
        rvJingxuanView.setAdapter(mHomeJingxuanAdapter);
        rvJingxuanView.addItemDecoration(jx2layoutManager);
        rvJingxuanView.scrollToPosition(0);


        BDLocationManager.getInstance().addCallback(mCityNameStatus);


    }



    @Override
    public void onResume() {
        super.onResume();
        refresh();
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



    private void refresh()
    {

        KLog.i("刷新首页数据");
        if(mHomeData!=null && mBDLocation!=null)
        {
            mPresenter.getHomeData(mBDLocation.getLatitude(),mBDLocation.getLongitude());
        }

    }



    /**
     * banner点击事件
     *
     * @param position
     */
    public void onClickItem(int position) {

        Bundle bundle  = new Bundle();
        bundle.putSerializable(ConstantsKeys.KEY_DATA,new DiscoverStoreData.DataBean.ListBean(bannerList.get(position).getService_id()));
        startActivity(ShopDetialActivity.class,bundle);
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


        mIndexMenuAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch(mIndexMenuList.get(position).getName())
                {
                    case "竞价":

                        startActivity(BidPriceListActivity.class);
                        break;

                    case "招标":
                        startActivity(TenderListActivity.class);
                        break;
                    case "创星集":

                        startActivity(InnovateListActivity.class);
                        break;

                    case "星客捕手":
                        if(LegalUserHelper.isLegalUserStatus(getActivity()))
                        {
                            if(CacheManager.getInstance().getToken().getData().getLoginType()==2) {
                                if (CacheManager.getInstance().getToken().getData().getBind_phone() == 0) {

                                    DialogUtils.showBindPhoneDialog(getActivity(), new FistBindPhoneCallback() {
                                        @Override
                                        public void onBind(String phone, String inviteCode) {

                                            showLoadDialog("正在绑定手机号");
                                            mPresenter.bindPhone(CacheManager.getInstance().getToken().getData().getUser_id(), CacheManager.getInstance().getToken().getData().getToken(), phone, inviteCode);
                                        }
                                    }).show();

                                } else {
                                    startActivity(XiaojingpushouActivity.class);
                                }
                            }else {
                                startActivity(XiaojingpushouActivity.class);
                            }

                        }

                        break;
                    case "工商财税":

                        Bundle bundle1 = new Bundle();
                        bundle1.putSerializable(ConstantsKeys.KEY_DATA,mIndexMenuList.get(position));
                        startActivity(IndexTuiguangActivity.class,bundle1);
                        break;
                    case "营销推广":

                        Bundle bundle2 = new Bundle();
                        bundle2.putSerializable(ConstantsKeys.KEY_DATA,mIndexMenuList.get(position));
                        startActivity(IndexTuiguangActivity.class,bundle2);
                        break;
                    case "数据IT" :

                        Bundle bundle3 = new Bundle();
                        bundle3.putSerializable(ConstantsKeys.KEY_DATA,mIndexMenuList.get(position));
                        startActivity(IndexTuiguangActivity.class,bundle3);
                        break;
                    case "更多服务":

                        startActivity(IndexMenuMoreActivity.class);
                        break;
                }
            }
        });





        mHomeRenqiAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DiscoverStoreData.DataBean.ListBean tem = new DiscoverStoreData.DataBean.ListBean();
                tem.setStore_id(mIndexRenqiList.get(position).getStore_id());
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,tem);
                startActivity(RenqiDetialActivity.class,bundle);
            }
        });


        mHomeJingxuanAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DiscoverStoreData.DataBean.ListBean tem = new DiscoverStoreData.DataBean.ListBean();
                tem.setStore_id(mIndexJingxuanList.get(position).getService_id());
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,tem);
                startActivity(ShopDetialActivity.class,bundle);
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



                mIndexMenuList.clear();
                mIndexMenuList.addAll(mHomeData.getData().getService_type_list());
                mIndexMenuAdapter.notifyDataSetChanged();



                mIndexRenqiList.clear();
                mIndexRenqiList.addAll(mHomeData.getData().getRenqi_list());
                mHomeRenqiAdapter.notifyDataSetChanged();



                mIndexJingxuanList.clear();
                mIndexJingxuanList.addAll(mHomeData.getData().getJingxuan_list());
                mHomeJingxuanAdapter.notifyDataSetChanged();


                RequestOptions requestOptions = new RequestOptions()
                        .placeholder(R.drawable.ic_index_plan)
                        .error(R.drawable.ic_index_plan)
                        ;
                Glide.with(getContext().getApplicationContext()).asBitmap()
                        .load(mHomeData.getData().getButtom().getImage())
                        .apply(requestOptions)
                        .into(ivInvoiceScheme);


                if(TextUtils.isEmpty(mHomeData.getData().getActy().getActy_name()))
                {
                    return;
                }





                if(dialog!=null && dialog.isShowing())
                {
                    return;
                }


                dialog =  DialogUtils.showHomeActivityDialog(getActivity(), mHomeData.getData().getActy(), new HomeActivityCallback() {

                    @Override
                    public void onJoin(Dialog dialog) {


                        if (LegalUserHelper.isLegalUserStatus(getActivity())) {




                            if(mHomeData.getData().getActy().getType()==1)
                            {
                                dialog.dismiss();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable(ConstantsKeys.KEY_DATA,mHomeData.getData().getActy());
                                startActivity(HomeActivityShareActivity.class,bundle);
                            }


                            if(mHomeData.getData().getActy().getType()==0)
                            {
                                dialog.dismiss();

                                mShareListener = new CustomShareListener(getActivity());
                                ShareBoardConfig mShareBoardConfig = new ShareBoardConfig();
                                mShareBoardConfig.setCancelButtonVisibility(false);
                                mShareBoardConfig.setTitleVisibility(false);
                                mShareBoardConfig.setIndicatorVisibility(false);
                                ShareAction mShareAction = new ShareAction(getActivity()).setDisplayList(
                                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                                        .setShareboardclickCallback(new ShareBoardlistener() {
                                            @Override
                                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                                UMImage web = new UMImage(getActivity(),mHomeData.getData().getActy().getShare_image());
                                                web.setThumb(new UMImage(getActivity(),mHomeData.getData().getActy().getShare_image()));
                                                new ShareAction(getActivity()).withMedia(web)
                                                        .setPlatform(share_media)
                                                        .setCallback(mShareListener)
                                                        .share();
                                            }
                                        });

                                mShareAction.open(mShareBoardConfig);
                            }



                            mPresenter.joinActivity(CacheManager.getInstance().getToken().getData().getUser_id(),
                                    CacheManager.getInstance().getToken().getData().getToken(),
                                    mHomeData.getData().getActy().getActy_id()
                                    );


                        }
                    }
                });

                dialog.show();
            }catch (RuntimeException e)
            {
                e.printStackTrace();
            }



        }


        if(object instanceof UserInfoData)
        {
            UserInfoData mUserInfoData = (UserInfoData) object;
            CacheManager.getInstance().setUserInfo(mUserInfoData);

        }


        if(object instanceof BindPhoneData)
        {
            BindPhoneData data = (BindPhoneData)object;
            LoginTokenData tokenData =  CacheManager.getInstance().getToken();
            if(tokenData!=null)
            {
                switch (tokenData.getData().getLoginType())
                {
                    case 1:
                        tokenData.getData().setFirst_login(0);
                        break;
                    case 2:
                        tokenData.getData().setBind_phone(1);
                        break;
                }
                CacheManager.getInstance().setToken(tokenData);
                ToastUtil.show(getContext(),data.getMessage());
                mPresenter.getUserInfo(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());
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
                mBDLocation = location;


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




    private static class CustomShareListener implements UMShareListener {

        private WeakReference<Activity> mActivity;

        private CustomShareListener(Activity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mActivity.get(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);



    }
}

