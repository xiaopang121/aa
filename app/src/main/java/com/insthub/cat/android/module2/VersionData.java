package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

/**
 * Created by linux on 2018/6/4.
 */

public class VersionData extends BaseData {


    /**
     * status : 10001
     * data : {"androidUrl":"www.baidu.com","androidForced":"1","iosForced":"","androidversionCode":"201","androidversionNote":"版本更新","type":"1","version":"2.0.1","iosversionName":"","iosversionCode":"","androidversionName":"2.0.1","appname":"2.0.1","create_time":"2018-07-19","vid":1,"iosUrl":"","iosversionNote":""}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * androidUrl : www.baidu.com
         * androidForced : 1
         * iosForced :
         * androidversionCode : 201
         * androidversionNote : 版本更新
         * type : 1
         * version : 2.0.1
         * iosversionName :
         * iosversionCode :
         * androidversionName : 2.0.1
         * appname : 2.0.1
         * create_time : 2018-07-19
         * vid : 1
         * iosUrl :
         * iosversionNote :
         */

        private String androidUrl;
        private String androidForced;
        private String iosForced;
        private int androidversionCode;
        private String androidversionNote;
        private String type;
        private String version;
        private String iosversionName;
        private String iosversionCode;
        private String androidversionName;
        private String appname;
        private String create_time;
        private int vid;
        private String iosUrl;
        private String iosversionNote;

        public String getAndroidUrl() {
            return androidUrl;
        }

        public void setAndroidUrl(String androidUrl) {
            this.androidUrl = androidUrl;
        }

        public String getAndroidForced() {
            return androidForced;
        }

        public void setAndroidForced(String androidForced) {
            this.androidForced = androidForced;
        }

        public String getIosForced() {
            return iosForced;
        }

        public void setIosForced(String iosForced) {
            this.iosForced = iosForced;
        }

        public int getAndroidversionCode() {
            return androidversionCode;
        }

        public void setAndroidversionCode(int androidversionCode) {
            this.androidversionCode = androidversionCode;
        }

        public String getAndroidversionNote() {
            return androidversionNote;
        }

        public void setAndroidversionNote(String androidversionNote) {
            this.androidversionNote = androidversionNote;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getIosversionName() {
            return iosversionName;
        }

        public void setIosversionName(String iosversionName) {
            this.iosversionName = iosversionName;
        }

        public String getIosversionCode() {
            return iosversionCode;
        }

        public void setIosversionCode(String iosversionCode) {
            this.iosversionCode = iosversionCode;
        }

        public String getAndroidversionName() {
            return androidversionName;
        }

        public void setAndroidversionName(String androidversionName) {
            this.androidversionName = androidversionName;
        }

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getVid() {
            return vid;
        }

        public void setVid(int vid) {
            this.vid = vid;
        }

        public String getIosUrl() {
            return iosUrl;
        }

        public void setIosUrl(String iosUrl) {
            this.iosUrl = iosUrl;
        }

        public String getIosversionNote() {
            return iosversionNote;
        }

        public void setIosversionNote(String iosversionNote) {
            this.iosversionNote = iosversionNote;
        }
    }
}
