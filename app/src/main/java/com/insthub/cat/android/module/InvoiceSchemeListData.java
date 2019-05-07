package com.insthub.cat.android.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linux on 2017/11/10.
 */

public class InvoiceSchemeListData extends BaseData {


    public DataBean data;


    public DataBean getData() {


        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable
    {




        private String id;

        private String mainPhoto;

        private String linkAddress;

        private int  status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMainPhoto() {
            return mainPhoto;
        }

        public void setMainPhoto(String mainPhoto) {
            this.mainPhoto = mainPhoto;
        }

        public String getLinkAddress() {
            return linkAddress;
        }

        public void setLinkAddress(String linkAddress) {
            this.linkAddress = linkAddress;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
