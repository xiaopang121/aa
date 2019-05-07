package com.insthub.cat.android.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fsp.SharedPreferencesUtil;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.futils.*;
import com.common.android.futils.TimeUtils;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.BidOauthCallback;
import com.insthub.cat.android.helper.BlackCallback;
import com.insthub.cat.android.helper.CommonMustCallback;
import com.insthub.cat.android.helper.DeleteCommentCallback;
import com.insthub.cat.android.helper.FistBindPhoneCallback;
import com.insthub.cat.android.helper.HomeActivityCallback;
import com.insthub.cat.android.helper.HongbaoDetailCallback;
import com.insthub.cat.android.helper.LegalUserHelper;
import com.insthub.cat.android.helper.OrderApplyFinishCallback;
import com.insthub.cat.android.helper.OtherYqCallback;
import com.insthub.cat.android.helper.PintuanCallback;
import com.insthub.cat.android.helper.SelectPayTypeCallback;
import com.insthub.cat.android.helper.SelectUserTypeCallback;
import com.insthub.cat.android.helper.ServiceCallback;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.CouponListData;
import com.insthub.cat.android.module2.HistoryRecordListData;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.module2.OpenRedPackageData;
import com.insthub.cat.android.module2.PintuanDetialData;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.activity.AccountActivity;
import com.insthub.cat.android.ui.activity.HombaoActivity;
import com.insthub.cat.android.ui.activity.MyCouponListActivity;
import com.insthub.cat.android.ui.activity.WxLoginActivity;
import com.insthub.cat.android.ui.adatper.SelectCouponListAdapter;
import com.insthub.cat.android.ui.widget.CountTimeDownHelper;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.ui.widget.OnInputCallback;
import com.insthub.cat.android.ui.widget.PasswordView;

import java.util.List;

import butterknife.Bind;

import static android.webkit.WebSettings.LOAD_NO_CACHE;

/**
 * 对话框
 * Created by linux on 2017/7/19.
 */

public class DialogUtils {



    /**
     * 显示拼团弹出框
     * @param mContext
     * @return
     */
    public static Dialog  showPinTuanDialog(final Context mContext, PintuanDetialData data,final PintuanCallback callback)
    {


        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 5);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width,(int)(ScreenInfo.getScreenHeight((Activity) mContext)*0.7));
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_pintuan, null);
        ImageView ivClose = mView.findViewById(R.id.iv_close);


        ImageView ivPinzhuHead=mView.findViewById(R.id.iv_pinzhu_head);

        Button btJoin=mView.findViewById(R.id.bt_join);

        TextView tvTitle = mView.findViewById(R.id.tv_title);
       final TextView tvContent = mView.findViewById(R.id.tv_sub_title);
        tvTitle.setText("参与"+DeclareUtil.formatePhone(data.getData().getPintuan().getPhone())+"的拼团");


