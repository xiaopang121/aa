package com.insthub.cat.android.ui.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.futils.IntentUtils;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.extend.roundedimageview.RoundedImageView;
import com.example.xlhratingbar_lib.XLHRatingBar;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.event.ActiveUserEvent;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.ActionListData;
import com.insthub.cat.android.module2.CollectStoreData;
import com.insthub.cat.android.module2.FriendInfoData;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.module2.MiaoshaDetialData;
import com.insthub.cat.android.module2.ShopCommentList;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.adatper.LocalImgAdapter;
import com.insthub.cat.android.ui.widget.MarqueTextView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.Bind;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * 秒杀详情
 * Created by linux on 2017/6/28.
 */

public class MiaoshaDetialActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View, LocalImgAdapter.BannerCallback {


    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;

    @Bind(R.id.banners)
    LMBanners mLBanners;

    @Bind(R.id.tv_coupony_name)
    TextView tvCouponyName;

    @Bind(R.id.tv_price)
    TextView tvPrice;

    @Bind(R.id.tv_comment_list)
    TextView tvCommentList;

    @Bind(R.id.rll_comment_bar)
    RelativeLayout tvCommentBar;



    @Bind(R.id.iv_comment_head)
    RoundedImageView ivCommentHead;

    @Bind(R.id.tv_comment_name)
    TextView tvCommentName;

    @Bind(R.id.ratingBar)
    XLHRatingBar ratingBar;

    @Bind(R.id.tv_comment_time)
    TextView tvCommentTime;

    @Bind(R.id.tv_comment_content)
    TextView tvCommentContent;


    @Bind(R.id.tv_couponay_address)
    TextView tvCommentAddress;

    @Bind(R.id.tv_collect)
    TextView tvCollect;

    @Bind(R.id.tv_chat)
    TextView tvChat;

    @Bind(R.id.tv_call)
    TextView tvCall;

    @Bind(R.id.tv_buy)
    TextView tvBuy;

    @Bind(R.id.tv_store_des)
    TextView tvStoreDes;

    @Bind(R.id.tv_service)
    TextView tvService;

    @Bind(R.id.lly_viewgroup)
    LinearLayout llyViewGroup;

    @Bind(R.id.iv_back)
    ImageView  ivBack;

    @Bind(R.id.tv_old_price)
    TextView tvOldPrice;

    @Bind(R.id.tv_last_size)
    TextView tvLastSize;



    @Bind(R.id.tv_share)
    ImageView  tvShare;


    @Bind(R.id.rll_action_data)
    RelativeLayout rllActionData;

    @Bind(R.id.action_head)
    RoundedImageView ivActionHead;

    @Bind(R.id.tv_action_content)
    MarqueTextView textView;


    private ArrayList<HomeData.DataBean.BannerListBean> bannerList = new ArrayList<>();

    private ActionListData.DataBean.ListBean data;


    private MiaoshaDetialData mStoreDetialData;

    private  CollectStoreData mCollectStoreData;

    UMShareListener mShareListener;


    private FriendInfoData mShareData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_miaosha_detial;
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
//        commonTitleBar.setTitle("店铺详情");
//        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);

        data = (ActionListData.DataBean.ListBean) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
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

            tvPrice.setText("¥" + mStoreDetialData.getData().getActivity().getDiscount_price());

            tvOldPrice.setText("¥" + mStoreDetialData.getData().getActivity().getOld_price());
            tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            tvLastSize.setText("仅剩"+mStoreDetialData.getData().getActivity().getDiscount_num()+"件");

            tvCommentAddress.setText(mStoreDetialData.getData().getAddress());


            tvStoreDes.setText(mStoreDetialData.getData().getService_advantage()+"");

            tvService.setText("服务内容："+mStoreDetialData.getData().getService_content());



