package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

import java.util.List;

/**
 * Created by linux on 2017/11/10.
 */

public class HistoryRecordListData extends BaseData {


    /**
     * status : 10001
     * data : {"count":11,"list":[{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":29},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":30},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":31},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":32},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":33},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":34},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":35},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":36},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":37},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":38},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":39}],"page_count":1}
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
         * count : 11
         * list : [{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":29},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":30},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":31},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":32},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":33},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":34},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":35},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":36},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":37},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":38},{"title":"测试店铺-2号","money":"0.1","create_time":"2018-05-09 14:18:54","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","store_id":"f2f229ac599640ce9cc528626ab6e149","rp_id":39}]
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
             * title : 测试店铺-2号
             * money : 0.1
             * create_time : 2018-05-09 14:18:54
             * image : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132
             * store_id : f2f229ac599640ce9cc528626ab6e149
             * rp_id : 29
             */

            private String title;
            private String money;
            private String create_time;
            private String image;
            private String store_id;
            private int rp_id;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public int getRp_id() {
                return rp_id;
            }

            public void setRp_id(int rp_id) {
                this.rp_id = rp_id;
            }
        }
    }
}
