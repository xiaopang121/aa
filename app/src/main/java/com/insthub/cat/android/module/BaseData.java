package com.insthub.cat.android.module;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by linux on 2017/6/28.
 */

public class BaseData implements Serializable {

    //状态码
    @SerializedName("status")
    protected  int  errcode;

    //异常消息
    @SerializedName("message")
    protected  String message;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
