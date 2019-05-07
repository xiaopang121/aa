package com.common.android.fui.model;

import java.io.Serializable;

/**
 * Created by macbook on 16/6/1.
 */
public class DataModel implements Serializable{


    public static final int  DATA_SUCCESS_CODE=1;

    public static final int DATA_ERROR_CODE=2;

    public int result;

    public String msg;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



}
