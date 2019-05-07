package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linux on 2018/6/4.
 */

public class ExtensionPageListData extends BaseData {


    /**
     * status : 10001
     * data : {"list":[{"user_name":"","phone":"13770733620","title_id":18,"address":"南京市","is_default":"1","bank":"中国银行","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","bank_account":"62284803940342","type":"0","tax_no":"654586658236844","title_name":" 戒律牧图我"}]}
     */
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {



            public String ep_id;

            public String image;

            public String title;

            public String create_time;

            public int sort;


            public String getEp_id() {
                return ep_id;
            }

            public void setEp_id(String ep_id) {
                this.ep_id = ep_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
