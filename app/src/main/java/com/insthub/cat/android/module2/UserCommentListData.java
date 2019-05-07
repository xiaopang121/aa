package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class UserCommentListData extends BaseData {
    /**
     * count : 1
     * list : [{"logo":"http://139.196.92.19:8080/upload/banner/logo.png","user_name":"Qwrewrwe","evaluate_content":"Qwewqeqwedasdzxvzdsav","views":0,"head_image":"http://139.196.92.19:8080/upload/coupon/4af7a1f4cb8d41389b4f36da1dc62721.jpg","create_time":"2018-06-07 16:24:23","score":3,"evaluate_id":6,"store_id":"7e81d46e621245958f95fe0f3e424e0d","user_id":"0bb7714890204418913a5f07776051bc","order_id":"20180606968683","store_name":"测试店铺-1号"}]
     * page_count : 1
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int count;
        private int page_count;
        /**
         * logo : http://139.196.92.19:8080/upload/banner/logo.png
         * user_name : Qwrewrwe
         * evaluate_content : Qwewqeqwedasdzxvzdsav
         * views : 0
         * head_image : http://139.196.92.19:8080/upload/coupon/4af7a1f4cb8d41389b4f36da1dc62721.jpg
         * create_time : 2018-06-07 16:24:23
         * score : 3
         * evaluate_id : 6
         * store_id : 7e81d46e621245958f95fe0f3e424e0d
         * user_id : 0bb7714890204418913a5f07776051bc
         * order_id : 20180606968683
         * store_name : 测试店铺-1号
         */

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
            private String logo;
            private String user_name;
            private String evaluate_content;
            private int views;
            private String head_image;
            private String create_time;
            private int score;
            private String evaluate_id;
            private String store_id;
            private String user_id;
            private String order_id;
            private String store_name;

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getEvaluate_content() {
                return evaluate_content;
            }

            public void setEvaluate_content(String evaluate_content) {
                this.evaluate_content = evaluate_content;
            }

            public int getViews() {
                return views;
            }

            public void setViews(int views) {
                this.views = views;
            }

            public String getHead_image() {
                return head_image;
            }

            public void setHead_image(String head_image) {
                this.head_image = head_image;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getEvaluate_id() {
                return evaluate_id;
            }

            public void setEvaluate_id(String evaluate_id) {
                this.evaluate_id = evaluate_id;
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
        }
    }


//
//    private DataBean data;
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        private int count;
//        private int page_count;
//
//        private List<ListBean> list;
//
//        public int getCount() {
//            return count;
//        }
//
//        public void setCount(int count) {
//            this.count = count;
//        }
//
//        public int getPage_count() {
//            return page_count;
//        }
//
//        public void setPage_count(int page_count) {
//            this.page_count = page_count;
//        }
//
//        public List<ListBean> getList() {
//            return list;
//        }
//
//        public void setList(List<ListBean> list) {
//            this.list = list;
//        }
//
//        public static class ListBean implements Serializable {
//
//
//            public String user_id;
//
//            public String head_image;
//
//            public String user_name;
//
//            public String store_id;
//
//            public String  store_name;
//
//            public int  score;
//
//            public int  views;
//
//
//            public StoreBean store;
//
//
//            public String evaluate_id;
//
//            public String evaluate_content;
//
//            public String getUser_id() {
//                return user_id;
//            }
//
//            public void setUser_id(String user_id) {
//                this.user_id = user_id;
//            }
//
//            public String getHead_image() {
//                return head_image;
//            }
//
//            public void setHead_image(String head_image) {
//                this.head_image = head_image;
//            }
//
//            public String getUser_name() {
//                return user_name;
//            }
//
//            public void setUser_name(String user_name) {
//                this.user_name = user_name;
//            }
//
//            public String getStore_id() {
//                return store_id;
//            }
//
//            public void setStore_id(String store_id) {
//                this.store_id = store_id;
//            }
//
//            public String getStore_name() {
//                return store_name;
//            }
//
//            public void setStore_name(String store_name) {
//                this.store_name = store_name;
//            }
//
//            public int getScore() {
//                return score;
//            }
//
//            public void setScore(int score) {
//                this.score = score;
//            }
//
//            public int getViews() {
//                return views;
//            }
//
//            public void setViews(int views) {
//                this.views = views;
//            }
//
//            public StoreBean getStore() {
//                return store;
//            }
//
//            public void setStore(StoreBean store) {
//                this.store = store;
//            }
//
//            public String getEvaluate_id() {
//                return evaluate_id;
//            }
//
//            public void setEvaluate_id(String evaluate_id) {
//                this.evaluate_id = evaluate_id;
//            }
//
//            public String getEvaluate_content() {
//                return evaluate_content;
//            }
//
//            public void setEvaluate_content(String evaluate_content) {
//                this.evaluate_content = evaluate_content;
//            }
//
//            public static class StoreBean implements Serializable
//            {
//
//                    public String store_image;
//
//                    public String store_name;
//
//                    public String store_des;
//
//                    public String store_id;
//
//
//                public String getStore_image() {
//                    return store_image;
//                }
//
//                public void setStore_image(String store_image) {
//                    this.store_image = store_image;
//                }
//
//                public String getStore_name() {
//                    return store_name;
//                }
//
//                public void setStore_name(String store_name) {
//                    this.store_name = store_name;
//                }
//
//                public String getStore_des() {
//                    return store_des;
//                }
//
//                public void setStore_des(String store_des) {
//                    this.store_des = store_des;
//                }
//
//                public String getStore_id() {
//                    return store_id;
//                }
//
//                public void setStore_id(String store_id) {
//                    this.store_id = store_id;
//                }
//            }
//
//        }
//    }






}
