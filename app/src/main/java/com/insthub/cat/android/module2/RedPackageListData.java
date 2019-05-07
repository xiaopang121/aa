package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

import java.util.List;

/**
 * Created by linux on 2018/6/4.
 */

public class RedPackageListData extends BaseData {


    /**
     * status : 10001
     * data : {"cuse_id":30,"coupon_money":213213,"list":[{"create_time":"2018-05-09 14:18:54","state":"0","money":"0.1","store_id":"f2f229ac599640ce9cc528626ab6e149","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","rp_id":56},{"create_time":"2018-05-09 14:18:54","state":"0","money":"0.1","store_id":"f2f229ac599640ce9cc528626ab6e149","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","rp_id":57},{"create_time":"2018-05-09 14:18:54","state":"0","money":"0.1","store_id":"f2f229ac599640ce9cc528626ab6e149","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","rp_id":58},{"create_time":"2018-05-09 14:18:54","state":"0","money":"0.1","store_id":"f2f229ac599640ce9cc528626ab6e149","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","rp_id":59}]}
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
         * cuse_id : 30
         * coupon_money : 213213
         * list : [{"create_time":"2018-05-09 14:18:54","state":"0","money":"0.1","store_id":"f2f229ac599640ce9cc528626ab6e149","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","rp_id":56},{"create_time":"2018-05-09 14:18:54","state":"0","money":"0.1","store_id":"f2f229ac599640ce9cc528626ab6e149","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","rp_id":57},{"create_time":"2018-05-09 14:18:54","state":"0","money":"0.1","store_id":"f2f229ac599640ce9cc528626ab6e149","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","rp_id":58},{"create_time":"2018-05-09 14:18:54","state":"0","money":"0.1","store_id":"f2f229ac599640ce9cc528626ab6e149","image":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132","user_id":"1c62fe0f9b6f46b8805d43a7c93ab2ce","rp_id":59}]
         */

        private int cuse_id;
        private int coupon_money;
        private List<ListBean> list;

        public int getCuse_id() {
            return cuse_id;
        }

        public void setCuse_id(int cuse_id) {
            this.cuse_id = cuse_id;
        }

        public int getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(int coupon_money) {
            this.coupon_money = coupon_money;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * create_time : 2018-05-09 14:18:54
             * state : 0
             * money : 0.1
             * store_id : f2f229ac599640ce9cc528626ab6e149
             * image : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7wqBZtDbJIz6YURodWg53uO5o64mUlRkyHUTUj0ibz9uCKgWELJrdXEq3I4KxRsojH103bYDcESw/132
             * user_id : 1c62fe0f9b6f46b8805d43a7c93ab2ce
             * rp_id : 56
             */

            private String create_time;
            private String state;
            private String money;
            private String store_id;
            private String image;
            private String user_id;
            private int rp_id;
            private String store_name;
            private String logo;

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
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

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
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
