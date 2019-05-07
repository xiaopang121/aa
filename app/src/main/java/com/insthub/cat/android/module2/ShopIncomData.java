package com.insthub.cat.android.module2;

import com.insthub.cat.android.module.BaseData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 商家收益
 * Created by linux on 2017/11/10.
 */

public class ShopIncomData extends BaseData {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private float total_income;

        public float getTotal_income() {
            return total_income;
        }

        public void setTotal_income(float total_income) {
            this.total_income = total_income;
        }
    }

}
