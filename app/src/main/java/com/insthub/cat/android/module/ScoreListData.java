package com.insthub.cat.android.module;
import java.util.ArrayList;
import java.util.List;

/**
 * 抬头 列表
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class ScoreListData extends BaseData {


    /**
     * status : 10001
     * data : {"count":11,"list":[{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","tax":"40000.00","company_name":"南京启腾腾信息科技有限公司","tax_code":"65585844","money":"440003.26","state":"1","invoice_image":"http://139.196.92.19:8080/upload//invoice/invoice1528174813926.jpg","create_time":"2018-04-27 10:27:30","store_id":"123123123","title_no":"1022A","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331022","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"},{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","company_name":"南京启腾腾信息科技有限公司","invoice_image":"http://139.196.92.19:8080/upload/null","state":"0","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331023","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"},{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","company_name":"南京启腾腾信息科技有限公司","invoice_image":"http://139.196.92.19:8080/upload/null","state":"0","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331024","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"},{"company_name":"李四","invoice_image":"http://139.196.92.19:8080/upload/null","state":"0","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331025"},{"tax":"55","company_name":"张三","tax_code":"fg0ihu982813jhhhss","money":"555","state":"1","invoice_image":"http://139.196.92.19:8080/upload//invoice/invoice1525680566316.jpg","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","title_no":"1022A","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331026"},{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","tax":"55","company_name":"南京启腾腾信息科技有限公司","tax_code":"fg0ihu982813jhhhss","money":"555","state":"1","invoice_image":"http://139.196.92.19:8080/upload//invoice/invoice1525680566316.jpg","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","title_no":"1022A","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331027","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"},{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","tax":"55","company_name":"南京启腾腾信息科技有限公司","tax_code":"fg0ihu982813jhhhss","money":"555","state":"1","invoice_image":"http://139.196.92.19:8080/upload//invoice/invoice1525680566316.jpg","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","title_no":"1022A","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331028","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"},{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","tax":"55","company_name":"南京启腾腾信息科技有限公司","tax_code":"fg0ihu982813jhhhss","money":"555","state":"1","invoice_image":"http://139.196.92.19:8080/upload//invoice/invoice1525680566316.jpg","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","title_no":"1022A","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331029","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"},{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","tax":"55","company_name":"南京启腾腾信息科技有限公司","tax_code":"fg0ihu982813jhhhss","money":"555","state":"1","invoice_image":"http://139.196.92.19:8080/upload//invoice/invoice1525680566316.jpg","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","title_no":"1022A","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331032","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"}],"page_count":1}
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * count : 11
         * list : [{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","tax":"40000.00","company_name":"南京启腾腾信息科技有限公司","tax_code":"65585844","money":"440003.26","state":"1","invoice_image":"http://139.196.92.19:8080/upload//invoice/invoice1528174813926.jpg","create_time":"2018-04-27 10:27:30","store_id":"123123123","title_no":"1022A","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331022","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"},{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","company_name":"南京启腾腾信息科技有限公司","invoice_image":"http://139.196.92.19:8080/upload/null","state":"0","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331023","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"},{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","company_name":"南京启腾腾信息科技有限公司","invoice_image":"http://139.196.92.19:8080/upload/null","state":"0","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331024","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"},{"company_name":"李四","invoice_image":"http://139.196.92.19:8080/upload/null","state":"0","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331025"},{"tax":"55","company_name":"张三","tax_code":"fg0ihu982813jhhhss","money":"555","state":"1","invoice_image":"http://139.196.92.19:8080/upload//invoice/invoice1525680566316.jpg","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","title_no":"1022A","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331026"},{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","tax":"55","company_name":"南京启腾腾信息科技有限公司","tax_code":"fg0ihu982813jhhhss","money":"555","state":"1","invoice_image":"http://139.196.92.19:8080/upload//invoice/invoice1525680566316.jpg","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","title_no":"1022A","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331027","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"},{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","tax":"55","company_name":"南京启腾腾信息科技有限公司","tax_code":"fg0ihu982813jhhhss","money":"555","state":"1","invoice_image":"http://139.196.92.19:8080/upload//invoice/invoice1525680566316.jpg","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","title_no":"1022A","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331028","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"},{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","tax":"55","company_name":"南京启腾腾信息科技有限公司","tax_code":"fg0ihu982813jhhhss","money":"555","state":"1","invoice_image":"http://139.196.92.19:8080/upload//invoice/invoice1525680566316.jpg","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","title_no":"1022A","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331029","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"},{"address_phone":"南京市鼓楼区中山北路99号 1312312312312","tax":"55","company_name":"南京启腾腾信息科技有限公司","tax_code":"fg0ihu982813jhhhss","money":"555","state":"1","invoice_image":"http://139.196.92.19:8080/upload//invoice/invoice1525680566316.jpg","create_time":"2018-05-11 10:27:30","store_id":"50d776b99f3b499388e601dfcc68a345","title_no":"1022A","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","invoice_id":"201804271026331032","bank_account":"南京银行 63584000000008974211","tax_no":"91320106MA1TDPBB49"}]
         * page_count : 1
         */

        private int count;
        private int page_count;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage_count() {
            return page_count;
        }

        public void setPage_count(int page_count) {
            this.page_count = page_count;
        }

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

        public static class ListBean {
            /**
             * address_phone : 南京市鼓楼区中山北路99号 1312312312312
             * tax : 40000.00
             * company_name : 南京启腾腾信息科技有限公司
             * tax_code : 65585844
             * money : 440003.26
             * state : 1
             * invoice_image : http://139.196.92.19:8080/upload//invoice/invoice1528174813926.jpg
             * create_time : 2018-04-27 10:27:30
             * store_id : 123123123
             * title_no : 1022A
             * user_id : 1c62fe0f9b6f46b8805d43a7c93ab2ce
             * invoice_id : 201804271026331022
             * bank_account : 南京银行 63584000000008974211
             * tax_no : 91320106MA1TDPBB49
             */

            private String address_phone;
            private String tax;
            private String company_name;
            private String tax_code;
            private float money;
            private String state;
            private String invoice_image;
            private String create_time;
            private String store_id;
            private String title_no;
            private String user_id;
            private String invoice_id;
            private String bank_account;
            private String tax_no;

            public String getAddress_phone() {
                return address_phone;
            }

            public void setAddress_phone(String address_phone) {
                this.address_phone = address_phone;
            }

            public String getTax() {
                return tax;
            }

            public void setTax(String tax) {
                this.tax = tax;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getTax_code() {
                return tax_code;
            }

            public void setTax_code(String tax_code) {
                this.tax_code = tax_code;
            }

            public float getMoney() {
                return money;
            }

            public void setMoney(float money) {
                this.money = money;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getInvoice_image() {
                return invoice_image;
            }

            public void setInvoice_image(String invoice_image) {
                this.invoice_image = invoice_image;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getTitle_no() {
                return title_no;
            }

            public void setTitle_no(String title_no) {
                this.title_no = title_no;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getInvoice_id() {
                return invoice_id;
            }

            public void setInvoice_id(String invoice_id) {
                this.invoice_id = invoice_id;
            }

            public String getBank_account() {
                return bank_account;
            }

            public void setBank_account(String bank_account) {
                this.bank_account = bank_account;
            }

            public String getTax_no() {
                return tax_no;
            }

            public void setTax_no(String tax_no) {
                this.tax_no = tax_no;
            }
        }
    }
}
