package com.insthub.cat.android.module2;

import com.google.gson.annotations.SerializedName;
import com.insthub.cat.android.module.BaseData;

/**
 * Created by linux on 2018/6/4.
 */

public class WXPrepayData extends BaseData {


    /**
     * status : 10001
     * data : {"timestamp":1529050818,"sign":"5C627DECC83D418832E76DFF4BA710B9","mch_id":"1505597381","return_msg":"OK","prepay_id":"wx15162018166277f55a5489fc1228678013","appid":"wxf1c27bf4e6631d6a","nonce_str":"XyV02jUEg4e1m79cklOgOR6l588kM8YY"}
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
         * timestamp : 1529050818
         * sign : 5C627DECC83D418832E76DFF4BA710B9
         * mch_id : 1505597381
         * return_msg : OK
         * prepay_id : wx15162018166277f55a5489fc1228678013
         * appid : wxf1c27bf4e6631d6a
         * nonce_str : XyV02jUEg4e1m79cklOgOR6l588kM8YY
         */

        private int timestamp;
        private String sign;
        private String mch_id;
        private String return_msg;
        private String prepay_id;
        private String appid;
        private String nonce_str;

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }
    }
}
