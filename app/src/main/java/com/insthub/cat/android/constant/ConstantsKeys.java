package com.insthub.cat.android.constant;

import android.support.annotation.StringDef;

import com.insthub.cat.android.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

/**
 * 键定义
 */

public class ConstantsKeys {


    /**
     * 定义用户性别 注解的方式，是对枚举的优化
     */
    @StringDef({Gender.MALE, Gender.FEMALE, Gender.OTHER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gender {
        String MALE = "male";
        String FEMALE = "female";
        String OTHER = "press";

    }

    //是否第一使用
    public static final String KEY_FIRST_USE="is_first_user";

    public static final String KEY_TOKEN="token";


    public static final String KEY_JUM_ACTIVITY_TICK_LIST="JUMP_MESSAGE_TICK_LIST";

    public static final String KEY_JUM_ACTIVITY_MSG_LIST="JUMP_MESSAGE_LIST";

    public static final String KEY_JUM_ACTIVITY="JUMP_URL";

    //跳转到 我的钱包
    public static final String KEY_JUM_WALET_ACTIVITY="JUMP_WALET";

    //IM info
    public static final String KEY_IM_LOGININF="IM_INFO";

    //是否开启手势密码
    public static final String KEY_GESTURE_ENABLE="gestrue_enable";
    //手势密码
    public static final String KEY_GESTURE_PASSWORD="gestrue_password";
    //手势间隔时间
    public static final String KEY_GESTURE_INTERVAL_TIME="gestrue_interval_time";

    //用户ID
    public static final String KEY_USER_ID="uid";
    //用户信息
    public static final String KEY_USER_INFO="user_info";

    //用户资金信息
    public static final String KEY_USER_MONEY="user_money";

    //通用传参数键
    public static final String KEY_DATA="DATA";

    //通用传参数键
    public static final String KEY_DATA2="DATA2";

    //百度推送消息
    public static final String KEY_PUSH_MESSAGE="MESSAGE";
    //通用传参数键
    public static final String KEY_FROM="FROM";


    //BANNERDATA
    public static final String KEY_CACHE_BANNERS="BANNER_DATA";


    public static final String KEY_DATA_STORE_ID="STORE_ID";
    public static final String KEY_DATA_SERVICE_ID="SERVICE_ID";
    public static final String KEY_DATA_JJ_TENDER_ID="JJ_TENDER_ID";
    public static final String KEY_DATA_ZB_TENDER_ID="ZB_TENDER_ID";
    public static final String KEY_DATA_ZJ_TENDER_ID="ZJ_TENDER_ID";




    //地图类型
    public static final String KEY_CACHE_MAP_TYPE="MAP_TYPE";
    //地图类型初始化
    public static final String KEY_CACHE_MAP_INIT="MAP_INIT";
    //TOKEN
    public static final String KEY_CACHE_TOKEN="TOKEN";

    //TOKEN
    public static final String KEY_CACHE_SEX="SEX";

    //TOKEN
    public static final String KEY_CACHE_USER_TYPE="TYPE";

    public static HashMap<String,Integer> errorMap = new HashMap<>();


     static
    {

        errorMap.put("-1", R.string.error_00000);
        errorMap.put("999", R.string.error_999);
        errorMap.put("900", R.string.error_900);
        errorMap.put("403", R.string.error_403);
        errorMap.put("0", R.string.error_0);
        errorMap.put("201", R.string.error_201);
        errorMap.put("202", R.string.error_202);
        errorMap.put("203", R.string.error_203);
        errorMap.put("204", R.string.error_204);
        errorMap.put("210", R.string.error_210);
        errorMap.put("1001", R.string.error_1001);
        errorMap.put("1002", R.string.error_1002);
        errorMap.put("1003", R.string.error_1003);
        errorMap.put("1004", R.string.error_1004);
        errorMap.put("10005", R.string.error_10005);
        errorMap.put("10006", R.string.error_10006);
        errorMap.put("10007", R.string.error_10007);
    }



    public static int getError(int code)
    {
        return errorMap.get(String.valueOf(code));
    }




}
