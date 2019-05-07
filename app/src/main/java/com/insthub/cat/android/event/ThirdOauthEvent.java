package com.insthub.cat.android.event;

/**
 * 第三方授权
 * Created by linux on 2017/11/7.
 */

public class ThirdOauthEvent extends BaseEvent {

    public String code;

    public int  type ;  // -1 授权 0==wx  1 ==ALIPAY


    public ThirdOauthEvent()
    {}


    public ThirdOauthEvent(String param, int oauthtype )
    {
        this.code = param;
        type = oauthtype;
    }




    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
