package com.insthub.cat.android.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 *
 * 格式化金额   保留两位小数
 * Created by linux on 2017/7/22.
 */

public class DeclareUtil {


    public static String  formateNumber(String data)
    {
        if(TextUtils.isEmpty(data))
        {
            return "0.00";
        }

        DecimalFormat df = new DecimalFormat("0.00");
       return  df.format(Double.valueOf(data));
    }


    public static String  formateNumber(float data)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        return  df.format(data);
    }


    public static String  formatePhone(String phone)
    {
        String result="";

        if(TextUtils.isEmpty(phone))
        {
            return "";
        }
      if(phone.length()==11)
      {
          result = phone.substring(0,3)+"****"+ phone.substring(7,11);
      }else
      {
          result = phone;
      }

      return result;
    }


}
