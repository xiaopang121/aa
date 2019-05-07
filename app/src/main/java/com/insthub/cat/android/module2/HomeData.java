package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linux on 2018/6/4.
 */

public class HomeData extends BaseData {





    /**
     * buttom : {"banner_id":11,"sort":0,"create_time":"","store_id":"","image":"http://www.qitengteng.com:8080/upload/banner/songdayinji3.jpg","lng":"","remarks":"","type":"5","lat":"","url":"http://www.qitengteng.com:8080/app/static/h5/free.html","city":"南京市"}
     * renqi_list : [{"service_id":"1","service_image":"http://www.qitengteng.com:8080/upload/store/dh_service_1.jpg","service_name":"资产评估","order_count":"133"},{"service_id":"7","service_image":"http://www.qitengteng.com:8080/upload/store/dr_service_1.jpg","service_name":"税务咨询","order_count":"211"},{"service_id":"16","service_image":"http://www.qitengteng.com:8080/upload/store/lp_service_2.jpg","service_name":"会计报表","order_count":"189"},{"service_id":"20","service_image":"http://www.qitengteng.com:8080/upload/store/tl_service_1.jpg","service_name":"税务代理","order_count":"369"}]
     * banner_list : [{"banner_id":1,"sort":1,"create_time":"2018-05-14 15:19:33","store_id":"ee1095e69b0749cf8b0c847a8f6e92f0","image":"banner/banner1.jpg","type":"0","url":"https://www.baidu.com/"},{"banner_id":2,"sort":2,"create_time":"2018-05-14 15:19:33","store_id":"384610c787894240b0d024087e41c4d1","image":"banner/banner2.jpg","type":"0","url":"https://www.baidu.com/"},{"banner_id":10,"sort":3,"create_time":"","store_id":"880d192077ac4674afd897fe31c37922","image":"banner/banner3.jpg","remarks":"","type":"0","url":"https://www.baidu.com/"}]
     * jingxuan_list : [{"logo":"http://www.qitengteng.com:8080/upload/store/dh_logo.jpg","price":"3000","store_id":"fc14e419f1d249999919ca0ef0c46ca7","store_name":"德衡服务"},{"logo":"http://www.qitengteng.com:8080/upload/store/dh_logo.jpg","price":"3000","store_id":"613af2e2105f4b17b7cf3b59bf9a60df","store_name":"德瑞服务"},{"logo":"http://www.qitengteng.com:8080/upload/store/dr_logo.jpg","price":"3000","store_id":"11cb679762414db99b99d0cb13e786f1","store_name":"量平服务"},{"logo":"http://www.qitengteng.com:8080/upload/store/tl_logo.jpg","price":"3000","store_id":"66e9b0ad500d45df8972d098bd7fbb18","store_name":"天朗服务"}]
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{



        private int is_join;


        private ActyBean acty;
        /**
         * banner_id : 11
         * sort : 0
         * create_time :
         * store_id :
         * image : http://www.qitengteng.com:8080/upload/banner/songdayinji3.jpg
         * lng :
         * remarks :
         * type : 5
         * lat :
         * url : http://www.qitengteng.com:8080/app/static/h5/free.html
         * city : 南京市
         */

        private ButtomBean buttom;
        /**
         * service_id : 1
         * service_image : http://www.qitengteng.com:8080/upload/store/dh_service_1.jpg
         * service_name : 资产评估
         * order_count : 133
         */

        private List<RenqiListBean> renqi_list;
        /**
         * banner_id : 1
         * sort : 1
         * create_time : 2018-05-14 15:19:33
         * store_id : ee1095e69b0749cf8b0c847a8f6e92f0
         * image : banner/banner1.jpg
         * type : 0
         * url : https://www.baidu.com/
         */

        private List<BannerListBean> banner_list;

        public int getIs_join() {
            return is_join;
        }

        public void setIs_join(int is_join) {
            this.is_join = is_join;
        }

        public ActyBean getActy() {
            return acty;
        }

        public void setActy(ActyBean acty) {
            this.acty = acty;
        }

        /**
         * logo : http://www.qitengteng.com:8080/upload/store/dh_logo.jpg
         * price : 3000
         * store_id : fc14e419f1d249999919ca0ef0c46ca7
         * store_name : 德衡服务
         */

        private List<JingxuanListBean> jingxuan_list;

        private List<ThemeListBean> theme_list;

        private List<NewsListBean> news_list;


        private List<ServiceTypeListBean>  service_type_list;

        public List<ServiceTypeListBean> getService_type_list() {

            if(service_type_list==null)
            {
                service_type_list = new ArrayList<>();
            }
            return service_type_list;
        }

        public void setService_type_list(List<ServiceTypeListBean> service_type_list) {
            this.service_type_list = service_type_list;
        }

        public List<NewsListBean> getNews_list() {
            return news_list;
        }

        public void setNews_list(List<NewsListBean> news_list) {
            this.news_list = news_list;
        }

        public List<ThemeListBean> getTheme_list() {
            return theme_list;
        }

