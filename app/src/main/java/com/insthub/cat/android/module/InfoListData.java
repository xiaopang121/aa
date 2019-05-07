package com.insthub.cat.android.module;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linux on 2017/11/10.
 */

public class InfoListData extends BaseData {


    public List<InfoItemData> data;


    public List<InfoItemData> getData() {

        if(data==null)
        {
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<InfoItemData> data) {
        this.data = data;
    }
}
