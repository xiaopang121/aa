package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.DeviceUtils;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.rxpay.sdk.PaymentStatus;
import com.common.rxpay.sdk.RxPay;
import com.common.rxpay.sdk.alipay.OrderInfoUtil2_0;
import com.common.rxpay.sdk.wechatpay.SignUtils;
import com.google.gson.Gson;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.Constants;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.event.WXShareEvent;
import com.insthub.cat.android.helper.BidOauthCallback;
import com.insthub.cat.android.helper.CommonMustCallback;
import com.insthub.cat.android.helper.SelectPayTypeCallback;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.CompeteTenderData;
import com.insthub.cat.android.module.TenderDetialData;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.module2.CreateServiceOrderData;
import com.insthub.cat.android.module2.UploadImageInfoData;
import com.insthub.cat.android.module2.WXPrepayData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.UploadImageAdapter;
import com.insthub.cat.android.ui.dialog.ActionSheetDialog;
import com.insthub.cat.android.ui.widget.SpacesItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;
import com.insthub.cat.android.utils.FileUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.insthub.cat.android.constant.Constants.RSA2_PRIVATE;


/**
 * z招标
 * Created by linux on 2017/6/28.
 */

public class TenderMustActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View, UploadImageAdapter.Callback {


    public static final int ACTIVITY_ALBUM_REQUESTCODE = 2000;

    public static final int ACTIVITY_CAMERA_REQUESTCODE = 2001;

    public static final int ACTIVITY_MODIFY_PHOTO_REQUESTCODE = 2002;

    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;


    @Bind(R.id.rv_image_list)
    public RecyclerView recyclerview;

    @Bind(R.id.et_price)
    EditText etPrice;

    @Bind(R.id.et_year)
    EditText etYear;

    @Bind(R.id.et_month)
    EditText etMonth;

    @Bind(R.id.et_day)
    EditText etDay;

    @Bind(R.id.check_rule)
    CheckBox checkRule;

    @Bind(R.id.tv_rule)
    TextView tvRule;

    @Bind(R.id.check_rule_2)
    CheckBox checkRule2;

    private LinkedList<UploadImageInfoData.DataBean> bannerImages = new LinkedList<>();
    private UploadImageAdapter mUploadImageAdapter;


    @Bind(R.id.tv_sure)
    TextView tvSure;


    private TenderDetialData mTenderDetialData;

    private String imagePath = "";


    private CustomShareListener mShareListener;

    private Handler mHandler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mUploadImageAdapter.notifyDataSetChanged();
        }
    };
    private RxPay rxPay;
    int  payType=-1;

    private int isPayStatus, isShareStatus;
    private Dialog dialog,paydialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxPay = new RxPay(this);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tender_must;
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
        mTenderDetialData =(TenderDetialData) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        commonTitleBar.setTitle("立即应标");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        bannerImages.add(new UploadImageInfoData.DataBean(-1));
        GridLayoutManager jxlayoutManager = new GridLayoutManager(getActivity(), 4);
        SpacesItemDecoration jx2layoutManager = new SpacesItemDecoration(UIUtil.dpToPx(getResources(), 5));
        recyclerview.setLayoutManager(jxlayoutManager);
        recyclerview.setHasFixedSize(true);
        mUploadImageAdapter = new UploadImageAdapter(this, bannerImages);
        recyclerview.setAdapter(mUploadImageAdapter);
        recyclerview.addItemDecoration(jx2layoutManager);
        recyclerview.scrollToPosition(0);


    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        subscribPayEvent();
        subscribShareEvent();
        commonTitleBar.ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mUploadImageAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                if (bannerImages.get(position).getType() == -1) {

                    showChangeIcon();
                }

            }
        });


        tvRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(RuleActivity.class,800);
            }
        });


        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if()
