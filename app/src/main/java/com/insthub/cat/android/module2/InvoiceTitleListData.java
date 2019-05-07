package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linux on 2018/6/4.
 */

public class InvoiceTitleListData extends BaseData {


    /**
     * status : 10001
     * data : {"list":[{"user_name":"","phone":"13770733620","title_id":18,"address":"南京市","is_default":"1","bank":"中国银行","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","bank_account":"62284803940342","type":"0","tax_no":"654586658236844","title_name":" 戒律牧图我"}]}
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

        public static class ListBean implements Serializable {
            /**
             * user_name :
             * phone : 13770733620
             * title_id : 18
             * address : 南京市
             * is_default : 1
             * bank : 中国银行
             * user_id : 1c62fe0f9b6f46b8805d43a7c93ab2ce
             * bank_account : 62284803940342
             * type : 0
             * tax_no : 654586658236844
             * title_name :  戒律牧图我
             */

            private String user_name;
            private String phone;
            private String title_id;
            private String address;
            private int is_default;
            private String bank;
            private String user_id;
            private String bank_account;
            private int type;
            private String tax_no;
            private String title_name;

            private float money;

            private String store_id;

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public float getMoney() {
                return money;
            }

            public void setMoney(float money) {
                this.money = money;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getTitle_id() {
                return title_id;
            }

            public void setTitle_id(String title_id) {
                this.title_id = title_id;
            }

            public int getIs_default() {
                return is_default;
            }

            public void setIs_default(int is_default) {
                this.is_default = is_default;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTax_no() {
                return tax_no;
            }

            public void setTax_no(String tax_no) {
                this.tax_no = tax_no;
            }

            public String getTitle_name() {
                return title_name;
            }

            public void setTitle_name(String title_name) {
                this.title_name = title_name;
            }
        }
    }
}
