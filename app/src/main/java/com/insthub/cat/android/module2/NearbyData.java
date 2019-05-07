package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/20 20:39
 * Desc:${DESC}
 */

public class NearbyData extends BaseData {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> list_area;
        /**
         * param_value : 1千米
         * param_key : 1000
         */

        private List<ListDistanceBean> list_distance;

        public List<String> getList_area() {
            return list_area;
        }

        public void setList_area(List<String> list_area) {
            this.list_area = list_area;
        }

        public List<ListDistanceBean> getList_distance() {
            return list_distance;
        }

        public void setList_distance(List<ListDistanceBean> list_distance) {
            this.list_distance = list_distance;
        }

        public static class ListDistanceBean {
            private String param_value;
            private String param_key;

            public String getParam_value() {
                return param_value;
            }

            public void setParam_value(String param_value) {
                this.param_value = param_value;
            }

            public String getParam_key() {
                return param_key;
            }

            public void setParam_key(String param_key) {
                this.param_key = param_key;
            }
        }
    }
}
