package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;

/**
 * User:macbook
 * DATE:2017/12/20 20:39
 * Desc:${DESC}
 */

public class UploadImageInfoData extends BaseData {


    /**
     * data : {"prefix":"http://139.196.92.19:8080/upload/","full_path":"http://139.196.92.19:8080/upload/head/76495b2eeeee495eb6b70ddc81be6d3d.png","save_path":"head/76495b2eeeee495eb6b70ddc81be6d3d.png"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * prefix : http://139.196.92.19:8080/upload/
         * full_path : http://139.196.92.19:8080/upload/head/76495b2eeeee495eb6b70ddc81be6d3d.png
         * save_path : head/76495b2eeeee495eb6b70ddc81be6d3d.png
         */

        private String prefix;
        private String full_path;
        private String save_path;

        private int  postion;

        private int type;


        public DataBean(){}

        public DataBean(int type){

            this.type = type;
        }

        public DataBean(String tt){
            this.full_path = tt;
        }

        public DataBean(String save_path,String full_path){
            this.save_path = save_path;
            this.full_path = full_path;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getPostion() {
            return postion;
        }

        public void setPostion(int postion) {
            this.postion = postion;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getFull_path() {
            return full_path;
        }

        public void setFull_path(String full_path) {
            this.full_path = full_path;
        }

        public String getSave_path() {
            return save_path;
        }

        public void setSave_path(String save_path) {
            this.save_path = save_path;
        }
    }
}
