package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.util.List;

/**
 * Created by linux on 2018/6/4.
 */

public class PubuListData extends BaseData {


    /**
     * count : 5
     * list : [{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"0.01","detail":"专业的财务管理","is_auth":"0","is_save":"0","image_list":["http://139.196.92.19:8080/upload/banner/news1.png","http://139.196.92.19:8080/upload/banner/news2.png"],"store_id":"6219a1b4c8b249acaf68936247c567d2","lng":"118.782255","banner_list":["http://139.196.92.19:8080/upload/banner/banner2.png","http://139.196.92.19:8080/upload/banner/banner2.png"],"service_content":"IT开发,工具应用,云通讯存储,行业方案,云计算,数据服务,微信开发,企业安全","distance":0,"address":"江苏省南京市鼓楼区中山北路","label_lv1":"3","create_time":"2018-04-16 14:07:12","is_del":"0","label_lv2":"28,29,30,31,32,33,34,35","is_recom":"1","user_id":"2d58e4b1fe0f494591737febda755c09","store_name":"测试","order_count":0,"lat":"32.071728"}]
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int count;
        /**
         * logo : http://139.196.92.19:8080/upload/banner/logo.png
         * service_price : 0.01
         * detail : 专业的财务管理
         * is_auth : 0
         * is_save : 0
         * image_list : ["http://139.196.92.19:8080/upload/banner/news1.png","http://139.196.92.19:8080/upload/banner/news2.png"]
         * store_id : 6219a1b4c8b249acaf68936247c567d2
         * lng : 118.782255
         * banner_list : ["http://139.196.92.19:8080/upload/banner/banner2.png","http://139.196.92.19:8080/upload/banner/banner2.png"]
         * service_content : IT开发,工具应用,云通讯存储,行业方案,云计算,数据服务,微信开发,企业安全
         * distance : 0
         * address : 江苏省南京市鼓楼区中山北路
         * label_lv1 : 3
         * create_time : 2018-04-16 14:07:12
         * is_del : 0
         * label_lv2 : 28,29,30,31,32,33,34,35
         * is_recom : 1
         * user_id : 2d58e4b1fe0f494591737febda755c09
         * store_name : 测试
         * order_count : 0
         * lat : 32.071728
         */

        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String logo;
            private String service_price;
            private String detail;
            private String is_auth;
            private String is_save;
            private String store_id;
            private String lng;
            private String service_content;
            private int distance;
            private String address;
            private String label_lv1;
            private String create_time;
            private String is_del;
            private String label_lv2;
            private String is_recom;
            private String user_id;
            private String store_name;
            private int order_count;
            private String lat;
            private List<String> image_list;
            private List<String> banner_list;

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

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
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
}