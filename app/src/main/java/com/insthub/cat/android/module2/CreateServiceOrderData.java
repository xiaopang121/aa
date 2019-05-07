package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

/**
 * Created by linux on 2018/6/4.
 */

public class CreateServiceOrderData extends BaseData {


    /**
     * service_fee : 3000.0
     * one_money : 0
     * state : 0
     * create_time : 2018-11-16 14:10:10
     * user_id : b5fbfde9fa5f464eab7198eb15bcc703
     * save_money : 380.0
     * order_id : SS20181116192144
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String service_fee;
        private String one_money;
        private String state;
        private String create_time;
        private String user_id;
        private String save_money;
        private String order_id;

        public String getService_fee() {
            return service_fee;
        }

        public void setService_fee(String service_fee) {
            this.service_fee = service_fee;
        }

        public String getOne_money() {
            return one_money;
        }

        public void setOne_money(String one_money) {
            this.one_money = one_money;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getSave_money() {
            return save_money;
        }

        public void setSave_money(String save_money) {
            this.save_money = save_money;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
    }
}
