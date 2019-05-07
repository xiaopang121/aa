package com.insthub.cat.android.chat.database;

import java.util.ArrayList;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.insthub.cat.android.chat.bean.ChatRecord;
import com.insthub.cat.android.chat.constant.User;

/**
 * 数据库工具类
 * 
 * @ClassName: DataBaseHelperUtil
 * @author yeliangliang
 * @date 2015-8-5 上午10:48:03
 */

public class DataBaseHelperUtil extends SQLiteOpenHelper {

	private static Context mContext;
	private static DataBaseHelperUtil mInstance;
	private static  String DB_NAME;// 数据库名称
	private static final int DB_VERSION = 1;// 数据库版本
	public static final String TABLE_NAME_CHAT_RECORD = "ChatRecord";// 聊天记录主库
	public static final String TABLE_NAME_CHAT_NEW_RECORD = "NewChatRecord";// 最近聊天记录
	private int openCount = 0;// 数据库打开次数
	private SQLiteDatabase database;

	/**
	 * 单例
	 * 
	 * @param context
	 * @return
	 * @author yeliangliang
	 * @date 2015-8-5 上午10:53:07
	 * @version V1.0
	 * @return DataBaseHelperUtil
	 */
	public synchronized static DataBaseHelperUtil getInstance(Context context) {
		mContext = context;
		if (mInstance == null) {
			DB_NAME = "ChatRecord"
					+ User.getInstance().getUserInfo().getUserPassName() + ".db";
			mInstance = new DataBaseHelperUtil(mContext, DB_NAME, null, DB_VERSION);
		}
		return mInstance;
	}

	public DataBaseHelperUtil(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建表
		creatTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// pass
	}

	/**
	 * 打开数据库，这种方式可以防止并发操作引起crash
	 * 
	 * @return
	 * @author yeliangliang
	 * @date 2015-8-5 上午11:01:42
	 * @version V1.0
	 * @return SQLiteDatabase
	 */
	public synchronized SQLiteDatabase openDataBase() {
		if (database == null) {
			database = mInstance.getWritableDatabase();
		}
		openCount++;
		return database;
	}

	/**
	 * 关闭数据库，这种方式可以防止并发操作引起crash
	 * 
	 * @author yeliangliang
	 * @date 2015-8-5 上午11:02:53
	 * @version V1.0
	 * @return void
	 */
	public synchronized void closeDataBase() {
		if (openCount == 0) {
			database.close();
		} else {
			openCount--;
		}
	}

	/**
	 * 创建表
	 * 
	 * @author yeliangliang
	 * @date 2015-8-5 上午11:07:28
	 * @version V1.0
	 * @return void
	 */
	public synchronized void creatTable(SQLiteDatabase db) {
		db.execSQL("drop table if exists " + TABLE_NAME_CHAT_RECORD);// 如果存在先干掉
		db.execSQL("drop table if exists " + TABLE_NAME_CHAT_NEW_RECORD);
		db.execSQL("create table "
				+ TABLE_NAME_CHAT_RECORD
				+ " (_id integer primary key,name char,passName char,time char,content char,imgPath char,staues char,source char)");
		db.execSQL("create table "
				+ TABLE_NAME_CHAT_NEW_RECORD
				+ " (_id integer primary key,name char,passName char,time char,content char,imgPath char,staues char,source char)");
	}

	/**
	 * 插入一条数据
	 * 
	 * @param chatRecord
	 * @author yeliangliang
	 * @date 2015-8-5 上午11:19:13
	 * @version V1.0
	 * @return void
	 */
	public synchronized void insertToTable(String tableName, ChatRecord chatRecord) {
		database.execSQL(
				"insert into "
						+ tableName
						+ " (_id,name,passName,time,content,imgPath,staues,source) values(?,?,?,?,?,?,?,?)",
				new Object[] { chatRecord.getId(), chatRecord.getName(), chatRecord.getPassName(),
						chatRecord.getTime(), chatRecord.getContent(), chatRecord.getImgPath(),
						chatRecord.getStaues(), chatRecord.getSource() });
	}

