package com.insthub.cat.android.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class RechareRecordListData extends BaseData {



    public List<DataBean> data;


    public List<DataBean> getData() {

        if(data==null)
        {
            data = new ArrayList<>();
        }
        return data;
    }




    public static class DataBean implements Serializable
    {

    }
}
