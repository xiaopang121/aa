package com.common.android.futils;


import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.common.android.fui.widget.ToastView;

/**
 * ToastUtil
 */
public class ToastUtil {
    private static Toast mToast;

    public static void show(Context context,int resId) {
        show(context,context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context,int resId, int duration) {
        show(context,context.getResources().getText(resId), duration);
    }

    public static void show(Context context,CharSequence text) {
        show(context,text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context,CharSequence text, int duration) {
        text = TextUtils.isEmpty(text == null ? "" : text.toString()) ? "请检查您的网络！"
                : text;
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

    public static void show(Context context,int resId, Object... args) {
        show(context,String.format(context.getResources().getString(resId), args),
                Toast.LENGTH_SHORT);
    }

    public static void show(Context context,String format, Object... args) {
        show( context,String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context,int resId, int duration, Object... args) {
        show(context,String.format(context.getResources().getString(resId), args),
                duration);
    }

    public static void show(Context context,String format, int duration, Object... args) {
        show(context,String.format(format, args), duration);
    }


}
