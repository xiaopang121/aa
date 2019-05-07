package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.RaiseItemData;
import com.insthub.cat.android.module.TaitouListData;
import com.insthub.cat.android.module2.AddInvoiceTitleData;
import com.insthub.cat.android.module2.InvoiceTitleListData;
import com.insthub.cat.android.module2.UpdateInvoiceTitleData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import butterknife.Bind;


/**
 * 编辑抬头
 * Created by linux on 2017/6/28.
 */

public class EditTaitouActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.tv_company_taitou)
    EditText tvCompanyTaitou;

    @Bind(R.id.tv_company_code)
    EditText tvCompanyCode;

    @Bind(R.id.tv_company_phone)
    EditText tvCompanyPhone;

    @Bind(R.id.tv_company_address)
    EditText tvCompanyAddress;
    @Bind(R.id.tv_company_bank)
    EditText tvCompanyBank;
    @Bind(R.id.tv_company_bank_code)
    EditText tvCompanyBankCode;


    @Bind(R.id.lly_company)
    LinearLayout llyCompany;



    @Bind(R.id.tv_person_name)
    EditText tvPersonName;
    @Bind(R.id.tv_person_phone)
    EditText tvPersonPhone;
    @Bind(R.id.lly_persion)
    LinearLayout llyPersion;
    @Bind(R.id.save)
    Button save;



    @Bind(R.id.rb_company)
    RadioButton rbCompany;
    @Bind(R.id.rb_person)
    RadioButton rbPerson;
    @Bind(R.id.rb_taitou_group)
    RadioGroup rbTaitouGroup;

    int  type = 1;


    private InvoiceTitleListData.DataBean.ListBean mRaiseItemData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        if(getIntent()!=null && getIntent().getExtras()!=null)
        {
            mRaiseItemData = (InvoiceTitleListData.DataBean.ListBean)getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
        }
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_taitou;
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
        commonTitleBar.setTitle(R.string.title_add_taitou);
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


        rbTaitouGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i)
                {
                    case R.id.rb_company:
                        type=0;
                        showCompanyView();
                        break;
                    case R.id.rb_person:
                        type=1;
                        showPersonView();
                        break;
                }
            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(mRaiseItemData==null)
                {

                    if(type==0)
                    {


                        if(TextUtils.isEmpty(tvCompanyTaitou.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入准确的抬头名称");
                            return;
                        }


                        if(TextUtils.isEmpty(tvCompanyCode.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入税号或者社会信用代码");
                            return;
                        }


                        if(TextUtils.isEmpty(tvCompanyPhone.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入单位电话号码");
                            return;
                        }



                        if(TextUtils.isEmpty(tvCompanyAddress.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入单位注册地址信息");
                            return;
                        }

                        if(TextUtils.isEmpty(tvCompanyBank.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入单位开户行名称");
                            return;
                        }


                        if(TextUtils.isEmpty(tvCompanyBankCode.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入单位银行帐号");
                            return;
                        }



                        showLoadDialog("正在添加发票抬头");

                        mPresenter.addInvoiceTitle(CacheManager.getInstance().getToken().getData().getUser_id(),
                                CacheManager.getInstance().getToken().getData().getToken(),
                                String.valueOf(type),
                                tvCompanyTaitou.getText().toString(),
                                tvCompanyCode.getText().toString(),
                                tvCompanyPhone.getText().toString(),
                                tvCompanyAddress.getText().toString(),
                                tvCompanyBank.getText().toString(),
                                tvCompanyBankCode.getText().toString(),
                                "");


                    }


                    if(type==1)
                    {
                        if(TextUtils.isEmpty(tvPersonName.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入准确的抬头名称");
                            return;
                        }


                        showLoadDialog("正在添加发票抬头");
                        mPresenter.addInvoiceTitle(CacheManager.getInstance().getToken().getData().getUser_id(),
                                CacheManager.getInstance().getToken().getData().getToken(),
                                String.valueOf(type),
                                "",
                                "",
                                tvPersonPhone.getText().toString(),
                               "",
                                "",
                               "",
                                tvPersonName.getText().toString());


                    }
                }else
                {


                    if(mRaiseItemData.getType()==0)
                    {

                        if(TextUtils.isEmpty(tvCompanyTaitou.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入准确的抬头名称");
                            return;
                        }


                        if(TextUtils.isEmpty(tvCompanyCode.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入税号或者社会信用代码");
                            return;
                        }


                        if(TextUtils.isEmpty(tvCompanyPhone.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入单位电话号码");
                            return;
                        }



                        if(TextUtils.isEmpty(tvCompanyAddress.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入单位注册地址信息");
                            return;
                        }

                        if(TextUtils.isEmpty(tvCompanyBank.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入单位开户行名称");
                            return;
                        }


                        if(TextUtils.isEmpty(tvCompanyBankCode.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入单位银行帐号");
                            return;
                        }
                        showLoadDialog("正在更新发票抬头");
                        mPresenter.updateInvoiceTitle(CacheManager.getInstance().getToken().getData().getUser_id(),
                                CacheManager.getInstance().getToken().getData().getToken(),
                                String.valueOf(0),
                                mRaiseItemData.getTitle_id(),
                                tvCompanyTaitou.getText().toString(),
                                tvCompanyCode.getText().toString(),
                                tvCompanyPhone.getText().toString(),
                                tvCompanyAddress.getText().toString(),
                                tvCompanyBank.getText().toString(),
                                tvCompanyBankCode.getText().toString(),
                                ""
                                );


                    }else
                    {

                        if(TextUtils.isEmpty(tvPersonName.getText().toString()))
                        {
                            ToastUtil.show(getContext(),"请输入准确的抬头名称");
                            return;
                        }


                        showLoadDialog("正在添加发票抬头");
                        mPresenter.updateInvoiceTitle(CacheManager.getInstance().getToken().getData().getUser_id(),
                                CacheManager.getInstance().getToken().getData().getToken(),
                                String.valueOf(1),
                                mRaiseItemData.getTitle_id(),
                               "",
                                "",
                                tvPersonPhone.getText().toString(),
                                "",
                               "",
                               "",
                                tvPersonName.getText().toString());




                    }





                }


            }
        });


    }



    public void showCompanyView()
    {
        llyCompany.setVisibility(View.VISIBLE);
        llyPersion.setVisibility(View.GONE);
         tvCompanyTaitou.setText("");
         tvCompanyCode.setText("");
         tvCompanyPhone.setText("");
         tvCompanyAddress.setText("");
         tvCompanyBank.setText("");
         tvCompanyBankCode.setText("");

        if(mRaiseItemData!=null) {
            tvCompanyTaitou.setText(mRaiseItemData.getTitle_name());
            tvCompanyCode.setText(mRaiseItemData.getTax_no());
            tvCompanyPhone.setText(mRaiseItemData.getPhone());
            tvCompanyAddress.setText(mRaiseItemData.getAddress());
            tvCompanyBank.setText(mRaiseItemData.getBank());
            tvCompanyBankCode.setText(mRaiseItemData.getBank_account());
            rbTaitouGroup.setEnabled(false);
            rbCompany.setEnabled(false);
        }
    }



    public void showPersonView()
    {
        llyCompany.setVisibility(View.GONE);
        llyPersion.setVisibility(View.VISIBLE);
        tvPersonName.setText("");
        tvPersonPhone.setText("");


        if(mRaiseItemData !=null)
        {
            tvPersonName.setText(mRaiseItemData.getUser_name());
            tvPersonPhone.setText(mRaiseItemData.getPhone());
            rbTaitouGroup.setEnabled(false);
            rbPerson.setEnabled(false);
        }


    }




    @Override
    protected void bindData() {
        super.bindData();


        if(mRaiseItemData!=null)
        {
            switch (mRaiseItemData.getType())
            {
                case 0:
                    rbTaitouGroup.check(R.id.rb_company);
                    break;
                case 1:
                    rbTaitouGroup.check(R.id.rb_person);
                    break;
            }
        }else
        {
            rbTaitouGroup.check(R.id.rb_company);
        }



    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {

        dismissLoadDialog();
        if(object instanceof AddInvoiceTitleData)
        {
            ToastUtil.show(this,"数据添加成功");
            setResult(RESULT_OK);
            finish();
        }


        if(object instanceof  UpdateInvoiceTitleData)
        {
            ToastUtil.show(this,"数据更新成功");
            setResult(RESULT_OK);
            finish();
        }

    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(),msg);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


}
