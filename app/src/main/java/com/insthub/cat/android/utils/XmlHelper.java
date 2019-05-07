package com.insthub.cat.android.utils;

/**
 * Created by linux on 2017/7/21.
 */


public class XmlHelper {


    public static  String  parsePayResult (String content)
    {


        int startPos = content.indexOf("<RESPONSEMSG>");
        int endPos =  content.indexOf("</RESPONSEMSG>");

        return content.substring(startPos+"<RESPONSEMSG>".length(),endPos);
    }






}