            if(mStoreDetialData.getData().getImage_list().size()>0)
            {
                for (int x=0;x<mStoreDetialData.getData().getImage_list().size();x++)
                {

                    ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(UIUtil.dpToPx(getResources(), 2), UIUtil.dpToPx(getResources(), 5), UIUtil.dpToPx(getResources(), 2), 0);
                    ImageView iv = new ImageView(getActivity());
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(getContext())
                            .load(mStoreDetialData.getData().getImage_list().get(x))
                            .into(iv);
                    llyViewGroup.addView(iv,lp);


                    ViewGroup.LayoutParams lp2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,UIUtil.dpToPx(getResources(), 5));
                    TextView diviede = new TextView(getActivity());
                    llyViewGroup.addView(diviede,lp2);
                }
            }

        }
    }


    @Override
    protected void bindEvent() {
        super.bindEvent();
        subscribActionUserEvent();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        tvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(CacheManager.getInstance().getToken()==null)
                {

                    ToastUtil.show(getActivity(),"请先登录后再操作");
                    startActivity(WxLoginActivity.class);
                    return;
                }

                showLoadDialog("正在提交数据");
                if(CacheManager.getInstance().getToken()!=null)
                {
                    mPresenter.collecStore(CacheManager.getInstance().getToken().getData().getUser_id(),
                            CacheManager.getInstance().getToken().getData().getToken(),data.getStore_id());
                }

            }
        });

        tvChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CacheManager.getInstance().getToken()==null)
                {
                    ToastUtil.show(getActivity(),"请先登录后再操作");
                    startActivity(WxLoginActivity.class);
                    return;
                }

                SessionHelper.startP2PSession(getActivity(), mStoreDetialData.getData().getIm_code());

            }
        });

        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(mStoreDetialData.getData().getPhone()))
                {
                    ToastUtil.show(getActivity(),"该商户暂无联系方式");
                    return ;
                }
                IntentUtils.call(getActivity(),mStoreDetialData.getData().getPhone());


            }
        });

        tvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CacheManager.getInstance().getToken()==null)
                {
                    ToastUtil.show(getActivity(),"请先登录后再操作");
                    startActivity(WxLoginActivity.class);
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,mStoreDetialData);
                startActivity(MiaoshaMakeOrderActivity.class,bundle);





            }
        });


        //评论列表
        tvCommentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle  = new Bundle();
                bundle.putString(ConstantsKeys.KEY_DATA,data.getStore_id());
                startActivity(ShopCommentListActivity.class,bundle);
            }
        });


        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mStoreDetialData==null)
                {
                    ToastUtil.show(getContext(),"分享失败，暂无数据");
                    return;
                }

                mShareListener = new CustomShareListener(MiaoshaDetialActivity.this);

                ShareBoardConfig mShareBoardConfig = new ShareBoardConfig();
                mShareBoardConfig.setCancelButtonVisibility(false);
                mShareBoardConfig.setTitleVisibility(false);
                mShareBoardConfig.setIndicatorVisibility(false);
                mShareListener = new CustomShareListener(MiaoshaDetialActivity.this);
                ShareAction mShareAction = new ShareAction(getActivity()).setDisplayList(
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                                if (snsPlatform.mShowWord.equals("复制链接")) {
                                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    // 将文本内容放到系统剪贴板里。
                                    cm.setText(mStoreDetialData.getData().getShare().getShare_image());
                                    ToastUtil.show(getActivity(),"复制链接成功");

                                }else
                                {
                                    UMWeb web = new UMWeb(mStoreDetialData.getData().getShare().getShare_url());
                                    web.setTitle(mStoreDetialData.getData().getShare().getShare_title());
                                    web.setDescription(mStoreDetialData.getData().getShare().getShare_desc());
                                    web.setThumb(new UMImage(getActivity(),mStoreDetialData.getData().getShare().getShare_image()));
                                    new ShareAction(getActivity()).withMedia(web)
                                            .setPlatform(share_media)
                                            .setCallback(mShareListener)
                                            .share();
                                }


                            }
                        });
                mShareAction.open(mShareBoardConfig);
            }
        });

    }


    @Override
    protected void bindData() {
        super.bindData();


        showLoadDialog("正在加载数据");
        mPresenter.getMiaoshaDetail(String.valueOf(data.getActivity_id()));

        mPresenter.getStoreEvaluation(1,20,data.getStore_id());



        if(CacheManager.getInstance().getToken()!=null)
        {
            mPresenter.collecStore(CacheManager.getInstance().getToken().getData().getUser_id(),
                    CacheManager.getInstance().getToken().getData().getToken(),data.getStore_id());


        }


    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {

        dismissLoadDialog();
        if (object instanceof MiaoshaDetialData) {
            mStoreDetialData = (MiaoshaDetialData) object;
            updateView();

        }


        if(object instanceof ShopCommentList)
        {
            ShopCommentList mShopCommentList = (ShopCommentList)object;
             tvCommentList.setText("用户评论（"+mShopCommentList.getData().getCount()+")");

             if(mShopCommentList.getData().getCount()>0)
             {


                 ivCommentHead.setImageResource(R.drawable.logo);
                 if(!TextUtils.isEmpty(mShopCommentList.getData().getList().get(0).getHead_image()))
                 {

//                     Glide.with(getContext())
//                             .load(mShopCommentList.getData().getList().get(0).getHead_image())
//                             .transform(new GlideCircleTransform(getContext()))
//                             .error(R.drawable.logo)
//                             .placeholder(R.drawable.logo)
//                             .into(ivCommentHead);

                     RequestOptions requestOptions = new RequestOptions()
                             .circleCrop()
                             .placeholder(R.drawable.logo)
                             .error(R.drawable.logo)
                             ;
                     Glide.with(getContext().getApplicationContext()).asBitmap()
                             .load(mShopCommentList.getData().getList().get(0).getHead_image())
                             .apply(requestOptions)
                             .into(ivCommentHead);
                 }

                 tvCommentName.setText(mShopCommentList.getData().getList().get(0).getUser_name());

                 ratingBar.setCountSelected((int)mShopCommentList.getData().getList().get(0).getScore());

                 tvCommentTime.setText(mShopCommentList.getData().getList().get(0).getCreate_time());

                 tvCommentTime.setText(mShopCommentList.getData().getList().get(0).getEvaluate_content());


             }else
             {
                 tvCommentBar.setVisibility(View.GONE);
             }


        }


        if(object instanceof CollectStoreData)
        {
            mCollectStoreData = (CollectStoreData)object;
            switch (mCollectStoreData.getData().getIs_collect())
            {
                case 0:

                    tvCollect.setText("未收藏");
                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.ic_collect_normal),null,null);
                    break;
                case 1:

                    tvCollect.setText("已收藏");
                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.drawable.ic_collect_pressed),null,null);
                    break;
            }

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



    private static class CustomShareListener implements UMShareListener {

        private WeakReference<MiaoshaDetialActivity> mActivity;

        private CustomShareListener(MiaoshaDetialActivity activity) {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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
