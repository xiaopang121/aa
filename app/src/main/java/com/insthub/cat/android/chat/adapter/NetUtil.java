package com.insthub.cat.android.chat.adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.packet.RosterPacket.ItemType;
import org.jivesoftware.smack.sasl.SASLErrorException;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.search.ReportedData;
import org.jivesoftware.smackx.search.UserSearchManager;
import org.jivesoftware.smackx.xdata.Form;

import com.insthub.cat.android.App;
import com.insthub.cat.android.chat.bean.ChatRecord;
import com.insthub.cat.android.chat.constant.Constant;
import com.insthub.cat.android.chat.constant.User;
import com.insthub.cat.android.chat.utils.threaad.ThreadPoolUtil;
import com.insthub.cat.android.chat.utils.threaad.UserInfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * 请求工具类
 * 
 * @ClassName: NetUtil
 * @author yeliangliang
 * @date 2015-7-22 下午4:22:14
 */
public class NetUtil {

	/**
	 * 判断网络是否连接
	 * 
	 * @return
	 * @author yeliangliang
	 * @date 2015-7-24 下午5:00:30
	 * @version V1.0
	 * @return boolean
	 */
	public static boolean isNetConnection() {

		ConnectivityManager mConnectivityManager = (ConnectivityManager) App.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
		if (networkInfo != null) {
			return true;
		}
		return false;

	}

	/**
	 * 登录请求
	 * 
	 * @param context
	 * @param passName
	 *            账号
	 * @param passWord
	 *            密码
	 * @return
	 * @author yeliangliang
	 * @date 2015-7-22 下午5:28:15
	 * @version V1.0
	 * @return boolean
	 */
	public static void requestLogin(final Context context, final String passName,
			final String passWord, final NetCallBackListener callBackListener) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					// 设置账号、密码、IP、服务器名称、端口
					// 关闭安全验证
					User user = User.getInstance();
					XMPPTCPConnectionConfiguration config = user.getConfiguration(passName,
							passWord);
					AbstractXMPPConnection conn2 = user.setConnection(config);
					conn2.connect();
					Log.e("NetUtil.java", "连接服务器成功");
					conn2.login();
			
