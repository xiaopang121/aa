package com.insthub.cat.android.module;

import com.insthub.cat.android.module2.StoreDetialData;

import java.io.Serializable;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class BidPriceDetialData extends BaseData {


    /**
     * detail : 找个常年代账
     * tender_id : 9da8868ea9f7482fbe0944226b1ca1e5
     * business_license : 1
     * type : 0
     * city : 南京市
     * invoice_type : 1
     * title : 找个常年代账
     * area : 鼓楼区
     * address : 南京市鼓楼区
     * end_time : 2018-11-11 12:00:00
     * pay_type : 1
     * province : 江苏省
     * create_time : 2018-10-31 14:58:52
     * service : 代理记账
     * annex : [{"annex_type":"1","file_name":"Objective-C的语法与Cocoa框架.pdf","url":"http://www.qitengteng.com:8080/upload/pdf/Objective-C的语法与Cocoa框架.pdf"}]
     * include_tax : 1
     * trans_type : 1
     * include_freight : 1
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String detail;
        private String tender_id;
        private int business_license;
        private String type;
        private String city;
        private int invoice_type;
        private String title;
        private String area;
        private String address;
        private String end_time;
        private int pay_type;
        private String province;
        private String create_time;
        private String service;
        private int include_tax;
        private int trans_type;
        private int include_freight;

        private String accid;

        private ShareBean share;
        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }


        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        /**
         * annex_type : 1
         * file_name : Objective-C的语法与Cocoa框架.pdf
         * url : http://www.qitengteng.com:8080/upload/pdf/Objective-C的语法与Cocoa框架.pdf
         */

        private List<AnnexBean> annex;

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTender_id() {
            return tender_id;
        }

        public void setTender_id(String tender_id) {
            this.tender_id = tender_id;
        }

        public int getBusiness_license() {
            return business_license;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getInvoice_type() {
            return invoice_type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public void setBusiness_license(int business_license) {
            this.business_license = business_license;
        }

        public void setInvoice_type(int invoice_type) {
            this.invoice_type = invoice_type;
        }

        public int getInclude_tax() {
            return include_tax;
        }

        public void setInclude_tax(int include_tax) {
            this.include_tax = include_tax;
        }

        public int getTrans_type() {
            return trans_type;
        }

        public void setTrans_type(int trans_type) {
            this.trans_type = trans_type;
        }

        public int getInclude_freight() {
            return include_freight;
        }

        public void setInclude_freight(int include_freight) {
            this.include_freight = include_freight;
        }

        public List<AnnexBean> getAnnex() {
            return annex;
        }

        public void setAnnex(List<AnnexBean> annex) {
            this.annex = annex;
        }

        public static class ShareBean implements Serializable{
            private String share_image;
            private String share_url;
            private String share_desc;
            private String share_title;

            public String getShare_image() {
                return share_image;
            }

            public void setShare_image(String share_image) {
                this.share_image = share_image;
            }

            public String getShare_url() {
                return share_url;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }

            public String getShare_desc() {
                return share_desc;
            }

            public void setShare_desc(String share_desc) {
                this.share_desc = share_desc;
            }

            public String getShare_title() {
                return share_title;
            }

            public void setShare_title(String share_title) {
                this.share_title = share_title;
            }
        }
    }
}
