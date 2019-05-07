package com.common.android.futils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 *
 */
public class IntentUtils {

    public static Intent startUriLink(String link) {
        Uri uri = Uri.parse(link);
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public static boolean checkResolveIntent(Activity activity, Intent intent) {
        //只有当检查出能够接受intent的对象不为空 返回true
        return intent.resolveActivity(activity.getPackageManager()) != null;
    }

    //Android获取一个用于打开图片文件的intent
    public static Intent startImageFile(File file,String type) {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, type);
        return intent;
    }


    //打电话
    public static  void call(Context context , String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
