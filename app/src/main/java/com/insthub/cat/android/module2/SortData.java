package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/20 20:39
 * Desc:${DESC}
 */

public class SortData extends BaseData {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sort_rule_key : 001
         * sort_rule_value : 月销量
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String sort_rule_key;
            private String sort_rule_value;

            public String getSort_rule_key() {
                return sort_rule_key;
            }

            public void setSort_rule_key(String sort_rule_key) {
                this.sort_rule_key = sort_rule_key;
            }

            public String getSort_rule_value() {
                return sort_rule_value;
            }

            public void setSort_rule_value(String sort_rule_value) {
                this.sort_rule_value = sort_rule_value;
            }
        }
    }
}
