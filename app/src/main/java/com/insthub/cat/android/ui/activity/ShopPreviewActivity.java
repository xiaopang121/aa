package com.insthub.cat.android.ui.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.IntentUtils;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.extend.roundedimageview.RoundedImageView;
import com.example.xlhratingbar_lib.XLHRatingBar;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.CollectStoreData;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.module2.FriendInfoData;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.module2.ShopCommentList;
import com.insthub.cat.android.module2.StoreDetialData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.adatper.LocalImgAdapter;
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

import butterknife.Bind;


/**
 * 店铺预览
 * Created by linux on 2017/6/28.
 */

public class ShopPreviewActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View, LocalImgAdapter.BannerCallback {


    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;

    @Bind(R.id.banners)
    LMBanners mLBanners;

    @Bind(R.id.tv_coupony_name)
    TextView tvCouponyName;

    @Bind(R.id.tv_price)
    TextView tvPrice;


    @Bind(R.id.tv_couponay_address)
    TextView tvCommentAddress;


    @Bind(R.id.tv_store_des)
    TextView tvStoreDes;

    @Bind(R.id.tv_service)
    TextView tvService;

    @Bind(R.id.lly_viewgroup)
    LinearLayout llyViewGroup;

    @Bind(R.id.iv_back)
    ImageView  ivBack;

    private ArrayList<HomeData.DataBean.BannerListBean> bannerList = new ArrayList<>();

    private StoreDetialData mStoreDetialData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shop_preview;
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

        mStoreDetialData = (StoreDetialData)getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        bannerList.add(new HomeData.DataBean.BannerListBean(""));
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
        mLBanners.setAdapter(new LocalImgAdapter(this, this), bannerList);
        //本地用法
        updateView();
    }


    private void updateView() {


        if (mStoreDetialData != null) {

            bannerList.clear();
            if (mStoreDetialData.getData().getBanner_list().size() > 0) {
                for (String item : mStoreDetialData.getData().getBanner_list()) {
                    bannerList.add(new HomeData.DataBean.BannerListBean(item));
                }
            }

            if(bannerList.isEmpty())
            {
                bannerList.add(new HomeData.DataBean.BannerListBean(""));
            }

            mLBanners.setAdapter(new LocalImgAdapter(getActivity(), this), bannerList);


            tvCouponyName.setText(mStoreDetialData.getData().getStore_name());

            tvPrice.setText("¥" + mStoreDetialData.getData().getPrice() + "起");

            tvCommentAddress.setText(mStoreDetialData.getData().getAddress());


            tvStoreDes.setText(mStoreDetialData.getData().getService_advantage());

            tvService.setText("服务内容："+mStoreDetialData.getData().getService_name());



            if(mStoreDetialData.getData().getImage_list().size()>0)
            {

                for (int x=0;x<mStoreDetialData.getData().getImage_list().size();x++)
                {

                    ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  UIUtil.dpToPx(getResources(), 150));
                    lp.setMargins(UIUtil.dpToPx(getResources(), 10), UIUtil.dpToPx(getResources(), 10), UIUtil.dpToPx(getResources(), 10), 0);
                    ImageView iv = new ImageView(getActivity());
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);

                    Glide.with(getContext())
                            .load(mStoreDetialData.getData().getImage_list().get(x))
                            .into(iv);

                    llyViewGroup.addView(iv,lp);
                }
            }

        }
    }


    @Override
    protected void bindEvent() {
        super.bindEvent();

        ivBack.setOnClickListener(new View.OnClickListener() {
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

        dismissLoadDialog();
        if (object instanceof StoreDetialData) {
            mStoreDetialData = (StoreDetialData) object;
            updateView();

        }





    }

    @Override
    public void showError(String msg, int code) {
        dismissLoadDialog();
        ToastUtil.show(this, msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    public void onClickItem(int position) {

    }


}
