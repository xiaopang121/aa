package com.insthub.cat.android.module;

/**
 * User:macbook
 * DATE:2017/12/20 23:15
 * Desc:${DESC}
 */

public class OrderInfoData extends BaseData{


    /**
     * orderInfoId : 6310de164ced4110948fc25b4f204d23
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String orderInfoId;

        public String getOrderInfoId() {
            return orderInfoId;
        }

        public void setOrderInfoId(String orderInfoId) {
            this.orderInfoId = orderInfoId;
        }
    }
}
