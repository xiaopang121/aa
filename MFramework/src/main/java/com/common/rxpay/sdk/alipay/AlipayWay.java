package com.common.rxpay.sdk.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.common.rxpay.sdk.PaymentStatus;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by cuieney on 18/08/2017.
 */

public class AlipayWay {

    public static Observable<PaymentStatus> payMoney(final Activity activity, final String orderInfo) {

      return  Observable.create(new Observable.OnSubscribe<PayTask>(){

            @Override
            public void call(Subscriber<? super PayTask> subscriber) {
                PayTask alipay = new PayTask(activity);
                subscriber.onNext(alipay);
            }

        }).map(new Func1<PayTask, PaymentStatus>() {

            @Override
            public PaymentStatus call(PayTask payTask) {
                return createPaymentStatus(payTask,orderInfo);
            }
        });

    }


    private static PaymentStatus createPaymentStatus(PayTask payTask,String orderInfo){
        Map<String, String> result = payTask.payV2(orderInfo, true);
        PayResult payResult = new PayResult(result);
        String resultStatus = payResult.getResultStatus();
        if (TextUtils.equals(resultStatus, "9000")) {
            return new PaymentStatus(true);
        } else {
            return new PaymentStatus(false);
        }
    }

}
