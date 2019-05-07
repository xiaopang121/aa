package com.insthub.cat.android.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linux on 2017/11/10.
 */

public class TodayThemeListData extends BaseData {


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




        private String id;

        private String title;

        private String mainPhoto;

        private long createTime;

        private int  status;

        private String content;

        public String getMainPhoto() {
            return mainPhoto;
        }

        public void setMainPhoto(String mainPhoto) {
            this.mainPhoto = mainPhoto;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
