package com.insthub.cat.android.ui.activity;

import android.app.Activity;
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

import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.DeviceUtils;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.rxpay.annotation.WX;
import com.common.rxpay.sdk.PaymentStatus;
import com.common.rxpay.sdk.RxPay;
import com.common.rxpay.sdk.alipay.OrderInfoUtil2_0;
import com.common.rxpay.sdk.wechatpay.SignUtils;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.Constants;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.OrderInfoData;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.module2.CreateServiceOrderData;
import com.insthub.cat.android.module2.WXPrepayData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.Bind;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.insthub.cat.android.constant.Constants.RSA2_PRIVATE;


/**
 * 账户信息
 * Created by linux on 2017/6/28.
 */

@WX(packageName = "com.insthub.cat.android")
public class MemberRechargeActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.tv_pay_wx)
    RadioButton tvPayWx;

    @Bind(R.id.tv_pay_alipay)
    RadioButton tvPayAlipay;

    @Bind(R.id.radio_group)
    RadioGroup radioGroup;

    @Bind(R.id.tv_pay_dg)
    TextView tvPayDg;

    @Bind(R.id.bt_sumbit)
    Button btSumbit;

    @Bind(R.id.ic_service_money)
    TextView tvServiceMoney;

    @Bind(R.id.ic_service_bond)
    TextView tvServiceBond;

    @Bind(R.id.tv_service_time)
    TextView tvServcieTime;


    private int payType=0;


    private RxPay rxPay;

    private float totalMoney=0;
    CreateServiceOrderData mCreateServiceOrderData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxPay = new RxPay(this);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_member_recharge;
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
        commonTitleBar.setTitle("缴纳会员费");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);

    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        subscribePayResultEvent();
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



        btSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(payType==0)
                {
                    ToastUtil.show(getContext(),R.string.state_select_paytype);

                    return;
                }

                showLoadDialog(getResources().getString(R.string.state_creatorder_ing));


                long servieFeeTime = 0;

                String serviceMoney="0";

                String saveMoney="0";

                if(!TextUtils.isEmpty(CacheManager.getInstance().getUserInfo().getData().getService_fee_time()))
                {
                    servieFeeTime= TimeUtils.parserTime(CacheManager.getInstance().getUserInfo().getData().getService_fee_time(),TimeUtils.FROAMTE_YMHMS);
                }

                if(CacheManager.getInstance().getUserInfo().getData().getService_fee()==0|| servieFeeTime<System.currentTimeMillis())
                {
                    saveMoney =String.valueOf(CacheManager.getInstance().getUserInfo().getData().getSave_money());

                }


                if(CacheManager.getInstance().getUserInfo().getData().getIs_save()==0)
                {
                    serviceMoney = String.valueOf(CacheManager.getInstance().getUserInfo().getData().getService_money());
                }


                mPresenter.createStoreServiceOrder(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),
                        serviceMoney
                        ,saveMoney,"0");
            }
        });



        tvPayDg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(ShopCompanyActivity.class);
            }
        });


    }

    /**
     * 支付结果
     */
    private void subscribePayResultEvent() {
        Subscription subscription = RxBusManager.getInstance()
                .toObservable(PaymentStatus.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PaymentStatus>() {
                    @Override
                    public void call(PaymentStatus event) {


                        KLog.i("11111111111111111111111111111111111111111111111111111111111111111111111111");
                        if(event.isStatus())
                        {

                            //支付成功后操作

                            mPresenter.getUserInfo(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());


                        }else
                        {

                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        throwable.printStackTrace();
                    }
                });
        RxBusManager.getInstance().addSubscription(this, subscription);
    }

    @Override
    protected void bindData() {
        super.bindData();

        long servieFeeTime = 0;

        totalMoney=0;

        if(!TextUtils.isEmpty(CacheManager.getInstance().getUserInfo().getData().getService_fee_time()))
        {
            servieFeeTime= TimeUtils.parserTime(CacheManager.getInstance().getUserInfo().getData().getService_fee_time(),TimeUtils.FROAMTE_YMHMS);
        }

        tvServiceMoney.setText("¥"+ CacheManager.getInstance().getUserInfo().getData().getSave_money()+"元/年");

        tvServcieTime.setText( CacheManager.getInstance().getUserInfo().getData().getService_fee_time());


        tvServiceBond.setText("¥"+CacheManager.getInstance().getUserInfo().getData().getService_money());


        if(CacheManager.getInstance().getUserInfo().getData().getService_fee()==0|| servieFeeTime<System.currentTimeMillis())
        {
            totalMoney=totalMoney+CacheManager.getInstance().getUserInfo().getData().getSave_money();

        }else
        {

            tvServiceMoney.setTextColor(getResources().getColor(R.color.B_black_50));
        }


        //判断是否到期  是否已经缴费
        if(CacheManager.getInstance().getUserInfo().getData().getIs_save()==0)
        {
            totalMoney=totalMoney+CacheManager.getInstance().getUserInfo().getData().getService_money();
        }else
        {

            tvServiceBond.setTextColor(getResources().getColor(R.color.B_black_50));
        }


        btSumbit.setText("确认支付 ¥"+totalMoney+"元");

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void showSuccess(Object object) {

        dismissLoadDialog();
        if(object instanceof CreateServiceOrderData)
        {
            mCreateServiceOrderData  = (CreateServiceOrderData)object;

            if(payType==1)
            {
                mPresenter.getWxPrePayData(mCreateServiceOrderData.getData().getOrder_id(),"","", DeviceUtils.getIpAddressString());
            }


            if(payType==2)
            {
                requestAlipay(mCreateServiceOrderData.getData().getOrder_id());
            }

        }

        if(object instanceof UserInfoData)
        {
            UserInfoData mUserInfoData = (UserInfoData)object;
            CacheManager.getInstance().setUserInfo(mUserInfoData);
            ToastUtil.show(getContext(),R.string.state_pay_success);
            setResult(Activity.RESULT_OK);
            finish();


        }


        if(object instanceof WXPrepayData)
        {
            dismissLoadDialog();
            WXPrepayData mWXPrepayData = (WXPrepayData)object;
            requestWechatpay(mWXPrepayData);
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

        String subject="缴纳会员费用";

        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(Constants.APPID,rsa2,Float.valueOf(totalMoney),"订单支付",orderId,Constants.NOTIFY_URL,subject);
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
                        ToastUtil.show(getContext(),"阿里支付状态："+throwable.getMessage());
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

    /**
     * 调用微信支付
     */
    private void requestWechatpay(WXPrepayData  mWXPrepayData){


        JSONObject object = new JSONObject();
        try
        {


            String uuid = UIUtil.getUUID();
            object.put("appid", Constants.WX_APPID);
            object.put("noncestr", uuid);
            object.put("package","prepay_id=" + mWXPrepayData.getData().getPrepay_id());
            object.put("partnerid",Constants.WX_MCHID);
            object.put("prepayid",mWXPrepayData.getData().getPrepay_id());
            object.put("timestamp",String.valueOf(System.currentTimeMillis() / 1000));
            object.put("sign", SignUtils.wxsign(object,Constants.WX_KEY));


        }catch (org.json.JSONException e)
        {
            e.printStackTrace();
        }

        rxPay.requestWXpay(object)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }
                });
    }


}
