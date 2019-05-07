package com.insthub.cat.android.utils;/**
 * Created by 011 on 2017/7/5.
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * project name : Product_JLD
 * description : 工具类
 * created by LHB at 2017/7/5
 */
public class UpdataAppUtils {


    //获取显示版本
    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //获取版本信息
    public static int getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

//
//    /**
//     * 升级提示框
//     */
//    public static void showUpdateDialog(final Context context, final VersionData msg) {
//
//        CommonDialog.Builder mDialog = new CommonDialog.Builder(context);
//
//        mDialog.setTitle("检测到新版本");
//        mDialog.setMessage(msg.getData().getUpdate_desc());
//        mDialog.setGravity(Gravity.LEFT, true);
//        mDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//
//                if (msg.getData().getLowest_version() != null ) {
//                    String version = UIUtil.getVersion(context);
//                    String service = msg.getData().getLowest_version();
//                    Float localVersion = Float.valueOf(version);
//                    Float serviceVersion = Float.valueOf(service);
//                    if (localVersion < serviceVersion) {
//                        System.exit(0);
//
//                    }
//                }
//
//                dialogInterface.dismiss();
//            }
//        });
//
//        mDialog.setPositiveButton("更新", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//                ToastUtil.show(context, "通知栏正在更新版本！");
//                //调用系统管理器
//                Intent intent = new Intent(context, DownloadService.class);
//                intent.putExtra("url", msg.getData().getDownload_url());
//                //intent.putExtra("url", "http://www.apk3.com/uploads/soft/guiguangbao/UCllq.apk");
//                context.startService(intent);
//
//
//            }
//        });
//
//        mDialog.create().show();
//
//    }
}
