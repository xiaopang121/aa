package com.insthub.cat.android.module;

import java.util.ArrayList;
import java.util.List;

/**
 * User:macbook
 * DATE:2018/11/15 18:49
 * Desc:${DESC}
 */
public class ZhengjiDraftData extends BaseData {


    /**
     * detail : 公司员工使用
     * accid : 64448878724347959a0bb1f2deac7695
     * service_type : 1
     * tender_id : 62bbf17f1dfe46aa94dc0ca5a27acaff
     * image : http://www.qitengteng.com:8080/upload/store/chuangxingji_bg5.png
     * service_content : 创意设计
     * share : {"share_image":"http://www.qitengteng.com:8080/upload/store/qifuxingqiulogo.png","share_type":"5","share_url":"http://www.qitengteng.com:8080/app/app/store/zhengjidetail.do?tender_id=62bbf17f1dfe46aa94dc0ca5a27acaff","share_desc":"公司吉祥物","tender_id":"62bbf17f1dfe46aa94dc0ca5a27acaff","share_title":"我正在用「企服星球APP」接了很多订单,效果很棒,推荐给你"}
     * title : 公司吉祥物
     * end_time : 2019-2-2——10：00：00
     * label_lv1 :
     * service : 创意设计
     * money : 100
     * create_time : 2019-01-30 15:41:28
     * label_lv2 :
     * annex : []
     * trait : 福利
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
        private String image;
        private String service_content;
        /**
         * share_image : http://www.qitengteng.com:8080/upload/store/qifuxingqiulogo.png
         * share_type : 5
         * share_url : http://www.qitengteng.com:8080/app/app/store/zhengjidetail.do?tender_id=62bbf17f1dfe46aa94dc0ca5a27acaff
         * share_desc : 公司吉祥物
         * tender_id : 62bbf17f1dfe46aa94dc0ca5a27acaff
         * share_title : 我正在用「企服星球APP」接了很多订单,效果很棒,推荐给你
         */

        private ShareBean share;
        private String title;
        private String end_time;
        private String label_lv1;
        private String service;
        private int money;
        private String create_time;
        private String label_lv2;
        private String trait;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getLabel_lv1() {
            return label_lv1;
        }

        public void setLabel_lv1(String label_lv1) {
            this.label_lv1 = label_lv1;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getLabel_lv2() {
            return label_lv2;
        }

        public void setLabel_lv2(String label_lv2) {
            this.label_lv2 = label_lv2;
        }

        public String getTrait() {
            return trait;
        }

        public void setTrait(String trait) {
            this.trait = trait;
        }

        public List<String> getAnnex() {

            if(annex==null)
            {
                annex= new ArrayList<>();
            }
            return annex;
        }

        public void setAnnex(List<String> annex) {
            this.annex = annex;
        }

        public static class ShareBean {
            private String share_image;
            private String share_type;
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

            public String getShare_type() {
                return share_type;
            }

            public void setShare_type(String share_type) {
                this.share_type = share_type;
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
