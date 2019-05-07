package com.insthub.cat.android.utils;

import android.text.TextUtils;

/**
 * Created by linux on 2017/7/20.
 */

public class StringUtils {

    /**
     * 隐藏银行卡号
     * @param cardNum
     */
    public static  String  hideCarNum(String cardNum)
    {

        if(TextUtils.isEmpty(cardNum))
        {
            return "";
        }

        int tatalLength = cardNum.length();
        StringBuffer sb = new StringBuffer();

        for(int x=0;x<tatalLength-4;x++)
        {
            sb.append("*");
        }

        sb.append(cardNum.substring(tatalLength-4,cardNum.length()));
        return sb.toString();

    }





}
