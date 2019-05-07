package com.insthub.cat.android.receiver;

import android.content.Context;

import com.common.android.flog.KLog;

import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * 自定义JPush message 接收器,包括操作tag/alias的结果返回(仅仅包含tag/alias新接口部分)
 * */
public class MyJPushMessageReceiver extends JPushMessageReceiver {

    @Override
    public void onTagOperatorResult(Context context,JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
        KLog.i(jPushMessage.getCheckTag());
    }
    @Override
    public void onCheckTagOperatorResult(Context context,JPushMessage jPushMessage){
        super.onCheckTagOperatorResult(context, jPushMessage);
        KLog.i(jPushMessage.getAlias());
    }
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
        KLog.i(jPushMessage.getAlias());
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onMobileNumberOperatorResult(context, jPushMessage);
        KLog.i(jPushMessage.getAlias());
    }
}
