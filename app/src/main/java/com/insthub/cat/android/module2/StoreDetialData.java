package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linux on 2018/6/4.
 */

public class StoreDetialData extends BaseData {


    /**
     * service_id : 20
     * phone : 13913399521
     * is_save : 0
     * lng : 118.78379463785205
     * service_name : 税务代理
     * contacts : 庄文娟
     * is_collect : 0
     * share : {"share_image":"http://www.qitengteng.com:8080/upload/store/tl_service_1.jpg","share_url":"http://www.qitengteng.com:8080/app/app/store/storedetail.do?store_id=66e9b0ad500d45df8972d098bd7fbb18","share_desc":"税务代理","share_title":"南京天朗企业管理咨询有限公司"}
     * label_lv1 : 1
     * is_recom : 0
     * label_lv2 : 15,16,17,18,19,20
     * user_id : 61f4ef0ccbc74ec5ad6d4bd88701a378
     * bank :
     * bank_account :
     * store_name : 南京天朗企业管理咨询有限公司
     * lat : 32.0497293688927
     * logo : http://www.qitengteng.com:8080/upload/store/tl_logo.jpg
     * is_auth : 0
     * image_list : ["http://www.qitengteng.com/upload/store/0e670d74aced47d1b247ad3f3bce7246.jpg","http://www.qitengteng.com/upload/store/340e7c4858a74b6ba269ad900d4cc97e.jpg","http://www.qitengteng.com:8080/upload/store/a798aec84b134894a95210694919c4a1.jpg"]
     * store_id : 66e9b0ad500d45df8972d098bd7fbb18
     * banner_list : ["http://www.qitengteng.com/upload/store/cb0f2830399348528630befd3c9bd3da.jpg","http://www.qitengteng.com:8080/upload/store/2d0d3b0ee7784c67a527be93f3e1373b.jpg","http://www.qitengteng.com:8080/upload/store/08f77a8a41764abb86743b080ec6cc5f.jpg"]
     * service_advantage : 专业高效
     * price : 3342
     * address : 南京鼓楼爱德商务中心4楼
     * create_time : 2018-10-08 15:47:35
     * is_del : 0
     * im_code :
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private String service_id;
        private String phone;
        private String is_save;
        private String lng;
        private String service_name;
        private String contacts;
        private int  is_collect;
        /**
         * share_image : http://www.qitengteng.com:8080/upload/store/tl_service_1.jpg
         * share_url : http://www.qitengteng.com:8080/app/app/store/storedetail.do?store_id=66e9b0ad500d45df8972d098bd7fbb18
         * share_desc : 税务代理
         * share_title : 南京天朗企业管理咨询有限公司
         */

        private ShareBean share;
        private String label_lv1;
        private String is_recom;
        private String label_lv2;
        private String user_id;
        private String bank;
        private String bank_account;
        private String store_name;
        private String lat;
        private String logo;
        private String is_auth;
        private String store_id;
        private String service_advantage;
        private float price;
        private String address;
        private String create_time;
        private String is_del;
        private String im_code;
        private List<String> image_list;
        private List<String> banner_list;

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

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

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
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

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
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
    }
}
