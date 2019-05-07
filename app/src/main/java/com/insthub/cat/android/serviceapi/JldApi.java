package com.insthub.cat.android.serviceapi;


import com.common.android.fapi.BaseApi;
import com.common.android.fsp.SharedPreferencesUtil;
import com.insthub.cat.android.App;
import com.insthub.cat.android.constant.Constants;
import com.insthub.cat.android.constant.ConstantsKeys;
import java.util.HashMap;

/**
 *
 *  接口管理
 * Created by linux on 2017/6/26.
 */

public class JldApi  extends BaseApi<JldApiService> {

    @Override
    protected String configServerHttpUrl() {
        return Constants.SERVICE_URL_PREFEX;
    }

    @Override
    protected Class<JldApiService> configServerEvent() {
        return JldApiService.class;
    }


    @Override
    protected HashMap<String, String> configHeaderKV() {

        HashMap<String, String> headerkey = new HashMap<>();
        headerkey.put("AccessToken",SharedPreferencesUtil.getInstance().getString(ConstantsKeys.KEY_CACHE_TOKEN,""));
        return headerkey;
    }


    @Override
    protected int httpscertificateSource() {
        return 0;
    }

    @Override
    protected boolean isOpenHttps() {
        return false ;
    }

    public JldApi() {
        super(App.getAppContext());
    }

    public static JldApi _BaseApi;

    public static Object lock = new Object();

    public static JldApi getInstance()
    {
        synchronized (lock)
        {
            if(_BaseApi==null)
            {
                _BaseApi = new JldApi();
            }
        }
        return _BaseApi;
    }

}
