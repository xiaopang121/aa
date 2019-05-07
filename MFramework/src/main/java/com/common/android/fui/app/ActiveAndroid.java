package com.common.android.fui.app;

import android.app.Application;

public final class ActiveAndroid {

	public synchronized static void initialize(Application application) {
		initialize(application, false);
	}

	public synchronized static void initialize(Application application,
			boolean loggingEnabled) {


	}

	public static void dispose(Application application) {

	}

}