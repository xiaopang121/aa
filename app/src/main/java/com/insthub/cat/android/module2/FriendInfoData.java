package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class FriendInfoData extends BaseData {


    /**
     * status : 10001
     * data : {"user_name":"Qwrewrwe","accid":"0bb7714890204418913a5f07776051bc","head_image":"http://139.196.92.19:8080/upload/coupon/4af7a1f4cb8d41389b4f36da1dc62721.jpg"}
     */

    private DataBean data;



    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * user_name : Qwrewrwe
         * accid : 0bb7714890204418913a5f07776051bc
         * head_image : http://139.196.92.19:8080/upload/coupon/4af7a1f4cb8d41389b4f36da1dc62721.jpg
         */

        private String user_name;
        private String accid;
        private String head_image;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public String getHead_image() {
            return head_image;
        }

        public void setHead_image(String head_image) {
            this.head_image = head_image;
        }
    }
}
