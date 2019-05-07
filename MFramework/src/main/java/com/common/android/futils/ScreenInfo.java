package com.common.android.futils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

/**
 * 得到屏幕宽高密度等
 * 
 */
public class ScreenInfo  {
	private Activity activity;
	/** 屏幕宽度（像素） */
	private int width;
	/** 屏幕高度（像素） */
	private int height;
	/** 屏幕密度（0.75 / 1.0 / 1.5） */
	private float density;
	/** 屏幕密度DPI（120 / 160 / 240） */
	private int densityDpi;
    /******工作区高度****不包括状态栏***/
	private int visibleDisplayFrame;

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public int getDensityDpi() {
		return densityDpi;
	}

	public void setDensityDpi(int densityDpi) {
		this.densityDpi = densityDpi;
	}

	public ScreenInfo(Activity activity) {
		this.activity = activity;
		ini();
	}

	
	
	public int getVisibleDisplayFrame() {
		return visibleDisplayFrame;
	}

	public void setVisibleDisplayFrame(int visibleDisplayFrame) {
		this.visibleDisplayFrame = visibleDisplayFrame;
	}

	private void ini() {
		DisplayMetrics metric = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		width = metric.widthPixels;
		height = metric.heightPixels;
		density = metric.density;
		densityDpi = metric.densityDpi;
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		if(frame.top==0)
		{
		   frame.top = getStatusBarHeight(activity);
		}
		visibleDisplayFrame = Math.abs(frame.bottom-frame.top);
	}
	
	
	
	//获取手机状态栏高度
    public static int getStatusBarHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        } 
        return statusBarHeight;
    }


	/**
	 * 获取屏幕高度
	 * @param context
	 * @return
     */
	public static int getScreenHeight(Activity context){
		DisplayMetrics metric = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric.heightPixels;
	}

	/**
	 * 获取屏幕宽度
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Activity context){
		DisplayMetrics metric = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric.widthPixels;
	}


	/**
	 * 获得当前屏幕亮度值
	 *
	 * @param mContext
	 * @return 0~100
	 */
	public static float getScreenBrightness(Context mContext) {
		int screenBrightness = 255;
		try {
			screenBrightness = Settings.System.getInt(mContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return screenBrightness / 255.0F * 100;
	}

	/**
	 * 设置当前屏幕亮度值
	 *
	 * @param paramInt 0~100
	 * @param mContext
	 */
	public static void saveScreenBrightness(int paramInt, Context mContext) {
		if (paramInt <= 5) {
			paramInt = 5;
		}
		try {
			float f = paramInt / 100.0F * 255;
			Settings.System.putInt(mContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, (int) f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 关闭亮度自动调节
	 *
	 * @param activity
	 */
	public static void stopAutoBrightness(Activity activity) {
		Settings.System.putInt(activity.getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS_MODE,
				Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
	}

	/**
	 * 开启亮度自动调节
	 *
	 * @param activity
	 */

	public static void startAutoBrightness(Activity activity) {
		Settings.System.putInt(activity.getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS_MODE,
				Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
	}
	/**
	 * 设置Activity的亮度
	 *
	 * @param paramInt
	 * @param mActivity
	 */
	public static void setScreenBrightness(int paramInt, Activity mActivity) {
		if (paramInt <= 5) {
			paramInt = 5;
		}
		Window localWindow = mActivity.getWindow();
		WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
		float f = paramInt / 100.0F;
		localLayoutParams.screenBrightness = f;
		localWindow.setAttributes(localLayoutParams);
	}




	/**
	 * 通过反射，获取包含虚拟键的整体屏幕高度
	 *
	 * @return
	 */
	private int getHasVirtualKey(Activity ctx) {
		int dpi = 0;
		Display display = ctx.getWindowManager().getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		@SuppressWarnings("rawtypes")
		Class c;
		try {
			c = Class.forName("android.view.Display");
			@SuppressWarnings("unchecked")
			Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
			method.invoke(display, dm);
			dpi = dm.heightPixels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dpi;
	}


	/**
	 * 不包含虚拟键的 高度
	 * @param ctx
	 * @return
	 */
	private int getNoHasVirtualKey(Activity ctx) {
		int height = ctx.getWindowManager().getDefaultDisplay().getHeight();
		return height;
	}


	/**
	 * 获取虚拟键高度
	 * @param act
	 * @return
	 */
	public  int getNavBarHeight(Activity act)
	{
		int height =  getHasVirtualKey(act) - getNoHasVirtualKey(act);
		return height;
	}


}
