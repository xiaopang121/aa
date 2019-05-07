package com.common.rxpay.sdk.alipay;

import com.common.android.futils.TimeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class OrderInfoUtil2_0 {






	private static final String ALGORITHM = "RSA";

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";

	private static String getAlgorithms(boolean rsa2) {
		return rsa2 ? SIGN_SHA256RSA_ALGORITHMS : SIGN_ALGORITHMS;
	}

	public static String sign(String content, String privateKey, boolean rsa2) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature
					.getInstance(getAlgorithms(rsa2));

			signature.initSign(priKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));

			byte[] signed = signature.sign();

			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	/**
	 * 构造授权参数列表
	 * 
	 * @param pid
	 * @param app_id
	 * @param target_id
	 * @return
	 */
	public static Map<String, String> buildAuthInfoMap(String pid, String app_id, String target_id, boolean rsa2) {
		Map<String, String> keyValues = new HashMap<String, String>();

		// 商户签约拿到的app_id，如：2013081700024223
		keyValues.put("app_id", app_id);

		// 商户签约拿到的pid，如：2088102123816631
		keyValues.put("pid", pid);

		// 服务接口名称， 固定值
		keyValues.put("apiname", "com.alipay.account.auth");

		// 商户类型标识， 固定值
		keyValues.put("app_name", "mc");

		// 业务类型， 固定值
		keyValues.put("biz_type", "openservice");

		// 产品码， 固定值
		keyValues.put("product_id", "APP_FAST_LOGIN");

		// 授权范围， 固定值
		keyValues.put("scope", "kuaijie");

		// 商户唯一标识，如：kkkkk091125
		keyValues.put("target_id", target_id);

		// 授权类型， 固定值
		keyValues.put("auth_type", "AUTHACCOUNT");

		// 签名类型
		keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");

		return keyValues;
	}

	/**
	 * 构造支付订单参数列表
	 * @param app_id
	 * @return
	 */
	public static Map<String, String> buildOrderParamMap(String app_id, boolean rsa2,float total_amount,String body,String outTradeNo,String notify_url,String subject) {
		Map<String, String> keyValues = new HashMap<String, String>();

		keyValues.put("app_id", app_id);

		keyValues.put("biz_content", "{\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\""+total_amount+"\",\"subject\":\""+subject+"\",\"body\":\""+body+"\",\"out_trade_no\":\"" + outTradeNo +  "\",\"notify_url\":\"" + notify_url + "\"}");
		
		keyValues.put("charset", "utf-8");

		keyValues.put("method", "alipay.trade.app.pay");

		keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");

		keyValues.put("timestamp", TimeUtils.formateTime(System.currentTimeMillis(),TimeUtils.FROAMTE_YMHMS));

		keyValues.put("version", "1.0");
		
		return keyValues;
	}
	
	/**
	 * 构造支付订单参数信息
	 * 
	 * @param map
	 * 支付订单参数
	 * @return
	 */
	public static String buildOrderParam(Map<String, String> map) {
		List<String> keys = new ArrayList<String>(map.keySet());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = map.get(key);
			sb.append(buildKeyValue(key, value, true));
			sb.append("&");
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		sb.append(buildKeyValue(tailKey, tailValue, true));

		return sb.toString();
	}
	
	/**
	 * 拼接键值对
	 * 
	 * @param key
	 * @param value
	 * @param isEncode
	 * @return
	 */
	private static String buildKeyValue(String key, String value, boolean isEncode) {
		StringBuilder sb = new StringBuilder();
		sb.append(key);
		sb.append("=");
		if (isEncode) {
			try {
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				sb.append(value);
			}
		} else {
			sb.append(value);
		}
		return sb.toString();
	}
	
	/**
	 * 对支付参数信息进行签名
	 * 
	 * @param map
	 *            待签名授权信息
	 * 
	 * @return
	 */
	public static String getSign(Map<String, String> map, String rsaKey, boolean rsa2) {
		List<String> keys = new ArrayList<String>(map.keySet());
		// key排序
		Collections.sort(keys);

		StringBuilder authInfo = new StringBuilder();
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = map.get(key);
			authInfo.append(buildKeyValue(key, value, false));
			authInfo.append("&");
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		authInfo.append(buildKeyValue(tailKey, tailValue, false));

		String oriSign = sign(authInfo.toString(), rsaKey, rsa2);
		String encodedSign = "";

		try {
			encodedSign = URLEncoder.encode(oriSign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "sign=" + encodedSign;
	}
	
	/**
	 * 要求外部订单号必须唯一。
	 * @return
	 */
	private static String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

}
