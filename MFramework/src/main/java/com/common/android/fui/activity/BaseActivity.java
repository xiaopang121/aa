package com.common.android.fui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.DhcpInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.common.android.ftheme.ThemeHelper;
import com.common.android.ftheme.utils.ThemeUtils;
import com.common.android.fui.swipeback.SwipeBackActivity;
import com.common.android.futils.DialogUtils;
import com.common.android.futils.StatusBarUtil;
import com.common.android.R;
import com.common.android.futils.TUtil;

import butterknife.ButterKnife;

/**
 * @fileName BaseActivity.java
 * @description Activity基类
 * @version 1.0
 */
public abstract class BaseActivity <T extends BasePresenter, E extends BaseModel> extends SwipeBackActivity  {
	protected Dialog mLoadDialog;
	public boolean isNight;
	public T mPresenter;
	public E mModel;
	protected int mAlpha = StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;

	//当前主题
	protected  int currentTheme=ThemeHelper.CARD_SAKURA;

	private  boolean isSetTranslucentStatus=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置主题
		currentTheme = ThemeHelper.getTheme(this);

		setContentView(getLayoutResId());

		ButterKnife.bind(this);
		//setTranslucentStatus();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

		mPresenter = TUtil.getT(this, 0);
		mModel = TUtil.getT(this, 1);
		bindPresenter();
		bindViewById();
		bindEvent();
		bindData();

	}

	protected <V extends View> V findViewByIds(int id) {
		return (V) findViewById(id);
	}

	/** 绑定界面UI **/
	protected  void bindViewById() {}

	/** 界面UI事件监听 **/
	protected  void bindEvent(){}
	/** 界面事件注销**/
	protected  void unbindEvent(){};

	/** 界面数据初始�? **/
	protected  void bindData(){}

    /**绑定操作**/
	protected  void bindPresenter(){}

	/**主题颜色 **/
	protected  int  bindColorPrimary()
	{
		return android.R.color.transparent;
	}

	/** 获取当前Activity **/
	protected Activity getActivity() {
		return this;
	}

	//绑定布局
	@LayoutRes
	protected abstract int getLayoutResId();

	/** 获取当前Context **/
	protected Context getContext() {
		return this;
	}




	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null,true);
	}


	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		startActivity(cls,bundle,true);
	}


	protected void startActivity(Class<?> cls, Intent bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		//startActivity(intent);

		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		defaultFinish();
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle,boolean anim) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}


	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivityForResult(Class<?> cls, Bundle bundle,int  requestCode) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent,requestCode);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}



	protected void startActivity(Class<?> cls,boolean anim) {
		startActivity(cls, null,anim);
	}


	/** 通过Action跳转界面 **/
	protected void startActivity(String action) {
		startActivity(action, null);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/** 含有Bundle通过Action跳转界面 **/
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/**
	 * requestCode 跳转
	 */
	public  void startActivityForResults(Intent intent, int requestCode) {
		startActivityForResult(intent, requestCode);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}


	public  void startActivityForResult(Class<?> cls, int requestCode) {
		startActivityForResult(new Intent(getActivity(),cls), requestCode);
	}

	/** 带有右进右出动画的�?�? **/
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	/** 默认�?�� **/
	public void defaultFinish() {
		super.finish();
	}



	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		refreshTheme();
	}


	/**
	 * 刷新主题
	 */
	public void refreshTheme() {

		//StatusBarUtil.setColor(this,getResources().getColor(R.color.B_black_10));
		if (ThemeHelper.getTheme(this) != currentTheme) {
			currentTheme=ThemeHelper.getTheme(this);
			ThemeUtils.refreshUI(this, new ThemeUtils.ExtraRefreshable() {
						@Override
						public void refreshGlobal(Activity activity) {

							if(isSetTranslucentStatus)
							{
								StatusBarUtil.setColor(activity,ThemeUtils.getColorById(activity, R.color.theme_color_primary));
							}

						}

						@Override
						public void refreshSpecificView(View view) {
							//TODO: will do this for each traversal
						}
					}
			);

		}
	}



	/**
	 * 设置状态栏背景状态
	 */
	protected void setTranslucentStatus() {
		StatusBarUtil.setColor(this, ThemeUtils.getColorById(this, bindColorPrimary()));
		isSetTranslucentStatus=true;
	}

	/**
	 * 显示加载框
	 *
	 * @param msg
	 */
	protected Dialog showLoadDialog(String msg) {
		try {
			mLoadDialog = DialogUtils.createDialog(getActivity(), "", msg);
			if (!mLoadDialog.isShowing()) {
				mLoadDialog.show();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return mLoadDialog;
	}


	/**
	 * 显示加载框
	 *
	 * @param msg
	 */
	protected Dialog showLoadDialog(String msg, DialogInterface.OnCancelListener listener) {
		try {
			mLoadDialog = DialogUtils.createDialog(getActivity(), "", msg);
			mLoadDialog.setOnCancelListener(listener);
			if (!mLoadDialog.isShowing()) {
				mLoadDialog.show();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return mLoadDialog;
	}


	protected void dismissLoadDialog() {
		if (mLoadDialog != null && mLoadDialog.isShowing()) {
			mLoadDialog.dismiss();
		}
	}



	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mPresenter != null)
			mPresenter.onDestroy();
		    //注销事件
		    unbindEvent();
		    ButterKnife.unbind(this);
	}


	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

}
