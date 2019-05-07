package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;
import com.insthub.cat.android.module.CollectListData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/20 20:39
 * Desc:${DESC}
 */

public class LoginTokenData extends BaseData {

    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable
    {


        private int  loginType ; //1 账号密码登录  2 微信登录
         private String phone;

         private int bind_phone;

         private String token;

         private String user_id;

         private String email;

         //0 非第一次登录，1 第一次登录
         private int first_login;


         private String accid;

         private String im_token;

         //客服账号
         private String im_code;


         private String share_image;

        public String getShare_image() {
            return share_image;
        }

        public void setShare_image(String share_image) {
            this.share_image = share_image;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getFirst_login() {
            return first_login;
        }

        public void setFirst_login(int first_login) {
            this.first_login = first_login;
        }


        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public String getIm_token() {
            return im_token;
        }

        public void setIm_token(String im_token) {
            this.im_token = im_token;
        }

        public String getIm_code() {
            return im_code;
        }

        public void setIm_code(String im_code) {
            this.im_code = im_code;
        }


        public int getLoginType() {
            return loginType;
        }

        public void setLoginType(int loginType) {
            this.loginType = loginType;
        }

        public int getBind_phone() {
            return bind_phone;
        }

        public void setBind_phone(int bind_phone) {
            this.bind_phone = bind_phone;
        }
    }
}
