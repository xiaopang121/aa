package com.insthub.cat.android.manager;

import android.content.Context;

import com.common.android.flog.KLog;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.insthub.cat.android.App;
import com.insthub.cat.android.module2.BindPushData;
import com.insthub.cat.android.respository.RepositoryImpl;

import cn.jpush.android.api.JPushInterface;
import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * User:macbook
 * DATE:2017/11/23 20:45
 * Desc:${DESC}
 */

public class JPushManager {


    public static JPushManager mJPushManager;


    public Context ctxt;


    public String registerId;


    public static JPushManager init(Context context)
    {
        if(mJPushManager==null)
        {
            mJPushManager = new JPushManager(context);
        }
        return mJPushManager;

    }



    public static JPushManager getInstance()
    {
        return mJPushManager;
    }



    public JPushManager(Context ctx)
    {
        ctxt = ctx;
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(ctx);     		// 初始化 JPush
        bindReighsterId();
    }



    public void setRegisterId(String token)
    {
          this.registerId = token;
    }


    public void bindReighsterId()
    {

        System.out.println("registerId:"+ JPushInterface.getRegistrationID(App.getAppContext()));

        if(CacheManager.getInstance().getToken()!=null)
        {

            RepositoryImpl.getInstance(App.getAppContext()).bindJpush(
                    CacheManager.getInstance().getToken().getData().getUser_id(),
                    CacheManager.getInstance().getToken().getData().getToken(), JPushInterface.getRegistrationID(App.getAppContext()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BindPushData>() {
                @Override
                public void onCompleted() {

                    KLog.i("极光推送绑定成功");
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onNext(BindPushData jpushTokenData) {

                }
            });
        }
    }


    public void setAlisa(Context ctx)
    {



    }



    public void removeAlisa()
    {
        JPushInterface.deleteAlias(ctxt,1);
    }



}
