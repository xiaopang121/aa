package com.insthub.cat.android.module;

import java.io.Serializable;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class InnovateListData extends BaseData {


    /**
     * count : 1
     * list : [{"title":"征集企业logo","create_time":"2018-10-31 11:39:17","money":5000,"state":"0","image":"http://www.qitengteng.com:8080/upload/store/chuangxingji_bg5.png","tender_id":"ab55fc44fc68417ea4ca567eb2ad2b46"}]
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
         * title : 征集企业logo
         * create_time : 2018-10-31 11:39:17
         * money : 5000
         * state : 0
         * image : http://www.qitengteng.com:8080/upload/store/chuangxingji_bg5.png
         * tender_id : ab55fc44fc68417ea4ca567eb2ad2b46
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
            private String title;
            private String create_time;
            private int money;
            private String state;
            private String image;
            private String tender_id;
            private String end_time;
            private int  join_count;

            public int getJoin_count() {
                return join_count;
            }

            public void setJoin_count(int join_count) {
                this.join_count = join_count;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getTender_id() {
                return tender_id;
            }

            public void setTender_id(String tender_id) {
                this.tender_id = tender_id;
            }
        }
    }
}
