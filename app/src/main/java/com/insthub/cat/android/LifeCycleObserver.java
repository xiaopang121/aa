package com.insthub.cat.android;

/**
 * Created by linux on 2017/7/5.
 */
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.common.android.fsp.SharedPreferencesUtil;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.ui.activity.MainActivity;

public class LifeCycleObserver {



    private static int mCount;

    private static LifeCycleObserver mLifeCycleObserver;

    private Activity currentAct;


    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String content = (String)msg.obj;
            App.getAppContext().logout(currentAct,content);
        }
    };

    public static LifeCycleObserver init(final App  app)
    {

        if(mLifeCycleObserver==null)
        {
            mLifeCycleObserver= new LifeCycleObserver(app);
        }

        return mLifeCycleObserver;

    }






    public LifeCycleObserver(final App app)
    {
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                if (activity instanceof MainActivity) {
                    if(SharedPreferencesUtil.getInstance().getBoolean(ConstantsKeys.KEY_GESTURE_ENABLE) &&
                            !TextUtils.isEmpty( SharedPreferencesUtil.getInstance().getString(ConstantsKeys.KEY_USER_ID))
                            &&  !TextUtils.isEmpty( SharedPreferencesUtil.getInstance().getString(ConstantsKeys.KEY_GESTURE_PASSWORD)))
                    {
                       /// activity.startActivity(new Intent(activity, GestureOauthLockActivity.class));
                    }

                }


            }

            @Override
            public void onActivityStarted(Activity activity) {
                mCount++;
                app.addActivityStack(activity);
                //如果mCount==1，说明是从后台到前台
                if (mCount == 1  && activity instanceof MainActivity) {
                    if(SharedPreferencesUtil.getInstance().getBoolean(ConstantsKeys.KEY_GESTURE_ENABLE) &&
                            !TextUtils.isEmpty( SharedPreferencesUtil.getInstance().getString(ConstantsKeys.KEY_USER_ID))
                            &&  !TextUtils.isEmpty( SharedPreferencesUtil.getInstance().getString(ConstantsKeys.KEY_GESTURE_PASSWORD)))
                    {
                       // activity.startActivity(new Intent(activity, GestureOauthLockActivity.class));
                    }

                }



            }

            @Override
            public void onActivityResumed( Activity activity) {

                currentAct = activity;




            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                mCount--;
                app.removeActivityStack(activity);
                if(mCount==0)
                {
                }

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }












}
