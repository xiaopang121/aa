package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.List;

/**
 * 砍价详情
 * Created by linux on 2018/6/4.
 */

public class CutdownDetialData extends BaseData {


    /**
     * status : 10001
     * data : {"phone":"13913040976","is_save":"0","lng":"118.76625636182112","contacts":"张三丰","service_content":"客户服务,协同OA,供应链,行业软件,管理顾问,法务服务","share":{"share_image":"http://139.196.92.19:8080/upload/store/46ae506c211e4c62b659808cea7f9798.jpg","share_url":"http://www.qitengteng.com:8080/app/app/app/store/storedetail.do?store_id=31df6d2f5aa740f290ac00830d4675f5","share_desc":"客户服务,协同OA,供应链,行业软件,管理顾问,法务服务","share_title":"宇宙神店"},"discount_price":"3000","label_lv1":"5","is_recom":"0","label_lv2":"45,46,47,48,49,50","user_id":"0bb7714890204418913a5f07776051bc","bank":"工商银行","bank_account":"12339817757400001355","store_name":"宇宙神店","activity":{"activity_id":1,"begin_time":"2018-07-30","discount_num":"0","store_id":"31df6d2f5aa740f290ac00830d4675f5","activity_name":"活动测试","remarks":"活动测试","cutdown_price":"2000","type":"1","num":"0","old_price":"5000","end_time":"2018-08-15","initiator_num":"5","create_time":"2018-07-26 09:53:58","money":"0","discount_price":"3000","order_count":0},"lat":"32.0858703153412","logo":"http://139.196.92.19:8080/upload/store/46ae506c211e4c62b659808cea7f9798.jpg","service_price":50000,"is_auth":"0","image_list":["http://139.196.92.19:8080/upload/store/30860c9f47c04c7ca61d38b3e6ce5dd8.jpg","http://139.196.92.19:8080/upload/store/0f1226ae2a72474bb509886d3f3cc878.jpg","http://139.196.92.19:8080/upload/store/5e4de57d9efd44799ad09641ef74b0f5.jpg","http://139.196.92.19:8080/upload/store/0f107e3412664793b3859a4dbb83a310.jpg","http://139.196.92.19:8080/upload/store/e51b1879489b489096e80c0f6cba8247.jpg"],"store_id":"31df6d2f5aa740f290ac00830d4675f5","banner_list":["http://139.196.92.19:8080/upload/store/964479c054fa4e06a4f0bda10becb000.jpg","http://139.196.92.19:8080/upload/store/92207cd1d6044689a79ad8e3f95e12da.jpg","http://139.196.92.19:8080/upload/store/8e82e9987a6040b482bfb1e14263c24f.jpg"],"service_advantage":"天下无敌。武功盖世，一统江湖。","address":"江苏省南京市鼓楼区中山北路","create_time":"2018-07-05 16:49:12","is_del":"0","im_code":"0bb7714890204418913a5f07776051bc"}
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
         * phone : 13913040976
         * is_save : 0
         * lng : 118.76625636182112
         * contacts : 张三丰
         * service_content : 客户服务,协同OA,供应链,行业软件,管理顾问,法务服务
         * share : {"share_image":"http://139.196.92.19:8080/upload/store/46ae506c211e4c62b659808cea7f9798.jpg","share_url":"http://www.qitengteng.com:8080/app/app/app/store/storedetail.do?store_id=31df6d2f5aa740f290ac00830d4675f5","share_desc":"客户服务,协同OA,供应链,行业软件,管理顾问,法务服务","share_title":"宇宙神店"}
         * discount_price : 3000
         * label_lv1 : 5
         * is_recom : 0
         * label_lv2 : 45,46,47,48,49,50
         * user_id : 0bb7714890204418913a5f07776051bc
         * bank : 工商银行
         * bank_account : 12339817757400001355
         * store_name : 宇宙神店
         * activity : {"activity_id":1,"begin_time":"2018-07-30","discount_num":"0","store_id":"31df6d2f5aa740f290ac00830d4675f5","activity_name":"活动测试","remarks":"活动测试","cutdown_price":"2000","type":"1","num":"0","old_price":"5000","end_time":"2018-08-15","initiator_num":"5","create_time":"2018-07-26 09:53:58","money":"0","discount_price":"3000","order_count":0}
         * lat : 32.0858703153412
         * logo : http://139.196.92.19:8080/upload/store/46ae506c211e4c62b659808cea7f9798.jpg
         * service_price : 50000
         * is_auth : 0
         * image_list : ["http://139.196.92.19:8080/upload/store/30860c9f47c04c7ca61d38b3e6ce5dd8.jpg","http://139.196.92.19:8080/upload/store/0f1226ae2a72474bb509886d3f3cc878.jpg","http://139.196.92.19:8080/upload/store/5e4de57d9efd44799ad09641ef74b0f5.jpg","http://139.196.92.19:8080/upload/store/0f107e3412664793b3859a4dbb83a310.jpg","http://139.196.92.19:8080/upload/store/e51b1879489b489096e80c0f6cba8247.jpg"]
         * store_id : 31df6d2f5aa740f290ac00830d4675f5
         * banner_list : ["http://139.196.92.19:8080/upload/store/964479c054fa4e06a4f0bda10becb000.jpg","http://139.196.92.19:8080/upload/store/92207cd1d6044689a79ad8e3f95e12da.jpg","http://139.196.92.19:8080/upload/store/8e82e9987a6040b482bfb1e14263c24f.jpg"]
         * service_advantage : 天下无敌。武功盖世，一统江湖。
         * address : 江苏省南京市鼓楼区中山北路
         * create_time : 2018-07-05 16:49:12
         * is_del : 0
         * im_code : 0bb7714890204418913a5f07776051bc
         */

