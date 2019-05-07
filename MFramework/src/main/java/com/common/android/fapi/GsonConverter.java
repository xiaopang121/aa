package com.common.android.fapi;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import retrofit2.Converter;

//import retrofit.converter.ConversionException;
//import retrofit.converter.Converter;
//import retrofit.mime.TypedInput;
//import retrofit.mime.TypedOutput;


/**
 * JSON 解析器
 * 
 * @author macbook
 *
 */
public class GsonConverter implements Converter {

//	@Override
//	public Object fromBody(TypedInput body, Type type)
//			throws ConversionException {
//		String text = null;
//		try {
//			text = stream2string(body.in());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			JSONObject obj = new JSONObject(text);
//			if(obj.has("data") )
//			{
//
//				if(TextUtils.isEmpty(obj.getString("data")) ||  obj.getString("data").equals("null"))
//				{
//					obj.remove("data");
//				}
//			}
//
//
//			Gson gson  = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<String>()).create();
//			gson.fromJson(obj.toString(), type);
//			return gson.fromJson(obj.toString(), type);
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//	@Override
//	public TypedOutput toBody(Object object) {
//		return null;
//	}
//
//


	@Override
	public Object convert(Object o) throws IOException {
		return null;
	}

	private static String stream2string(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder out = new StringBuilder();
		char[] buff = new char[1024];
		int off = 0;
		int c = 0;
		while ((c = reader.read(buff, off, 1024)) != -1) {
			out.append(String.valueOf(buff, 0, c));
		}

		return getDataBody(out.toString());
	}



	//<string xmlns="http://www.amano.com.cn/WebService">{"code":1,"msg":"用户名或密码错误"}</string>
	private static String getDataBody(String data)
	{
		data.replace("<string xmlns=\'http://www.amano.com.cn/WebService\'>","");
		data.replace("</string>","");
		return data;
	}

}
