package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/20 20:39
 * Desc:${DESC}
 */

public class DiscoverStoreData extends BaseData {


    /**
     * status : 10001
     * data : {"count":2,"list":[{"idcard_front":"","phone":"13770733624","detail":"","is_save":"1","score":4.5,"lng":"118.778936","business_license":"","contacts":"几楼涂","service_content":"税务筹划,融资服","distance":15,"label_lv1":"1","is_recom":"1","label_lv2":"13,15,16","user_id":"6cae853662aa452593fb6fd080dee3bc","bank":"农业银行","bank_account":"62284803652483","store_name":"咯啦咯啦咯","lat":"32.074875","logo":"http://139.196.92.19:8080/upload/head/5e231ba2a3444068980ac50d6f117264.png","service_price":1,"idcard_back":"","is_auth":"1","image_list":["http://139.196.92.19:8080/upload/head/image3-1.png","http://139.196.92.19:8080/upload/head/image3-2.png","http://139.196.92.19:8080/upload/head/image3-3.png"],"store_id":"c18eeba47b7740579c04f2a175881003","banner_list":["http://139.196.92.19:8080/upload/head/banner3-1.png","http://139.196.92.19:8080/upload/head/banner3-2.png","http://139.196.92.19:8080/upload/head/banner3-3.png"],"service_advantage":"你lol咯考虑兔兔too哦哦","address":"1222222222222","create_time":"2018-06-28 11:27:13","is_del":"0","order_count":3},{"idcard_front":"","phone":"0000000","detail":"hhhh","is_save":"0","lng":"118.782331","business_license":"","contacts":"0000","service_content":"记账工商,税务筹划,会计审计","distance":467,"label_lv1":"1","is_recom":"0","label_lv2":"12,13,14","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","bank":"111111","bank_account":"111111111","store_name":"个HAJX","lat":"32.071862","logo":"http://139.196.92.19:8080/upload/head/c1e70d3fddfc4554b0220ad46b3021e4.png","service_price":8888888,"idcard_back":"","is_auth":"0","image_list":["http://139.196.92.19:8080/upload/banner/2bcfd29a2648486591e2775ea9468816.png","http://139.196.92.19:8080/upload/banner/1bc4f9dd5b074c39bd6219f4b9b7ace1.png","http://139.196.92.19:8080/upload/banner/61b697baa01f4439a88a54c560466718.png"],"store_id":"8ff1c172e3344ea2b43242d67e7ad648","banner_list":["http://139.196.92.19:8080/upload/banner/2bcfd29a2648486591e2775ea9468816.png","http://139.196.92.19:8080/upload/banner/1bc4f9dd5b074c39bd6219f4b9b7ace1.png","http://139.196.92.19:8080/upload/banner/61b697baa01f4439a88a54c560466718.png"],"address":"123456789","create_time":"2018-07-02 10:54:58","is_del":"0","order_count":0}],"page_count":1}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean  implements Serializable{
        /**
         * count : 2
         * list : [{"idcard_front":"","phone":"13770733624","detail":"","is_save":"1","score":4.5,"lng":"118.778936","business_license":"","contacts":"几楼涂","service_content":"税务筹划,融资服","distance":15,"label_lv1":"1","is_recom":"1","label_lv2":"13,15,16","user_id":"6cae853662aa452593fb6fd080dee3bc","bank":"农业银行","bank_account":"62284803652483","store_name":"咯啦咯啦咯","lat":"32.074875","logo":"http://139.196.92.19:8080/upload/head/5e231ba2a3444068980ac50d6f117264.png","service_price":1,"idcard_back":"","is_auth":"1","image_list":["http://139.196.92.19:8080/upload/head/image3-1.png","http://139.196.92.19:8080/upload/head/image3-2.png","http://139.196.92.19:8080/upload/head/image3-3.png"],"store_id":"c18eeba47b7740579c04f2a175881003","banner_list":["http://139.196.92.19:8080/upload/head/banner3-1.png","http://139.196.92.19:8080/upload/head/banner3-2.png","http://139.196.92.19:8080/upload/head/banner3-3.png"],"service_advantage":"你lol咯考虑兔兔too哦哦","address":"1222222222222","create_time":"2018-06-28 11:27:13","is_del":"0","order_count":3},{"idcard_front":"","phone":"0000000","detail":"hhhh","is_save":"0","lng":"118.782331","business_license":"","contacts":"0000","service_content":"记账工商,税务筹划,会计审计","distance":467,"label_lv1":"1","is_recom":"0","label_lv2":"12,13,14","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","bank":"111111","bank_account":"111111111","store_name":"个HAJX","lat":"32.071862","logo":"http://139.196.92.19:8080/upload/head/c1e70d3fddfc4554b0220ad46b3021e4.png","service_price":8888888,"idcard_back":"","is_auth":"0","image_list":["http://139.196.92.19:8080/upload/banner/2bcfd29a2648486591e2775ea9468816.png","http://139.196.92.19:8080/upload/banner/1bc4f9dd5b074c39bd6219f4b9b7ace1.png","http://139.196.92.19:8080/upload/banner/61b697baa01f4439a88a54c560466718.png"],"store_id":"8ff1c172e3344ea2b43242d67e7ad648","banner_list":["http://139.196.92.19:8080/upload/banner/2bcfd29a2648486591e2775ea9468816.png","http://139.196.92.19:8080/upload/banner/1bc4f9dd5b074c39bd6219f4b9b7ace1.png","http://139.196.92.19:8080/upload/banner/61b697baa01f4439a88a54c560466718.png"],"address":"123456789","create_time":"2018-07-02 10:54:58","is_del":"0","order_count":0}]
         * page_count : 1
         */

