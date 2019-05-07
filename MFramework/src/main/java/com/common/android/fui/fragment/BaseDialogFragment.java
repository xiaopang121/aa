/*
 * Copyright (C) 2016 Bilibili
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.common.android.fui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.common.android.R;
import com.common.android.fui.activity.BaseModel;
import com.common.android.fui.activity.BasePresenter;
import com.common.android.futils.DialogUtils;
import com.common.android.futils.TUtil;
import butterknife.ButterKnife;


/**
 * 对话框基类
 CardPickerDialog dialog = new CardPickerDialog();
 dialog.setClickListener(SettingActivity.this);
 dialog.show(getSupportFragmentManager(), CardPickerDialog.TAG);
 */
public abstract  class BaseDialogFragment <T extends BasePresenter, E extends BaseModel> extends DialogFragment  {
    public static final String TAG = "CardPickerDialog";
    public T mPresenter;
    public E mModel;
    protected Dialog mLoadDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme_AppCompat_Dialog_Alert_Translate);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        mPresenter = TUtil.getT(this, 0);
        mModel= TUtil.getT(this,1);
        if(mPresenter!=null){
            mPresenter.context=this.getActivity();
        }
        bindPresenter();
    }

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void bindPresenter();

    /**
     * 设置布局
     * @return
     */
    protected abstract int  getLayoutResId();


    /**
     * 显示加载框
     *
     * @param msg
     */
    protected Dialog showLoadDialog(String msg) {
        try {
            mLoadDialog = DialogUtils.createDialog(getActivity(), "", msg);
            if (!mLoadDialog.isShowing()) {
                mLoadDialog.show();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return mLoadDialog;
    }


    /**
     * 显示加载框
     *
     * @param msg
     */
    protected Dialog showLoadDialog(String msg, DialogInterface.OnCancelListener listener) {
        try {
            mLoadDialog = DialogUtils.createDialog(getActivity(), "", msg);
            mLoadDialog.setOnCancelListener(listener);
            if (!mLoadDialog.isShowing()) {
                mLoadDialog.show();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return mLoadDialog;
    }


    protected void dismissLoadDialog() {
        if (mLoadDialog != null && mLoadDialog.isShowing()) {
            mLoadDialog.dismiss();
        }
    }


}
