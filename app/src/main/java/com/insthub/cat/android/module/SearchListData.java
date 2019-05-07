package com.insthub.cat.android.module;

import java.io.Serializable;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class SearchListData extends BaseData {


    /**
     * data : {"list":[{"gs_code":"3070402000000000000","count":2,"gs_id":4100,"bstore_id":"123123123","lg_id":1,"gs_name":"住宿服务","parent_id":4098}]}
     */

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

        public static class ListBean implements Serializable{
            /**
             * gs_code : 3070402000000000000
             * count : 2
             * gs_id : 4100
             * bstore_id : 123123123
             * lg_id : 1
             * gs_name : 住宿服务
             * parent_id : 4098
             */

            private String gs_code;
            private int count;
            private int gs_id;
            private String bstore_id;
            private int lg_id;
            private String gs_name;
            private int parent_id;

            public String getGs_code() {
                return gs_code;
            }

            public void setGs_code(String gs_code) {
                this.gs_code = gs_code;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getGs_id() {
                return gs_id;
            }

            public void setGs_id(int gs_id) {
                this.gs_id = gs_id;
            }

            public String getBstore_id() {
                return bstore_id;
            }

            public void setBstore_id(String bstore_id) {
                this.bstore_id = bstore_id;
            }

            public int getLg_id() {
                return lg_id;
            }

            public void setLg_id(int lg_id) {
                this.lg_id = lg_id;
            }

            public String getGs_name() {
                return gs_name;
            }

            public void setGs_name(String gs_name) {
                this.gs_name = gs_name;
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
