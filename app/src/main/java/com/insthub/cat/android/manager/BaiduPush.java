package com.insthub.cat.android.manager;

import android.app.Application;

/**
 * Created by linux on 2017/9/8.
 */

public class BaiduPush {


    public static void init(Application app)
    {
//        PushManager.startWork(app, PushConstants.LOGIN_TYPE_API_KEY,
//                StrUtil.getMetaValue(app, "api_key"));
//
//
//
//        // 1.默认通知
//        // 若您的应用需要适配Android O（8.x）系统，且将目标版本targetSdkVersion设置为26及以上时：
//        // SDK提供设置Android O（8.x）新特性---通知渠道的设置接口。
//        // 若不额外设置，SDK将使用渠道名默认值"Push"；您也可以仿照以下3行代码自定义channelId/channelName。
//        // 注：非targetSdkVersion 26的应用无需以下调用且不会生效
//        BasicPushNotificationBuilder bBuilder = new BasicPushNotificationBuilder();
//        bBuilder.setChannelId("testDefaultChannelId");
//        bBuilder.setChannelName("testDefaultChannelName");
//        // PushManager.setDefaultNotificationBuilder(this, bBuilder); //使自定义channel生效
//
//        // 2.自定义通知
//        // 设置自定义的通知样式，具体API介绍见用户手册
//        // 请在通知推送界面中，高级设置->通知栏样式->自定义样式，选中并且填写值：1，
//        // 与下方代码中 PushManager.setNotificationBuilder(this, 1, cBuilder)中的第二个参数对应
//        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
//                R.layout.notification_custom_builder,
//                R.id.notification_icon,
//                R.id.notification_title,
//                R.id.notification_text);
//
//        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
//        cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
//        cBuilder.setStatusbarIcon(app.getApplicationInfo().icon);
//        cBuilder.setLayoutDrawable(R.drawable.ic_launcher);
//        cBuilder.setNotificationSound(Uri.withAppendedPath(
//                MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6").toString());
//        // 若您的应用需要适配Android O（8.x）系统，且将目标版本targetSdkVersion设置为26及以上时：
//        // 可自定义channelId/channelName, 若不设置则使用默认值"Push"；
//        // 注：非targetSdkVersion 26的应用无需以下2行调用且不会生效
////        cBuilder.setChannelId("testId");
////        cBuilder.setChannelName("testName");
//        // 推送高级设置，通知栏样式设置为下面的ID，ID应与server下发字段notification_builder_id值保持一致
//        PushManager.setNotificationBuilder(app, 1, cBuilder);
    }

}
