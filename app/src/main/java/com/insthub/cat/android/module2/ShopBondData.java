package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

import java.util.List;

/**
 * Created by linux on 2018/6/4.
 */

public class ShopBondData extends BaseData {


    /**
     * status : 10001
     * data : {"email":"15181608354@qq.com","label_lv2_str":["客户服务","协同OA","供应链","行业软件","管理顾问","法务服务"],"money":4100,"label_lv1":"5","label_lv2":"45,46,47,48,49,50","store_id":"31df6d2f5aa740f290ac00830d4675f5"}
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
         * email : 15181608354@qq.com
         * label_lv2_str : ["客户服务","协同OA","供应链","行业软件","管理顾问","法务服务"]
         * money : 4100
         * label_lv1 : 5
         * label_lv2 : 45,46,47,48,49,50
         * store_id : 31df6d2f5aa740f290ac00830d4675f5
         */

        private String email;
        private int money;
        private String label_lv1;
        private String label_lv2;
        private String store_id;
        private List<String> label_lv2_str;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getLabel_lv1() {
            return label_lv1;
        }

        public void setLabel_lv1(String label_lv1) {
            this.label_lv1 = label_lv1;
        }

        public String getLabel_lv2() {
            return label_lv2;
        }

        public void setLabel_lv2(String label_lv2) {
            this.label_lv2 = label_lv2;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public List<String> getLabel_lv2_str() {

            return  label_lv2_str;
        }




        public String getServerContent()
        {String result ="";


            if(label_lv2_str!=null)
            {
                for (String item :label_lv2_str)
                {
                    result=result+","+item;
                }
            }

            return result;
        }


        public void setLabel_lv2_str(List<String> label_lv2_str) {
            this.label_lv2_str = label_lv2_str;
        }
    }
}