        private int count;
        private int page_count;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage_count() {
            return page_count;
        }

        public void setPage_count(int page_count) {
            this.page_count = page_count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * idcard_front :
             * phone : 13770733624
             * detail :
             * is_save : 1
             * score : 4.5
             * lng : 118.778936
             * business_license :
             * contacts : 几楼涂
             * service_content : 税务筹划,融资服
             * distance : 15
             * label_lv1 : 1
             * is_recom : 1
             * label_lv2 : 13,15,16
             * user_id : 6cae853662aa452593fb6fd080dee3bc
             * bank : 农业银行
             * bank_account : 62284803652483
             * store_name : 咯啦咯啦咯
             * lat : 32.074875
             * logo : http://139.196.92.19:8080/upload/head/5e231ba2a3444068980ac50d6f117264.png
             * service_price : 1
             * idcard_back :
             * is_auth : 1
             * image_list : ["http://139.196.92.19:8080/upload/head/image3-1.png","http://139.196.92.19:8080/upload/head/image3-2.png","http://139.196.92.19:8080/upload/head/image3-3.png"]
             * store_id : c18eeba47b7740579c04f2a175881003
             * banner_list : ["http://139.196.92.19:8080/upload/head/banner3-1.png","http://139.196.92.19:8080/upload/head/banner3-2.png","http://139.196.92.19:8080/upload/head/banner3-3.png"]
             * service_advantage : 你lol咯考虑兔兔too哦哦
             * address : 1222222222222
             * create_time : 2018-06-28 11:27:13
             * is_del : 0
             * order_count : 3
             */

            private String idcard_front;
            private String phone;
            private String detail;
            private String is_save;
            private float score;
            private String lng;
            private String business_license;
            private String contacts;
            private String service_content;
            private int distance;
            private String label_lv1;
            private String is_recom;
            private String label_lv2;
            private String user_id;
            private String bank;
            private String bank_account;
            private String store_name;
            private String lat;
            private String logo;
            private int service_price;
            private String idcard_back;
            private String is_auth;
            private String store_id;
            private String service_advantage;
            private String address;
            private String create_time;
            private String is_del;
            private int order_count;
            private List<String> image_list;
            private List<String> banner_list;
            private String service_name;

            private String service_id;
            private String image;

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }

            public ListBean()
            {

            }


            public ListBean(String pstore_id)
            {
                this.store_id = pstore_id;
            }


            public String getIdcard_front() {
                return idcard_front;
            }

            public void setIdcard_front(String idcard_front) {
                this.idcard_front = idcard_front;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getIs_save() {
                return is_save;
            }

            public void setIs_save(String is_save) {
                this.is_save = is_save;
            }

            public float getScore() {
                return score;
            }

            public void setScore(float score) {
                this.score = score;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getBusiness_license() {
                return business_license;
            }

            public void setBusiness_license(String business_license) {
                this.business_license = business_license;
            }

            public String getContacts() {
                return contacts;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
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

            public String getLabel_lv1() {
                return label_lv1;
            }

            public void setLabel_lv1(String label_lv1) {
                this.label_lv1 = label_lv1;
            }

            public String getIs_recom() {
                return is_recom;
            }

            public void setIs_recom(String is_recom) {
                this.is_recom = is_recom;
            }

            public String getLabel_lv2() {
                return label_lv2;
            }

            public void setLabel_lv2(String label_lv2) {
                this.label_lv2 = label_lv2;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getBank() {
                return bank;
            }

            public void setBank(String bank) {
                this.bank = bank;
            }

            public String getBank_account() {
                return bank_account;
            }

            public void setBank_account(String bank_account) {
                this.bank_account = bank_account;
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

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getService_price() {
                return service_price;
            }

            public void setService_price(int service_price) {
                this.service_price = service_price;
            }

            public String getIdcard_back() {
                return idcard_back;
            }

            public void setIdcard_back(String idcard_back) {
                this.idcard_back = idcard_back;
            }

            public String getIs_auth() {
                return is_auth;
            }

            public void setIs_auth(String is_auth) {
                this.is_auth = is_auth;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getService_advantage() {
                return service_advantage;
            }

            public void setService_advantage(String service_advantage) {
                this.service_advantage = service_advantage;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public int getOrder_count() {
                return order_count;
            }

            public void setOrder_count(int order_count) {
                this.order_count = order_count;
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
}
