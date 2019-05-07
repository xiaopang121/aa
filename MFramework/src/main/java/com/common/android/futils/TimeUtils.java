package com.common.android.futils;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by macbook
 */
public class TimeUtils {
    private static final String TAG = "TimeUtils";

    /**
     * 星期
     */
    public static String [] WEEKS=new String[]{"周日","周一","周二","周三","周四","周五","周六"};
    public static final String FROMATE_HM="HH:mm";
    public static final String FROMATE_MS="mm:ss";
    public static final String FROMATE_HMS="HH:mm:ss";
    public static final String FROMATE_YMHM="yyyy-MM-dd HH:mm";
    /**
     *
     */
    public static final String FROAMTE_YMHMS="yyyy-MM-dd HH:mm:ss";

    public static final String FROMATE_YMHM_ZH="yyyy年MM月dd日 ";

    public static final String FROMATE_YMHM_ZH_ex="dd天HH时mm分ss秒";

    public static final String FROMATE_MD_ZH="MM月dd日 ";
    public static final String FROMATE_YMD="yyyy-MM-dd";

    public static final int SECOND = 1000;
    public static final int MINUTES = SECOND * 60;
    public static final int HOUR = MINUTES * 60;
    public static final int DAY = HOUR * 24;
    public static final int WEEK = DAY * 7;

    /**
     * 返回用户友好的时间差
     *
     * @param httpTime    来自网络数据的时间
     * @param currentTime 当前时间
     * @return
     */
    public static String getTimeDifference(int httpTime, long currentTime) {

        long hTime = ((long) httpTime) * 1000;
        long dTime = currentTime - hTime;
        if (dTime < MINUTES) {
            return dTime / SECOND + "秒前";
        } else if (dTime < HOUR) {
            return dTime / MINUTES + "分钟前";
        } else if (dTime < DAY) {
            return dTime / HOUR + "小时前";
        } else if (dTime < WEEK) {
            return dTime / DAY + "天前";
        } else {
            return DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new Date(hTime));
        }

    }


    /**
     * 根据格式 格式化时间
     * @param time
     * @param formate
     * @return
     */
    public static String formateTime(long time,String formate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        return sdf.format(new Date(time));
    }

    /**
     * 解析时间
     * @param parserTime  时间
     * @param parserFormate 时间类型
     * @param targetFormate 目标类型
     * @return
     */
    public static long   parserTime(String parserTime,String parserFormate)
    {

        long result=0;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(parserFormate);

            Date date = sdf.parse(parserTime);
            result = date.getTime();
        }catch (ParseException e)
        {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * 解析时间
     * @param parserTime  时间
     * @param parserFormate 时间类型
     * @param targetFormate 目标类型
     * @return
     */
    public static String  parserTime(String parserTime,String parserFormate,String targetFormate)
    {

        String result="";
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(parserFormate);

            SimpleDateFormat t_sdf = new SimpleDateFormat(targetFormate);
            Date date = sdf.parse(parserTime);
            result = t_sdf.format(date);
        }catch (ParseException e)
        {
            e.printStackTrace();
        }

        return result;

    }


    /**
     * 获取当前是几点
     * @return
     */
    public static int getCurrentHour()
    {
        return  Integer.valueOf(formateTime(System.currentTimeMillis(),"HH"));
    }


    /**
     * 适合格式化音乐时间
     * @param time
     * @return
     */
    public static final String makeLongTimeString(long time) {
        time /= 1000;
        int minute = (int)(time / 60);
        minute %= 60;
        int second =  (int)(time % 60);
        @SuppressWarnings("unused")
        int hour = minute / 60;
        return String.format("%02d:%02d",minute, second);
    }


    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }


}
