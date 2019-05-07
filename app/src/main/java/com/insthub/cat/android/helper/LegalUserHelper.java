package com.insthub.cat.android.helper;

import android.app.Activity;
import android.content.Intent;

import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.ui.activity.LoginActivity;
import com.insthub.cat.android.ui.activity.WxLoginActivity;

/**
 * 对用户信息进行合法性判断
 * Created by linux on 2017/9/18.
 */

public class LegalUserHelper {



    //用户信息未登录
    public static final int  STATE_NO_USER=1000;

    //用户没有资金
    public static final int  STATE_NO_MONEY=3000;


    //未开通存管
    public static final int  STATE_NO_DEPOSITORY=2000;

    //未绑卡
    public static final int  STATE_NO_BANKCARD=4000;

    //状态正常
    public static final int  STATE_OK=1000000;

    /**
     * 判断是否是合法用户
     * @return
     */
    public static int  isLegalUserStatus()
    {


        //用户未登录
        if(null == CacheManager.getInstance().getUserInfo() || CacheManager.getInstance().getToken()==null) return STATE_NO_USER;

        return STATE_OK;


    }


    /**
     * 判断用户是否合法
     * @param ctx
     * @return
     */
    public static boolean isLegalUserStatus(Activity ctx)
    {

        //用户信息基本是否合法
        switch (LegalUserHelper.isLegalUserStatus())
        {
            case LegalUserHelper.STATE_NO_USER:
                ToastUtil.show(ctx,"请先登录后再操作！");
                startActivity(ctx,WxLoginActivity.class,LegalUserHelper.STATE_NO_USER);
                return false;
            case LegalUserHelper.STATE_NO_DEPOSITORY:
                return false ;
            case LegalUserHelper.STATE_NO_MONEY:
                ToastUtil.show(ctx,"请先登录后再操作！");
                startActivity(ctx,WxLoginActivity.class,STATE_NO_MONEY);
                return false;
            case LegalUserHelper.STATE_NO_BANKCARD:


                return false;
            case LegalUserHelper.STATE_OK:
                 return true;
                default:
                    return false;
        }

    }




    public static  void startActivity(Activity activity ,Class<?> cls,int requestCode)
    {
       // activity.startActivity(new Intent(activity,cls));
        activity.startActivityForResult(new Intent(activity,cls),requestCode);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }



}
