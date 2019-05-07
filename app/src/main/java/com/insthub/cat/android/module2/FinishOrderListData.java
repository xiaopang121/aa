package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

import java.util.List;

/**
 * Created by linux on 2017/11/10.
 */

public class FinishOrderListData extends BaseData {


    /**
     * status : 10001
     * data : {"count":13,"list":[{"service_time":"2018-6-09--2018-7-05","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP规划","service_price":"3000","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","state":"3","create_time":"2018-06-08 09:18:41","store_id":"7e81d46e621245958f95fe0f3e424e0d","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180608033370","store_name":"测试店铺-1号"},{"service_time":"2018-6-09--2018-7-04","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP规划","service_price":"3000","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","state":"3","create_time":"2018-06-08 09:18:41","store_id":"7e81d46e621245958f95fe0f3e424e0d","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180608040274","store_name":"测试店铺-1号"},{"service_time":"2018-6-09--2018-7-05","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP规划","service_price":"3000","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","state":"3","create_time":"2018-06-08 09:18:41","store_id":"7e81d46e621245958f95fe0f3e424e0d","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180608119644","store_name":"测试店铺-1号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","order_id":"20180603709297","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 21:01:43","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-1号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","order_id":"20180603566483","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 20:55:47","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-1号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"d9198e4bafc14e99add99026e0d2c3b8","remarks":"无","order_id":"20180603303523","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 20:54:27","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-4号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","order_id":"20180603489883","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 20:54:27","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-1号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","order_id":"20180603919762","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 20:54:27","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-1号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","order_id":"20180603626601","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 19:55:58","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-1号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"d9198e4bafc14e99add99026e0d2c3b8","remarks":"无","order_id":"20180603443132","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 19:55:43","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-4号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","order_id":"20180603793113","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 19:54:03","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-1号"}],"page_count":1}
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
         * count : 13
         * list : [{"service_time":"2018-6-09--2018-7-05","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP规划","service_price":"3000","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","state":"3","create_time":"2018-06-08 09:18:41","store_id":"7e81d46e621245958f95fe0f3e424e0d","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180608033370","store_name":"测试店铺-1号"},{"service_time":"2018-6-09--2018-7-04","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP规划","service_price":"3000","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","state":"3","create_time":"2018-06-08 09:18:41","store_id":"7e81d46e621245958f95fe0f3e424e0d","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180608040274","store_name":"测试店铺-1号"},{"service_time":"2018-6-09--2018-7-05","logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_content":"BP规划","service_price":"3000","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","state":"3","create_time":"2018-06-08 09:18:41","store_id":"7e81d46e621245958f95fe0f3e424e0d","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","order_id":"20180608119644","store_name":"测试店铺-1号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","order_id":"20180603709297","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 21:01:43","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-1号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","order_id":"20180603566483","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 20:55:47","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-1号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"d9198e4bafc14e99add99026e0d2c3b8","remarks":"无","order_id":"20180603303523","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 20:54:27","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-4号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","order_id":"20180603489883","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 20:54:27","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-1号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","order_id":"20180603919762","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 20:54:27","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-1号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","order_id":"20180603626601","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 19:55:58","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-1号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"d9198e4bafc14e99add99026e0d2c3b8","remarks":"无","order_id":"20180603443132","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 19:55:43","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-4号"},{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","service_price":"3000","state":"3","store_id":"7e81d46e621245958f95fe0f3e424e0d","remarks":"无","order_id":"20180603793113","service_time":"2018-6-03--2018-7-03","service_content":"BP规划","pay_type":"0","head_image":"http://139.196.92.19:8080/upload/head/defaulthead.png","create_time":"2018-06-03 19:54:03","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","store_name":"测试店铺-1号"}]
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

        public static class ListBean {
            /**
             * service_time : 2018-6-09--2018-7-05
             * logo : http://139.196.92.19:8080/upload/banner/logo.png
             * service_content : BP规划
             * service_price : 3000
             * pay_type : 0
             * head_image : http://139.196.92.19:8080/upload/head/defaulthead.png
             * state : 3
             * create_time : 2018-06-08 09:18:41
             * store_id : 7e81d46e621245958f95fe0f3e424e0d
             * user_id : 1c62fe0f9b6f46b8805d43a7c93ab2ce
             * order_id : 20180608033370
             * store_name : 测试店铺-1号
             * remarks : 无
             */

            private String service_time;
            private String logo;
            private String service_content;
            private String service_price;
            private String pay_type;
            private String head_image;
            private String state;
            private String create_time;
            private String store_id;
            private String user_id;
            private String order_id;
            private String store_name;
            private String remarks;
            private String user_name;
            private String service_name;

            private String accid;

            public String getAccid() {
                return accid;
            }

            public void setAccid(String accid) {
                this.accid = accid;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getService_time() {
                return service_time;
            }

            public void setService_time(String service_time) {
                this.service_time = service_time;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getService_content() {
                return service_content;
            }

            public void setService_content(String service_content) {
                this.service_content = service_content;
            }

            public String getService_price() {
                return service_price;
            }

            public void setService_price(String service_price) {
                this.service_price = service_price;
            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
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

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }
        }
    }
}
