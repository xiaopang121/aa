package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linux on 2018/6/4.
 */

public class ActionListData extends BaseData {


    /**
     * status : 10001
     * data : {"count":3,"list":[{"activity_id":5,"begin_time":"2018-07-30","discount_num":"0","store_id":"31df6d2f5aa740f290ac00830d4675f5","activity_name":"活动测试5","remarks":"","cutdown_price":"0.00","type":"3","num":"0","old_price":"5000","end_time":"2018-09-17","initiator_num":"0","create_time":"2018-07-26 09:53:58","money":"0.00","discount_price":"3000"},{"activity_id":6,"begin_time":"2018-07-30","discount_num":"0","store_id":"c18eeba47b7740579c04f2a175881003","activity_name":"活动测试6","remarks":"","cutdown_price":"0.00","type":"3","num":"0","old_price":"5000","end_time":"2018-09-17","initiator_num":"0","create_time":"2018-07-26 09:53:58","money":"0.00","discount_price":"3000"},{"activity_id":11,"begin_time":"2018-07-30","discount_num":"0","store_id":"d48fc243f54b4f9a8cb7ac60e955bcdd","activity_name":"测试活动11","remarks":"","cutdown_price":"0.00","type":"3","num":"0","old_price":"5000","end_time":"2018-08-15","initiator_num":"0","create_time":"2018-07-26 09:53:58","money":"0.00","discount_price":"3000"}],"page_count":1}
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * count : 3
         * list : [{"activity_id":5,"begin_time":"2018-07-30","discount_num":"0","store_id":"31df6d2f5aa740f290ac00830d4675f5","activity_name":"活动测试5","remarks":"","cutdown_price":"0.00","type":"3","num":"0","old_price":"5000","end_time":"2018-09-17","initiator_num":"0","create_time":"2018-07-26 09:53:58","money":"0.00","discount_price":"3000"},{"activity_id":6,"begin_time":"2018-07-30","discount_num":"0","store_id":"c18eeba47b7740579c04f2a175881003","activity_name":"活动测试6","remarks":"","cutdown_price":"0.00","type":"3","num":"0","old_price":"5000","end_time":"2018-09-17","initiator_num":"0","create_time":"2018-07-26 09:53:58","money":"0.00","discount_price":"3000"},{"activity_id":11,"begin_time":"2018-07-30","discount_num":"0","store_id":"d48fc243f54b4f9a8cb7ac60e955bcdd","activity_name":"测试活动11","remarks":"","cutdown_price":"0.00","type":"3","num":"0","old_price":"5000","end_time":"2018-08-15","initiator_num":"0","create_time":"2018-07-26 09:53:58","money":"0.00","discount_price":"3000"}]
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
             * activity_id : 5
             * begin_time : 2018-07-30
             * discount_num : 0
             * store_id : 31df6d2f5aa740f290ac00830d4675f5
             * activity_name : 活动测试5
             * remarks :
             * cutdown_price : 0.00
             * type : 3
             * num : 0
             * old_price : 5000
             * end_time : 2018-09-17
             * initiator_num : 0
             * create_time : 2018-07-26 09:53:58
             * money : 0.00
             * discount_price : 3000
             */

            private int activity_id;
            private String begin_time;
            private String discount_num;
            private String store_id;
            private String activity_name;
            private String remarks;
            private String cutdown_price;
            private int  type;
            private String num;
            private String old_price;
            private String end_time;
            private String initiator_num;
            private String create_time;
            private String money;
            private String discount_price;
            private float distance;
            private String logo;

            private float sold_percent; //秒杀

            private String total_discount_num; //秒杀

            public float getSold_percent() {
                return sold_percent;
            }

            public void setSold_percent(int sold_percent) {
                this.sold_percent = sold_percent;
            }

            public String getTotal_discount_num() {
                return total_discount_num;
            }

            public void setTotal_discount_num(String total_discount_num) {
                this.total_discount_num = total_discount_num;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public void setSold_percent(float sold_percent) {
                this.sold_percent = sold_percent;
            }

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

            public String  getDiscount_num() {
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

            public String getCutdown_price() {
                return cutdown_price;
            }

            public void setCutdown_price(String cutdown_price) {
                this.cutdown_price = cutdown_price;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getOld_price() {
                return old_price;
            }

            public void setOld_price(String old_price) {
                this.old_price = old_price;
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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getDiscount_price() {
                return discount_price;
            }

            public void setDiscount_price(String discount_price) {
                this.discount_price = discount_price;
            }

            public float getDistance() {
                return distance;
            }

            public void setDistance(float distance) {
                this.distance = distance;
            }
        }
    }
}
