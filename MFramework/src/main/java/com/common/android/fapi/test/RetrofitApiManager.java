package com.common.android.fapi.test;


import com.common.android.BuildConfig;
import com.common.android.fapi.BaseApi;
import com.common.android.fapi.StApiInterface;
import com.common.test.App;

import java.util.HashMap;


/**
 * 网络请求动态代理管理
 * @author macbook
 *
 */
public class RetrofitApiManager extends BaseApi<StApiInterface> {


	public static RetrofitApiManager instance;


	public static RetrofitApiManager getInstance()
	{
		if(instance==null)
		{
			instance = new RetrofitApiManager();
		}
		return instance;
	}

	@Override
	protected boolean isOpenHttps() {
		return false;
	}

	@Override
	protected int httpscertificateSource() {
		return 0;
	}

	@Override
	protected String configServerHttpUrl() {
		return null;
	}


	@Override
	protected HashMap<String, String> configHeaderKV() {
		return null;
	}

	@Override
	protected Class<StApiInterface> configServerEvent() {
		return StApiInterface.class;
	}

	public RetrofitApiManager() {
		super(App.getAppContext());
	}
}
