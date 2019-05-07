package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class UseOrderListData extends BaseData {


    /**
     * status : 10001
     * data : {"count":24,"list":[{"service_time":"2018-6-01--2018-7-01","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP规划","service_price":"3000","state":"0","create_time":"2018-06-01 09:57:35","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180601969387","store_name":"测试店铺-1号"},{"service_time":"2018-6-01--2018-7-01","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP\\U89c4\\U5212","service_price":"3000","state":"0","create_time":"2018-06-01 09:56:14","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"\\U65e0","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180601154626","store_name":"测试店铺-1号"},{"service_time":"2018-6-01--2018-7-01","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP\\U89c4\\U5212","service_price":"3000","state":"0","create_time":"2018-06-01 09:55:32","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"\\U65e0","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180601891477","store_name":"测试店铺-1号"},{"service_time":"2018-6-01--2018-7-01","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP\\U89c4\\U5212","service_price":"3000","state":"0","create_time":"2018-06-01 09:46:25","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"\\U65e0","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180601500325","store_name":"测试店铺-1号"}],"page_count":2}
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
         * count : 24
         * list : [{"service_time":"2018-6-01--2018-7-01","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP规划","service_price":"3000","state":"0","create_time":"2018-06-01 09:57:35","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180601969387","store_name":"测试店铺-1号"},{"service_time":"2018-6-01--2018-7-01","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP\\U89c4\\U5212","service_price":"3000","state":"0","create_time":"2018-06-01 09:56:14","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"\\U65e0","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180601154626","store_name":"测试店铺-1号"},{"service_time":"2018-6-01--2018-7-01","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP\\U89c4\\U5212","service_price":"3000","state":"0","create_time":"2018-06-01 09:55:32","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"\\U65e0","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180601891477","store_name":"测试店铺-1号"},{"service_time":"2018-6-01--2018-7-01","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP\\U89c4\\U5212","service_price":"3000","state":"0","create_time":"2018-06-01 09:46:25","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"\\U65e0","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180601500325","store_name":"测试店铺-1号"}]
         * page_count : 2
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
             * service_time : 2018-6-01--2018-7-01
             * logo : http://139.196.92.19:8080/upload/banner/logo.png
             * service_content : BP规划
             * service_price : 3000
             * state : 0
             * create_time : 2018-06-01 09:57:35
             * store_id : 7e81d46e621245958f95fe0f3e424e0d
             * remarks : 无
             * user_id : 1c62fe0f9b6f46b8805d43a7c93ab2ce
             * order_id : 20180601969387
             * store_name : 测试店铺-1号
             */

            private String service_time;
            private String logo;
            private String service_content;
            private String service_price;
            private int  state;
            private String create_time;
            private String store_id;
            private String remarks;
            private String user_id;
            private String order_id;
            private String store_name;
            private int type;
            private String pintuan_id;
            private String leader;
            private String activity_id;
            private String kanjia_id;
            private String service_id;
            private String service_name;
            private String service_image;
            private int  count;
            private String accid;

            public String getAccid() {
                return accid;
            }

            public void setAccid(String accid) {
                this.accid = accid;
            }

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }

            public String getService_image() {
                return service_image;
            }

            public void setService_image(String service_image) {
                this.service_image = service_image;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getService_time() {
                return service_time;
            }

            public void setService_time(String service_time) {
                this.service_time = service_time;
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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
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

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getPintuan_id() {
                return pintuan_id;
            }

            public void setPintuan_id(String pintuan_id) {
                this.pintuan_id = pintuan_id;
            }

            public String getLeader() {
                return leader;
            }

            public void setLeader(String leader) {
                this.leader = leader;
            }

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }

            public String getKanjia_id() {
                return kanjia_id;
            }

            public void setKanjia_id(String kanjia_id) {
                this.kanjia_id = kanjia_id;
            }
        }
    }
}
