package com.insthub.cat.android.module;

import java.io.Serializable;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class BidPriceListData extends BaseData {


    /**
     * count : 2
     * list : [{"title":"找个常年代账","area":"鼓楼区","address":"南京市鼓楼区","end_time":"2018-11-11 12:00:00","state":"0","province":"江苏省","service":"代理记账","create_time":"2018-10-31 09:33:28","tender_id":"34f0698faf9b45a8b1edc98d93cc7ea6","re_days":9,"type":"0","city":"南京市"},{"title":"找个常年代账","area":"鼓楼区","address":"南京市鼓楼区","end_time":"2018-11-11 12:00:00","state":"0","province":"江苏省","service":"代理记账","create_time":"2018-10-31 14:58:52","tender_id":"9da8868ea9f7482fbe0944226b1ca1e5","re_days":9,"type":"0","city":"南京市"}]
     * page_count : 1
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private int count;
        private int page_count;
        /**
         * title : 找个常年代账
         * area : 鼓楼区
         * address : 南京市鼓楼区
         * end_time : 2018-11-11 12:00:00
         * state : 0
         * province : 江苏省
         * service : 代理记账
         * create_time : 2018-10-31 09:33:28
         * tender_id : 34f0698faf9b45a8b1edc98d93cc7ea6
         * re_days : 9
         * type : 0
         * city : 南京市
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

        public static class ListBean implements Serializable{
            private String title;
            private String area;
            private String address;
            private String end_time;
            private String state;
            private String province;
            private String service;
            private String create_time;
            private String tender_id;
            private int re_days;
            private String type;
            private String city;
            private int  join_count;

            public int getJoin_count() {
                return join_count;
            }

            public void setJoin_count(int join_count) {
                this.join_count = join_count;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getService() {
                return service;
            }

            public void setService(String service) {
                this.service = service;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getTender_id() {
                return tender_id;
            }

            public void setTender_id(String tender_id) {
                this.tender_id = tender_id;
            }

            public int getRe_days() {
                return re_days;
            }

            public void setRe_days(int re_days) {
                this.re_days = re_days;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }
    }
}
