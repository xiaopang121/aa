package com.insthub.cat.android.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linux on 2017/11/10.
 */

public class InsertRaiseListData extends BaseData {


    public List<DataBean> data;


    public List<DataBean> getData() {

        if(data==null)
        {
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable
    {




    }
}
