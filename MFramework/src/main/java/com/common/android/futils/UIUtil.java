package com.common.android.futils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.UUID;


public class UIUtil {


    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        uniqueId = uniqueId.replace("-", "");
        return uniqueId;
    }


    /**
     * dp转换PX
     * @param res
     * @param dp
     * @return
     */
    public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }




    /**
     * 根据分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获得屏幕尺寸
     * @param context
     * @return
     */
    public static Point getScreenSize(Context context) {
        Point point = new Point();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getSize(point);
        return point;
    }


    /**
     * 获取应用版本号
     *
     * @param context context
     * @return 版本号
     */
    public static String getVersion(Context context) {
        String version="";
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return version;
    }


    /**
     * 获取应用版本号
     *
     * @param context context
     * @return 版本号
     */
    public static int  getVersionCode(Context context) {
        int  version=0;
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return version;
    }



    /**
     * 隐藏软键盘
     */
    public static void hideKeyboard(Activity c) {
        try {
            InputMethodManager imm = (InputMethodManager) c
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(c.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {
        }
    }

}
