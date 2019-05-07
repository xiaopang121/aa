package com.insthub.cat.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.common.android.flog.KLog;
import com.common.android.fui.rxanroid.RxBusManager;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.dataloader.LocalXjMsgLoader;
import com.insthub.cat.android.event.ActionEvent;
import com.insthub.cat.android.event.ActiveUserEvent;
import com.insthub.cat.android.event.HongbaoEvent;
import com.insthub.cat.android.event.XiaojingEvent;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.manager.JPushManager;
import com.insthub.cat.android.module2.AdviserData;
import com.insthub.cat.android.ui.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JIGUANG-Example";

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Bundle bundle = intent.getExtras();
		//	Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));


			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				KLog.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
				JPushManager.getInstance().setRegisterId(regId);

			} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
				Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
				processCustomMessage(context, bundle);

			} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
				KLog.d(TAG, "[MyReceiver] 接收到推送下来的通知");
				int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
				KLog.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
				processCustomMessage(context, bundle);


			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				KLog.d(TAG, "[MyReceiver] 用户点击打开了通知");

//				Intent i = new Intent(context, MainActivity.class);
//				i.putExtras(bundle);
//				i.putExtra(ConstantsKeys.KEY_JUM_ACTIVITY, 1000);
//				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				context.startActivity(i);
//


				String data = processAdviserMessage(context,bundle,intent);



			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
				KLog.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
				//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

			} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
				boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
				KLog.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
			} else {
				KLog.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
			}
		} catch (Exception e){

		}

	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
					KLog.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					KLog.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}
	
	//send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {

		if(bundle==null)
		{
			return;
		}
		String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
		String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);

		if (!TextUtils.isEmpty(extras)) {
			try {
				JSONObject extraJson = new JSONObject(extras);

				if(CacheManager.getInstance().getToken()==null)
				{
                   return;
				}

				if(extraJson.has("type"))
				{
					int  type = extraJson.getInt("type");
					KLog.i("type:"+type);
					if(type==0) //小鲸捕手
					{

						String phone = extraJson.getString("phone");
						String content = extraJson.getString("content");

						String im_code = extraJson.getString("im_code");
						String image = extraJson.getString("image");
						String service_name = extraJson.getString("service_name");
						String details="";
						String key="";



						if(extraJson.has("details"))
						{
							details = extraJson.getString("details");
						}

						if(extraJson.has("keywords"))
						{
							key = extraJson.getString("keywords");
						}



						LocalXjMsgLoader.save(new XiaojingEvent(CacheManager.getInstance().getToken().getData().getUser_id(),phone,content,key,im_code,service_name,details,image),context);
						RxBusManager.getInstance().post(new XiaojingEvent());
					}

					if(type==1) //红包
					{

						RxBusManager.getInstance().post(new HongbaoEvent());
					}


					if(type==4) //
					{
						String   head_image = extraJson.getString("head_image");
						String   cotnent = extraJson.getString("content");
						RxBusManager.getInstance().post(new ActiveUserEvent(head_image,cotnent));
					}





				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (RuntimeException e)
			{
				e.printStackTrace();
			}

		}
	}


	private String processAdviserMessage(Context context, Bundle bundle,Intent intent) {

		if(bundle==null)
		{
			return "";
		}


		String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
		String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
		if (!TextUtils.isEmpty(extras)) {
			try {
				JSONObject extraJson = new JSONObject(extras);

				if(extraJson.has("type"))
				{
					int  type = extraJson.getInt("type");
					KLog.i("type:"+type);
					if(type==0) //小鲸捕手
					{

						Bundle Bundle2 = new Bundle();
						Bundle2.putString(ConstantsKeys.KEY_JUM_ACTIVITY_MSG_LIST,"");
						intent.putExtras(Bundle2);
						intent.setClass(context.getApplicationContext(), MainActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					}

					if(type==1) //红包
					{

						RxBusManager.getInstance().post(new HongbaoEvent());
					}


					if(type==2) //小鲸捕手
					{

						Bundle Bundle2 = new Bundle();
						Bundle2.putString(ConstantsKeys.KEY_JUM_ACTIVITY_TICK_LIST,"");
						intent.putExtras(Bundle2);
						intent.setClass(context.getApplicationContext(), MainActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					}


					if(type==3) //小鲸捕手
					{

						String url = extraJson.getString("url");
						if(!TextUtils.isEmpty(url))
						{
							Bundle Bundle2 = new Bundle();
							Bundle2.putString(ConstantsKeys.KEY_JUM_ACTIVITY,url);
							intent.putExtras(Bundle2);
						}

						intent.setClass(context.getApplicationContext(), MainActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					}


					if(type==4) //
					{
						String   head_image = extraJson.getString("head_image");
						String   cotnent = extraJson.getString("content");
						RxBusManager.getInstance().post(new ActiveUserEvent(head_image,cotnent));
					}

					if(type==7) //打印机活动
					{
//						String   title = extraJson.getString("title");
						String   cotnent = extraJson.getString("content");
//						RxBusManager.getInstance().post(new ActionEvent(title,cotnent));


						Bundle Bundle2 = new Bundle();
						Bundle2.putString(ConstantsKeys.KEY_JUM_WALET_ACTIVITY,"");
						intent.putExtras(Bundle2);
						intent.setClass(context.getApplicationContext(), MainActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);

					}


				}
			} catch (JSONException e) {
				e.printStackTrace();
			}catch (RuntimeException e)
			{
				e.printStackTrace();
			}

		}

		return null;
	}
}
