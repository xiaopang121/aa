package com.insthub.cat.android.module;


import java.io.Serializable;
import java.util.List;

/**
 * Created by linux on 2017/11/10.
 */

public class CollectListData extends BaseData {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * logo : http://www.qitengteng.com:8080/upload/store/logo18_1.jpg
         * service_content : 供应链
         * distance : 20586
         * is_auth : 0
         * is_save : 1
         * create_time : 2018-09-14 23:27:30
         * is_recom : 0
         * store_id : be367dfa3e004cf68b0db0c303b41471
         * user_id : b5fbfde9fa5f464eab7198eb15bcc703
         * store_name : 家云电商
         * order_count : 0
         */

        private List<StoreListBean> store_list;
        /**
         * logo :
         * create_time : 2018-11-18 15:21:40
         * image :
         * user_id : b5fbfde9fa5f464eab7198eb15bcc703
         * order_count : 0
         */

        private List<ServiceListBean> service_list;

        public List<StoreListBean> getStore_list() {
            return store_list;
        }

        public void setStore_list(List<StoreListBean> store_list) {
            this.store_list = store_list;
        }

        public List<ServiceListBean> getService_list() {
            return service_list;
        }

        public void setService_list(List<ServiceListBean> service_list) {
            this.service_list = service_list;
        }

        public static class StoreListBean implements Serializable{
            private String logo;
            private String service_content;
            private int distance;
            private String is_auth;
            private String is_save;
            private String create_time;
            private String is_recom;
            private String store_id;
            private String user_id;
            private String store_name;
            private int order_count;

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

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public String getIs_auth() {
                return is_auth;
            }

            public void setIs_auth(String is_auth) {
                this.is_auth = is_auth;
            }

            public String getIs_save() {
                return is_save;
            }

            public void setIs_save(String is_save) {
                this.is_save = is_save;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getIs_recom() {
                return is_recom;
            }

            public void setIs_recom(String is_recom) {
                this.is_recom = is_recom;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
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

            public int getOrder_count() {
                return order_count;
            }

            public void setOrder_count(int order_count) {
                this.order_count = order_count;
            }
        }

        public static class ServiceListBean  implements Serializable{
            private String service_id;
            private String store_id;
            private String store_name;
            private double distance;
            private int score;
            private String is_auth;
            private String is_save;
            private String is_recom;
            private String service_name;


            private String logo;
            private String create_time;
            private String image;
            private String user_id;
            private int order_count;

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getIs_auth() {
                return is_auth;
            }

            public void setIs_auth(String is_auth) {
                this.is_auth = is_auth;
            }

            public String getIs_save() {
                return is_save;
            }

            public void setIs_save(String is_save) {
                this.is_save = is_save;
            }

            public String getIs_recom() {
                return is_recom;
            }

            public void setIs_recom(String is_recom) {
                this.is_recom = is_recom;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public int getOrder_count() {
                return order_count;
            }

            public void setOrder_count(int order_count) {
                this.order_count = order_count;
            }
        }
    }
}
