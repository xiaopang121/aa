package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.DeviceUtils;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.rxpay.sdk.RxPay;
import com.common.rxpay.sdk.alipay.OrderInfoUtil2_0;
import com.common.rxpay.sdk.wechatpay.SignUtils;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.Constants;
import com.insthub.cat.android.helper.SelectPayTypeCallback;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.CreateOrderData;
import com.insthub.cat.android.module2.OauthStroeData;
import com.insthub.cat.android.module2.ShopBondData;
import com.insthub.cat.android.module2.WXPrepayData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.utils.DialogUtils;
import com.insthub.cat.android.utils.TimeUtils;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import java.util.Map;

import butterknife.Bind;
import rx.Observer;
import rx.schedulers.Schedulers;

import static com.insthub.cat.android.constant.Constants.RSA2_PRIVATE;


/**
 * 开铺认证 服务支付
 * Created by linux on 2017/6/28.
 */

public class ShopOauthPayActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.tv_service)
    TextView tvService;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_email)
    EditText tvEmail;
    @Bind(R.id.bt_shop_cancel)
    Button btShopCancel;
    @Bind(R.id.bt_shop_sure)
    Button btShopSure;

    ShopBondData mShopBondData;

    private int payType=0;
    private RxPay rxPay;
    private IWXAPI api;

    private CreateOrderData mCreateOrderData;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxPay = new RxPay(this);

    }
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shop_oauth_pay;
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
        commonTitleBar.setTitle("支付服务");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        api = WXAPIFactory.createWXAPI(this, Constants.WX_APPID);

        mPresenter.countShopMoney(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());


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


        btShopCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        btShopSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(tvEmail.getText().toString()))
                {
                    ToastUtil.show(getContext(),"请输入邮箱");
                    return;
                }


                DialogUtils.showPayTypeDialog(getContext(), new SelectPayTypeCallback() {
                    @Override
                    public void onSelect(int type) {

                        switch (type)
                        {
                            case 1: //支付宝
                                payType=1;

                                createOrder();
                                break;
                            case 2: //微信
                                payType=2;
                                createOrder();
                                break;
                            case 3: //对公账号
                                startActivity(ShopCompanyActivity.class);
                                break;
                        }
                    }
                }).show();
            }
        });
    }


    @Override
    protected void bindData() {
        super.bindData();

    }



    private void createOrder()
    {
        showLoadDialog("正在创建订单");
        float  price = mShopBondData.getData().getMoney();
        String time = com.common.android.futils.TimeUtils.formateTime(System.currentTimeMillis(), com.common.android.futils.TimeUtils.FROMATE_HM);
        mPresenter.createOrder(CacheManager.getInstance().getToken().getData().getUser_id(),
                CacheManager.getInstance().getToken().getData().getToken(),
                mShopBondData.getData().getServerContent(), price,time,"缴纳店铺保证金", mShopBondData.getData().getStore_id());
    }



    private void updateInfo()
    {
        if(mShopBondData!=null)
        {
            tvService.setText(mShopBondData.getData().getServerContent());
            tvMoney.setText(mShopBondData.getData().getMoney()+"");
        }
    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {
        dismissLoadDialog();
        if (object instanceof ShopBondData) {

            mShopBondData = (ShopBondData)object;
            updateInfo();
        }



        if(object instanceof CreateOrderData)
        {
            mCreateOrderData = (CreateOrderData)object;


            if(payType==1)
            {
                dismissLoadDialog();
                requestAlipay(mCreateOrderData.getData().getOrder_id());
            }

            if(payType==2)
            {
                mPresenter.getWxPrePayData(mCreateOrderData.getData().getOrder_id(),"","", DeviceUtils.getIpAddressString());
            }
        }


        if(object instanceof WXPrepayData)
        {
            dismissLoadDialog();
            WXPrepayData mWXPrepayData = (WXPrepayData)object;
            requestWechatpay(mWXPrepayData);
        }

    }


    @Override
    public void showError(String msg, int code) {

        dismissLoadDialog();
        ToastUtil.show(this, msg);
    }








    /**************************************************支付宝支付************************************************/

    /**
     * 调用支付宝支付
     * @param orderId
     */
    private void  requestAlipay(String orderId){

        boolean rsa2 = (RSA2_PRIVATE.length() > 0);

        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(Constants.APPID,rsa2,mShopBondData.getData().getMoney(),"订单支付",orderId,Constants.NOTIFY_URL,"缴纳店铺保证金");
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : Constants.RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;
        KLog.i("param",orderInfo);
        rxPay.requestAlipay(orderInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        //ToastUtil.show(getContext(),"阿里支付状态："+throwable.getMessage());
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                        if(!aBoolean)
                        {
                            ToastUtil.show(getContext(),R.string.state_pay_cancle);
                        }else
                        {
                            ToastUtil.show(getContext(),R.string.state_pay_success);

                            startActivity(ShowMakeOrderSuccessActivity.class);
                            setResult(Activity.RESULT_OK);
                            finish();
                        }

                    }
                });


    }


    /**************************************************微信支付************************************************/

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
            object.put("timestamp",String.valueOf(genTimeStamp()));
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




    /**
     * 调用微信支付
     * @param orderId
     */
    private void requestWechatpay(String orderId){


        JSONObject object = new JSONObject();
        try
        {

            String uuid = UIUtil.getUUID();
            object.put("appid", Constants.WX_APPID);
            object.put("noncestr", uuid);
            object.put("package","prepay_id=" + orderId);
            object.put("partnerid",Constants.WX_MCHID);
            object.put("prepayid",orderId);
            object.put("timestamp",String.valueOf(genTimeStamp()));
            object.put("sign", SignUtils.wxsign(object,Constants.WX_KEY));

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

        }catch (org.json.JSONException e)
        {
            e.printStackTrace();
        }
    }


    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }












    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


}
