package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

/**
 * Created by linux on 2018/6/4.
 */

public class AdviserData extends BaseData {

    private String url;

    private int type;



    public AdviserData(String paramurl)
    {

        this.url = paramurl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
