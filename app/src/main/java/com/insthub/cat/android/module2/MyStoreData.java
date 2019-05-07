package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linux on 2018/6/4.
 */

public class MyStoreData extends BaseData {


    /**
     * logo : http://139.196.92.19:8080/upload/banner/logo.png
     * phone : 17361973617
     * service_price : 0.01
     * detail : 666666
     * is_auth : 0
     * is_save : 0
     * image_list : ["http://139.196.92.19:8080/upload/banner/news1.png","http://139.196.92.19:8080/upload/banner/news2.png"]
     * store_id : 13e18995c098416fb1bd1aeaa16b0bb9
     * lng : 118.782255
     * banner_list : ["http://139.196.92.19:8080/upload/banner/banner2.png","http://139.196.92.19:8080/upload/banner/banner2.png"]
     * service_content : 记账工商,税务筹划,会计审计,融资服务,资产评估,保险担保,金融科技,基金信托
     * address : 江苏省南京市鼓楼区中山北路
     * label_lv1 : 1
     * create_time : 2018-06-07 10:18:22
     * is_del : 0
     * label_lv2 : 12,13,14,15,16,17,18,19
     * is_recom : 0
     * im_code : 1c62fe0f9b6f46b8805d43a7c93ab2ce
     * user_id : 1c62fe0f9b6f46b8805d43a7c93ab2ce
     * store_name : 测试店铺-6号
     * lat : 32.071728
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String logo;
        private String phone;
        private String service_price;
        private String detail;
        private String is_auth;
        private String is_save;
        private String store_id;
        private String lng;
        private String service_content;
        private String address;
        private String label_lv1;
        private String create_time;
        private String is_del;
        private String label_lv2;
        private String is_recom;
        private String im_code;
        private String user_id;
        private String store_name;
        private String lat;
        private List<String> image_list;
        private List<String> banner_list;

        private String bank_account;

        private String bank;


        private String contacts;

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getBank_account() {
            return bank_account;
        }

        public void setBank_account(String bank_account) {
            this.bank_account = bank_account;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getService_price() {
            return service_price;
        }

        public void setService_price(String service_price) {
            this.service_price = service_price;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
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

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getService_content() {
            return service_content;
        }

        public void setService_content(String service_content) {
            this.service_content = service_content;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLabel_lv1() {
            return label_lv1;
        }

        public void setLabel_lv1(String label_lv1) {
            this.label_lv1 = label_lv1;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getLabel_lv2() {
            return label_lv2;
        }

        public void setLabel_lv2(String label_lv2) {
            this.label_lv2 = label_lv2;
        }

        public String getIs_recom() {
            return is_recom;
        }

        public void setIs_recom(String is_recom) {
            this.is_recom = is_recom;
        }

        public String getIm_code() {
            return im_code;
        }

        public void setIm_code(String im_code) {
            this.im_code = im_code;
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

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public List<String> getImage_list() {
            return image_list;
        }

        public void setImage_list(List<String> image_list) {
            this.image_list = image_list;
        }

        public List<String> getBanner_list() {
            return banner_list;
        }

        public void setBanner_list(List<String> banner_list) {
            this.banner_list = banner_list;
        }
    }
}
