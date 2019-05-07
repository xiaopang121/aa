package com.insthub.cat.android.utils;

import android.text.TextUtils;

public class Utils {
	public static int getWidth(int height) {
		int temp = height / 9;

		return 1;
	}
//
//	public static int getHeight(int width) {
//		int temp = width / 16;
//
//		return temp * 9;
//	}
	/**
	 * 宽高比例
	 * @param widthProportion
	 * @param heightProportion
	 * @param width
	 * @return
	 */
	public static int getHeight(int widthProportion,int heightProportion,int width) {
		int temp = width / widthProportion;
		
		return temp * heightProportion;
	}



	public static String hidePhone(String phonenum) {
		if (!TextUtils.isEmpty(phonenum) && phonenum.length() > 6) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < phonenum.length(); i++) {
				char c = phonenum.charAt(i);
				if (i >= 3 && i <= 6) {
					sb.append('*');
				} else {
					sb.append(c);
				}
			}
			return sb.toString();
		}

		return phonenum;
	}


}
