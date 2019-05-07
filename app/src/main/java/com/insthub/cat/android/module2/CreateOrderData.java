package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

/**
 * Created by linux on 2018/6/4.
 */

public class CreateOrderData extends BaseData {


    /**
     * status : 10001
     * data : {"logo":"banner/logo.png","service_price":"0.01","state":"0","store_id":"13e18995c098416fb1bd1aeaa16b0bb9","remarks":"vvcccxxxxxz","order_id":"20180612066384","service_time":"2018-06-12,2018-06-28","service_content":"税务筹划","user_name":"Qwrewrwe","head_image":"coupon/4af7a1f4cb8d41389b4f36da1dc62721.jpg","create_time":"2018-06-12 12:30:23","user_id":"0bb7714890204418913a5f07776051bc","store_name":"测试店铺-6号"}
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
         * logo : banner/logo.png
         * service_price : 0.01
         * state : 0
         * store_id : 13e18995c098416fb1bd1aeaa16b0bb9
         * remarks : vvcccxxxxxz
         * order_id : 20180612066384
         * service_time : 2018-06-12,2018-06-28
         * service_content : 税务筹划
         * user_name : Qwrewrwe
         * head_image : coupon/4af7a1f4cb8d41389b4f36da1dc62721.jpg
         * create_time : 2018-06-12 12:30:23
         * user_id : 0bb7714890204418913a5f07776051bc
         * store_name : 测试店铺-6号
         */

        private String logo;
        private String service_price;
        private String state;
        private String store_id;
        private String remarks;
        private String order_id;
        private String service_time;
        private String service_content;
        private String user_name;
        private String head_image;
        private String create_time;
        private String user_id;
        private String store_name;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getService_price() {
            return service_price;
        }

        public void setService_price(String service_price) {
            this.service_price = service_price;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getService_time() {
            return service_time;
        }

        public void setService_time(String service_time) {
            this.service_time = service_time;
        }

        public String getService_content() {
            return service_content;
        }

        public void setService_content(String service_content) {
            this.service_content = service_content;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getHead_image() {
            return head_image;
        }

        public void setHead_image(String head_image) {
            this.head_image = head_image;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }
    }
}
