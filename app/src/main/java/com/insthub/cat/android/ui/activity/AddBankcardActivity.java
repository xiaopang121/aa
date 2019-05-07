package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.AddBankcardData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import butterknife.Bind;


/**
 * 添加银行卡
 * Created by linux on 2017/6/28.
 */

public class AddBankcardActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.et_name)
    EditText etName;

    @Bind(R.id.rll_add_bankcard)
    LinearLayout rllAddBankcard;

    @Bind(R.id.et_bank)
    EditText etBank;

    @Bind(R.id.et_bankcard)
    EditText etBankcard;

    @Bind(R.id.iv_clear)
    ImageView ivClear;

    @Bind(R.id.login)
    Button login;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_bankcard;
    }

    @Override
    protected int bindColorPrimary() {
        return 0;
    }


    @Override
    protected void bindPresenter() {
        super.bindPresenter();
        mPresenter.setVM(this, mModel);
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
        commonTitleBar.setTitle(R.string.title_withdraw);
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


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(etName.getText().toString()))
                {
                    showLoadDialog("请输入持卡人姓名");
                    return;
               }


                if(TextUtils.isEmpty(etBank.getText().toString()))
                {
                    showLoadDialog("请输入开户行");
                    return;
                }


                if(TextUtils.isEmpty(etBankcard.getText().toString()))
                {
                    showLoadDialog("请输入卡号");
                    return;
                }


                showLoadDialog("正在提交数据");

                mPresenter.addBankCard(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),
                        etBank.getText().toString(),etBankcard.getText().toString(),etName.getText().toString());

            }
        });


        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etBankcard.setText("");
            }
        });


    }


    @Override
    protected void bindData() {
        super.bindData();


    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {

        dismissLoadDialog();
        if(object instanceof AddBankcardData)
        {
            AddBankcardData mbankCard = (AddBankcardData)object;
            startActivityForResult(AddBankResultActivity.class,100);
            finish();

        }

    }

    @Override
    public void showError(String msg, int code) {
        dismissLoadDialog();
        ToastUtil.show(getContext(),msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK)
        {

            setResult(Activity.RESULT_OK);
            finish();
        }
    }
}