        private String phone;
        private String is_save;
        private String lng;
        private String contacts;
        private String service_content;
        private ShareBean share;
        private String discount_price;
        private String label_lv1;
        private String is_recom;
        private String label_lv2;
        private String user_id;
        private String bank;
        private String bank_account;
        private String store_name;
        private ActivityBean activity;
        private String lat;
        private String logo;
        private String service_price;
        private String is_auth;
        private String store_id;
        private String service_advantage;
        private String address;
        private String create_time;
        private String is_del;
        private String im_code;
        private List<String> image_list;
        private List<String> banner_list;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIs_save() {
            return is_save;
        }

        public void setIs_save(String is_save) {
            this.is_save = is_save;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
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

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public String getDiscount_price() {
            return discount_price;
        }

        public void setDiscount_price(String discount_price) {
            this.discount_price = discount_price;
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

        public ActivityBean getActivity() {
            return activity;
        }

        public void setActivity(ActivityBean activity) {
            this.activity = activity;
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

        public String getService_price() {
            return service_price;
        }

        public void setService_price(String service_price) {
            this.service_price = service_price;
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

        public String getIm_code() {
            return im_code;
        }

        public void setIm_code(String im_code) {
            this.im_code = im_code;
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

        public static class ShareBean implements Serializable{
            /**
             * share_image : http://139.196.92.19:8080/upload/store/46ae506c211e4c62b659808cea7f9798.jpg
             * share_url : http://www.qitengteng.com:8080/app/app/app/store/storedetail.do?store_id=31df6d2f5aa740f290ac00830d4675f5
             * share_desc : 客户服务,协同OA,供应链,行业软件,管理顾问,法务服务
             * share_title : 宇宙神店
             */

            private String share_image;
            private String share_url;
            private String share_desc;
            private String share_title;

            public String getShare_image() {
                return share_image;
            }

            public void setShare_image(String share_image) {
                this.share_image = share_image;
            }

            public String getShare_url() {
                return share_url;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }

            public String getShare_desc() {
                return share_desc;
            }

            public void setShare_desc(String share_desc) {
                this.share_desc = share_desc;
            }

            public String getShare_title() {
                return share_title;
            }

            public void setShare_title(String share_title) {
                this.share_title = share_title;
            }
        }

        public static class ActivityBean implements Serializable{
            /**
             * activity_id : 1
             * begin_time : 2018-07-30
             * discount_num : 0
             * store_id : 31df6d2f5aa740f290ac00830d4675f5
             * activity_name : 活动测试
             * remarks : 活动测试
             * cutdown_price : 2000
             * type : 1
             * num : 0
             * old_price : 5000
             * end_time : 2018-08-15
             * initiator_num : 5
             * create_time : 2018-07-26 09:53:58
             * money : 0
             * discount_price : 3000
             * order_count : 0
             */

            private int activity_id;
            private String begin_time;
            private String discount_num;
            private String store_id;
            private String activity_name;
            private String remarks;
            private float cutdown_price;
            private String type;
            private String num;
            private float old_price;
            private String end_time;
            private String initiator_num;
            private String create_time;
            private float money;
            private float discount_price;
            private int order_count;

            public int getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(int activity_id) {
                this.activity_id = activity_id;
            }

            public String getBegin_time() {
                return begin_time;
            }

            public void setBegin_time(String begin_time) {
                this.begin_time = begin_time;
            }

            public String getDiscount_num() {
                return discount_num;
            }

            public void setDiscount_num(String discount_num) {
                this.discount_num = discount_num;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getActivity_name() {
                return activity_name;
            }

            public void setActivity_name(String activity_name) {
                this.activity_name = activity_name;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public float getCutdown_price() {
                return cutdown_price;
            }

            public void setCutdown_price(float cutdown_price) {
                this.cutdown_price = cutdown_price;
            }

            public float getOld_price() {
                return old_price;
            }

            public void setOld_price(float old_price) {
                this.old_price = old_price;
            }

            public float getMoney() {
                return money;
            }

            public void setMoney(float money) {
                this.money = money;
            }

            public float getDiscount_price() {
                return discount_price;
            }

            public void setDiscount_price(float discount_price) {
                this.discount_price = discount_price;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }



            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getInitiator_num() {
                return initiator_num;
            }

            public void setInitiator_num(String initiator_num) {
                this.initiator_num = initiator_num;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
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
