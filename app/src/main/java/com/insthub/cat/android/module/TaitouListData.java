package com.insthub.cat.android.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 抬头 列表
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class TaitouListData extends BaseData {

    public List<RaiseItemData> data;


    public List<RaiseItemData> getData() {

        if(data==null)
        {
            data = new ArrayList<>();
        }
        return data;
    }



    public void setData(List<RaiseItemData> data) {
        this.data = data;
    }

}
