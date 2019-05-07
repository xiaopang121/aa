package com.insthub.cat.android.utils;

import android.content.Context;
import android.text.TextUtils;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.insthub.cat.android.manager.BDLocationManager;


/**
 * 定位
 *
 * @author Administrator
 */
public class LocationUtils {
    private LocationClient mLocationClient = null;
    private CityNameStatus mCityNameStatus;
    private BDLocation curBDLocation;

    private int locInvitial = 10000;

    //上次定位时间
    private long lastLocationTime;

    private boolean isFrist=false;
    /**
     * 实现定位回调监听
     */
    BDAbstractLocationListener mLocationListener = new BDAbstractLocationListener() {


        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || TextUtils.isEmpty(location.getCity()))
                return;
            if (location.getLocType() != 161)
                return;
            curBDLocation = location;
            String city = location.getDistrict();

            if (mCityNameStatus != null) {
                mCityNameStatus.update(city, curBDLocation,null);
            }

        }

    };

    public LocationUtils(Context context, CityNameStatus cityNameStatus) {
        lastLocationTime = System.currentTimeMillis();
        mCityNameStatus = cityNameStatus;
        mLocationClient = new LocationClient(context);
        mLocationClient.setLocOption(getLocationClientOption(context));
        mLocationClient.registerLocationListener(mLocationListener);
    }

    // 开始定位
    public void startLocation() {
        mLocationClient.start();
        mCityNameStatus.detecting();
    }

    // 结束定位
    public void stopLocation() {
        mLocationClient.stop();

    }

    public boolean isStarted() {
        return mLocationClient.isStarted();
    }

    private LocationClientOption getLocationClientOption(Context context) {
        LocationClientOption mOption = new LocationClientOption();
        mOption.setLocationMode(LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        mOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        mOption.setScanSpan(locInvitial);//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        mOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        mOption.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
        mOption.setNeedDeviceDirect(false);//可选，设置是否需要设备方向结果
        mOption.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        mOption.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        mOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        mOption.setOpenGps(false);//可选，默认false，设置是否开启Gps定位
        mOption.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        return mOption;
    }

    public interface CityNameStatus {
        public  void detecting();

        public  void update(String city, BDLocation location, BDLocation newloc);

    }




    public synchronized void dispatch(String city)
    {
        long duration = System.currentTimeMillis()-lastLocationTime;

        lastLocationTime = System.currentTimeMillis();

    }
}
