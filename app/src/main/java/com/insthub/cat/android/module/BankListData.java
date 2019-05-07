package com.insthub.cat.android.module;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class BankListData extends BaseData {


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
            /**
             * create_time : 2018-06-15 12:44:48
             * bank : 农业银行
             * user_id : 1c62fe0f9b6f46b8805d43a7c93ab2ce
             * bank_account : 6228480399757413426
             * card_id : 65c14b5e2a8044deaae269e0c7cc6939
             */

            private String create_time;
            private String bank;
            private String user_id;
            private String bank_account;
            private String card_id;

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getBank() {
                return bank;
            }

            public void setBank(String bank) {
                this.bank = bank;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getBank_account() {
                return bank_account;
            }

            public void setBank_account(String bank_account) {
                this.bank_account = bank_account;
            }

            public String getCard_id() {
                return card_id;
            }

            public void setCard_id(String card_id) {
                this.card_id = card_id;
            }
        }
    }
}
