package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

/**
 * Created by linux on 2018/6/4.
 */

public class OrderDetialData extends BaseData {


    /**
     * logo : http://139.196.92.19:8080/upload/banner/logo.png
     * service_price : 0.01
     * state : 0
     * store_id : 13e18995c098416fb1bd1aeaa16b0bb9
     * remarks :
     * order_id : 20180615163473
     * service_time : 2018-06-15,2020-06-15
     * service_content : 资产评估
     * user_name : 祖师爷
     * head_image : http://139.196.92.19:8080/upload/head/13adefecc5ec475eb33755c65549c2f6.png
     * create_time : 2018-06-15 20:28:09
     * user_id : 1c62fe0f9b6f46b8805d43a7c93ab2ce
     * store_name : 测试店铺-6号
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String logo;
        private String service_price;
        private int state;
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
        private String phone;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

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

        public int getState() {
            return state;
        }

        public void setState(int state) {
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
