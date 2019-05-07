package com.insthub.cat.android.module;

import java.util.ArrayList;
import java.util.List;

/**
 * User:macbook
 * DATE:2018/11/15 18:49
 * Desc:${DESC}
 */
public class ZhaobiaoDraftData extends BaseData {


    /**
     * detail : 需要提供统一的服装，
     * phone : 13815415556
     * service_type : 0
     * tender_id : b495693022ca4f88a260f75d49f57229
     * type : 1
     * invoice_type : 0
     * city : 南京市
     * service_content :
     * share : {"share_image":"http://www.qitengteng.com:8080/upload/store/qifuxingqiulogo.png","share_url":"http://www.qitengteng.com:8080/app/app/store/zhaobiaodetail.do?tender_id=b495693022ca4f88a260f75d49f57229","share_desc":"卫生打扫","tender_id":"b495693022ca4f88a260f75d49f57229","share_title":"我正在用「企服星球APP」接了很多订单,效果很棒,推荐给你"}
     * title : 卫生打扫
     * pub_time : 2019-02-02 10:00:00
     * area : 鼓楼区
     * label_lv1 : 8
     * province : 江苏省
     * label_lv2 : 63
     * survey : 保洁，
     * annex : []
     * req_list : []
     * accid : 64448878724347959a0bb1f2deac7695
     * collect_address : 新模范马路南理工创业园
     * end_time : 2019-02-02 10:00:00
     * address : 南京市鼓楼区新模范马路南理工创业园
     * tender_req : 0
     * create_time : 2019-01-30 15:47:15
     * service : 后勤外包
     * include_tax : 1
     * include_freight : 0
     * collect : 何经理
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
        private String phone;
        private int service_type;
        private String tender_id;
        private String type;
        private int invoice_type;
        private String city;
        private String service_content;
        /**
         * share_image : http://www.qitengteng.com:8080/upload/store/qifuxingqiulogo.png
         * share_url : http://www.qitengteng.com:8080/app/app/store/zhaobiaodetail.do?tender_id=b495693022ca4f88a260f75d49f57229
         * share_desc : 卫生打扫
         * tender_id : b495693022ca4f88a260f75d49f57229
         * share_title : 我正在用「企服星球APP」接了很多订单,效果很棒,推荐给你
         */

        private ShareBean share;
        private String title;
        private String pub_time;
        private String area;
        private String label_lv1;
        private String province;
        private String label_lv2;
        private String survey;
        private String accid;
        private String collect_address;
        private String end_time;
        private String address;
        private int tender_req;
        private String create_time;
        private String service;
        private int include_tax;
        private int include_freight;
        private String collect;
        private List<String> annex;
        private List<String> req_list;

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getInvoice_type() {
            return invoice_type;
        }

        public void setInvoice_type(int invoice_type) {
            this.invoice_type = invoice_type;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
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

        public String getPub_time() {
            return pub_time;
        }

        public void setPub_time(String pub_time) {
            this.pub_time = pub_time;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }


        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }



        public String getSurvey() {
            return survey;
        }

        public void setSurvey(String survey) {
            this.survey = survey;
        }

        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public String getCollect_address() {
            return collect_address;
        }

        public void setCollect_address(String collect_address) {
            this.collect_address = collect_address;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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


        public String getCollect() {
            return collect;
        }

        public void setCollect(String collect) {
            this.collect = collect;
        }

        public String getLabel_lv1() {
            return label_lv1;
        }

        public void setLabel_lv1(String label_lv1) {
            this.label_lv1 = label_lv1;
        }

        public String getLabel_lv2() {
            return label_lv2;
        }

        public void setLabel_lv2(String label_lv2) {
            this.label_lv2 = label_lv2;
        }

        public int getTender_req() {
            return tender_req;
        }

        public void setTender_req(int tender_req) {
            this.tender_req = tender_req;
        }

        public int getInclude_tax() {
            return include_tax;
        }

        public void setInclude_tax(int include_tax) {
            this.include_tax = include_tax;
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

        public List<String> getReq_list() {
            if(req_list==null)
            {
                req_list = new ArrayList<>();
            }
            return req_list;
        }

        public void setReq_list(List<String> req_list) {
            this.req_list = req_list;
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