					Log.e("NetUtil.java", "登录成功");
					// 创建用户对象,保存用户信息
					UserInfo userInfo = new UserInfo(passName + "@" + Constant.SERVICE_NAME,
							passWord, passName);
					user.setUserInfo(userInfo);
					SharedPreferences sp = context.getSharedPreferences("userInfo",
							context.MODE_PRIVATE);
					sp.edit().putString("passWord", "123456").putString("passName", "18305142009")
							.commit();
					callBackListener.responseSuccess(Constant.REQUEST_SUCCESS, null);
					return;
				} catch (SASLErrorException e) {
					e.printStackTrace();
					Log.e("NetUtil.java", "用户名密码错误");
					callBackListener.responseFailed(Constant.LOGIN_PASSWORD_ERROR);
				} catch (SmackException e) {
					e.printStackTrace();
					Log.e("NetUtil.java", "登录失败");
					callBackListener.responseFailed(Constant.REQUEST_FAILD);
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("NetUtil.java", "登录失败");
					callBackListener.responseFailed(Constant.REQUEST_FAILD);
				} catch (XMPPException e) {
					e.printStackTrace();
					Log.e("NetUtil.java", "登录失败");
					callBackListener.responseFailed(Constant.REQUEST_FAILD);
				} catch(RuntimeException e)
				{
					callBackListener.responseFailed(Constant.REQUEST_FAILD);
				} 
			}
		};
		// 将任务添加进线程池
		ThreadPoolUtil.insertTaskToSinglePool(runnable);
	}

	/**
	 * 请求好友列表
	 * 
	 * @param context
	 * @param callBackListener
	 * @author yeliangliang
	 * @date 2015-7-27 下午2:10:50
	 * @version V1.0
	 * @return void
	 */
	public static void requestRosterList(final Context context,
			final NetCallBackListener callBackListener) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				User user = User.getInstance();
				AbstractXMPPConnection connection = user.getConnection();
				if (connection == null) {
					callBackListener.responseFailed(Constant.REQUEST_FAILD);
					Log.e("NetUtil.java", "连接器为空");
					return;
				}
				Roster roster = Roster.getInstanceFor(connection);
				Collection<RosterEntry> entries = roster.getEntries();
				if (entries == null) {
					callBackListener.responseFailed(Constant.REQUEST_FAILD);
					Log.e("NetUtil.java", "好友列表返回为空，请求失败");
					return;
				}
				List<RosterEntry> list = new ArrayList<RosterEntry>();
				for (RosterEntry entry : entries) {
					//只有双方都是好友才显示
//					if (entry.getType()==ItemType.both) {
						list.add(entry);
//					}
				}
				
				callBackListener.responseSuccess(Constant.REQUEST_SUCCESS, list);
			}
		};
		ThreadPoolUtil.insertTaskToCatchPool(runnable);
	}

	/**
	 * 发送消息
	 * 
	 * @param context
	 *            对方的用户名
	 * @param chatMessage
	 * @param callBackListener
	 * @author yeliangliang
	 * @date 2015-8-3 下午3:11:17
	 * @version V1.0
	 * @return void
	 */
	public static void postMessage(final Context context, final ChatRecord chatMessage,
			final Chat chat, final NetCallBackListener callBackListener) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					chat.sendMessage(chatMessage.getContent());
					callBackListener.responseSuccess(Constant.REQUEST_SUCCESS, null);
				} catch (NotConnectedException e) {
					callBackListener.responseFailed(Constant.REQUEST_FAILD);
					e.printStackTrace();
				}
			}
		};
		ThreadPoolUtil.insertTaskToCatchPool(runnable);
	}

	/**
	 * 搜索好友
	 * 
	 * @author yeliangliang
	 * @date 2015-8-26 下午11:03:42
	 * @version V1.0
	 * @return void
	 */
	public static void searchFriends(final Context context, final String keyStr,
			final NetCallBackListener listener) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				UserSearchManager search = new UserSearchManager(User.getInstance().getConnection());
				ReportedData data = null;
				try {
					Form searchForm = search.getSearchForm("search."
							+ User.getInstance().getConnection().getServiceName());

					Form answerForm = searchForm.createAnswerForm();
					answerForm.setAnswer("Username", true);
					answerForm.setAnswer("Name", true);
					answerForm.setAnswer("search", keyStr);
					data = search.getSearchResults(answerForm, "search."
							+ User.getInstance().getConnection().getServiceName());
				} catch (Exception e) {
					listener.responseError(Constant.REQUEST_ERROR);
					e.printStackTrace();
				}
				if (data == null) {
					listener.responseFailed(Constant.REQUEST_FAILD);
					return;
				}
				listener.responseSuccess(Constant.REQUEST_SUCCESS, data.getRows());
			}
		};
		ThreadPoolUtil.insertTaskToCatchPool(runnable);
	}

	/**
	 * 添加好友
	 * 
	 * @author yeliangliang
	 * @date 2015-8-26 下午11:13:34
	 * @version V1.0
	 * @return void
	 */
	public static void addFriends(final Context context, final String name,
			final NetCallBackListener listener) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					// 添加好友
					Roster roster = Roster.getInstanceFor(User.getInstance().getConnection());
					roster.createEntry(name + "@" + Constant.SERVICE_NAME, name,
							new String[] { "Friends" });
					listener.responseSuccess(Constant.REQUEST_SUCCESS, null);
				} catch (Exception e) {
					listener.responseFailed(Constant.REQUEST_FAILD);
					e.printStackTrace();
				}
			}
		};
		ThreadPoolUtil.insertTaskToCatchPool(runnable);
	}

	/**
	 * 删除好友
	 * 
	 * @author yeliangliang
	 * @date 2015-8-26 下午11:24:17
	 * @version V1.0
	 * @return void
	 */
	public static void deleteFriend(final Context context, final String passName,
			final NetCallBackListener listener) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Roster roster = Roster.getInstanceFor(User.getInstance().getConnection());
				try {
					roster.removeEntry(roster.getEntry(passName));
				} catch (Exception e) {
					listener.responseFailed(Constant.REQUEST_FAILD);
					e.printStackTrace();
					return;
				}
				listener.responseSuccess(Constant.REQUEST_SUCCESS, null);
			}
		};
		ThreadPoolUtil.insertTaskToCatchPool(runnable);
	}
	
	
	
	public static void disConnection()
	{
         if(User.getInstance().getConnection()!=null)
         {
        	 User.getInstance().getConnection().disconnect();
         }
	}
}
