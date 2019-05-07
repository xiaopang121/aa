package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.StrUtil;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.ModifyPwdData;
import com.insthub.cat.android.module2.EmailCodeData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 找回密码 修改
 * Created by linux on 2017/6/26.
 */

public class FindModifyActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.et_mobile)
    EditText etMobile;
    @Bind(R.id.et_smscode)
    EditText etSmscode;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.bt_sumbit)
    Button btSumbit;
    @Bind(R.id.bt_getsms)
    Button btGetsms;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_find_modify;
    }


    @Override
    protected int bindColorPrimary() {
        return R.color.transparent;
    }

    @Override
    protected void bindViewById() {
        super.bindViewById();
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        commonTitleBar.setTitle(R.string.title_find);
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);


    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        commonTitleBar.ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(etMobile.getText().toString()) || etMobile.getText().toString().length() != 11) {

                    ToastUtil.show(getActivity(), "请输入正确的手机号码");
                    return;
                }


                if (TextUtils.isEmpty(etSmscode.getText().toString())) {
                    ToastUtil.show(getActivity(), "请输入验证码");
                    return;
                }

                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    ToastUtil.show(getActivity(), "请输入密码");
                    return;
                }


                showLoadDialog("正在提交数据");
                mPresenter.forgetModifyPassword(etMobile.getText().toString(), etPassword.getText().toString(), etSmscode.getText().toString());

            }
        });


        btGetsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etMobile.getText().toString()) ||  !StrUtil.isEmail(etMobile.getText().toString())) {

                    ToastUtil.show(getActivity(), "请输入正确邮箱");
                    return;
                }

                showLoadDialog("正在获取验证码");
                mPresenter.getEmailCode(etMobile.getText().toString());
            }
        });


    }


    @Override
    protected void bindPresenter() {
        super.bindPresenter();

        mPresenter.setVM(this, mModel);
    }


    @Override
    public void showSuccess(Object object) {
        dismissLoadDialog();

        if (object instanceof ModifyPwdData) {
            ModifyPwdData mForgetPasswordData = (ModifyPwdData) object;
            ToastUtil.show(getContext(), "修改成功");
            setResult(Activity.RESULT_OK);
            finish();
        }


        if (object instanceof EmailCodeData) {
            EmailCodeData mForgetPasswordData = (EmailCodeData) object;
            ToastUtil.show(getContext(),mForgetPasswordData.getMessage());

        }



    }

    @Override
    public void showError(String msg, int code) {
        dismissLoadDialog();
        ToastUtil.show(getContext(), "修改失败");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
