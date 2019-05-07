package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by linux on 2017/11/10.
 */

public class ShopOrderListData extends BaseData {


    /**
     * count : 37
     * list : [{"service_time":"2018-06-15,2020-06-15","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"资产评估","service_price":"0.01","state":"0","create_time":"2018-06-15 20:28:09","store_id":"13e18995c098416fb1bd1aeaa16b0bb9","remarks":"","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180615163473","store_name":"测试店铺-6号"}]
     * page_count : 2
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
        private int page_count;
        /**
         * service_time : 2018-06-15,2020-06-15
         * logo : http://139.196.92.19:8080/upload/banner/logo.png
         * service_content : 资产评估
         * service_price : 0.01
         * state : 0
         * create_time : 2018-06-15 20:28:09
         * store_id : 13e18995c098416fb1bd1aeaa16b0bb9
         * remarks :
         * user_id : 1c62fe0f9b6f46b8805d43a7c93ab2ce
         * order_id : 20180615163473
         * store_name : 测试店铺-6号
         */

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
            private String service_time;
            private String logo;
            private String service_content;
            private String service_price;
            private int state;
            private String create_time;
            private String store_id;
            private String remarks;
            private String user_id;
            private String order_id;
            private String store_name;
            private String user_name;
            private String money;

            private String service_name;

            private String finish_time;

            private String accid;

            public String getAccid() {
                return accid;
            }

            public void setAccid(String accid) {
                this.accid = accid;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }

            public String getFinish_time() {
                return finish_time;
            }

            public void setFinish_time(String finish_time) {
                this.finish_time = finish_time;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
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
        }
    }
}
