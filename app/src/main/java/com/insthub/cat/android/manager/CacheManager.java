package com.insthub.cat.android.manager;

import android.content.Context;
import android.text.TextUtils;

import com.common.android.fcache.ACache;
import com.common.android.fsp.SharedPreferencesUtil;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.module2.LoginTokenData;
import com.insthub.cat.android.nim.DemoCache;
import com.insthub.cat.android.utils.Constant;
import com.netease.nimlib.sdk.auth.LoginInfo;

/**
 * 缓存管理
 * Created by linux on 2017/7/17.
 */

public class CacheManager {


    public static CacheManager _manager;
    public Context context;
    private UserInfoData mUserInfoData;

    private LoginTokenData token;

    private LoginInfo  mImLoginInfo;

    public LoginTokenData getToken() {

        if(token==null)
        {
            token = (LoginTokenData)ACache.get(context).getAsObject(ConstantsKeys.KEY_TOKEN);

        }
        return token;
    }

    public void setToken(LoginTokenData token) {
        this.token = token;
        ACache.get(context).put(ConstantsKeys.KEY_TOKEN,token);
    }


    public void setImLoginInfo(LoginInfo loginInfo)
    {
        mImLoginInfo = loginInfo;
        ACache.get(context).put(ConstantsKeys.KEY_IM_LOGININF,loginInfo);
    }


    public LoginInfo getImLoginInf()
    {
        if(mImLoginInfo==null)
        {
            mImLoginInfo = (LoginInfo)ACache.get(context).getAsObject(ConstantsKeys.KEY_IM_LOGININF);

        }


        return mImLoginInfo;
    }



    //缓存通知时间
    private static  final int  NOTICE_SAVE_TIME=10*60*1000;


    public static void init(Context context)
    {
        if(_manager==null)
        {
            _manager = new CacheManager(context);
        }
    }


    public CacheManager(Context context)
    {
        this.context = context;
    }


    public static CacheManager getInstance()
    {
        return _manager;
    }


    /**
     * 设置用户信息
     * @param param
     */
    public void setUserInfo(UserInfoData param)
    {
        ACache.get(context).put(ConstantsKeys.KEY_USER_INFO,param);
        mUserInfoData = param;
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public UserInfoData getUserInfo()
    {


        if(mUserInfoData==null)
        {
            mUserInfoData = (UserInfoData) ACache.get(context).getAsObject(ConstantsKeys.KEY_USER_INFO);
        }

        return mUserInfoData;

    }


    /**
     * 获取版本信息
     */
    public void getVersionData()
    {

    }



    /**
     * 清理缓存
     */
    public void clear(){

        mUserInfoData = null;
        token = null;
        mImLoginInfo = null;
        ACache.get(context).clear();
    }

}


