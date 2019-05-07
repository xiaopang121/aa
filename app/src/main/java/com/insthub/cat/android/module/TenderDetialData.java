package com.insthub.cat.android.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class TenderDetialData extends BaseData {

    /**
     * phone : 13913040976
     * detail : app开发
     * collect_address : 南京市雨花区
     * tender_id : cc3dce01cf65433fa8c7fa69aa1a2a3d
     * type : 1
     * city : 南京市
     * invoice_type : 1
     * pub_time : 2018-11-10
     * title : app开发
     * area : 鼓楼区
     * address : 鼓楼区XXX路aaa号bbb
     * end_time : 2018-11-05
     * province : 江苏省
     * service : 代理记账
     * create_time : 2018-11-02 10:30:47
     * survey : ["概况"]
     * include_tax : 1
     * annex : []
     * req_list : 1.公司3年以上,2.同类型app开发经验,3.开发效率
     * collect : zq
     * include_freight : 1
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private String phone;
        private String detail;
        private String collect_address;
        private String tender_id;
        private String type;
        private String city;
        private int  invoice_type;
        private String pub_time;
        private String title;
        private String area;
        private String address;
        private String end_time;
        private String province;
        private String service;
        private String create_time;
        private int include_tax;
        private String[] req_list;
        private String collect;
        private int include_freight;
        private String survey;
        private List<AnnexBean> annex;
        private String accid;

        private ShareBean share;
        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
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


        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getCollect_address() {
            return collect_address;
        }

        public void setCollect_address(String collect_address) {
            this.collect_address = collect_address;
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

        public String getPub_time() {
            return pub_time;
        }

        public void setPub_time(String pub_time) {
            this.pub_time = pub_time;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getInclude_tax() {
            return include_tax;
        }

        public void setInclude_tax(int include_tax) {
            this.include_tax = include_tax;
        }

        public String[] getReq_list() {
            return req_list;
        }

        public void setReq_list(String[] req_list) {
            this.req_list = req_list;
        }

        public String getCollect() {
            return collect;
        }

        public void setCollect(String collect) {
            this.collect = collect;
        }

        public int getInclude_freight() {
            return include_freight;
        }

        public void setInclude_freight(int include_freight) {
            this.include_freight = include_freight;
        }

//        public List<String> getSurvey() {
//
//            if(survey==null)
//            {
//                survey = new ArrayList<>();
//            }
//            return survey;
//        }
//
//        public void setSurvey(List<String> survey) {
//            this.survey = survey;
//        }


        public String getSurvey() {
            return survey;
        }

        public void setSurvey(String survey) {
            this.survey = survey;
        }

        public List<AnnexBean> getAnnex() {
            return annex;
        }

        public void setAnnex(List<AnnexBean> annex) {
            this.annex = annex;
        }

    }
}
