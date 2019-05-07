package com.insthub.cat.android.dataloader;

import android.content.Context;

import com.common.android.fdao.utils.AbDBDaoImpl;
import com.insthub.cat.android.event.XiaojingEvent;
import com.insthub.cat.android.provider.DBConfigHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/11/3 22:35
 * Desc:${DESC}
 */

public class LocalXjMsgLoader {



    /**
     *
     * 保存消息
     * @param city
     * @param context
     */
    public static void save(XiaojingEvent city, Context context) {
        AbDBDaoImpl<XiaojingEvent> mAbDBDaoImpl = new AbDBDaoImpl<XiaojingEvent>(
                DBConfigHelper.getInstance(context), XiaojingEvent.class);
        mAbDBDaoImpl.startWritableDatabase(true);
        if (city._id==0) {
            mAbDBDaoImpl.insert(city,true);
        }else
        {
            mAbDBDaoImpl.update(city);
        }
        mAbDBDaoImpl.closeDatabase();

    }



    /**
     * 查找指定的纪录
     *
     * @param context
     * @return
     */
    public static XiaojingEvent find(String id, Context context) {
        AbDBDaoImpl<XiaojingEvent> mAbDBDaoImpl = new AbDBDaoImpl<XiaojingEvent>(
                DBConfigHelper.getInstance(context), XiaojingEvent.class);
        mAbDBDaoImpl.startWritableDatabase(true);
        XiaojingEvent mFlvItemModel = mAbDBDaoImpl.queryOne("_id",id);
        mAbDBDaoImpl.closeDatabase();
        return mFlvItemModel;
    }

    /**
     * 查找所有的纪录
     *
     * @param context
     * @return
     */
    public static List<XiaojingEvent> findAll(Context context,String uid) {

        List<XiaojingEvent> notifications = new ArrayList<XiaojingEvent>();
        AbDBDaoImpl<XiaojingEvent> mAbDBDaoImpl = new AbDBDaoImpl<XiaojingEvent>(DBConfigHelper.getInstance(context), XiaojingEvent.class);
        mAbDBDaoImpl.startWritableDatabase(true);
        notifications.addAll(mAbDBDaoImpl.queryList("uid='"+uid+"'",null));
        mAbDBDaoImpl.closeDatabase();
        return notifications;

    }


    /**
     * 查询纪录数量
     *
     * @param context
     * @return
     */
    public static int getUnReadCount(Context context,String uid) {

        AbDBDaoImpl<XiaojingEvent> mAbDBDaoImpl = new AbDBDaoImpl<XiaojingEvent>(DBConfigHelper.getInstance(context), XiaojingEvent.class);
        mAbDBDaoImpl.startWritableDatabase(true);
        int count = mAbDBDaoImpl.queryCount("uid='"+uid+"' and state=1",null);
        mAbDBDaoImpl.closeDatabase();
        return count;
    }



    public static void  deleteItem(Context context,int id)
    {
        AbDBDaoImpl<XiaojingEvent> mAbDBDaoImpl = new AbDBDaoImpl<XiaojingEvent>(DBConfigHelper.getInstance(context), XiaojingEvent.class);
        mAbDBDaoImpl.startWritableDatabase(true);
        mAbDBDaoImpl.delete(" _id = '"+id+"'",null);
        mAbDBDaoImpl.closeDatabase();
    }


}
