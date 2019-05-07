package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.App;
import com.insthub.cat.android.R;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.manager.JPushManager;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.module2.LoginTokenData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.DemoCache;
import com.netease.nim.uikit.common.util.string.MD5;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 登录
 * Created by linux on 2017/6/26.
 */

public class LoginActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.lly_input_bar)
    LinearLayout llyInputBar;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @Bind(R.id.tv_goro_register)
    TextView tvGoroRegister;

    private   UserInfoData mUserInfoData;
    private   LoginTokenData mLoginTokenData;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
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
        commonTitleBar.setTitle(R.string.login);
//
//        etPhone.setText("13913040976");
//        etPassword.setText("123456");

//
//        etPhone.setText("13913040976");
//        etPassword.setText("123456");


     //   17361973617   123456


    }

    @Override
    protected void bindEvent() {
        super.bindEvent();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(etPhone.getText().toString()) || etPhone.getText().toString().length()<11)
                {

                    ToastUtil.show(getActivity(),"请输入正确的手机号码");
                    return;
                }

                if(TextUtils.isEmpty(etPassword.getText().toString()))
                {

                    ToastUtil.show(getActivity(),"请输入密码");
                    return;
                }

                showLoadDialog("正在登陆");
                mPresenter.login(etPhone.getText().toString(),etPassword.getText().toString(), BDLocationManager.getInstance().getCurLocation().getLatitude(),BDLocationManager.getInstance().getCurLocation().getLongitude());

            }
        });



        tvForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(FindModifyActivity.class);
            }
        });




        tvGoroRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),RegisterActivity.class);
                startActivityForResult(RegisterActivity.class,100);
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

        if(object instanceof LoginTokenData)
        {
            mLoginTokenData = (LoginTokenData)object;
            mLoginTokenData.getData().setLoginType(1);
            mPresenter.getUserInfo(mLoginTokenData.getData().getUser_id(),mLoginTokenData.getData().getToken());
        }


        if(object instanceof  UserInfoData )
        {

            mUserInfoData = (UserInfoData) object;
            LoginInfo info = new LoginInfo(mLoginTokenData.getData().getAccid(),mLoginTokenData.getData().getIm_token());
           // LoginInfo info = new LoginInfo("13815413228","123456");
            NIMClient.getService(AuthService.class).login(info)
                    .setCallback(new RequestCallback<LoginInfo>() {
                        @Override
                        public void onSuccess(LoginInfo object) {

                            KLog.i("登录成功----");
                            ToastUtil.show(getContext(),"登录成功");
                            dismissLoadDialog();
                            CacheManager.getInstance().setToken(mLoginTokenData);
                            CacheManager.getInstance().setUserInfo(mUserInfoData);
                            CacheManager.getInstance().setImLoginInfo(object);

                            JPushManager.getInstance().bindReighsterId();
                            DemoCache.setAccount(mLoginTokenData.getData().getAccid());
                            Intent intent = new Intent();
                            intent.setClass(getContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            finish();

                        }

                        @Override
                        public void onFailed(int i) {

                            CacheManager.getInstance().clear();
                            dismissLoadDialog();
                            ToastUtil.show(getContext(),"登录失败");
                            KLog.i("登录失败----"+i);

                        }

                        @Override
                        public void onException(Throwable throwable) {
                            throwable.printStackTrace();
                            dismissLoadDialog();
                            ToastUtil.show(getContext(),"登录失败");
                        }
                    });



        }


    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(),msg);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK)
        {
            finish();
        }
    }
}
