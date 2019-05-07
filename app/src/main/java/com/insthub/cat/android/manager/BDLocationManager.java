package com.insthub.cat.android.manager;

import android.content.Context;
import android.text.TextUtils;

import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.common.android.flog.KLog;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.futils.NetWorkUtil;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.module2.City;
import com.insthub.cat.android.utils.LocationUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * User:macbook
 * DATE:2017/11/26 22:55
 * Desc:${DESC}
 */

public class BDLocationManager {


    public static  BDLocationManager _manager;

    public Context ctx;

    private LocationUtils mLocationUtils;


    private BDLocation curLocation;

    private String curcity;


    private  boolean isUpdate=true;

    //回调
    public ArrayList<LocationUtils.CityNameStatus> callbacklist = new ArrayList<>();

    private Timer mTimer;


    public BDLocation getCurLocation() {

        if(curLocation==null)
        {
            curLocation = new BDLocation();
            curLocation.setLongitude(0);
            curLocation.setLatitude(0);
        }
        return curLocation;
    }

    public void setCurLocation(BDLocation curLocation) {
        this.curLocation = curLocation;
    }


    public  BDLocation newLocation;

    /**
     * 定位回调
     */
    private LocationUtils.CityNameStatus mCityNameStatus = new LocationUtils.CityNameStatus(){

        @Override
        public void detecting() {

        }

        @Override
        public void update(String city, BDLocation aMapLocation,BDLocation  item) {

            newLocation = aMapLocation;
            if (!TextUtils.isEmpty(city) && isUpdate) {
                curcity = city;
                curLocation =aMapLocation;
                updateCallback();
                //上传定位
            }else
            {

            }
        }
    };






    public static BDLocationManager  init(Context context)
    {
        if(_manager==null)
        {
            _manager = new BDLocationManager(context);
        }

        return _manager;
    }



    public static BDLocationManager getInstance()
    {
        return _manager;
    }



    public BDLocationManager(Context context)
    {
        ctx  = context;
        mLocationUtils = new LocationUtils(context,mCityNameStatus);
        startLocation();
    }



    public void startLocation()
    {
        if(!NetWorkUtil.isNetConnected(ctx))
        {
            ToastUtil.show(ctx,"您的网络不给力");
            return ;
        }

        if( !mLocationUtils.isStarted())
        {
            mLocationUtils.startLocation();
        }




    }



    public void stopLocation()
    {
         if(mLocationUtils.isStarted())
         {
             mLocationUtils.stopLocation();
         }


    }


    public void addCallback( LocationUtils.CityNameStatus callback)
    {
        callbacklist.add(callback);
        if(!TextUtils.isEmpty(curcity))
        {
            callback.update(curcity,curLocation,newLocation);
        }

    }



    public void removeCallback( LocationUtils.CityNameStatus callback)
    {
        callbacklist.remove(callback);
    }



    public void onDestroy()
    {
        stopLocation();
    }



    private  void updateCallback()
    {
        for(LocationUtils.CityNameStatus callback :callbacklist)
        {
            callback.update(curcity,curLocation,newLocation);
        }
    }



    public void  setLoationInfo(City city)
    {
        curLocation = new BDLocation();
        Address  addrew = new Address.Builder().country(city.getName()).build();
        curLocation.setAddr(addrew);
        curLocation.setLatitude(city.getLat());
        curLocation.setLongitude(city.getLon());
        isUpdate =false;
    }
}
