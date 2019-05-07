package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;

/**
 * Created by linux on 2018/6/4.
 */

public class KanjiaInfoData extends BaseData {


    /**
     * status : 10001
     * data : {"old_price":"5000","max_discount":4999.99,"kanjia_id":3,"state":"0","create_time":"2018-08-17 14:46:02","discount_price":"0.01","cutdown_percent":0,"user_id":"0bb7714890204418913a5f07776051bc","cutdown_price":0}
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
         * old_price : 5000
         * max_discount : 4999.99
         * kanjia_id : 3
         * state : 0
         * create_time : 2018-08-17 14:46:02
         * discount_price : 0.01
         * cutdown_percent : 0
         * user_id : 0bb7714890204418913a5f07776051bc
         * cutdown_price : 0
         */

        private int kanjia_id;
        private String user_id;
        private float old_price;
        private float discount_price;
        private int cutdown_price;
        private String create_time;
        private int cutdown_percent;
        private String state;
        private double max_discount;
        private int kanjia_count;
        private String user_name;
        private String head_image;
        private String store_image;
        private String logo;
        private String service_content;
        private String share_image;
        private String store_id;
        private String store_name;

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getShare_image() {
            return share_image;
        }

        public void setShare_image(String share_image) {
            this.share_image = share_image;
        }



        public int getKanjia_count() {
            return kanjia_count;
        }

        public void setKanjia_count(int kanjia_count) {
            this.kanjia_count = kanjia_count;
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

        public String getStore_image() {
            return store_image;
        }

        public void setStore_image(String store_image) {
            this.store_image = store_image;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getService_content() {
            return service_content;
        }

        public void setService_content(String service_content) {
            this.service_content = service_content;
        }

        public float getOld_price() {
            return old_price;
        }

        public void setOld_price(float old_price) {
            this.old_price = old_price;
        }

        public double getMax_discount() {
            return max_discount;
        }

        public void setMax_discount(double max_discount) {
            this.max_discount = max_discount;
        }

        public int getKanjia_id() {
            return kanjia_id;
        }

        public void setKanjia_id(int kanjia_id) {
            this.kanjia_id = kanjia_id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public float getDiscount_price() {
            return discount_price;
        }

        public void setDiscount_price(float discount_price) {
            this.discount_price = discount_price;
        }

        public int getCutdown_percent() {
            return cutdown_percent;
        }

        public void setCutdown_percent(int cutdown_percent) {
            this.cutdown_percent = cutdown_percent;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getCutdown_price() {
            return cutdown_price;
        }

        public void setCutdown_price(int cutdown_price) {
            this.cutdown_price = cutdown_price;
        }
    }
}
