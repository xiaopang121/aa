package com.common.rxpay.sdk.wechatpay;

import com.common.android.flog.KLog;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by linux on 2018/1/9.
 */

public class SignUtils {



    public static String wxsign(org.json.JSONObject object,String secretKey)
    {
        StringBuffer sb = new StringBuffer();
        Iterator<String> keys = object.keys();
        try
        {
            while (keys.hasNext())
            {
                String key = keys.next();
                sb.append(key).append("=").append(object.get(key)).append("&");
            }
            sb.append("key=");
            sb.append(secretKey);

        }catch (org.json.JSONException e)
        {
            e.printStackTrace();

        }
        String sign = getMessageDigest(sb.toString().getBytes());
        KLog.i("签名结果："+sign);
       return sign.toUpperCase();


    }


    public final static String getMessageDigest(byte[] buffer) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }







}
