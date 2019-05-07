package com.insthub.cat.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.common.android.flog.CrashHandler;
import com.common.android.flog.KLog;
import com.common.android.fsp.SharedPreferencesUtil;
import com.common.android.fui.app.ActiveApplicaton;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.event.AccountExceptionEvent;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.manager.BaiduPush;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.manager.DBManager;
import com.insthub.cat.android.manager.JPushManager;
import com.insthub.cat.android.manager.ShareManager;
import com.insthub.cat.android.manager.theme.ThemeManager;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.nim.NIMManager;
import com.insthub.cat.android.provider.DBConfigHelper;
import com.insthub.cat.android.ui.activity.MainActivity;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;

import java.util.ArrayList;


/**
 * 开发者 nriet
 * 2016-10-9 9:40
 * com.insthub.qhqxjc.android.ui
 */
public class App extends ActiveApplicaton {


    public static App _app;
    public static Context mContext;

    //当前用户信息
    private UserInfoData mUserInfoData;


    private ArrayList<Activity> actStack = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        _app = this;
        mContext = getApplicationContext();

        //初始化主题
        ThemeManager.getInstance();
        //初始化日志
        KLog.init(BuildConfig.LOG_DEBUG,"KLOG");
        //初始化系统参数配置
        SharedPreferencesUtil.init(this,"sys_prefs", Activity.MODE_PRIVATE);
        //初始化Crash
         CrashHandler.getInstance().init(this);
        //注册生命周期管理
        LifeCycleObserver.init(this);
        //初始化缓存
        CacheManager.init(this);
        //初始化数据库
        DBConfigHelper.getInstance(this);
        //7.0 权限问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        //初始化百度推送
        JPushManager.init(this);

        BDLocationManager.init(this);

        //分享
        ShareManager.init(this);


        new DBManager(this).copyDBFile();

        //网易云信
        NIMManager.init(this);

    }



    public static App getAppContext()
    {
        return _app;
    }


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    /**
     * 添加activity
     * @param act
     */
    public void addActivityStack(Activity act )
    {
        actStack.add(act);
    }


    public ArrayList<Activity>  getActivityStackList()
    {
        return actStack;
    }



    /**
     * 删除
     * @param act
     */
    public void removeActivityStack(Activity act)
    {

        if(actStack.contains(act))
        {
            actStack.remove(act);
        }
    }



    public void finish()
    {
        for(Activity act :actStack)
        {
            act.finish();
        }
    }


    public void logout()
    {
        SharedPreferencesUtil.getInstance().putString(ConstantsKeys.KEY_USER_ID,"");
        SharedPreferencesUtil.getInstance().putString(ConstantsKeys.KEY_GESTURE_PASSWORD,"");
        SharedPreferencesUtil.getInstance().putBoolean(ConstantsKeys.KEY_GESTURE_ENABLE,false);
        CacheManager.getInstance().clear();
        NIMClient.getService(AuthService.class).logout();
        // finish();
    }


    public boolean logout(Activity activity,String content)
    {

        SharedPreferencesUtil.getInstance().putString(ConstantsKeys.KEY_USER_ID,"");
        SharedPreferencesUtil.getInstance().putString(ConstantsKeys.KEY_GESTURE_PASSWORD,"");
        SharedPreferencesUtil.getInstance().putBoolean(ConstantsKeys.KEY_GESTURE_ENABLE,false);
        CacheManager.getInstance().clear();
        RxBusManager.getInstance().post(new AccountExceptionEvent());
        ToastUtil.show(activity,content);

        if(activity!=null && !(activity instanceof MainActivity))
        {
            Intent intent = new Intent(activity, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.overridePendingTransition(com.common.android.R.anim.push_left_in, com.common.android.R.anim.push_left_out);
            activity.finish();
         }

        //com.insthub.ship.android.utils.DialogUtils.showOutLineDialog(App.getAppContext());
        return false;
    }

}
