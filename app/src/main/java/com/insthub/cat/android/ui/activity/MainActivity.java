package com.insthub.cat.android.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.futils.DialogUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.android.futils.fpermission.MPermission;
import com.common.android.futils.fpermission.annotation.OnMPermissionDenied;
import com.common.android.futils.fpermission.annotation.OnMPermissionGranted;
import com.common.android.futils.fpermission.annotation.OnMPermissionNeverAskAgain;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.event.ActionEvent;
import com.insthub.cat.android.event.HongbaoEvent;
import com.insthub.cat.android.event.ThirdOauthEvent;
import com.insthub.cat.android.helper.FistBindPhoneCallback;
import com.insthub.cat.android.helper.LegalUserHelper;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.BidPriceListData;
import com.insthub.cat.android.module.InnovateListData;
import com.insthub.cat.android.module.TenderListData;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.module2.BindPhoneData;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.module2.LoginTokenData;
import com.insthub.cat.android.module2.VersionData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.fragment.HomeFragment.HomeFragment;
import com.insthub.cat.android.utils.UpdateAppHttpUtil;
import com.netease.nim.uikit.common.util.C;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateDialogFragment;
import com.vector.update_app.listener.IUpdateDialogFragmentListener;

import java.util.Timer;
import java.util.TimerTask;

import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * 主界面
 * Created by linux on 2017/6/26.
 */

public class MainActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {

    private final int BASIC_PERMISSION_REQUEST_CODE = 128;


    private boolean isCheck;

