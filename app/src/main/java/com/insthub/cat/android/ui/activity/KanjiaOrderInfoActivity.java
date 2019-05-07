package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.CreateKanjiaData;
import com.insthub.cat.android.module2.CutdownDetialData;
import com.insthub.cat.android.module2.KanjiaInfoData;
import com.insthub.cat.android.module2.UseOrderListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.widget.CountTimeDownHelper;
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

import butterknife.Bind;


/**
 * 砍价结果页面
 * Created by linux on 2017/6/28.
 */

public class KanjiaOrderInfoActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.iv_head)
    RoundedImageView ivHead;

    @Bind(R.id.tv_name)
    TextView tvName;

    @Bind(R.id.shop_head)
    RoundedImageView shopHead;

    @Bind(R.id.tv_shop_name)
    TextView tvShopName;

    @Bind(R.id.tv_shop_service)
    TextView tvShopService;

    @Bind(R.id.tv_shop_money)
    TextView tvShopMoney;

    @Bind(R.id.tv_order_size)
    TextView tvOrderSize;
    @Bind(R.id.tv_shop_old_money)
    TextView tvShopOldMoney;

    @Bind(R.id.tv_kan_state)
    TextView tvState;

    @Bind(R.id.tv_count_down)
    TextView tvCountDown;

    @Bind(R.id.bt_cut)
    Button btShare;

    @Bind(R.id.tv_pay)
    TextView tvPay;

    @Bind(R.id.project_progress1)
    ProgressBar progressBar;

   private  UseOrderListData.DataBean.ListBean data;
    UMShareListener mShareListener;

    private CountTimeDownHelper mHelper;

    private KanjiaInfoData mKanjiaInfoData;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_kanjia;
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
        data = (UseOrderListData.DataBean.ListBean) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        commonTitleBar.mView.setBackgroundColor(Color.WHITE);
        commonTitleBar.setTitle("助力砍价");
        commonTitleBar.tvTitle.setTextColor(Color.BLACK);
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_grey_800_24dp);

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


        tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(CacheManager.getInstance().getToken()==null)
                {
                    ToastUtil.show(getActivity(),"请先登录后再操作");
                    startActivity(WxLoginActivity.class);
                    return;
                }

                mKanjiaInfoData.getData().setStore_id(data.getStore_id());
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,mKanjiaInfoData);
                startActivity(KanjiaMakeOrderActivity2.class,bundle);
            }
        });

        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mShareListener = new CustomShareListener(getActivity());
                ShareBoardConfig mShareBoardConfig = new ShareBoardConfig();
                mShareBoardConfig.setCancelButtonVisibility(false);
                mShareBoardConfig.setTitleText("已砍"+mKanjiaInfoData.getData().getCutdown_price()+"元,邀请好友帮忙砍吧！");
                mShareBoardConfig.setTitleVisibility(true);
                mShareBoardConfig.setIndicatorVisibility(false);
                mShareBoardConfig.setTitleTextColor(Color.WHITE);
                mShareBoardConfig.setShareboardBackgroundColor(Color.BLACK);
                ShareAction mShareAction = new ShareAction(getActivity()).setDisplayList(
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                                UMImage web = new UMImage(getActivity(),mKanjiaInfoData.getData().getShare_image());
//                                web.setTitle(mCutdownDetialData.getData().getShare().getShare_title());
//                                web.setDescription(mCutdownDetialData.getData().getShare().getShare_desc());
                                web.setThumb(new UMImage(getActivity(),mKanjiaInfoData.getData().getShare_image()));
                                new ShareAction(getActivity()).withMedia(web)
                                        .setPlatform(share_media)
                                        .setCallback(mShareListener)
                                        .share();
                            }
                        });


                mShareAction.open(mShareBoardConfig);
            }
        });



    }


    @Override
    protected void bindData() {
        super.bindData();


        tvShopName.setText(data.getStore_name());

        mPresenter.getPersionCutdownInfo(CacheManager.getInstance().getToken().getData().getUser_id(), CacheManager.getInstance().getToken().getData().getToken(),data.getKanjia_id());

    }




    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {


        if(object instanceof KanjiaInfoData)
        {

            mKanjiaInfoData =(KanjiaInfoData)object;
            updateInfo(mKanjiaInfoData);
        }
    }



    private void updateInfo(KanjiaInfoData mKanjiaInfoData)
    {



        String state = "已砍"+mKanjiaInfoData.getData().getCutdown_price()+"元，最多砍"+mKanjiaInfoData.getData().getMax_discount()+"元";

        SpannableString spannableString = new SpannableString(state);

        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.yellow));
        spannableString.setSpan(colorSpan, 2, String.valueOf(mKanjiaInfoData.getData().getCutdown_price()).length()+2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvState.setText(spannableString);
        progressBar.setProgress(mKanjiaInfoData.getData().getCutdown_percent());



        long endTime =  TimeUtils.parserTime(mKanjiaInfoData.getData().getCreate_time(),TimeUtils.FROMATE_YMD)+24*60*60*1000;

        long distancTime = (endTime-System.currentTimeMillis())/1000;

        if(distancTime<0)
        {
            distancTime=0;
        }


        float currentPrice = mKanjiaInfoData.getData().getOld_price()-mKanjiaInfoData.getData().getCutdown_price();
        tvPay.setText("需要付款"+currentPrice+"元");

        mHelper = new CountTimeDownHelper(new CountDownTimer(distancTime*1000,1 * 1000 - 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                String counteTime = TimeUtils.formateTime(millisUntilFinished,TimeUtils.FROMATE_HMS);
                tvCountDown.setText("已剩 "+counteTime+" 秒过期，快来砍价吧！");
            }

            @Override
            public void onFinish() {
                tvCountDown.setText("已剩 00:00:00 秒过期，快来砍价吧！");
            }
        });
        mHelper.start();






        if (!TextUtils.isEmpty(CacheManager.getInstance().getUserInfo().getData().getHead_image())) {

            RequestOptions requestOptions2 = new RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.ic_default_head)
                    .error(R.drawable.ic_default_head);
            Glide.with(getContext()).asBitmap()
                    .load(CacheManager.getInstance().getUserInfo().getData().getHead_image())
                    .apply(requestOptions2)
                    .into(ivHead);
        } else {
            ivHead.setImageResource(R.drawable.ic_default_head);
        }

        tvName.setText(CacheManager.getInstance().getUserInfo().getData().getUser_name());


        if (!TextUtils.isEmpty(mKanjiaInfoData.getData().getLogo())) {

            RequestOptions requestOptions2 = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_default_head)
                    .error(R.drawable.ic_default_head);
            Glide.with(getContext()).asBitmap()
                    .load(mKanjiaInfoData.getData().getLogo())
                    .apply(requestOptions2)
                    .into(shopHead);
        } else {
            shopHead.setImageResource(R.drawable.ic_default_head);
        }



        tvShopService.setText(mKanjiaInfoData.getData().getService_content());

        tvShopMoney.setText("￥" + mKanjiaInfoData.getData().getDiscount_price());


        tvShopOldMoney.setText("￥" + mKanjiaInfoData.getData().getOld_price());

        tvOrderSize.setText( mKanjiaInfoData.getData().getKanjia_count()+"人已成功砍价");


    }




    @Override
    public void showError(String msg, int code) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
        RxBusManager.getInstance().unSubscribe(this);

        if(mHelper!=null)
        {
            mHelper.onDestroy();
        }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}