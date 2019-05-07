package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linux on 2017/11/10.
 */

public class BannerListData extends BaseData {


    public List<DataBean> data;


    public List<DataBean> getData() {

        if(data==null)
        {
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable
    {




        private String url;

        private String cover;

        private String title;

        private String id;

        private String content;

        private long  create_time;


        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }
    }
}
