package com.insthub.cat.android.provider;

import android.content.Context;
import com.common.android.fdao.utils.AbDBHelper;
import com.insthub.cat.android.event.XiaojingEvent;

/**
 * 数据库配置
 */
public class DBConfigHelper extends AbDBHelper {

    private static final String DBNAME = "sys.db";
    // 当前数据库的版本
    private static final int DBVERSION = 1;
    // 要初始化的表
    private static final Class<?>[] clazz = {XiaojingEvent.class};

    public static DBConfigHelper mDBInsideHelper;

    public Context mContext;

    public DBConfigHelper(Context context) {
        super(context, DBNAME, null, DBVERSION, clazz);
        mContext = context;
    }


    public static DBConfigHelper getInstance(Context context)
    {

        if(mDBInsideHelper==null)
        {
            mDBInsideHelper = new DBConfigHelper(context);
        }
        return mDBInsideHelper;
    }



}
