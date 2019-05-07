package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import butterknife.Bind;

import static android.webkit.WebSettings.LOAD_NO_CACHE;


/**
 * 分享
 * Created by linux on 2017/6/28.
 */

public class HomeActivityShareActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;


    @Bind(R.id.iv_share_webview)
    WebView webView;
    UMShareListener mShareListener;


    private boolean isInit;


    private HomeData.DataBean.ActyBean mActyBean;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_acitivyt_share;
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

        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        commonTitleBar.setRighImageMenu(R.drawable.ic_more_horiz_white_24dp);
        mActyBean = (HomeData.DataBean.ActyBean)getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
        commonTitleBar.setTitle(mActyBean.getActy_name());

        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(true);  //支持缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows();  //多窗口
        webSettings.setAllowFileAccess(true);  //设置可以访问文件
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setBuiltInZoomControls(false); //设置支持缩放
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(false); //关闭全局缓存
        webSettings.setCacheMode(LOAD_NO_CACHE); //指定缓存模式
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }




    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        commonTitleBar.ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(webView.canGoBack())
                {

                    webView.goBack();
                }else
                {
                    finish();
                }

            }
        });


        commonTitleBar.ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                share();
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {


        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

//                try {
//                    JSONObject json = new JSONObject();
//                    json.put("token", CacheManager.getInstance().getToken().getData().getToken());
//                    json.put("user_id",CacheManager.getInstance().getToken().getData().getUser_id());
//
//                    KLog.i("url:"+mActyBean.getUrl());
//                    KLog.i("javascript:getLoginContent("+CacheManager.getInstance().getToken().getData().getUser_id()+","+CacheManager.getInstance().getToken().getData().getToken()+")");
//                    webView.loadUrl("javascript:getLoginContent("+CacheManager.getInstance().getToken().getData().getUser_id()+","+CacheManager.getInstance().getToken().getData().getToken()+")");
//                }catch (JSONException e)
//                {
//                    e.printStackTrace();
//                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {



                if(url.equals("http://www.qitengteng.com/app/static/toShare"))
                {
                    share();
                     return true;
                }

                return false;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);


            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {

                handler.proceed();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.getSettings()
                            .setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
                }
            }


        });


    }


    @Override
    protected void bindData() {
        super.bindData();

        webView.loadUrl(mActyBean.getUrl()+"?user_id="+CacheManager.getInstance().getToken().getData().getUser_id()+"&token="+CacheManager.getInstance().getToken().getData().getToken());
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


    private void share()
    {

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
