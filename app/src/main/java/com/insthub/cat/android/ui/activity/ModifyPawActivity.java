package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 找回密码
 * Created by linux on 2017/6/26.
 */

public class ModifyPawActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.et_oldpwd)
    EditText etOldpwd;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.et_password2)
    EditText etPassword2;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_modify_pwd;
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
        commonTitleBar.setTitle(R.string.title_modify_pwd);
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        commonTitleBar.setRighButtonTitle(R.string.title_finish);


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


        commonTitleBar.btRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (TextUtils.isEmpty(etOldpwd.getText().toString()) ) {

                    ToastUtil.show(getActivity(), "请输入旧密码");
                    return;
                }


                if(TextUtils.isEmpty(etPassword.getText().toString()))
                {
                    ToastUtil.show(getActivity(), "请输入验新密码");
                    return;
                }

                if(TextUtils.isEmpty(etPassword2.getText().toString()))
                {
                    ToastUtil.show(getActivity(), "请确认新密码");
                    return;
                }


                if(!etPassword.getText().toString().equals(etPassword2.getText().toString()))
                {
                    ToastUtil.show(getActivity(), "两次新密码不相同，请重新输入!");
                    return;
                }



                showLoadDialog("正在提交数据");
                mPresenter.modifyPassword(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),etPassword2.getText().toString(),etOldpwd.getText().toString());

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
        finish();


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
}
