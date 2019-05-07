package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
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
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.rxpay.sdk.PayUtils;
import com.common.rxpay.sdk.PaymentStatus;
import com.common.rxpay.sdk.RxPay;
import com.common.rxpay.sdk.alipay.OrderInfoUtil2_0;
import com.common.rxpay.sdk.wechatpay.SignUtils;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.Constants;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.CreateKanjiaData;
import com.insthub.cat.android.module2.CreateOrderData;
import com.insthub.cat.android.module2.CutdownDetialData;
import com.insthub.cat.android.module2.MiaoshaDetialData;
import com.insthub.cat.android.module2.WXPrepayData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.insthub.cat.android.constant.Constants.RSA2_PRIVATE;


/**
 * Created by linux on 2017/6/28.
 */

public class KanjiaMakeOrderActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.tv_shopname)
    TextView tvShopname;
    @Bind(R.id.tv_service)
    TextView tvService;
    @Bind(R.id.tv_total_money)
    TextView tvTotalMoney;

    @Bind(R.id.tv_market)
    TextView tvMarket;
    @Bind(R.id.tv_pay_alipay)
    RadioButton tvPayAlipay;
    @Bind(R.id.tv_pay_wx)
    RadioButton tvPayWx;
    @Bind(R.id.radio_group)
    RadioGroup radioGroup;
    @Bind(R.id.tv_pay)
    TextView tvPay;
    private CutdownDetialData dataBean;

    private CreateKanjiaData mCreateKanjiaData;

    private int payType=0;
    private RxPay rxPay;
    private IWXAPI api;

    //平台生成的订单号
    private String plateOrderId;


    private  CreateOrderData mCreateOrderData;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxPay = new RxPay(this);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_miaosha_makeorder;
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
        commonTitleBar.setTitle("确认订单");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        api = WXAPIFactory.createWXAPI(this, Constants.WX_APPID);
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


        subscribePayResultEvent();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i)
                {
                    case R.id.tv_pay_alipay:
                        payType=1;
                        break;
                    case R.id.tv_pay_wx:
                        payType=2;
                        break;
                }
            }
        });

        tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(payType==0)
                {
                    ToastUtil.show(getActivity(),"请选择支付方式");
                    return ;
                }


                showLoadDialog("正在创建订单");


                float price = dataBean.getData().getActivity().getOld_price()-dataBean.getData().getActivity().getCutdown_price();

                mPresenter.createOrder(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),
                        dataBean.getData().getService_content(),
                        price,dataBean.getData().getActivity().getBegin_time(),
                        tvMarket.getText().toString(),
                        dataBean.getData().getStore_id());

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
                        if(event.isStatus())
                        {

                          //支付成功后操作
                            ToastUtil.show(getContext(),R.string.state_pay_success);
                            startActivity(ShowMakeOrderSuccessActivity.class);
                            setResult(Activity.RESULT_OK);
                            finish();

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


        dataBean = (CutdownDetialData) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
        mCreateKanjiaData = (CreateKanjiaData) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA2);


        tvShopname.setText(dataBean.getData().getStore_name());
        tvService.setText(dataBean.getData().getService_content());

        float currentPrice = mCreateKanjiaData.getData().getOld_price()-mCreateKanjiaData.getData().getCutdown_price();


        tvTotalMoney.setText("￥"+currentPrice);
        tvPay.setText("确认支付：￥"+currentPrice);
        tvMarket.setText(dataBean.getData().getActivity().getRemarks());

    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {


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
        ToastUtil.show(this,msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }






    /**************************************************支付宝支付************************************************/

    /**
     * 调用支付宝支付
     * @param orderId
     */
    private void  requestAlipay(String orderId){

        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        float currentPrice = dataBean.getData().getActivity().getOld_price()-dataBean.getData().getActivity().getCutdown_price();
        String subject=dataBean.getData().getStore_name();
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(Constants.APPID,rsa2,currentPrice,"订单支付",orderId,Constants.NOTIFY_URL,subject);
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


    /**
     * 获取预支付订单
     */
    private class PrePayIdAsyncTask extends AsyncTask<String,Void, Map<String, String>>
    {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }
        @Override
        protected Map<String, String> doInBackground(String... params) {
            // TODO Auto-generated method stub
            String url=String.format(params[0]);
            String entity=getProductArgs(params[1]);
            KLog.i(entity);
            byte[] buf= PayUtils.httpPost(url, entity);
            String content = new String(buf);
            KLog.i(content);
            Map<String,String> xml=decodeXml(content);
            return xml;
        }
        @Override
        protected void onPostExecute(Map<String, String> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            if(!TextUtils.isEmpty(result.get("prepay_id")))
            {
                requestWechatpay(result.get("prepay_id"));
            }else {
                ToastUtil.show(getActivity(),"生成支付订单失败");
            }

        }
    }


    private String getProductArgs(String orderId) {
        // TODO Auto-generated method stub
        StringBuffer xml=new StringBuffer();
        try {
            xml.append("<xml>");
            List<NameValuePair> packageParams=new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", Constants.WX_APPID));
            packageParams.add(new BasicNameValuePair("body", getString(R.string.app_name)+"-支付"));
            packageParams.add(new BasicNameValuePair("mch_id", Constants.WX_MCHID));
            packageParams.add(new BasicNameValuePair("nonce_str",getNonceStr()));
            packageParams.add(new BasicNameValuePair("notify_url", Constants.WX_NOTIFY_URL));//写你们的回调地址
            packageParams.add(new BasicNameValuePair("out_trade_no",orderId));
            int money =(int)(dataBean.getData().getActivity().getDiscount_price()*100);
            packageParams.add(new BasicNameValuePair("total_fee",String.valueOf(money)));
            packageParams.add(new BasicNameValuePair("trade_type", "APP"));
            String sign=getPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));
            String xmlString=toXml(packageParams);

            try {
                xmlString=new String(xmlString.toString().getBytes(), "ISO8859-1");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return xmlString;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }



    //生成随机号，防重发
    private String getNonceStr() {
        // TODO Auto-generated method stub
        Random random=new Random();
        return SignUtils.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }



    /**
     生成签名
     */

    private String getPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.WX_APPSECRET);
        String packageSign = SignUtils.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        Log.e("Simon",">>>>"+packageSign);
        return packageSign;
    }


    /**
     * 转换成xml
     */
    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<"+params.get(i).getName()+">");
            sb.append(params.get(i).getValue());
            sb.append("</"+params.get(i).getName()+">");
        }
        sb.append("</xml>");
        Log.e("Simon",">>>>"+sb.toString());
        return sb.toString();
    }


    /**
     * 解析预支付订单信息
     * @param content
     * @return
     */
    public Map<String,String> decodeXml(String content) {

        try {
            Map<String, String> xml = new HashMap<String, String>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(content));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {

                String nodeName=parser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:

                        if("xml".equals(nodeName)==false){
                            //实例化student对象
                            xml.put(nodeName,parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = parser.next();
            }

            return xml;
        } catch (Exception e) {
            Log.e("Simon","----"+e.toString());
        }
        return null;

    }

}
