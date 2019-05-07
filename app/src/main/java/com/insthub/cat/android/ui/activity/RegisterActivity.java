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
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 注册解码
 * Created by linux on 2017/6/26.
 */

public class RegisterActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.et_password_confign)
    EditText etPasswordConfign;
    @Bind(R.id.et_email)
    EditText etEmail;
    @Bind(R.id.bt_register)
    Button btRegister;

    @Bind(R.id.et_invite_code)
    EditText etInviteCode;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
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
        commonTitleBar.setTitle(R.string.title_register);
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


        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(etPhone.getText().toString())) {
                    ToastUtil.show(getActivity(), R.string.state_input_mobile);
                    return;
                }

                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    ToastUtil.show(getActivity(), "请输入密码");
                    return;
                }


                if (TextUtils.isEmpty(etPasswordConfign.getText().toString())) {
                    ToastUtil.show(getActivity(),"请输入确认密码");
                    return;
                }

                if (TextUtils.isEmpty(etEmail.getText().toString())) {
                    ToastUtil.show(getActivity(),"请输入邮箱");
                    return;
                }

                showLoadDialog(getString(R.string.state_register_ing));
                mPresenter.register(etPhone.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(),etInviteCode.getText().toString());

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

        if (object instanceof UserInfoData) {
            UserInfoData data = (UserInfoData) object;
            CacheManager.getInstance().setUserInfo(data);
            ToastUtil.show(getActivity(), "注册成功");
            setResult(Activity.RESULT_OK);
            finish();
        }


    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(),msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
