package com.insthub.cat.android.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class OfferListData extends BaseData {


    /**
     * status : 10001
     * data : {"list":[{"create_time":"2018-06-15 12:44:48","bank":"农业银行","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","bank_account":"6228480399757413426","card_id":"65c14b5e2a8044deaae269e0c7cc6939"}]}
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

            if(list==null)
            {
                list = new ArrayList<>();
            }
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{



            private String tender_id;

            private String title;

            private String create_time;
            private String price;

            private int  state;

            private String user_id;

            private String compete_id;

            private String finish_time;

            private int tender_state;

            public int getTender_state() {
                return tender_state;
            }

            public void setTender_state(int tender_state) {
                this.tender_state = tender_state;
            }

            public String getTender_id() {
                return tender_id;
            }

            public void setTender_id(String tender_id) {
                this.tender_id = tender_id;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getCompete_id() {
                return compete_id;
            }

            public void setCompete_id(String compete_id) {
                this.compete_id = compete_id;
            }

            public String getFinish_time() {
                return finish_time;
            }

            public void setFinish_time(String finish_time) {
                this.finish_time = finish_time;
            }
        }
    }
}
