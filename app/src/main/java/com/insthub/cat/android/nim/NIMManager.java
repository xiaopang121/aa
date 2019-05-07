package com.insthub.cat.android.nim;

import android.app.Application;
import android.content.Context;

import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.nim.event.DemoOnlineStateContentProvider;
import com.insthub.cat.android.nim.preference.UserPreferences;
import com.insthub.cat.android.nim.push.DemoMixPushMessageHandler;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.UIKitOptions;
import com.netease.nim.uikit.business.contact.core.query.PinYin;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.mixpush.NIMPushClient;
import com.netease.nimlib.sdk.util.NIMUtil;

/**
 * User:macbook
 * DATE:2018/6/13 00:36
 * Desc:${DESC}
 */

public class NIMManager {


    private  static NIMManager instance;

    private Context app;




    public static  NIMManager init(Application app)
    {
        if(instance==null)
        {
            instance = new NIMManager(app);
        }
        return instance;
    }



    public NIMManager(Context context)
    {
        app = context;
        DemoCache.setContext(app);
        // 4.6.0 开始，第三方推送配置入口改为 SDKOption#mixPushConfig，旧版配置方式依旧支持。
        NIMClient.init(app, getLoginInfo(), NimSDKOptionConfig.getSDKOptions(app));


        // 以下逻辑只在主进程初始化时执行
        if (NIMUtil.isMainProcess(app)) {

            // 注册自定义推送消息处理，这个是可选项
            NIMPushClient.registerMixPushMessageHandler(new DemoMixPushMessageHandler());

            // init pinyin
            PinYin.init(app);
            PinYin.validate();
            // 初始化UIKit模块
            initUIKit();
            // 初始化消息提醒
            NIMClient.toggleNotification(UserPreferences.getNotificationToggle());
            // 云信sdk相关业务初始化
            NIMInitManager.getInstance().init(true);

        }
    }


    private LoginInfo getLoginInfo() {
//        String account = Preferences.getUserAccount();
//        String token = Preferences.getUserToken();
//
//        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(token)) {
//            DemoCache.setAccount(account.toLowerCase());
//            return new LoginInfo(account, token);
//        } else {
//            return null;
//        }

        LoginInfo  info = CacheManager.getInstance().getImLoginInf();


        if(info!=null)
        {
          DemoCache.setAccount(info.getAccount());
        }


        return CacheManager.getInstance().getImLoginInf();
    }


    private void initUIKit() {
        // 初始化
        NimUIKit.init(app, buildUIKitOptions());
//
//        // 设置地理位置提供者。如果需要发送地理位置消息，该参数必须提供。如果不需要，可以忽略。
//        NimUIKit.setLocationProvider(new NimDemoLocationProvider());
//
        // IM 会话窗口的定制初始化。
        SessionHelper.init();
//
        // 聊天室聊天窗口的定制初始化。
 //       ChatRoomSessionHelper.init();
//
        // 通讯录列表定制初始化
  //      ContactHelper.init();
//
//        // 添加自定义推送文案以及选项，请开发者在各端（Android、IOS、PC、Web）消息发送时保持一致，以免出现通知不一致的情况
//        NimUIKit.setCustomPushContentProvider(new DemoPushContentProvider());
//
        NimUIKit.setOnlineStateContentProvider(new DemoOnlineStateContentProvider());
    }


    private UIKitOptions buildUIKitOptions() {
        UIKitOptions options = new UIKitOptions();
        // 设置app图片/音频/日志等缓存目录
        options.appCacheDir = NimSDKOptionConfig.getAppCacheDir(app) + "/app";
        return options;
    }




    public void login()
    {


    }


}
