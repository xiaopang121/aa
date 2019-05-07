package com.common.android.fui.app;

import android.app.Application;
import android.content.Context;

public class ActiveApplicaton extends Application {


	private static Context sAppContext;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		sAppContext = this;
		ActiveAndroid.initialize(this);
	}
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		ActiveAndroid.dispose(this);
	}



	public static Context getAppContext()
	{
		return sAppContext;
	}

}
