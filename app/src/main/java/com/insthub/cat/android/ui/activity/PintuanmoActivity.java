package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.module2.PintuanDetialData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
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
 * 拼团更多
 * Created by linux on 2017/6/28.
 */

public class PintuanmoActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.center_view2)
    View centerView2;
    @Bind(R.id.iv_pinzhu_head)
    RoundedImageView ivPinzhuHead;
    @Bind(R.id.iv_pinother_head)
    RoundedImageView ivPinotherHead;
    @Bind(R.id.tv_invite_title)
    TextView tvInviteTitle;
    @Bind(R.id.bt_join)
    Button btJoin;
    @Bind(R.id.tv_product_name)
    TextView tvProductName;
    @Bind(R.id.tv_service)
    TextView tvService;
    UMShareListener mShareListener;
    private PintuanDetialData dataBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pintuan_more;
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
        commonTitleBar.setTitle("拼团未成功");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        dataBean = (PintuanDetialData) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
        Glide.with(getActivity())
                .load(dataBean.getData().getPintuan().getHead_image())
                .into(ivPinzhuHead);


        long time   =  com.common.android.futils.TimeUtils.parserTime(dataBean.getData().getPintuan().getStart_time(), com.common.android.futils.TimeUtils.FROAMTE_YMHMS);

        String counteTime = com.common.android.futils.TimeUtils.formateTime(time, com.common.android.futils.TimeUtils.FROMATE_HMS);

        String  [] timeSplite = counteTime.split(":");

        if(timeSplite.length==3)
        {
            timeSplite[0] = Integer.valueOf(timeSplite[0])+24+"";
            tvTime.setText(timeSplite[0]+":"+ timeSplite[1]+":"+ timeSplite[2]+"后结束");
        }


        String temp = "还差1人，赶快邀请好友来拼团吧";
        SpannableString spannableString = new SpannableString(temp);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.red));
        spannableString.setSpan(colorSpan, 2, temp.length()-12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvInviteTitle.setText(spannableString);




        tvProductName.setText(dataBean.getData().getStore_name()+"-"+dataBean.getData().getService_content());
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



        btJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mShareListener = new CustomShareListener(getActivity());
                ShareBoardConfig mShareBoardConfig = new ShareBoardConfig();
                mShareBoardConfig.setCancelButtonVisibility(false);
                mShareBoardConfig.setTitleText("邀请好友来拼团");
                mShareBoardConfig.setTitleVisibility(true);
                mShareBoardConfig.setIndicatorVisibility(false);
                ShareAction mShareAction = new ShareAction(getActivity()).setDisplayList(
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                                UMWeb web = new UMWeb(dataBean.getData().getShare().getShare_url());
                                web.setTitle(dataBean.getData().getShare().getShare_title());
                                web.setDescription(dataBean.getData().getShare().getShare_desc());
                                web.setThumb(new UMImage(getActivity(),dataBean.getData().getShare().getShare_image()));
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


    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {

    }

    @Override
    public void showError(String msg, int code) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
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
