package com.insthub.cat.android.manager;

import android.app.Application;

import com.insthub.cat.android.constant.Constants;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by linux on 2017/9/8.
 */

public class ShareManager {


    public static void init(Application app)
    {

//初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(app, "5ae2dd7ff29d987ba20001a4", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "");

        PlatformConfig.setWeixin(Constants.WX_APPID, Constants.WX_APPSECRET);
        PlatformConfig.setSinaWeibo("760006847", "90c526cb7b3ea6b5f58fa1fea17a3001", "http://sns.whalecloud.com/");
        PlatformConfig.setQQZone("1106975596", "z7YYwBusOoMiqSJG");

    }

}
