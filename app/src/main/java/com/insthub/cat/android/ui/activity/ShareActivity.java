package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.insthub.cat.android.R;
import com.insthub.cat.android.manager.CacheManager;
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
 * 分享
 * Created by linux on 2017/6/28.
 */

public class ShareActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;


    @Bind(R.id.iv_share_image)
    ImageView ivShareImage;
    UMShareListener mShareListener;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_share;
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
        commonTitleBar.setTitle("邀请好友获取VIP资格");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        commonTitleBar.setRighImageMenu(R.drawable.ic_more_horiz_white_24dp);



        RequestOptions requestOptions2 = new RequestOptions()

                ;
        Glide.with(getContext()).asBitmap()
                .load(CacheManager.getInstance().getUserInfo().getData().getShare_image())
                .apply(requestOptions2)
                .into(ivShareImage);



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


        commonTitleBar.ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
                                UMImage web = new UMImage(getActivity(),CacheManager.getInstance().getUserInfo().getData().getShare_image());
                                web.setThumb(new UMImage(getActivity(),CacheManager.getInstance().getUserInfo().getData().getShare_image()));
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
    public void showError(String msg,int code) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
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
