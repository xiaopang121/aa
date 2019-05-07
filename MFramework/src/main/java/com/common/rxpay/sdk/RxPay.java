package com.common.rxpay.sdk;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.common.rxpay.sdk.alipay.AlipayWay;
import com.common.rxpay.sdk.wechatpay.WXPayWay;

import org.json.JSONObject;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by cuieney on 18/08/2017.
 */

public class RxPay {
    static final String TAG = "RxPay";
    private Activity activity;

    public RxPay(@NonNull Activity activity) {
        this.activity = activity;
    }

    @SuppressWarnings({"WeakerAccess", "unused"})
    public Observable<Boolean> requestAlipay(@NonNull final String orderInfo){
        return aliPayment(orderInfo);
    }


    @SuppressWarnings({"WeakerAccess", "unused"})
    public Observable<Boolean> requestWXpay(@NonNull final JSONObject json){
        return wxPayment(json);
    }


    @SuppressWarnings("WeakerAccess")
    private Observable.Transformer<Object,Boolean> ensure(final PayWay payWay, final String orderInfo, final JSONObject json){
        return new Observable.Transformer<Object, Boolean>() {

            @Override
            public Observable<Boolean> call(Observable<Object> objectObservable) {
                if (payWay == PayWay.WECHATPAY) {
                    return requestImplementation(json).map(new Func1<PaymentStatus, Boolean>() {

                        @Override
                        public Boolean call(PaymentStatus paymentStatus) {
                            return paymentStatus.isStatus();
                        }
                    });
                }

                return requestImplementation(orderInfo).map(new Func1<PaymentStatus, Boolean>() {

                    @Override
                    public Boolean call(PaymentStatus paymentStatus) {
                        return paymentStatus.isStatus();
                    }
                });

            }
        };
    }

    private Observable<PaymentStatus> requestImplementation(final JSONObject json){
        return WXPayWay.payMoney(activity,json);
    }

    private Observable<PaymentStatus> requestImplementation(final String orderInfo){
        return AlipayWay.payMoney(activity, orderInfo);
    }

    @SuppressWarnings({"WeakerAccess", "unused"})
    private Observable<Boolean> aliPayment(final String orderInfo){
        return Observable.just(orderInfo).compose(ensure(PayWay.ALIPAY,orderInfo,null));
    }

    @SuppressWarnings({"WeakerAccess", "unused"})
    private Observable<Boolean> wxPayment(final JSONObject json){
        return Observable.just(json).compose(ensure(PayWay.WECHATPAY,null,json));
    }

}
