package com.common.rxpay.sdk.wechatpay;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.common.android.flog.KLog;
import com.common.rxpay.sdk.PaymentStatus;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;


/**
 * wechat Method of payment
 */

public class WXPayWay {


    public static Observable<PaymentStatus> payMoney(final Activity context, final JSONObject json) {

        return Observable.create(new Observable.OnSubscribe<PaymentStatus>() {
            @Override
            public void call(Subscriber<? super PaymentStatus> subscriber) {

                try {


                    KLog.i("tttt:"+json.toString());
                    final String appId = getAppId(context);
                    final IWXAPI api = WXAPIFactory.createWXAPI(context, appId);
                    api.registerApp(appId);
                    PayReq req = new PayReq();
                    req.appId = appId;
                    req.partnerId		= json.getString("partnerid");
                    req.prepayId		= json.getString("prepayid");
                    req.nonceStr		= json.getString("noncestr");
                    req.timeStamp		= json.getString("timestamp");
                    req.packageValue	= json.getString("package");
                    req.sign			= json.getString("sign");
                    req.extData			= "app data";
                    boolean sendReq = api.sendReq(req);
                    subscriber.onNext(new PaymentStatus(sendReq));
                    subscriber.onCompleted();
                }catch (Exception e)
                {
                    e.printStackTrace();
                    subscriber.onNext(new PaymentStatus(false));
                    subscriber.onCompleted();
                }
            }
        });



    }

    public static String getAppId(Activity context) {
        ApplicationInfo info = null;
        try {
            info = context.getApplication().getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            String wx_appid = info.metaData.getString("WX_APPID");
            if (wx_appid == null) {
                throw new NullPointerException("appid not null");
            }
            return wx_appid;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
