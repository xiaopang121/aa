package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.widget.ClipImageLayout;
import com.insthub.cat.android.utils.BitmapUtils;
import com.insthub.cat.android.utils.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;

/**
 * 图片剪切页面
 */
public class PhotoClipActivity extends BaseActivity {

	@Bind(R.id.common_title_bar)
	CommonTitleBar commonTitleBar;

	private ClipImageLayout mClipImageLayout;
	private String noticeImagePath = null;
	private Button bt_cancel, bt_ok;
	private String tempCropFilePath;
	private Context context;
	private String classId;
	private BitmapUtils bitmapUtils;
	private Bitmap bitmap;


	@Override
	protected void bindViewById() {
		super.bindViewById();

		int statubar = ScreenInfo.getStatusBarHeight(getActivity());
		ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
		lp.height = lp.height + statubar;
		commonTitleBar.setPadding(0, statubar, 0, 0);
		commonTitleBar.setLayoutParams(lp);
		commonTitleBar.invalidate();
		commonTitleBar.setTitle("图片裁剪");
		commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);

		context = this;
		bt_cancel = (Button) findViewById(R.id.bt_photo_cancle);
		bt_ok = (Button) findViewById(R.id.bt_photo_ok);
		Intent intent = getIntent();
		String path = (String) intent.getExtras().get("path");

		bitmapUtils = new BitmapUtils(context);

		mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);
		mClipImageLayout.setHorizontalPadding(1);
		mClipImageLayout.setProportion(10, 7);//直接设置比例
		mClipImageLayout.setImageDrawable(path);

		// 图片选择 需要去裁剪的图片路径
		tempCropFilePath = getFilePath();

		LogUtils.d("获取地址 = " + tempCropFilePath);
		bt_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		bt_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 剪切图片
				bitmap = mClipImageLayout.clip();
				BitmapUtils bitmaputil = new BitmapUtils(
						getApplicationContext());

				if (bitmap != null) {
					// 压缩保存图片
					bitmaputil.saveBitmapInSD(tempCropFilePath, bitmap);
					// sendData();//上传
					Intent intent = new Intent();
					intent.putExtra("path", tempCropFilePath);
					setResult(RESULT_OK, intent);
					finish();
					recycle();
				}

			}
		});

	}


	@Override
	protected void bindData() {
		super.bindData();
	}


	public String getMydir() {
		return (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()) || !Environment
				.isExternalStorageRemovable()) ? Environment
				.getExternalStorageDirectory().getPath() + File.separator + ""
				: context.getCacheDir().getPath() + File.separator + "";
	}

	// 获取图片路径
	public String getFilePath() {
		LogUtils.d("根目录路径  = " + getMydir());
		String path = getMydir();
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		path = path + timeStamp + ".jpg";
		return path;
	}

	@Override
	protected void onDestroy() {
		recycle();
		super.onDestroy();
	}

	public void recycle() {
		if (bitmap != null && !bitmap.isRecycled()) {
			bitmap.recycle();// 回收bitmap
			bitmap = null;
			System.gc();
		}
	}


	@Override
	protected int getLayoutResId() {
		return R.layout.activity_image_clip;
	}
}
