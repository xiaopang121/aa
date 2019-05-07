package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

import java.util.List;

/**
 * Created by linux on 2018/6/4.
 */

public class PintuanListData extends BaseData {


    /**
     * status : 10001
     * data : {"list":[{"phone":"13913040976","pintuan_id":"2","activity_id":"5","end_time":"2018-08-21 09:03:05","leader":"0bb7714890204418913a5f07776051bc","head_image":"http://139.196.92.19:8080/upload/coupon/d8ceda6c6ae24b5f9d9704c3480a2a52.jpg","state":"0","create_time":"2018-08-20 09:03:05","start_time":"2018-08-20 09:03:05"},{"phone":"13913040976","pintuan_id":"3","activity_id":"5","end_time":"2018-08-21 09:03:05","leader":"0bb7714890204418913a5f07776051bc","head_image":"http://139.196.92.19:8080/upload/coupon/d8ceda6c6ae24b5f9d9704c3480a2a52.jpg","state":"0","create_time":"2018-08-20 09:03:05","start_time":"2018-08-20 09:03:05"},{"phone":"13913040976","pintuan_id":"4","activity_id":"5","end_time":"2018-08-21 09:03:05","leader":"0bb7714890204418913a5f07776051bc","head_image":"http://139.196.92.19:8080/upload/coupon/d8ceda6c6ae24b5f9d9704c3480a2a52.jpg","state":"0","create_time":"2018-08-20 09:03:05","start_time":"2018-08-20 09:03:05"},{"phone":"13913040976","pintuan_id":"5","activity_id":"5","end_time":"2018-08-21 09:03:05","leader":"0bb7714890204418913a5f07776051bc","head_image":"http://139.196.92.19:8080/upload/coupon/d8ceda6c6ae24b5f9d9704c3480a2a52.jpg","state":"0","create_time":"2018-08-20 09:03:05","start_time":"2018-08-20 09:03:05"},{"phone":"13913040976","pintuan_id":"1","activity_id":"5","end_time":"2018-08-14 10:38:22","leader":"0bb7714890204418913a5f07776051bc","head_image":"http://139.196.92.19:8080/upload/coupon/d8ceda6c6ae24b5f9d9704c3480a2a52.jpg","state":"0","create_time":"2018-08-13 10:38:22","start_time":"2018-08-13 10:38:22"}]}
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

        public static class ListBean {
            /**
             * phone : 13913040976
             * pintuan_id : 2
             * activity_id : 5
             * end_time : 2018-08-21 09:03:05
             * leader : 0bb7714890204418913a5f07776051bc
             * head_image : http://139.196.92.19:8080/upload/coupon/d8ceda6c6ae24b5f9d9704c3480a2a52.jpg
             * state : 0
             * create_time : 2018-08-20 09:03:05
             * start_time : 2018-08-20 09:03:05
             */

            private String phone;
            private String pintuan_id;
            private String activity_id;
            private String end_time;
            private String leader;
            private String head_image;
            private String state;
            private String create_time;
            private String start_time;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPintuan_id() {
                return pintuan_id;
            }

            public void setPintuan_id(String pintuan_id) {
                this.pintuan_id = pintuan_id;
            }

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getLeader() {
                return leader;
            }

            public void setLeader(String leader) {
                this.leader = leader;
            }

            public String getHead_image() {
                return head_image;
            }

            public void setHead_image(String head_image) {
                this.head_image = head_image;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }
        }
    }
}
