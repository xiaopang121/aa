package com.insthub.cat.android.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.App;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.GlideCircleTransform;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.LogoutData;
import com.insthub.cat.android.module2.VersionData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.dialog.CommonDialog;
import com.insthub.cat.android.utils.UpdateAppHttpUtil;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateDialogFragment;
import com.vector.update_app.listener.IUpdateDialogFragmentListener;

import butterknife.Bind;


/**
 * 账户信息
 * Created by linux on 2017/6/28.
 */

public class SettingActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {

    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.detail_head)
    RoundedImageView detailHead;
    @Bind(R.id.user_nickname)
    TextView userNickname;
    @Bind(R.id.rll_view_head)
    RelativeLayout rllViewHead;
    @Bind(R.id.rll_modifypwd)
    RelativeLayout rllModifypwd;
    @Bind(R.id.rll_feedback)
    RelativeLayout rllFeedback;
    @Bind(R.id.rll_clearcache)
    RelativeLayout rllClearcache;
    @Bind(R.id.rll_about)
    RelativeLayout rllAbout;
    @Bind(R.id.rll_checkup)
    RelativeLayout rllCheckup;

    @Bind(R.id.bt_exit)
    Button btExit;

    @Bind(R.id.rll_delete_user)
    RelativeLayout rllDeleteUser;
    private boolean isLogout=false;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            dismissLoadDialog();
            bindData();

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting;
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
        commonTitleBar.setTitle(R.string.title_setting);
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



        rllViewHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AccountActivity.class);
            }
        });


        rllModifypwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ModifyPawActivity.class);
            }
        });



        rllFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FeedBackActivity.class);
            }
        });



        rllAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AboutActivity.class);
            }
        });



        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ToastUtil.show(getContext(),"退出成功");
                App.getAppContext().logout();
                finish();

            }
        });


        rllDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLogout=true;
                showLoadDialog("正在注销");
                mPresenter.logout(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken());
            }
        });


        rllClearcache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CommonDialog.Builder commonDialog = new CommonDialog.Builder(getContext());
                commonDialog.setTitle("提示");
                commonDialog.setMessage("确认要清除缓存？");
                commonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        showLoadDialog("正在清除缓存");
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                dismissLoadDialog();
                                ToastUtil.show(getContext(),"缓存清理完成");
                            }
                        },1000);
                    }
                });
                commonDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                commonDialog.create().show();
            }
        });


        rllCheckup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                isLogout=false;
                showLoadDialog("正在检查版本");
                mPresenter.getVersion();

            }
        });

    }




    @Override
    protected void bindData() {
        super.bindData();


        //设置头像
        if (!TextUtils.isEmpty(CacheManager.getInstance().getUserInfo().getData().getHead_image())) {
            RequestOptions requestOptions2 = new RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.ic_default_head)
                    .error(R.drawable.ic_default_head);
            Glide.with(getContext()).asBitmap()
                    .load(CacheManager.getInstance().getUserInfo().getData().getHead_image())
                    .apply(requestOptions2)
                    .into(detailHead);

        } else {
            detailHead.setImageResource(R.drawable.ic_default_head);
        }
//
//
//        userRealname.setText(CacheManager.getInstance().getUserInfo().getData().getName());
//
//        tvNickname.setText(CacheManager.getInstance().getUserInfo().getData().getNickName());
//
//
//        userMobile.setText(CacheManager.getInstance().getUserInfo().getData().getPhone());
//
//        String[] gender = getResources().getStringArray(R.array.gender);
//
//        userGender.setText(CacheManager.getInstance().getUserInfo().getData().getSex());
//
//        String[] usertype = getResources().getStringArray(R.array.user_type);
//
//
//        userType.setText(getString(SharedPreferencesUtil.getInstance().getInt(ConstantsKeys.KEY_CACHE_USER_TYPE, R.string.title_user_type)));
//
//        // userType.setText();

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void showSuccess(Object object) {


        dismissLoadDialog();

        if(object instanceof VersionData)
        {
            VersionData mVersionData = (VersionData)object;

            int  currentVersion = UIUtil.getVersionCode(getContext());

            int tagVersion = mVersionData.getData().getAndroidversionCode();


            if(currentVersion>=tagVersion)
            {

                ToastUtil.show(getContext(),"当前版本是最新版本！");
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
            UpdateDialogFragment  mUpdateDialogFragment = UpdateDialogFragment.newInstance(bundle);
            mUpdateDialogFragment.show(((FragmentActivity) getActivity()).getSupportFragmentManager(), "dialog");
            mUpdateDialogFragment.setUpdateDialogFragmentListener(new IUpdateDialogFragmentListener() {
                @Override
                public void onUpdateNotifyDialogCancel(UpdateAppBean updateApp) {


                }
            });

        }


        if(object instanceof LogoutData)
        {
            ToastUtil.show(getContext(),"注销成功");
            App.getAppContext().logout();
            finish();
        }

    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(), msg);
        if(isLogout)
        {
           startActivity(LoginActivity.class);
        }else
        {

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
