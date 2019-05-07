package com.insthub.cat.android.module;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linux on 2017/11/10.
 */

public class CouponListData extends BaseData {



    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * use_time :
             * state : 0
             * create_time : 2018-04-09 15:39:51
             * money : 200
             * user_id : 1c62fe0f9b6f46b8805d43a7c93ab2ce
             * type : 0
             * deduction_id : 2
             */

            private String use_time;
            private int state;
            private String create_time;
            private int money;
            private String user_id;
            private String type;
            private String deduction_id;
            private String limit_time;
            private String limit_money;

            public String getLimit_time() {
                return limit_time;
            }

            public void setLimit_time(String limit_time) {
                this.limit_time = limit_time;
            }

            public String getLimit_money() {
                return limit_money;
            }

            public void setLimit_money(String limit_money) {
                this.limit_money = limit_money;
            }

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

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
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

            public String getDeduction_id() {
                return deduction_id;
            }

            public void setDeduction_id(String deduction_id) {
                this.deduction_id = deduction_id;
            }
        }
    }
}
