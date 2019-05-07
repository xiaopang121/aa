package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.rxpay.annotation.WX;
import com.common.rxpay.sdk.RxPay;
import com.common.rxpay.sdk.alipay.OrderInfoUtil2_0;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.Constants;
import com.insthub.cat.android.module.OrderInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import org.json.JSONObject;

import java.util.Map;

import butterknife.Bind;
import rx.Observer;
import rx.schedulers.Schedulers;

import static com.insthub.cat.android.constant.Constants.RSA2_PRIVATE;


/**
 * 账户信息
 * Created by linux on 2017/6/28.
 */

@WX(packageName = "com.insthub.ship.android")
public class RechargeActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;


    @Bind(R.id.tv_account_amount)
    TextView tvAccountAmount;



    @Bind(R.id.tv_input_money)
    EditText tvInputMoney;


    @Bind(R.id.tv_pay_wx)
    RadioButton tvPayWx;

    @Bind(R.id.tv_pay_alipay)
    RadioButton tvPayAlipay;

    @Bind(R.id.radio_group)
    RadioGroup radioGroup;

    @Bind(R.id.tv_refund_des)
    TextView tvRefundDes;

    @Bind(R.id.bt_sumbit)
    Button btSumbit;

    @Bind(R.id.tv_recharge_prompt)
    TextView tvRechargePrompt;

    @Bind(R.id.tv_recharge_protocol)
    TextView tvRechargeProtocol;



    private int payType=0;




    private RxPay rxPay;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxPay = new RxPay(this);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_rrecharge;
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
        commonTitleBar.setTitle(R.string.title_recharge_2);
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



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                switch (i)
                {
                    case R.id.tv_pay_wx:
                        payType=1;
                        break;

                    case R.id.tv_pay_alipay:
                        payType=2;
                        break;
                }
            }
        });

        tvInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        btSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(tvInputMoney.getText().toString()))
                {
                    ToastUtil.show(getContext(),R.string.state_input_money);

                    return;
                }

                if(tvInputMoney.getText().toString().equals("."))
                {
                    ToastUtil.show(getContext(),R.string.state_input_money);
                    return;
                }

                if(payType==0)
                {
                    ToastUtil.show(getContext(),R.string.state_select_paytype);

                    return;
                }


                float money = Float.valueOf(tvInputMoney.getText().toString());
                showLoadDialog(getResources().getString(R.string.state_creatorder_ing));
               // mPresenter.buildOrder(String.valueOf(payType),money);
            }
        });


        tvRechargeProtocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        tvRefundDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        bindData();

    }

    @Override
    public void showSuccess(Object object) {

        dismissLoadDialog();
        if(object instanceof  OrderInfoData)
        {
            OrderInfoData data = (OrderInfoData)object;

            if(payType==1)
            {
                requestWechatpay(data.getData().getOrderInfoId());
            }


            if(payType==2)
            {
                requestAlipay(data.getData().getOrderInfoId());
            }

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
    private void  requestAlipay(String orderId){
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        String subject="充值";
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(Constants.APPID,rsa2,Float.valueOf(tvInputMoney.getText().toString()),"订单支付",orderId,Constants.NOTIFY_URL,subject);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : Constants.RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;
        rxPay.requestAlipay(orderInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        ToastUtil.show(getContext(),"支付异常："+throwable.getMessage());
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if(aBoolean)
                        {
                            ToastUtil.show(getContext(),"支付成功");
                        }else
                        {
                            ToastUtil.show(getContext(),"支付失败");
                        }
                    }
                });
    }

    private void requestWechatpay(String orderId){


        JSONObject object = new JSONObject();
//        object.put("appid","");
//        object.put("noncestr","");
//        object.put("package","");
//        object.put("partnetid","");
//        object.put("prepayid","");
//        object.put("sign","");
//        object.put("timestammp",System.currentTimeMillis()/1000);


        rxPay.requestWXpay(object)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
// payState.setText("微信支付状态："+throwable.getMessage());
                        ToastUtil.show(getContext(),"微信支付状态："+throwable.getMessage());
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        ToastUtil.show(getContext(),"微信支付状态："+aBoolean);
                        //payState.setText("微信支付状态："+aBoolean);
                    }
                });
    }

}