//                {
//                       DialogUtils.showBitPromptDialog(getContext(), null).show();
//                }




                if(!checkRule.isChecked())
                {
                    ToastUtil.show(getContext(), "请先同意企服星球《需求单发布以及违规处理规则》");
                    return ;
                }


                if(!checkRule2.isChecked())
                {
                    ToastUtil.show(getContext(), "请先同意阅读要求");
                    return ;
                }


                String price = etPrice.getText().toString();

                if (TextUtils.isEmpty(price) || Float.valueOf(price) <= 0) {
                    ToastUtil.show(getContext(), "请输入价格");
                    return;
                }

                String date = etYear.getText().toString() + "-" + etMonth.getText().toString() + "-" + etDay.getText().toString();

                if (!TimeUtils.isValidDate(date)) {
                    ToastUtil.show(getContext(), "请输入正确的结束时间");
                    return;
                }


                StringBuffer imageBuffer = new StringBuffer();

                for (int x = 0; x < bannerImages.size(); x++) {
                    imageBuffer.append(bannerImages.get(x).getSave_path());
                    if (x != bannerImages.size() - 1) {
                        imageBuffer.append(",");
                    }
                }


                showLoadDialog("正在提交数据");
                mPresenter.competeTender(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),
                        mTenderDetialData.getData().getTender_id(),
                        imageBuffer.toString(),
                        Float.valueOf(price),
                        date

                );


            }
        });

    }



    /**
     * 注册分享事件
     */
    private void subscribShareEvent() {
        Subscription subscription = RxBusManager.getInstance()
                .toObservable(WXShareEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WXShareEvent>() {
                    @Override
                    public void call(WXShareEvent event) {


                        KLog.i("收到 分享事件");

                        if(event.state==1)
                        {
                            isShareStatus =2;
                            dismissDialog();
                        }else
                        {
                            isShareStatus=3;
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

    /**
     * 注册支付事件
     */
    private void subscribPayEvent() {
        Subscription subscription = RxBusManager.getInstance()
                .toObservable(PaymentStatus.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PaymentStatus>() {
                    @Override
                    public void call(PaymentStatus event) {


                        KLog.i("收到 分享事件");

                        if(event.isStatus())
                        {
                            isPayStatus = 2;
                            dismissDialog();
                        }else
                        {
                            isPayStatus=3;
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


    public void dismissDialog()
    {
        if(dialog!=null && dialog.isShowing())
        {
            dialog.dismiss();
        }
    }

    @Override
    protected void bindData() {
        super.bindData();

    }

    @Override
    public void onResume() {
        super.onResume();
        if(CacheManager.getInstance().getUserInfo().getData().getIs_save()==0)
        {
            DialogUtils.showBitPromptDialog(getContext(), new BidOauthCallback() {
                @Override
                public void onOauth() {

                    startActivity(MemberServiceActivity.class);
                }

                @Override
                public void onCancel() {

                    startActivity(MainActivity.class);

                }
            }).show();

        }else
        {

            if(isShareStatus==0 && isPayStatus==0)
            {


                if(dialog!=null && dialog.isShowing())
                {
                    dialog.dismiss();

                }
                dialog =  DialogUtils.showMusitShareDialog(getContext(), CacheManager.getInstance().getUserInfo().getData().getRandom_num(), new CommonMustCallback() {
                    @Override
                    public void onShare() {

                        isShareStatus=1;
                        share();
                    }

                    @Override
                    public void onPay() {
                        isPayStatus=1;
                        paydialog =  DialogUtils.showPayTypeDialog2(getContext(), new SelectPayTypeCallback() {
                            @Override
                            public void onSelect(int type) {


                                payType = type;
                                showLoadDialog(getResources().getString(R.string.state_creatorder_ing));
                                mPresenter.createStoreServiceOrder(CacheManager.getInstance().getToken().getData().getUser_id(),
                                        CacheManager.getInstance().getToken().getData().getToken(),"0","0","1");
                            }
                        });


                        paydialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {

                                if(payType==-1)
                                {
                                    finish();
                                }
                            }
                        });

                        paydialog.show();
                    }
                });


                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if(isPayStatus ==0 && isShareStatus==0)
                        {
                            finish();
                        }
                    }
                });
                dialog .show();
            }

        }

    }

    @Override
    public void showSuccess(Object object) {


        dismissLoadDialog();
        if (object instanceof CompeteTenderData) {

            ToastUtil.show(getContext(), "数据提交成功");
            finish();
        }
        if(object instanceof CreateServiceOrderData)
        {
            CreateServiceOrderData data = (CreateServiceOrderData)object;

            if(payType==1) //支付宝
            {
                requestAlipay(data.getData().getOrder_id());
            }


            if(payType==2)
            {
                mPresenter.getWxPrePayData(data.getData().getOrder_id(),"","", DeviceUtils.getIpAddressString());
            }
        }

        if(object instanceof UserInfoData)
        {
            UserInfoData mUserInfoData = (UserInfoData)object;
            CacheManager.getInstance().setUserInfo(mUserInfoData);
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
        ToastUtil.show(getActivity(), msg);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {

                case ACTIVITY_ALBUM_REQUESTCODE:
                    if (resultCode == Activity.RESULT_OK) {
                        cutPhoto(data.getData(), true);
                    }
                    break;
                case ACTIVITY_CAMERA_REQUESTCODE:
                    if (resultCode == Activity.RESULT_OK) {

                        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                        bitmapOptions.inSampleSize = 2;
                        int degree = FileUtil.readPictureDegree(imagePath);
                        Bitmap cameraBitmap = BitmapFactory.decodeFile(imagePath, bitmapOptions);
                        cameraBitmap = FileUtil.rotaingImageView(degree, cameraBitmap);
                        FileUtil.saveCutBitmapForCache(this, cameraBitmap);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            cutPhoto(FileProvider.getUriForFile(getActivity(), "com.insthub.cat.android.provider", new File(imagePath)), true);
                        } else {
                            cutPhoto(Uri.fromFile(new File(imagePath)), true);
                        }

                    }
                    break;
                case ACTIVITY_MODIFY_PHOTO_REQUESTCODE:
                    // NOTICE: 上传头像接口ok


                    uploadUserHead(imagePath);


                    break;
                case 800:
                    checkRule.setChecked(true);
                    break;
            }

        }

    }


    private void showChangeIcon() {

        getPhotoPath();
        new ActionSheetDialog(getActivity())
                .builder()
                .setTitle("选择获取图片的方式")
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("相机", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {


                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用android自带的照相机

                                Uri imageUri = null;
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                                    imageUri = FileProvider.getUriForFile(getActivity(), "com.insthub.cat.android.provider", new File(imagePath));
                                } else {
                                    imageUri = Uri.fromFile(new File(imagePath));
                                }
                                List resInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                                if (resInfoList.size() == 0) {
                                    ToastUtil.show(getActivity(), "没有合适的相机应用程序");
                                    return;
                                }
                                Iterator resInfoIterator = resInfoList.iterator();
                                while (resInfoIterator.hasNext()) {
                                    ResolveInfo resolveInfo = (ResolveInfo) resInfoIterator.next();
                                    String packageName = resolveInfo.activityInfo.packageName;
                                    KLog.i("packName:" + packageName);
                                    grantUriPermission(packageName, imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                }
                                KLog.i("url:" + imageUri.toString());

                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                                startActivityForResult(intent, ACTIVITY_CAMERA_REQUESTCODE);


                            }
                        })
                .addSheetItem("图库", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent i = new Intent(Intent.ACTION_PICK, null);// 调用android的图库
                                i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(i, ACTIVITY_ALBUM_REQUESTCODE);

                            }
                        }).show();

        //          }
        //      });
    }


    public void getPhotoPath() {
        if (!TextUtils.isEmpty(imagePath)) {

            try {
                File file = new File(imagePath);
                file.delete();

            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }

        imagePath = FileUtil.getHeadPhotoDir() + System.currentTimeMillis() + ".png";
    }


    /**
     * 裁剪图片
     */
    public void cutPhoto(Uri uri, boolean isHeadPic) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        if (isHeadPic) {
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);

            intent.putExtra("scale", true);
            //只能设置成false，k920无法返回
            intent.putExtra("return-data", false);

            Uri tempUrl = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tempUrl = FileProvider.getUriForFile(getActivity(), "com.insthub.cat.android.provider", new File(imagePath));

            } else {
                tempUrl = Uri.fromFile(new File(imagePath));
            }

            List resInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if (resInfoList.size() == 0) {
                ToastUtil.show(getActivity(), "没有合适的相机应用程序");
                return;
            }
            Iterator resInfoIterator = resInfoList.iterator();
            while (resInfoIterator.hasNext()) {
                ResolveInfo resolveInfo = (ResolveInfo) resInfoIterator.next();
                String packageName = resolveInfo.activityInfo.packageName;
                grantUriPermission(packageName, tempUrl, Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }

            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUrl);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
            intent.putExtra("noFaceDetection", true);
        } else {
            // 是否保留比例
            intent.putExtra("scale", "true");
            intent.putExtra("output", Uri.fromFile(FileUtil.getWallPaperFile()));
        }

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, ACTIVITY_MODIFY_PHOTO_REQUESTCODE);
    }


    /**
     * 上传图片
     */
    public void uploadUserHead(String imagePath) {

        showLoadDialog("正在上传数据");
        RequestParams params = new RequestParams();
        params.addHeader("Connection", "Keep-Alive");
        // params.addHeader("Content-Type", "multipart/form-data;charset=utf-8");
        params.addBodyParameter("content_type", "head");
        params.addBodyParameter("file", new File(imagePath), "image/png");

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, Constants.SERVICE_URL_PREFEX + "upload/save.do", params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String s = responseInfo.result;
                KLog.i("content", s);
                //headImage
                try {
                    Gson gson = new Gson();
                    UploadImageInfoData logo = gson.fromJson(s, UploadImageInfoData.class);
                    bannerImages.removeLast();
                    bannerImages.add(logo.getData());
                    if (bannerImages.size() < 9) {
                        bannerImages.add(new UploadImageInfoData.DataBean(-1));
                    }

                    mHandler.sendEmptyMessage(0);
                    dismissLoadDialog();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {

                dismissLoadDialog();
            }
        });
    }


    @Override
    public void delete(int position) {

        bannerImages.remove(position);
        mUploadImageAdapter.notifyDataSetChanged();
    }




    private void share()
    {

        mShareListener = new CustomShareListener(this);
        ShareBoardConfig mShareBoardConfig = new ShareBoardConfig();
        mShareBoardConfig.setCancelButtonVisibility(false);
        mShareBoardConfig.setTitleVisibility(false);
        mShareBoardConfig.setIndicatorVisibility(false);
        ShareAction mShareAction = new ShareAction(getActivity()).setDisplayList(
                SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                        if (snsPlatform.mShowWord.equals("复制链接")) {
                            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            // 将文本内容放到系统剪贴板里。
                            cm.setText(mTenderDetialData.getData().getShare().getShare_image());
                            ToastUtil.show(getActivity(),"复制链接成功");

                        }else
                        {
                            UMWeb web = new UMWeb(mTenderDetialData.getData().getShare().getShare_url());
                            web.setTitle(mTenderDetialData.getData().getShare().getShare_title());
                            web.setDescription(mTenderDetialData.getData().getShare().getShare_desc());
                            web.setThumb(new UMImage(getActivity(),mTenderDetialData.getData().getShare().getShare_image()));
                            new ShareAction(getActivity()).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        }


                    }
                });
        mShareAction.open(mShareBoardConfig);




    }





    private class CustomShareListener implements UMShareListener {

        private WeakReference<Activity> mActivity;

        private CustomShareListener(Activity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            dismissDialog();
            Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mActivity.get(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }


    private void  requestAlipay(String orderId){
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        String subject="报名费";
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(Constants.APPID,rsa2,Float.valueOf(1),"订单支付",orderId,Constants.NOTIFY_URL,subject);
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
                            isPayStatus =2;
                            dismissDialog();
                        }else
                        {isPayStatus =3;
                            ToastUtil.show(getContext(),"支付失败");
                        }
                    }
                });
    }

    /**
     * 调用微信支付
     */
    private void requestWechatpay(WXPrepayData mWXPrepayData){


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
