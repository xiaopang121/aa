package com.insthub.cat.android.module;

import java.io.Serializable;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class TenderListData extends BaseData {


    /**
     * count : 1
     * list : [{"title":"app开发","area":"鼓楼区","address":"鼓楼区XXX路aaa号bbb","end_time":"2018-11-05","province":"江苏省","service":"代理记账","create_time":"2018-11-02 10:30:47","tender_id":"cc3dce01cf65433fa8c7fa69aa1a2a3d","type":"1","city":"南京市"}]
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
         * title : app开发
         * area : 鼓楼区
         * address : 鼓楼区XXX路aaa号bbb
         * end_time : 2018-11-05
         * province : 江苏省
         * service : 代理记账
         * create_time : 2018-11-02 10:30:47
         * tender_id : cc3dce01cf65433fa8c7fa69aa1a2a3d
         * type : 1
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

        public static class ListBean  implements Serializable{
            private String title;
            private String area;
            private String address;
            private String end_time;
            private String province;
            private String service;
            private String create_time;
            private String tender_id;
            private String type;
            private String city;
            private String create_time_desc;
            private String state;
            private String detail;
            private int  join_count;

            public int getJoin_count() {
                return join_count;
            }

            public void setJoin_count(int join_count) {
                this.join_count = join_count;
            }
            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getCreate_time_desc() {
                return create_time_desc;
            }

            public void setCreate_time_desc(String create_time_desc) {
                this.create_time_desc = create_time_desc;
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
