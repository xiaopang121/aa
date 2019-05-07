package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/20 20:39
 * Desc:${DESC}
 */

public class SameLevelLabelData extends BaseData {


    /**
     * list : [{"level":2,"lable_name":"特种装备","label_id":110,"store_count":0,"parent_id":1},{"level":2,"lable_name":"设备租赁","label_id":111,"store_count":0,"parent_id":1},{"level":2,"lable_name":"企业保险","label_id":112,"store_count":0,"parent_id":1},{"level":2,"lable_name":"办公租赁","label_id":113,"store_count":0,"parent_id":1},{"level":2,"lable_name":"代理记账","label_id":114,"store_count":0,"parent_id":1},{"level":2,"lable_name":"信用担保","label_id":115,"store_count":0,"parent_id":1},{"level":2,"lable_name":"企业保险","label_id":116,"store_count":0,"parent_id":1}]
     * store_count : 0
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int store_count;
        /**
         * level : 2
         * lable_name : 特种装备
         * label_id : 110
         * store_count : 0
         * parent_id : 1
         */

        private List<ListBean> list;

        public int getStore_count() {
            return store_count;
        }

        public void setStore_count(int store_count) {
            this.store_count = store_count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int level;
            private String lable_name;
            private int label_id;
            private int store_count;
            private int parent_id;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getLable_name() {
                return lable_name;
            }

            public void setLable_name(String lable_name) {
                this.lable_name = lable_name;
            }

            public int getLabel_id() {
                return label_id;
            }

            public void setLabel_id(int label_id) {
                this.label_id = label_id;
            }

            public int getStore_count() {
                return store_count;
            }

            public void setStore_count(int store_count) {
                this.store_count = store_count;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }
        }
    }
}