	/**
	 * 最近联系：插入最近的一条记录
	 * 

	 * @author yeliangliang
	 * @date 2015-8-5 上午11:31:34
	 * @version V1.0
	 * @return void
	 */
	public synchronized void insertNewRecord(ChatRecord chatRecord) {
		Cursor cursor = database.rawQuery("select * from " + TABLE_NAME_CHAT_NEW_RECORD
				+ " where name = ? ", new String[] { chatRecord.getName() });
		if (cursor.moveToNext()) {
			// 存在数据 修改时间和内容、来源即可
			database.execSQL(
					"update " + TABLE_NAME_CHAT_NEW_RECORD
							+ " set time= ? , content = ? , source = ?,staues = ? where name = '"
							+ chatRecord.getName() + "'",
					new Object[] { chatRecord.getTime(), chatRecord.getContent(),
							chatRecord.getSource(), chatRecord.getStaues() });
		} else {
			// 不存在，新创建
			database.execSQL(
					"insert into "
							+ TABLE_NAME_CHAT_NEW_RECORD
							+ " (_id,name,passName,time,content,imgPath,staues,source) values(?,?,?,?,?,?,?,?)",
					new Object[] { chatRecord.getId(), chatRecord.getName(),
							chatRecord.getPassName(), chatRecord.getTime(),
							chatRecord.getContent(), chatRecord.getImgPath(),
							chatRecord.getStaues(), chatRecord.getSource() });
		}
		if (cursor != null) {
			cursor.close();
		}
	}

	/**
	 * 修改消息未读状态为已读
	 * 
	 * @author yeliangliang
	 * @date 2015年9月11日 下午6:16:00
	 * @version V1.0
	 * @return void
	 */
	public synchronized void modifyNewRecordStaues(String name) {
		database.execSQL("update " + TABLE_NAME_CHAT_NEW_RECORD + " set staues = ? where name = '"
				+ name + "'", new Object[] { "0" });
	}

	/**
	 * 最近联系： 获取最近的一条记录
	 *
	 * @author yeliangliang
	 * @date 2015-8-5 上午11:31:34
	 * @version V1.0
	 * @return void
	 */
	public synchronized ArrayList<ChatRecord> searchNewRecord() {
		ArrayList<ChatRecord> list = new ArrayList<ChatRecord>();
		Cursor cursor = database.rawQuery("select * from " + TABLE_NAME_CHAT_NEW_RECORD
				+ " order by time desc", null);
		while (cursor != null && cursor.moveToNext()) {
			list.add(new ChatRecord(cursor));
		}
		if (cursor != null) {
			cursor.close();
		}
		return list;
	}

	/**
	 * 获取当前账户人的聊天记录
	 * 
	 * @param passName
	 * @return
	 * @author yeliangliang
	 * @date 2015-8-6 下午6:19:15
	 * @version V1.0
	 * @return ArrayList<ChatRecord>
	 */
	public synchronized ArrayList<ChatRecord> searchNowChatRecord(String passName) {
		ArrayList<ChatRecord> list = new ArrayList<ChatRecord>();
		Cursor cursor = database.rawQuery("select * from " + TABLE_NAME_CHAT_RECORD
				+ " where passName = ?", new String[] { passName });
		while (cursor != null && cursor.moveToNext()) {
			list.add(new ChatRecord(cursor));
		}
		if (cursor != null) {
			cursor.close();
		}
		return list;

	}

	/**
	 * 更具用户名删除聊天记录
	 * 
	 * @param name
	 * @author yeliangliang
	 * @date 2015-8-21 下午6:05:11
	 * @version V1.0
	 * @return void
	 */
	public synchronized void deleteRecordByName(String name) {
		database.execSQL("delete from " + TABLE_NAME_CHAT_NEW_RECORD + " where name = '" + name
				+ "'");
		database.execSQL("delete from " + TABLE_NAME_CHAT_RECORD + " where name = '" + name + "'");
	}

}
