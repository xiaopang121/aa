package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.Constants;
import com.insthub.cat.android.event.ThirdOauthEvent;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.manager.JPushManager;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.module2.LoginTokenData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.DemoCache;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 登录
 * Created by linux on 2017/6/26.
 */

public class WxLoginActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {



    @Bind(R.id.lly_change_login_type)
    RelativeLayout llyChangeLoginType;


    @Bind(R.id.tv_wx_login)
    RelativeLayout tvWxLogin;

    private IWXAPI api;

    private   UserInfoData mUserInfoData;
    private   LoginTokenData mLoginTokenData;

    private String tempcode="";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login_wx;
    }


    @Override
    protected int bindColorPrimary() {
        return R.color.transparent;
    }

    @Override
    protected void bindViewById() {
        super.bindViewById();

        api = WXAPIFactory.createWXAPI(this, Constants.WX_APPID);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        subscribeOauthEvent();

        llyChangeLoginType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(LoginActivity.class);
            }
        });


        tvWxLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 SendAuth.Req req = new SendAuth.Req();
                 req.scope = "snsapi_userinfo";
                 req.state = "wechat_sdk_demo_test";
                 api.sendReq(req);
            }
        });

    }


    @Override
    protected void bindPresenter() {
        super.bindPresenter();

        mPresenter.setVM(this, mModel);
    }


    @Override
    public void showSuccess(Object object) {


        if(object instanceof LoginTokenData)
        {
            mLoginTokenData = (LoginTokenData)object;
            mLoginTokenData.getData().setLoginType(2);
            mPresenter.getUserInfo(mLoginTokenData.getData().getUser_id(),mLoginTokenData.getData().getToken());
        }

        if (object instanceof UserInfoData) {
            mUserInfoData = (UserInfoData) object;
            LoginInfo info = new LoginInfo(mLoginTokenData.getData().getAccid(),mLoginTokenData.getData().getIm_token());
            NIMClient.getService(AuthService.class).login(info)
                    .setCallback(new RequestCallback<LoginInfo>() {
                        @Override
                        public void onSuccess(LoginInfo object) {

                            KLog.i("登录成功----");
                            ToastUtil.show(getContext(),"登录成功");
                            dismissLoadDialog();
                            CacheManager.getInstance().setToken(mLoginTokenData);
                            CacheManager.getInstance().setUserInfo(mUserInfoData);
                            CacheManager.getInstance().setImLoginInfo(object);
                            JPushManager.getInstance().bindReighsterId();
                            DemoCache.setAccount(mLoginTokenData.getData().getAccid());
                            Intent intent = new Intent();
                            intent.setClass(getContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            finish();

                        }

                        @Override
                        public void onFailed(int i) {

                            CacheManager.getInstance().clear();
                            dismissLoadDialog();
                            ToastUtil.show(getContext(),"登录失败");
                            KLog.i("登录失败----"+i);

                        }

                        @Override
                        public void onException(Throwable throwable) {
                            throwable.printStackTrace();
                            dismissLoadDialog();
                            ToastUtil.show(getContext(),"登录失败");
                        }
                    });

        }


    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(), msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            finish();
        }
    }



    /**
     * 第三方登录认证结果
     */
    private void subscribeOauthEvent() {
        Subscription subscription = RxBusManager.getInstance()
                .toObservable(ThirdOauthEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ThirdOauthEvent>() {
                    @Override
                    public void call(ThirdOauthEvent event) {

                        if(event.getType()==0)
                        {

                            showLoadDialog("正在登陆");

                            getWXOauthOpenId(event.getCode());

                        }else if(event.getType()==1)
                        {
                            ToastUtil.show(getContext(),event.getCode());

                        }else if(event.getType()==-1)
                        {
                            ToastUtil.show(getContext(),event.getCode());
                        }
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
     * 获取微信登录获取WXOPENID
     * @param code
     */
    private synchronized void getWXOauthOpenId(String code)
    {


        KLog.i("wx_code"+code+"---code---"+tempcode);

        if(!TextUtils.isEmpty(tempcode))
        {
            return ;
        }
        tempcode = code;


        String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+Constants.WX_APPID+"&secret="+Constants.WX_APPSECRET+
                "&code="+code+"&grant_type=authorization_code";
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(0);
        http.send(HttpRequest.HttpMethod.GET, urlStr, null, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String s = responseInfo.result;

                KLog.i("wx",s);
                try {
                    JSONObject object = new JSONObject(s);
                    String openId =   object.getString("openid");
                    String access_token = object.getString("access_token");
                    getWXOauthUserInfo(access_token,openId);

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

                dismissLoadDialog();
            }
        });

    }


    /**
     * 获取用户信息
     * @param openId
     */
    private void getWXOauthUserInfo(String accesstoken,String openId)
    {

        String urlStr = "https://api.weixin.qq.com/sns/userinfo?access_token="+accesstoken+"&openid="+openId;
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(0);
        http.send(HttpRequest.HttpMethod.GET, urlStr, null, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String s = responseInfo.result;

                KLog.i("wx",s);
                try {
                    JSONObject object = new JSONObject(s);
                    String unionid =   object.getString("unionid");
                    String openId =   object.getString("openid");
                    String nickname =   object.getString("nickname");
                    String headimgurl =   object.getString("headimgurl");

                    mPresenter.wxlogin(openId,unionid,headimgurl,nickname, BDLocationManager.getInstance().getCurLocation().getLatitude(),BDLocationManager.getInstance().getCurLocation().getLongitude());


                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

                dismissLoadDialog();
            }
        });
    }



}