//        String temp = "还差1人拼团";
//        long time   =  com.common.android.futils.TimeUtils.parserTime(data.getData().getPintuan().getStart_time(), com.common.android.futils.TimeUtils.FROAMTE_YMHMS);
//
//        String counteTime = com.common.android.futils.TimeUtils.formateTime(time, com.common.android.futils.TimeUtils.FROMATE_HMS);
//
//        String  [] timeSplite = counteTime.split(":");
//
//        if(timeSplite.length==3)
//        {
//            timeSplite[0] = Integer.valueOf(timeSplite[0])+24+"";
//            tvContent.setText(temp+","+ timeSplite[0]+":"+ timeSplite[1]+":"+ timeSplite[2]+"后结束");
//        }



        long endTime =  com.common.android.futils.TimeUtils.parserTime(data.getData().getPintuan().getCreate_time(), TimeUtils.FROMATE_YMD);

        long distancTime = endTime+24*60*60*1000-System.currentTimeMillis();

        if(distancTime<0)
        {
            distancTime=0;
        }

      final   CountTimeDownHelper  mHelper = new CountTimeDownHelper(new CountDownTimer(distancTime*1000,1 * 1000 - 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                String counteTime = TimeUtils.formateTime(millisUntilFinished,TimeUtils.FROMATE_HMS);

                tvContent.setText("还差1人拼团,"+ counteTime+"后结束");
            }

            @Override
            public void onFinish() {
                tvContent.setText("还差1人拼团,00:00:00后结束");
            }
        });
        mHelper.start();

        Glide.with(mContext)
                .load(data.getData().getPintuan().getHead_image())
                .into(ivPinzhuHead);



        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(LegalUserHelper.isLegalUserStatus((Activity) mContext))
                {
                    callback.onClickSure();
                }
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mHelper.onDestroy();
            }
        });
        return dialog;
    }


    /**
     * 显示开奖弹出框
     * @param mContext
     * @return
     */
    public static Dialog  showPrizeDialog(final Context mContext,String content,String title)
    {


        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 5);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width,(int)(ScreenInfo.getScreenHeight((Activity) mContext)*0.7));
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_prize, null);
        ImageView ivClose = (ImageView) mView.findViewById(R.id.iv_close);
        TextView tvTitle = mView.findViewById(R.id.tv_title);
        TextView tvContent = mView.findViewById(R.id.tv_content);
        tvTitle.setText(title);
        tvContent.setText(content);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button btSure = (Button) mView.findViewById(R.id.bt_sure);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(LegalUserHelper.isLegalUserStatus((Activity) mContext))
                {
                    ((Activity) mContext).startActivity(new Intent(mContext,MyCouponListActivity.class));
                }
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }



    public static Dialog  showPayTypeDialog(final Context mContext, final SelectPayTypeCallback callback)
    {


        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 40);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_paytype, null);
        RadioGroup rg = (RadioGroup)mView.findViewById(R.id.radigroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {



                switch (i)
                {
                    case R.id.rb_alipay:

                        callback.onSelect(1);
                        break;
                    case R.id.rb_wx:
                        callback.onSelect(2);
                        break;
                    case R.id.rb_bank:
                        callback.onSelect(3);
                        break;
                }

                dialog.dismiss();

            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }





    public static Dialog showAdviser(final Context mContext,String url)
    {
        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 60);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ScreenInfo.getScreenHeight((Activity) mContext)/5*4);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_adviser, null);
        WebView webview = mView.findViewById(R.id.webView);
        final ImageView ivClose =mView.findViewById(R.id.iv_close);
        WebSettings webSettings = webview.getSettings();
        webSettings.setSupportZoom(true);  //支持缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows();  //多窗口
        webSettings.setAllowFileAccess(true);  //设置可以访问文件
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setBuiltInZoomControls(false); //设置支持缩放
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(false); //关闭全局缓存
        webSettings.setCacheMode(LOAD_NO_CACHE); //指定缓存模式
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webview.loadUrl(url);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }




    public static Dialog showHongbaoDialog(final Context mContext)
    {



        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 60);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ScreenInfo.getScreenHeight((Activity) mContext)/2);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_hongbao, null);
        final ImageView ivAdersor = (ImageView)mView.findViewById(R.id.iv_hongbao);
        final ImageView ivClose = (ImageView)mView.findViewById(R.id.iv_close);

        ivAdersor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mContext.startActivity(new Intent(mContext,HombaoActivity.class));
            }
        });


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;

    }



    /**
     * 历史记录 领取红包
     * @param mContext
     * @return
     */
    public static Dialog  showReceiveHongbaoDialog(final Context mContext, final  HistoryRecordListData.DataBean.ListBean mOpenRedPackageData ,final HongbaoDetailCallback callback)
    {

        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 60);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ScreenInfo.getScreenHeight((Activity) mContext)/5*4);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_recevier, null);
        final ImageView ivAdersor = (ImageView)mView.findViewById(R.id.iv_advisor);
        final TextView tvMoney = (TextView)mView.findViewById(R.id.tv_money);
        final TextView tvDetial= (TextView)mView.findViewById(R.id.tv_detial);
        final ImageView ivClose = (ImageView)mView.findViewById(R.id.iv_close);

        RequestOptions requestOptions2 = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_default_advert)
                .error(R.drawable.ic_default_advert)
                ;
        Glide.with(mContext).asBitmap()
                .load(mOpenRedPackageData.getImage())
                .apply(requestOptions2)
                .into(ivAdersor);

        tvMoney.setText("已领取:￥"+mOpenRedPackageData.getMoney());

        tvMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        tvDetial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if(callback!=null)
                {
                    callback.onClickSure(mOpenRedPackageData.getStore_id());
                }


            }
        });


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }





    public static Dialog  showBindPhoneDialog(final Context mContext, final FistBindPhoneCallback callback)
    {


        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 40);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_bindphone, null);
        final EditText etPhone = (EditText)mView.findViewById(R.id.et_phone);
        final EditText etCode = (EditText)mView.findViewById(R.id.et_invite_code);
        Button btSure = (Button) mView.findViewById(R.id.bt_sure);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(etPhone.getText().toString()))
                {
                    ToastUtil.show(mContext,"请输入您的手机号码");
                    return;
                }


                if(callback!=null)
                {
                    callback.onBind(etPhone.getText().toString(),etCode.getText().toString());
                }

                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }



    public static Dialog  showCouponDialog(Context mContext, final List<CouponListData.DataBean.ListBean> list, final ServiceCallback callback)
    {

        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 60);

        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_buttom_coupon, null);
        RecyclerView recyclerview = (RecyclerView) mView.findViewById(R.id.recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        final SelectCouponListAdapter mCommentAdapter = new SelectCouponListAdapter(mContext,list);
        recyclerview.setAdapter(mCommentAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST, true, R.drawable.menu_item_divider_black_1));

        mCommentAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mCommentAdapter.setSelect(position);
            }
        });
        TextView btSure = (TextView) mView.findViewById(R.id.btn_sure);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(callback!=null)
                {
                    callback.onService(mCommentAdapter.getSelect());
                }

                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.addContentView(mView, pMLayoutParams);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        return dialog;
    }





    public static Dialog  showServiceDialog(Context mContext, final ServiceCallback callback, final int position)
    {

        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 60);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ScreenInfo.getScreenHeight((Activity) mContext)/4);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_service, null);
        Button btservice = (Button) mView.findViewById(R.id.bt_service);

        btservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(callback!=null)
                {
                    callback.onService(position);
                }
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }


    /**
     * 提现对话框
     * @param mContext
     * @return
     */
    public static Dialog  showWithdrawDialog(final Context mContext)
    {

        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 60);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ScreenInfo.getScreenHeight((Activity) mContext)/4);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_withdraw, null);
        Button btservice = (Button) mView.findViewById(R.id.bt_service);
        btservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }


    /**
     * 提现申请话框
     * @param mContext
     * @return
     */
    public static Dialog  showWithdrawApplayDialog(final Context mContext)
    {

        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 40);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_widthdraw_apply, null);
        Button btservice = (Button) mView.findViewById(R.id.bt_sure);
        btservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                SessionHelper.startP2PSession(mContext, CacheManager.getInstance().getToken().getData().getIm_code());
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }



    /**
     * 黑名单弹出框
     * @param mContext
     * @param callback
     * @param position
     * @return
     */
    public static Dialog  showBlackDialog(Context mContext, final BlackCallback callback, final int position)
    {

        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 60);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ScreenInfo.getScreenHeight((Activity) mContext)/4);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_black, null);


        Button btCancel = (Button) mView.findViewById(R.id.bt_cancel);

        Button btSure = (Button) mView.findViewById(R.id.bt_sure);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             dialog.dismiss();
            }
        });

        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(callback!=null)
                {
                    callback.onClickSure(position);
                }
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }
    /**
     * 删除订单
     * @param mContext
     * @param callback
     * @param position
     * @return
     */
    public static Dialog  showDeleteOrderDialog(Context mContext, final DeleteCommentCallback callback, final int position)
    {

        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 60);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ScreenInfo.getScreenHeight((Activity) mContext)/4);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_delete_comment, null);


        TextView tvTitle = (TextView) mView.findViewById(R.id.tv_dialog_title);

        tvTitle.setText("确定要删除这条订单吗？");

        Button btCancel = (Button) mView.findViewById(R.id.bt_cancel);

        Button btSure = (Button) mView.findViewById(R.id.bt_sure);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(callback!=null)
                {
                    callback.onClickSure(dialog,position);
                }
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }


    /**
     * 删除评论
     * @param mContext
     * @param callback
     * @param position
     * @return
     */
    public static Dialog  showDeleteCommentDialog(Context mContext, final DeleteCommentCallback callback, final int position)
    {

        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 60);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ScreenInfo.getScreenHeight((Activity) mContext)/4);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_delete_comment, null);


        Button btCancel = (Button) mView.findViewById(R.id.bt_cancel);

        Button btSure = (Button) mView.findViewById(R.id.bt_sure);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(callback!=null)
                {
                    callback.onClickSure(dialog,position);
                }
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }




    /**
     * 显示弹出框密码输入框

     */
    public static Dialog showPasswordDialog(final Context mContext,final OnInputCallback callback)
    {

        final Dialog  dialog = new Dialog(mContext,R.style.dialog2);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(ScreenInfo.getScreenWidth((Activity) mContext), LinearLayout.LayoutParams.WRAP_CONTENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_trade, null);
        dialog.setCanceledOnTouchOutside(true);
        dialog.addContentView(mView, pMLayoutParams);

        final PasswordView pwdView = (PasswordView) mView.findViewById(R.id.passwordview);
        pwdView.setOnnputCallback(callback);

        ImageView ivClose=(ImageView) mView.findViewById(R.id.iv_close);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        return dialog;

    }