    /**
     * 基本权限管理
     */
    private final String[] BASIC_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_SETTINGS
    };
    private Bundle mBundle;

    private HomeFragment mHomeFragment;
    private boolean isExit;


    private Dialog dialog;



    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processExtraData();
        setSwipeBackEnable(false);
    }

    @Override
    protected void bindViewById() {
        super.bindViewById();
        requestBasicPermission();
        navigateLibrary.run();

    }

    public static void startActivity(Context context)
    {


        Intent intent = new Intent(context,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

    }


    @Override
    protected void bindEvent() {
        super.bindEvent();

        //注册额红包事件
        subscribeHongbaoEvent();

        subscribePrintActionEvent();

    }

    @Override
    protected void bindPresenter() {


        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void bindData() {
        super.bindData();

        if(CacheManager.getInstance().getToken()!=null && CacheManager.getInstance().getUserInfo()==null)
        {
            mPresenter.getUserInfo(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());
        }



      //  com.insthub.cat.android.utils.DialogUtils.showAdviser(getContext(),"http://www.baidu.com").show();

        mPresenter.getVersion();


        if(CacheManager.getInstance().getToken()!=null && CacheManager.getInstance().getUserInfo()==null)
        {
            mPresenter.getUserInfo(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());
        }


    }






    //主界面
    private Runnable navigateLibrary = new Runnable() {
        public void run() {
            mHomeFragment = HomeFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, mHomeFragment).commitAllowingStateLoss();

        }
    };


    /**
     * 申请权限
     */
    private void requestBasicPermission() {
        MPermission.printMPermissionResult(true, this, BASIC_PERMISSIONS);
        MPermission.with(MainActivity.this)
                .setRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(BASIC_PERMISSIONS)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess() {
        // ToastUtil.show(this, R.string.permission_success);
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    @OnMPermissionNeverAskAgain(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed() {
        // ToastUtil.show(this,R.string.permission_fault);
    }

    @Override
    protected int bindColorPrimary() {
        return R.color.red;
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        processExtraData();
    }

    public void processExtraData() {

        if (getIntent().getExtras() != null) {

            //处理广告
            if (getIntent().getExtras().containsKey(ConstantsKeys.KEY_JUM_ACTIVITY)) {


                String url = getIntent().getExtras().getString(ConstantsKeys.KEY_JUM_ACTIVITY);
//                Bundle mBundle = new Bundle();
//                mBundle.putString(ConstantsKeys.KEY_JUM_ACTIVITY, url);
//                if (!TextUtils.isEmpty(url)) {
//                    startActivity(CommonAdviserActivity.class, mBundle);
//                }
//

                com.insthub.cat.android.utils.DialogUtils.showAdviser(getContext(),url).show();
            }


            //小鲸消息
            if (getIntent().getExtras().containsKey(ConstantsKeys.KEY_JUM_ACTIVITY_MSG_LIST)) {
                startActivity(XjMsgListActivity.class);
            }



            //开票列表
            if (getIntent().getExtras().containsKey(ConstantsKeys.KEY_JUM_ACTIVITY_TICK_LIST)) {
                startActivity(MyTicketActivity.class);
            }




            //跳转到店铺详情
            if(getIntent().getExtras().containsKey(ConstantsKeys.KEY_DATA_STORE_ID))
            {
                String lineId = getIntent().getExtras().getString(ConstantsKeys.KEY_DATA_STORE_ID);
                DiscoverStoreData.DataBean.ListBean tem = new DiscoverStoreData.DataBean.ListBean();
                tem.setStore_id(lineId);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,tem);
                startActivity(RenqiDetialActivity.class,bundle);
            }


            //跳转到服务详情
            if(getIntent().getExtras().containsKey(ConstantsKeys.KEY_DATA_SERVICE_ID))
            {
                String lineId = getIntent().getExtras().getString(ConstantsKeys.KEY_DATA_SERVICE_ID);
                DiscoverStoreData.DataBean.ListBean tem = new DiscoverStoreData.DataBean.ListBean();
                tem.setStore_id(lineId);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,tem);


                startActivity(ShopDetialActivity.class,bundle);
            }


            //跳转到竞价详情
            if(getIntent().getExtras().containsKey(ConstantsKeys.KEY_DATA_JJ_TENDER_ID))
            {
                String lineId = getIntent().getExtras().getString(ConstantsKeys.KEY_DATA_JJ_TENDER_ID);
                BidPriceListData.DataBean.ListBean tem = new BidPriceListData.DataBean.ListBean();
                tem.setTender_id(lineId);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,tem);
                bundle.putInt(ConstantsKeys.KEY_FROM,1);
                startActivity(BidDetialActivity.class,bundle);
            }


            //跳转到招标详情
            if(getIntent().getExtras().containsKey(ConstantsKeys.KEY_DATA_ZB_TENDER_ID))
            {
                String lineId = getIntent().getExtras().getString(ConstantsKeys.KEY_DATA_ZB_TENDER_ID);
                TenderListData.DataBean.ListBean mBidPricteItem  = new TenderListData.DataBean.ListBean ();
                mBidPricteItem.setTender_id(lineId);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,mBidPricteItem);
                bundle.putInt(ConstantsKeys.KEY_FROM,1);
                startActivity(TenderDetialActivity.class,bundle);
            }


            //跳转到创新集详情
            if(getIntent().getExtras().containsKey(ConstantsKeys.KEY_DATA_ZJ_TENDER_ID))
            {
                String lineId = getIntent().getExtras().getString(ConstantsKeys.KEY_DATA_ZJ_TENDER_ID);
                InnovateListData.DataBean.ListBean tem = new InnovateListData.DataBean.ListBean();
                tem.setTender_id(lineId);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,tem);
                bundle.putInt(ConstantsKeys.KEY_FROM,1);
                startActivity(InnovateDetialActivity.class,bundle);
            }



            //跳转到我的钱包
            if(getIntent().getExtras().containsKey(ConstantsKeys.KEY_JUM_WALET_ACTIVITY))
            {
                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    startActivity(MyWallettActivity.class);
                }
            }


        }
}



    private void checkFirstLogin()
    {




        if(CacheManager.getInstance().getToken()!=null && !isCheck && CacheManager.getInstance().getToken().getData()!=null)
        {

//            if(CacheManager.getInstance().getToken().getData().getLoginType()==1)
//            {
//                if(CacheManager.getInstance().getToken().getData().getFirst_login()==1)
//                {
//
//
//                    if(dialog!=null && dialog.isShowing())
//                    {
//                        return;
//                    }
//
//                    dialog =  com.insthub.cat.android.utils.DialogUtils.showBindPhoneDialog(this, new FistBindPhoneCallback() {
//                        @Override
//                        public void onBind(String phone, String inviteCode) {
//
//                            showLoadDialog("正在绑定手机号");
//                            mPresenter.bindPhone(CacheManager.getInstance().getToken().getData().getUser_id(), CacheManager.getInstance().getToken().getData().getToken(),phone,inviteCode);
//                        }
//                    });
//
//                    dialog.show();
//                }
//                isCheck =true;
//            }


            if(CacheManager.getInstance().getToken().getData().getLoginType()==2)
            {
                if(CacheManager.getInstance().getToken().getData().getFirst_login()==1 && CacheManager.getInstance().getToken().getData().getBind_phone()==0)
                {

                    if(dialog!=null && dialog.isShowing())
                    {
                        return;
                    }

                    dialog = com.insthub.cat.android.utils.DialogUtils.showBindPhoneDialog(this, new FistBindPhoneCallback() {
                        @Override
                        public void onBind(String phone, String inviteCode) {

                            showLoadDialog("正在绑定手机号");
                            mPresenter.bindPhone(CacheManager.getInstance().getToken().getData().getUser_id(), CacheManager.getInstance().getToken().getData().getToken(),phone,inviteCode);
                        }
                    });

                    dialog.show();
                }
                isCheck =true;
            }

        }else
        {
            isCheck=false;
        }
    }







    /**
     * 红包事件
     */
    private void subscribeHongbaoEvent() {
        Subscription subscription = RxBusManager.getInstance()
                .toObservable(HongbaoEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HongbaoEvent>() {
                    @Override
                    public void call(HongbaoEvent event) {
                        if(CacheManager.getInstance().getToken()!=null)
                        {

                            try {
                                com.insthub.cat.android.utils.DialogUtils.showHongbaoDialog(getActivity()).show();
                            }catch (RuntimeException e)
                            {
                                e.printStackTrace();
                                ToastUtil.show(getContext(),"请打开悬浮窗权限！");
                            }

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
     * 打印机红包
     */
    private void subscribePrintActionEvent() {
        Subscription subscription = RxBusManager.getInstance()
                .toObservable(ActionEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ActionEvent>() {
                    @Override
                    public void call(ActionEvent event) {
                        if(CacheManager.getInstance().getToken()!=null)
                        {

                            try {
                                com.insthub.cat.android.utils.DialogUtils.showPrizeDialog(getActivity(),event.getContent(),event.getTitle()).show();
                            }catch (RuntimeException e)
                            {
                                e.printStackTrace();
                                ToastUtil.show(getContext(),"请打开悬浮窗权限！");
                            }

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






    //退出操作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            ToastUtil.show(getActivity(), "再按一次退出程序，确定退出吗");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);

        } else {


            defaultFinish();
            System.exit(0);
        }
    }


    @Override
    public void showSuccess(Object object) {

        dismissLoadDialog();
        if(object instanceof UserInfoData)
        {
            UserInfoData mUserInfoData = (UserInfoData) object;
            CacheManager.getInstance().setUserInfo(mUserInfoData);
            checkFirstLogin();

        }


        if(object instanceof BindPhoneData)
        {
            BindPhoneData data = (BindPhoneData)object;
            LoginTokenData tokenData =  CacheManager.getInstance().getToken();

            if(tokenData!=null)
            {
                switch (tokenData.getData().getLoginType())
                {
                    case 1:
                        tokenData.getData().setFirst_login(0);
                        break;
                    case 2:
                        tokenData.getData().setBind_phone(1);
                        break;
                }
                CacheManager.getInstance().setToken(tokenData);
                ToastUtil.show(getContext(),data.getMessage());
                mPresenter.getUserInfo(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());
            }
        }

        if(object instanceof VersionData)
        {
            VersionData mVersionData = (VersionData)object;

            int  currentVersion = UIUtil.getVersionCode(getContext());

            int tagVersion = mVersionData.getData().getAndroidversionCode();


            if(currentVersion>=tagVersion)
            {

               // ToastUtil.show(getContext(),"当前版本是最新版本！");
                return ;
            }

            Bundle bundle = new Bundle();
            UpdateAppBean mUpdateApp = new UpdateAppBean();
            mUpdateApp.setApkFileUrl(mVersionData.getData().getAndroidUrl());
            mUpdateApp.setTargetPath(Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM");
            mUpdateApp.setHttpManager(new UpdateAppHttpUtil());
            mUpdateApp.setHideDialog(false);
            mUpdateApp.setUpdateLog(mVersionData.getData().getAndroidversionNote());
            mUpdateApp.setNewVersion(mVersionData.getData().getAndroidversionName());
            mUpdateApp.showIgnoreVersion(false);
            mUpdateApp.dismissNotificationProgress(false);
            mUpdateApp.setOnlyWifi(false);


            if(mVersionData.getData().getAndroidForced().equals("1"))
            {
                mUpdateApp.setConstraint(true);
            }else
            {
                mUpdateApp.setConstraint(false);

            }
            bundle.putSerializable(UpdateAppManager.INTENT_KEY, mUpdateApp);
            UpdateDialogFragment mUpdateDialogFragment = UpdateDialogFragment.newInstance(bundle);
            mUpdateDialogFragment.show(((FragmentActivity) getActivity()).getSupportFragmentManager(), "dialog");
            mUpdateDialogFragment.setUpdateDialogFragmentListener(new IUpdateDialogFragmentListener() {
                @Override
                public void onUpdateNotifyDialogCancel(UpdateAppBean updateApp) {


                }
            });

        }
    }


    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(),msg);
        if(code==10007)
        {
            CacheManager.getInstance().clear();
        }



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }





    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkFirstLogin();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        KLog.i("111111111111111111111111111333333333333");

        if(mHomeFragment!=null)
        {
            mHomeFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
