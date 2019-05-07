package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/20 20:39
 * Desc:${DESC}
 */

public class UserDeductionData extends BaseData {


    /**
     * count : 1
     * list : [{"use_time":"","state":"0","create_time":"2018-04-09 15:39:51","limit_money":"5000.00","money":"200.00","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","type":"0","deduction_id":2,"limit_time":""}]
     * page_count : 1
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
         * use_time :
         * state : 0
         * create_time : 2018-04-09 15:39:51
         * limit_money : 5000.00
         * money : 200.00
         * user_id : 1c62fe0f9b6f46b8805d43a7c93ab2ce
         * type : 0
         * deduction_id : 2
         * limit_time :
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

        public static class ListBean {
            private String use_time;
            private int state;
            private String create_time;
            private String limit_money;
            private String money;
            private String user_id;
            private String type;
            private int deduction_id;
            private String limit_time;

            public String getUse_time() {
                return use_time;
            }

            public void setUse_time(String use_time) {
                this.use_time = use_time;
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

            public String getLimit_money() {
                return limit_money;
            }

            public void setLimit_money(String limit_money) {
                this.limit_money = limit_money;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getDeduction_id() {
                return deduction_id;
            }

            public void setDeduction_id(int deduction_id) {
                this.deduction_id = deduction_id;
            }

            public String getLimit_time() {
                return limit_time;
            }

            public void setLimit_time(String limit_time) {
                this.limit_time = limit_time;
            }
        }
    }
}