//
//    public static void showKeFuDialog(final Context context) {
//        CommonDialog.Builder commonDialog = new CommonDialog.Builder(context);
//        commonDialog.setTitle("客服电话");
//        commonDialog.setMessage("400-889-7650");
//        commonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:400-889-7650"
//                ));
//                // intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent2);
//            }
//        });
//        commonDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//        commonDialog.create().show();
//    }


    /**
     * 竞价 温馨提示
     * @param mContext
     * @param callback
     * @return
     */
    public static Dialog  showBitPromptDialog(final Context mContext, final BidOauthCallback callback)
    {


        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 40);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_bidprompt, null);
        Button btCancel = (Button) mView.findViewById(R.id.bt_cancel);
        Button btSure = (Button) mView.findViewById(R.id.bt_sure);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(callback!=null)
                {
                    callback.onOauth();
                }

                dialog.dismiss();
            }
        });



        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(callback!=null)
                {
                    callback.onCancel();
                }

                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);

        dialog.setCancelable(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }




    /**
     * 申请验收
     * @param mContext
     * @param callback
     * @return
     */
    public static Dialog  showOrderApplyFinishDialog(final Context mContext, final OrderApplyFinishCallback callback)
    {


        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 40);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_order_applay_finish, null);
        Button btCancel = (Button) mView.findViewById(R.id.bt_cancel);
        Button btSure = (Button) mView.findViewById(R.id.bt_sure);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(callback!=null)
                {
                    callback.finish();
                }

                dialog.dismiss();
            }
        });



        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }



    /**
     * 付款给服务商
     * @param mContext
     * @param callback
     * @return
     */
    public static Dialog  showOrderApplyServiceDialog(final Context mContext, final OrderApplyFinishCallback callback)
    {


        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 40);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_order_applay_finish, null);
        TextView tvContent = mView.findViewById(R.id.et_phone);
        tvContent.setText("确认该服务已经完成，付款给服务商？");
        Button btCancel = (Button) mView.findViewById(R.id.bt_cancel);
        Button btSure = (Button) mView.findViewById(R.id.bt_sure);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(callback!=null)
                {
                    callback.finish();
                }

                dialog.dismiss();
            }
        });



        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }






    public static Dialog  showOtherYqDialog(final Context mContext, final OtherYqCallback callback)
    {


        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 10);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_other_yq, null);
        final EditText etPhone = (EditText)mView.findViewById(R.id.et_phone);
        Button btSure = (Button) mView.findViewById(R.id.bt_sure);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(etPhone.getText().toString()))
                {
                    ToastUtil.show(mContext,"请输入要求内容");
                    return;
                }


                if(callback!=null)
                {
                    callback.onValue(etPhone.getText().toString());
                }

                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }




    public static Dialog  showMusitShareDialog(final Context mContext, int num,final CommonMustCallback callback)
    {


        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 30);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_comment_bid, null);
        final TextView etTitle = mView.findViewById(R.id.tv_title);

        etTitle.setText("还剩"+num+"个参与名额");

        TextView btShare = (TextView) mView.findViewById(R.id.tv_zhuanfa);

        TextView btPay = (TextView) mView.findViewById(R.id.tv_pay);

        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(callback!=null)
                {
                    callback.onShare();
                }

                dialog.dismiss();
            }
        });


        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback!=null)
                {
                    callback.onPay();
                }

                dialog.dismiss();
            }
        });


        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }





    public static Dialog  showPayTypeDialog2(final Context mContext, final SelectPayTypeCallback callback)
    {


        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 40);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_paytype2, null);
        RadioGroup rg = (RadioGroup)mView.findViewById(R.id.radigroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {



                switch (i)
                {
                    case R.id.rb_alipay:

                        callback.onSelect(1);
                        break;
                    case R.id.rb_wx:
                        callback.onSelect(2);
                        break;
                    case R.id.rb_bank:
                        callback.onSelect(3);
                        break;
                }

                dialog.dismiss();

            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }




    public static Dialog  showPromptDialog(final Context mContext)
    {


        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 30);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_bid_prompt, null);
        final TextView etTitle = mView.findViewById(R.id.tv_title);

        TextView tvCancel = (TextView) mView.findViewById(R.id.tv_cancel);

        TextView tvSure = (TextView) mView.findViewById(R.id.tv_sure);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                dialog.dismiss();
            }
        });


        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }



    public static Dialog  showHomeActivityDialog(final Activity mContext , HomeData.DataBean.ActyBean  acty , final HomeActivityCallback callback)
    {

        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 30);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_home_activity, null);
        final ImageView  ivClose = mView.findViewById(R.id.iv_close);
        final ImageView ivActivity = mView.findViewById(R.id.iv_acitivty);
        final ImageView etJoin = mView.findViewById(R.id.tv_join);

        RequestOptions requestOptions2 = new RequestOptions()
                .fitCenter();
        Glide.with(mContext).asBitmap()
                .load(acty.getImage())
                .apply(requestOptions2)
                .into(ivActivity);


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        etJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(callback!=null){

                    callback.onJoin(dialog );
                }
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }



    public static Dialog  showLargeImage(final Activity mContext ,String url)
    {

        final Dialog dialog = new Dialog(mContext, R.style.dialog);
        int width = ScreenInfo.getScreenWidth((Activity) mContext) - UIUtil.dpToPx(mContext.getResources(), 30);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_show_image, null);
        final ImageView ivActivity = mView.findViewById(R.id.iv_acitivty);

        RequestOptions requestOptions2 = new RequestOptions()
                .fitCenter();
        Glide.with(mContext).asBitmap()
                .load(url)
                .apply(requestOptions2)
                .into(ivActivity);

        ivActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;
    }

}
