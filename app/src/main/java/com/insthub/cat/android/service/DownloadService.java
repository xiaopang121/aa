package com.insthub.cat.android.service;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.common.android.flog.KLog;
import com.insthub.cat.android.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class DownloadService extends Service{

	public static final String DOWNLOAD_PATH = 
			Environment.getExternalStorageDirectory().getAbsolutePath()+
			"/downloads/";
	public static final String TAG = "download";
	private String url;//下载链接
	private int length;//文件长度
	private String fileName=null;//文件名
	private Notification notification;
	private RemoteViews contentView;
	private NotificationManager notificationManager;
	
	private static final int MSG_INIT = 0;
	private static final int URL_ERROR = 1;
	private static final int NET_ERROR = 2;
	private static final int DOWNLOAD_SUCCESS = 3;
	private static final int UPDATE=5;

	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case UPDATE:

				int [] data = (int [])msg.obj;

				int percent = data[0]*100/data[1];
				notifyNotification(percent, 100);

				if(percent==100 && scheduledExecutorService!=null && !scheduledExecutorService.isShutdown())
				{
					scheduledExecutorService.shutdown();
					scheduledExecutorService=null;
				}



				break;
			case DOWNLOAD_SUCCESS:
				//下载完成
				notifyNotification(100, 100);
				//installApk(DownloadService.this,new File(DOWNLOAD_PATH,fileName));
				Toast.makeText(DownloadService.this, "下载完成", Toast.LENGTH_SHORT).show();
				break;
			case URL_ERROR:
				Toast.makeText(DownloadService.this, "下载地址错误", Toast.LENGTH_SHORT).show();
				break;
			case NET_ERROR:
				Toast.makeText(DownloadService.this, "连接失败，请检查网络设置", Toast.LENGTH_SHORT).show();
			}
		};
	};


	private  long  refernece;

	private BroadcastReceiver receiver;

	private DownloadChangeObserver mDownloadChangeObserver;

	private ScheduledExecutorService scheduledExecutorService;

	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(intent != null){
			url = intent.getStringExtra("url");
			if(url != null && !TextUtils.isEmpty(url)){
			//	new InitThread(url).start();
				download(url);
			}else{
				//mHandler.sendEmptyMessage(URL_ERROR);
			}
			
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
//	/**
//	 * 初始化子线程
//	 * @author dong
//	 *
//	 */
//	class InitThread extends Thread{
//		String url = "";
//
//		public InitThread(String url) {
//			this.url = url;
//		}
//		public void run() {
//			HttpURLConnection conn= null;
//			RandomAccessFile raf = null;
//			try {
//				//连接网络文件
//				KLog.i("1111111111111111111111111");
//				URL url = new URL(this.url);
//				conn = (HttpURLConnection) url.openConnection();
//				conn.setConnectTimeout(60 * 1000);
//				conn.setRequestMethod("GET");
//				int length = -1;
//				if(conn.getResponseCode() == HttpStatus.SC_OK){
//					//获得文件长度
//					length = conn.getContentLength();
//				}
//				KLog.i("LENGTH:"+length);
//
//				if(length <= 0){
//					return;
//				}
//
//				KLog.i("11111112222222222222222");
//				File dir = new File(DOWNLOAD_PATH);
//				if(!dir.exists()){
//					dir.mkdir();
//				}
//				fileName = this.url.substring(this.url.lastIndexOf("/")+1, this.url.length());
//				if(fileName==null && TextUtils.isEmpty(fileName) && !fileName.contains(".apk")){
//					fileName = getPackageName()+".apk";
//				}
//				KLog.i("FILE_NAME："+fileName);
//				File file = new File(dir, fileName);
//				file.createNewFile();
//				KLog.i("file："+file.toString());
//				raf = new RandomAccessFile(file, "rwd");
//				//设置文件长度
//				raf.setLength(length);
//				mHandler.obtainMessage(MSG_INIT,length).sendToTarget();
//			} catch (Exception e) {
//				mHandler.sendEmptyMessage(URL_ERROR);
//				e.printStackTrace();
//			} finally{
//				try {
//					conn.disconnect();
//					raf.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	/**
//	 * 下载线程
//	 * @author dong
//	 *
//	 */
//	class DownloadThread extends Thread{
//		String url;
//		int length;
//		public DownloadThread(String url, int length) {
//			this.url = url;
//			this.length = length;
//		}
//		@Override
//		public void run() {
//			HttpURLConnection conn = null;
//			RandomAccessFile raf = null;
//			InputStream input = null;
//			try {
//
//				URL url = new URL(this.url);
//				conn = (HttpURLConnection) url.openConnection();
//				conn.setConnectTimeout(60 * 1000);
//				conn.setRequestMethod("GET");
//				//设置下载位置
//				int start =0;
//				conn.setRequestProperty("Range", "bytes="+0+"-"+length);
//				//设置文件写入位置
//				File file = new File(DownloadService.DOWNLOAD_PATH,fileName);
//				raf = new RandomAccessFile(file, "rwd");
//				raf.seek(start);
//				long mFinished = 0;
//				//开始下载
//				if(conn.getResponseCode() == HttpStatus.SC_PARTIAL_CONTENT){
//					//LogUtil.i("下载开始了。。。");
//					//读取数据
//					input = conn.getInputStream();
//					byte[] buffer = new byte[1024*4];
//					int len = -1;
//					long speed = 0;
//					long time = System.currentTimeMillis();
//					while((len = input.read(buffer)) != -1){
//						//写入文件
//
//						raf.write(buffer,0,len);
//						//把下载进度发送广播给Activity
//						mFinished += len;
//						speed += len;
//						if(System.currentTimeMillis() - time > 1000){
//							time = System.currentTimeMillis();
//
//							notifyNotification(mFinished,length);
//							Log.i(TAG, "mFinished=="+mFinished);
//							Log.i(TAG, "length=="+length);
//							Log.i(TAG, "speed=="+speed);
//							speed = 0;
//
//						}
//
//					}
//					mHandler.sendEmptyMessage(DOWNLOAD_SUCCESS);
//					Log.i(TAG, "下载完成了。。。");
//				}else{
//					Log.i(TAG, "下载出错了。。。");
//					mHandler.sendEmptyMessage(NET_ERROR);
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally{
//				try {
//					if(conn != null){
//						conn.disconnect();
//					}
//					if(raf != null){
//						raf.close();
//					}
//					if(input != null ){
//						input.close();
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//
//			}
//		}
//	}
//
	@SuppressWarnings("deprecation")
	public void createNotification() {
		//com.fuiou.mobile.util.DialogUtils.showDialog(getApplicationContext(),"正在下载，请等待...");
        notification = new Notification(
                R.mipmap.ic_launcher,//应用的图标
                "安装包正在下载...",
                System.currentTimeMillis());
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        //notification.flags = Notification.FLAG_AUTO_CANCEL;

        /*** 自定义  Notification 的显示****/
        contentView = new RemoteViews(getPackageName(), R.layout.notification_item);
        contentView.setProgressBar(R.id.progress, 100, 0, false);
        contentView.setTextViewText(R.id.tv_progress, "0%");
        notification.contentView = contentView;

        /*updateIntent = new Intent(this, AboutActivity.class);
      	updateIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
     	updateIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
     	pendingIntent = PendingIntent.getActivity(this, 0, updateIntent, 0);
      	notification.contentIntent = pendingIntent;*/
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //设置notification的PendingIntent
		/*Intent intt = new Intent(this, MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(this,100, intt,Intent.FLAG_ACTIVITY_NEW_TASK	| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		notification.contentIntent = pi;*/

		notificationManager.notify(R.layout.notification_item, notification);
    }

	private void notifyNotification(long percent,long length){

		if(percent==length)
		{
			contentView.setTextViewText(R.id.tv_title, "下载完成");
		}else
		{
			contentView.setTextViewText(R.id.tv_title, "金陵贷-下载中");
		}
		contentView.setTextViewText(R.id.tv_progress, (percent*100/length)+"%");
        contentView.setProgressBar(R.id.progress, (int)length,(int)percent, false);
        notification.contentView = contentView;
        notificationManager.notify(R.layout.notification_item, notification);
	}







	public void download(String url)
	{

		receiver();

		//createNotification();

		DownloadManager dManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
		Uri uri = Uri.parse(url);
		DownloadManager.Request request = new DownloadManager.Request(uri);
		request.setDestinationInExternalPublicDir("download", "jinlingdai.apk");
		request.setDescription("金陵贷新版本下载");
		request.setTitle("金陵贷");
		request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		request.setMimeType("application/vnd.android.package-archive");
        // 设置为可被媒体扫描器找到
		request.allowScanningByMediaScanner();
        // 设置为可见和可管理
		request.setVisibleInDownloadsUi(true);
		refernece = dManager.enqueue(request);
		// 把当前下载的ID保存起来
//		SharedPreferences sPreferences = getSharedPreferences("downloadcomplte", 0);
//		sPreferences.edit().putLong("refernece", refernece).commit();


	}






	public void receiver()
	{
//
//		if(receiver!=null)
//		{
//			unregisterReceiver(receiver);
//			receiver = null;
//		}
//
//
//		if(mDownloadChangeObserver!=null)
//		{
//			getContentResolver().unregisterContentObserver(mDownloadChangeObserver);
//			mDownloadChangeObserver = null;
//		}


		IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
		receiver = new BroadcastReceiver() {

			public void onReceive(Context context, Intent intent) {
				long myDwonloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
				if (refernece == myDwonloadID) {
					DownloadManager dManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
					Intent install = new Intent(Intent.ACTION_VIEW);
					Uri downloadFileUri = dManager.getUriForDownloadedFile(myDwonloadID);
					install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
					install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(install);
				}
			}
		};
		registerReceiver(receiver, filter);

		//mDownloadChangeObserver = new DownloadChangeObserver();
		//getContentResolver().registerContentObserver(Uri.parse("content://downloads/my_downloads"), true,mDownloadChangeObserver);
	}

	/**
	 * 监听下载进度
	 */
	private class DownloadChangeObserver extends ContentObserver {

		public DownloadChangeObserver() {
			super(new Handler());
			scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		}

		/**
		 * 当所监听的Uri发生改变时，就会回调此方法
		 *
		 * @param selfChange 此值意义不大, 一般情况下该回调值false
		 */
		@Override
		public void onChange(boolean selfChange) {
			if(scheduledExecutorService!=null)
			{
				scheduledExecutorService.scheduleAtFixedRate(progressRunnable, 0, 2, TimeUnit.SECONDS);
			}


		}
	}


	/**
	 * 查询进度
	 */
	private  Runnable progressRunnable = new Runnable() {
		@Override
		public void run() {

			int [] data = getBytesAndStatus(refernece);
			Message  msg = mHandler.obtainMessage();
			msg.obj = data;
			msg.what =UPDATE;
			mHandler.sendMessage(msg);


		}
	};



	/**
	 * 通过query查询下载状态，包括已下载数据大小，总大小，下载状态
	 *
	 * @param downloadId
	 * @return
	 */
	private int[] getBytesAndStatus(long downloadId) {
		int[] bytesAndStatus = new int[]{
				-1, -1, 0
		};
		DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
		DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
		Cursor cursor = null;
		try {
			cursor = downloadManager.query(query);
			if (cursor != null && cursor.moveToFirst()) {
				//已经下载文件大小
				bytesAndStatus[0] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));

				//下载文件的总大小
				bytesAndStatus[1] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

				KLog.i("下载中"+	bytesAndStatus[0]+"---------------"+bytesAndStatus[1]);
				//下载状态
				bytesAndStatus[2] = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return bytesAndStatus;
	}

	public void onDestroy()
	{
		unregisterReceiver(receiver);
	}





}
