package com.common.android.fui.activity;

import android.content.Context;

import com.common.android.fui.rxanroid.RxManage;

/**
 * Created by baixiaokang on 16/4/22.
 */

/**
 * 处理操作
 * @param <E>
 * @param <T>
 */
public abstract class BasePresenter<E, T> {
    public Context context;
    public E mModel;
    public T mView;
    public RxManage mRxManage = new RxManage();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public abstract void onStart();

    public void onDestroy() {
        mRxManage.clear();
    }
}
