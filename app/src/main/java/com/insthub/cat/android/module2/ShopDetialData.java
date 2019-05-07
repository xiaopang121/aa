package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.List;

/**
 * User:macbook
 * DATE:2018/11/6 20:13
 * Desc:${DESC}
 */
public class ShopDetialData extends BaseData {


    /**
     * logo : store/logo5_1.jpg
     * is_collect : 0
     * share : {"share_image":"http://www.qitengteng.com:8080/upload/store/logo5_1.jpg","share_url":"http://www.qitengteng.com:8080/app/app/store/storedetail.do?store_id=fc14e419f1d249999919ca0ef0c46ca7","share_title":"富有资产"}
     * phone : 13978991366
     * service_list : [{"service_id":"1","sort":1,"price":"3000","store_id":"fc14e419f1d249999919ca0ef0c46ca7","image":"http://www.qitengteng.com:8080/upload/store/dh_service_1.jpg","service_name":"资产评估"},{"service_id":"2","sort":2,"price":"3000","store_id":"fc14e419f1d249999919ca0ef0c46ca7","image":"http://www.qitengteng.com:8080/upload/store/dh_service_2.jpg","service_name":"土地评估"},{"service_id":"3","sort":3,"price":"3000","store_id":"fc14e419f1d249999919ca0ef0c46ca7","image":"http://www.qitengteng.com:8080/upload/store/dh_service_3.jpg","service_name":"司法鉴定评估"},{"service_id":"4","sort":4,"price":"3000","store_id":"fc14e419f1d249999919ca0ef0c46ca7","image":"http://www.qitengteng.com:8080/upload/store/dh_service_4.jpg","service_name":"医疗设备评估"},{"service_id":"5","sort":5,"price":"3000","store_id":"fc14e419f1d249999919ca0ef0c46ca7","image":"http://www.qitengteng.com:8080/upload/store/dh_service_5.jpg","service_name":"教学设备评估"},{"service_id":"6","sort":6,"price":"3000","store_id":"fc14e419f1d249999919ca0ef0c46ca7","image":"http://www.qitengteng.com:8080/upload/store/dh_service_6.jpg","service_name":"买卖计税评估"}]
     * address : 江苏省南京市玄武区珠江路438号
     * store_id : fc14e419f1d249999919ca0ef0c46ca7
     * desc_image_list : []
     * store_name : 富有资产
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private String logo;
        private int is_collect;

        private String background_image;
        /**
         * share_image : http://www.qitengteng.com:8080/upload/store/logo5_1.jpg
         * share_url : http://www.qitengteng.com:8080/app/app/store/storedetail.do?store_id=fc14e419f1d249999919ca0ef0c46ca7
         * share_title : 富有资产
         */

        private ShareBean share;
        private String phone;
        private String address;
        private String store_id;
        private String store_name;
        private int collect_count;
        private String desc_detail;
        private String accid;

        public String getDesc_detail() {
            return desc_detail;
        }

        public void setDesc_detail(String desc_detail) {
            this.desc_detail = desc_detail;
        }

        public String getBackground_image() {
            return background_image;
        }

        public void setBackground_image(String background_image) {
            this.background_image = background_image;
        }

        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        /**
         * service_id : 1
         * sort : 1
         * price : 3000
         * store_id : fc14e419f1d249999919ca0ef0c46ca7
         * image : http://www.qitengteng.com:8080/upload/store/dh_service_1.jpg
         * service_name : 资产评估
         */



        private List<ServiceListBean> service_list;
        private List<String> desc_image_list;

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public List<ServiceListBean> getService_list() {
            return service_list;
        }

        public void setService_list(List<ServiceListBean> service_list) {
            this.service_list = service_list;
        }

        public List<String> getDesc_image_list() {
            return desc_image_list;
        }

        public void setDesc_image_list(List<String> desc_image_list) {
            this.desc_image_list = desc_image_list;
        }

        public static class ShareBean implements Serializable {
            private String share_image;
            private String share_url;
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

            public String getShare_title() {
                return share_title;
            }

            public void setShare_title(String share_title) {
                this.share_title = share_title;
            }
        }

        public static class ServiceListBean  implements Serializable {
            private String service_id;
            private int sort;
            private String price;
            private String store_id;
            private String image;
            private String service_name;

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
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
        }
    }
}
