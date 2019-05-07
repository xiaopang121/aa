package com.insthub.cat.android.ui.widget;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/**
 * 倒计时Button帮助类
 */
public class CountTimeDownHelper {

    // 倒计时timer
    private CountDownTimer countDownTimer;
    // 计时结束的回调接口
    private OnFinishListener listener;

    /**
     * @param button        需要显示倒计时的Button
     * @param defaultString 默认显示的字符串
     * @param max           需要进行倒计时的最大值,单位是秒
     * @param interval      倒计时的间隔，单位是秒
     */
    public CountTimeDownHelper(CountDownTimer  param) {

        countDownTimer = param;
    }






    /**
     * 开始倒计时
     */
    public void start() {
        countDownTimer.start();
    }

    /**
     * 设置倒计时结束的监听器
     *
     * @param listener
     */
    public void setOnFinishListener(OnFinishListener listener) {
        this.listener = listener;
    }

    /**
     * 计时结束的回调接口
     *
     * @author zhaokaiqiang
     */
    public interface OnFinishListener {
        public void finish();
    }


    public void onDestroy() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

    }


}
