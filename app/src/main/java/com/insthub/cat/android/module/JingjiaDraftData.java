package com.insthub.cat.android.module;

import java.util.ArrayList;
import java.util.List;

/**
 * User:macbook
 * DATE:2018/11/15 18:49
 * Desc:${DESC}
 */
public class JingjiaDraftData extends BaseData {


    /**
     * detail : 装修，设计
     * accid : 64448878724347959a0bb1f2deac7695
     * service_type : 0
     * tender_id : 1948a373d0ca4a03b3114b316f54e744
     * business_license : 1
     * type : 0
     * city : 市辖区
     * invoice_type : 0
     * service_content :
     * share : {"share_image":"http://www.qitengteng.com:8080/upload/store/qifuxingqiulogo.png","share_url":"http://www.qitengteng.com:8080/app/app/store/jingjiadetail.do?tender_id=1948a373d0ca4a03b3114b316f54e744","share_desc":"4s店装修","tender_id":"1948a373d0ca4a03b3114b316f54e744","share_title":"我正在用「企服星球APP」接了很多订单,效果很棒,推荐给你"}
     * title : 4s店装修
     * area : 和平区
     * address : 和平大道101号
     * end_time : 2019-02-02 10:00:00
     * pay_type : 0
     * label_lv1 : 5
     * province : 天津市
     * create_time : 2019-01-31 10:06:43
     * service : 工艺设计
     * label_lv2 : 44
     * annex : []
     * include_tax : 1
     * trans_type : 0
     * include_freight : 0
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String detail;
        private String accid;
        private int service_type;
        private String tender_id;
        private int business_license;
        private String type;
        private String city;
        private int invoice_type;
        private String service_content;
        /**
         * share_image : http://www.qitengteng.com:8080/upload/store/qifuxingqiulogo.png
         * share_url : http://www.qitengteng.com:8080/app/app/store/jingjiadetail.do?tender_id=1948a373d0ca4a03b3114b316f54e744
         * share_desc : 4s店装修
         * tender_id : 1948a373d0ca4a03b3114b316f54e744
         * share_title : 我正在用「企服星球APP」接了很多订单,效果很棒,推荐给你
         */

        private ShareBean share;
        private String title;
        private String area;
        private String address;
        private String end_time;
        private int pay_type;
        private String label_lv1;
        private String province;
        private String create_time;
        private String service;
        private String label_lv2;
        private int include_tax;
        private int trans_type;
        private int include_freight;
        private List<String> annex;

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public int getService_type() {
            return service_type;
        }

        public void setService_type(int service_type) {
            this.service_type = service_type;
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

        public void setBusiness_license(int business_license) {
            this.business_license = business_license;
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

        public void setInvoice_type(int invoice_type) {
            this.invoice_type = invoice_type;
        }

        public String getService_content() {
            return service_content;
        }

        public void setService_content(String service_content) {
            this.service_content = service_content;
        }

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
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

        public String getLabel_lv1() {
            return label_lv1;
        }

        public void setLabel_lv1(String label_lv1) {
            this.label_lv1 = label_lv1;
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

        public String getLabel_lv2() {
            return label_lv2;
        }

        public void setLabel_lv2(String label_lv2) {
            this.label_lv2 = label_lv2;
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

        public List<String> getAnnex() {

            if(annex==null)
            {
                annex = new ArrayList<>();
            }
            return annex;
        }

        public void setAnnex(List<String> annex) {
            this.annex = annex;
        }

        public static class ShareBean {
            private String share_image;
            private String share_url;
            private String share_desc;
            private String tender_id;
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

            public String getTender_id() {
                return tender_id;
            }

            public void setTender_id(String tender_id) {
                this.tender_id = tender_id;
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
