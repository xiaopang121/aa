package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class NewsListData extends BaseData {


    /**
     * status : 10001
     * data : {"count":1,"list":[{"content":"<p>啊啊啊啊啊123213333<\/p>","news_id":"1","detail":"http://139.196.92.19:8080/app/app/user/getNewsDetail.do?news_id=1","title":"标题","subtitle":"副标题","image":"http://139.196.92.19:8080/upload/banner/banner2.png","type":"1"}],"page_count":1}
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
         * count : 1
         * list : [{"content":"<p>啊啊啊啊啊123213333<\/p>","news_id":"1","detail":"http://139.196.92.19:8080/app/app/user/getNewsDetail.do?news_id=1","title":"标题","subtitle":"副标题","image":"http://139.196.92.19:8080/upload/banner/banner2.png","type":"1"}]
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
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * content : <p>啊啊啊啊啊123213333</p>
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
    }
}
