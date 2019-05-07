package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.BankListData;
import com.insthub.cat.android.module2.WithdrawApplyData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import butterknife.Bind;


/**
 * 提现
 * Created by linux on 2017/6/28.
 */

public class WithdrawActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.rll_add_bankcard)
    RelativeLayout rllAddBankcard;

    @Bind(R.id.tv_money_title)
    TextView tvMoneyTitle;

    @Bind(R.id.tv_unit)
    TextView tvUnit;

    @Bind(R.id.tv_last_money)
    TextView tvLastMoney;

    @Bind(R.id.tv_withdraw_all)
    TextView tvWithdrawAll;

    @Bind(R.id.login)
    Button btWithdraw;


    @Bind(R.id.tv_select_bank)
    TextView tvSelectBank;

    @Bind(R.id.et_input_money)
    EditText etInput;


    BankListData.DataBean.ListBean  bankData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_withdraw;
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
        etInput.setText("0.0");
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


        rllAddBankcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  startActivity(AddBankcardActivity.class);

                startActivityForResult(BankListActivity.class,200);

            }
        });


        tvWithdrawAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                etInput.setText(CacheManager.getInstance().getUserInfo().getData().getBalance()+"");
            }
        });


        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!TextUtils.isEmpty(etInput.getText().toString()) )
                {

                    float value = Float.valueOf(etInput.getText().toString());

                    if(value >0 && value<=CacheManager.getInstance().getUserInfo().getData().getBalance())
                    {
                        btWithdraw.setEnabled(true);
                    }else
                    {
                        btWithdraw.setEnabled(false);
                    }
                }
            }
        });



        btWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(bankData==null)
                {
                    ToastUtil.show(getActivity(),"请选择银行卡");
                    return ;
                }



                if(TextUtils.isEmpty(etInput.getText().toString()) ||etInput.getText().toString().equals("0.0"))
                {
                    ToastUtil.show(getActivity(),"请输入提现金额");
                    return ;
                }



                float  money = Float.valueOf(etInput.getText().toString()) ;

                if(money==0 || money >CacheManager.getInstance().getUserInfo().getData().getBalance())
                {
                    ToastUtil.show(getActivity(),"账户余额不足,无法提现");
                    return ;
                }


                showLoadDialog("正在提交数据");
                mPresenter.withdrawApply(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),
                        bankData.getCard_id(),money);

            }
        });

    }


    @Override
    protected void bindData() {
        super.bindData();


        tvLastMoney.setText("￥"+CacheManager.getInstance().getUserInfo().getData().getBalance());

    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {


        dismissLoadDialog();
        if(object instanceof WithdrawApplyData)
        {
            WithdrawApplyData mWithdrawApplyData = (WithdrawApplyData) object;
            ToastUtil.show(this, mWithdrawApplyData.getMessage());
        }

    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(this,msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode== Activity.RESULT_OK && requestCode==200)
        {
            bankData=( BankListData.DataBean.ListBean) data.getExtras().getSerializable(ConstantsKeys.KEY_DATA);



            if(bankData.getBank().startsWith("农业"))
            {
                tvSelectBank.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_nong),null,null,null);

            }else if(bankData.getBank().startsWith("中国"))
            {
                tvSelectBank.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_zhong),null,null,null);
            }else if(bankData.getBank().startsWith("工商"))
            {
                tvSelectBank.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_gong),null,null,null);
            }else if(bankData.getBank().startsWith("建设"))
            {
                tvSelectBank.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_jianshe),null,null,null);
            }else if(bankData.getBank().startsWith("交通"))
            {
                tvSelectBank.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_jiaotong),null,null,null);
            }


            tvSelectBank.setText(bankData.getBank_account());

        }
    }
}