        public void setTheme_list(List<ThemeListBean> theme_list) {
            this.theme_list = theme_list;
        }

        public ButtomBean getButtom() {
            return buttom;
        }

        public void setButtom(ButtomBean buttom) {
            this.buttom = buttom;
        }

        public List<RenqiListBean> getRenqi_list() {

            if(renqi_list==null)
            {
                renqi_list = new ArrayList<>();
            }
            return renqi_list;
        }

        public void setRenqi_list(List<RenqiListBean> renqi_list) {
            this.renqi_list = renqi_list;
        }

        public List<BannerListBean> getBanner_list() {
            return banner_list;
        }

        public void setBanner_list(List<BannerListBean> banner_list) {
            this.banner_list = banner_list;
        }

        public List<JingxuanListBean> getJingxuan_list() {
            return jingxuan_list;
        }

        public void setJingxuan_list(List<JingxuanListBean> jingxuan_list) {
            this.jingxuan_list = jingxuan_list;
        }

        public static class ButtomBean implements Serializable {
            private int banner_id;
            private int sort;
            private String create_time;
            private String store_id;
            private String image;
            private String lng;
            private String remarks;
            private String type;
            private String lat;
            private String url;
            private String city;

            public int getBanner_id() {
                return banner_id;
            }

            public void setBanner_id(int banner_id) {
                this.banner_id = banner_id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }

        public static class RenqiListBean implements Serializable{
            private String logo;
            private String store_id;
            private String store_name;
            private String order_count;

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getOrder_count() {
                return order_count;
            }

            public void setOrder_count(String order_count) {
                this.order_count = order_count;
            }
        }

        public static class BannerListBean implements Serializable{
            private int banner_id;
            private int sort;
            private String create_time;
            private String service_id;
            private String image;
            private String type;
            private String url;


            public BannerListBean(String url)
            {
                this.image = url;
            }


            public BannerListBean()
            {
            }


            public int getBanner_id() {
                return banner_id;
            }

            public void setBanner_id(int banner_id) {
                this.banner_id = banner_id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class JingxuanListBean implements Serializable{
            private String service_id;
            private String price;
            private String service_image;
            private String service_name;

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getService_image() {
                return service_image;
            }

            public void setService_image(String service_image) {
                this.service_image = service_image;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }
        }


        public static class ThemeListBean implements Serializable{
            /**
             * banner_id : 4
             * sort : 1
             * store_id : 6219a1b4c8b249acaf68936247c567d2
             * image : http://139.196.92.19:8080/upload/banner/news1.png
             * type : 3
             */

//            private int banner_id;
//            private int sort;
//            private String store_id;
//            private String image;
//            private String type;


            private int banner_id;
            private int sort;
            private String store_id;
            private String image;
            private String type_name;
            private int activity_type_id;

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public int getActivity_type_id() {
                return activity_type_id;
            }

            public void setActivity_type_id(int activity_type_id) {
                this.activity_type_id = activity_type_id;
            }

            public int getBanner_id() {
                return banner_id;
            }

            public void setBanner_id(int banner_id) {
                this.banner_id = banner_id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

        }


        public static class NewsListBean {
            /**
             * content :  啊啊啊啊啊123213333
             * news_id : 1
             * detail : http://139.196.92.19:8080/app/app/user/getNewsDetail.do?news_id=1
             * title : 标题
             * subtitle : 副标题
             * image : http://139.196.92.19:8080/upload/banner/banner2.png
             * type : 1
             */

            private String content;
            private String news_id;
            private String detail;
            private String title;
            private String subtitle;
            private String image;
            private String type;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getNews_id() {
                return news_id;
            }

            public void setNews_id(String news_id) {
                this.news_id = news_id;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }


        public static class ServiceTypeListBean implements Serializable
        {
            private String image;
            private String name;

            private int  label_id;

            public int getLabel_id() {
                return label_id;
            }

            public void setLabel_id(int label_id) {
                this.label_id = label_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }



        public static class ActyBean implements Serializable
        {
            private String share_image ;
            private  int state;
            private  String image;
            private  int type;
            private  String acty_name;
            private String url;
            private String city;
            private String acty_id;
//


//            private String share_image ="http://www.qitengteng.com:8080/upload/head/64448878724347959a0bb1f2deac7695.jpg";
//            private  int state=0;
//            private  String image="http://www.qitengteng.com:8080/upload/head/888.png";
//            private  int type=0;
//            private  String acty_name="砸金蛋";
//            private String url="http://www.qitengteng.com:8080/app/static/h5/fdinvitation.html";
//            private String city="南京";
//            private String acty_id="2";




            public String getShare_image() {
                return share_image;
            }

            public void setShare_image(String share_image) {
                this.share_image = share_image;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getActy_name() {
                return acty_name;
            }

            public void setActy_name(String acty_name) {
                this.acty_name = acty_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getActy_id() {
                return acty_id;
            }

            public void setActy_id(String acty_id) {
                this.acty_id = acty_id;
            }
        }
    }


}
