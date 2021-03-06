/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 mob.com. All rights reserved.
 */

package com.insthub.cat.android.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.ToastUtil;
import com.common.rxpay.sdk.wechatpay.WXPayWay;
import com.insthub.cat.android.event.ThirdOauthEvent;
import com.insthub.cat.android.event.WXShareEvent;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

///** 微信客户端回调activity示例 */
//public class WXEntryActivity extends WechatHandlerActivity {
//
//	/**
//	 * 处理微信发出的向第三方应用请求app message
//	 * <p>
//	 * 在微信客户端中的聊天页面有“添加工具”，可以将本应用的图标添加到其中
//	 * 此后点击图标，下面的代码会被执行。Demo仅仅只是打开自己而已，但你可
//	 * 做点其他的事情，包括根本不打开任何页面
//	 */
//	public void onGetMessageFromWXReq(WXMediaMessage msg) {
//		if (msg != null) {
//			Intent iLaunchMyself = getPackageManager().getLaunchIntentForPackage(getPackageName());
//			startActivity(iLaunchMyself);
//		}
//	}
//
//	/**
//	 * 处理微信向第三方应用发起的消息
//	 * <p>
//	 * 此处用来接收从微信发送过来的消息，比方说本demo在wechatpage里面分享
//	 * 应用时可以不分享应用文件，而分享一段应用的自定义信息。接受方的微信
//	 * 客户端会通过这个方法，将这个信息发送回接收方手机上的本demo中，当作
//	 * 回调。
//	 * <p>
//	 * 本Demo只是将信息展示出来，但你可做点其他的事情，而不仅仅只是Toast
//	 */
//	public void onShowMessageFromWXReq(WXMediaMessage msg) {
//		if (msg != null && msg.mediaObject != null
//				&& (msg.mediaObject instanceof WXAppExtendObject)) {
//			WXAppExtendObject obj = (WXAppExtendObject) msg.mediaObject;
//			Toast.makeText(this, obj.extInfo, Toast.LENGTH_SHORT).show();
//		}
//	}
//}


/**
 * DO NOT EDIT THIS FILE!!! IT WAS GENERATED BY RXPAY. */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String appId = WXPayWay.getAppId(this);
		api = WXAPIFactory.createWXAPI(this, appId);
		api.handleIntent(getIntent(), this);;
	}

	@Override
	public void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);;
	}


	@Override
	public void onReq(BaseReq baseReq) {

	}

	@Override
	public void onResp(BaseResp baseResp) {

		if(baseResp instanceof SendAuth.Resp){
			SendAuth.Resp newResp = (SendAuth.Resp) baseResp;
			//获取微信传回的code
			String code = newResp.code;
			//ToastUtil.show(this, "授权码"+code);
			RxBusManager.getInstance().post(new ThirdOauthEvent(code,0));

		}else
		{


			switch (baseResp.errCode)
			{
				case BaseResp.ErrCode.ERR_OK:
					ToastUtil.show(getApplicationContext(),"分享成功");
					RxBusManager.getInstance().post(new WXShareEvent(1));



					break;
				case BaseResp.ErrCode.ERR_USER_CANCEL:
					ToastUtil.show(getApplicationContext(),"取消分享");
					RxBusManager.getInstance().post(new WXShareEvent(0));
					break;
				default:
					RxBusManager.getInstance().post(new WXShareEvent(0));
					break;
			}
		}
		finish();
	}
}
