package com.insthub.cat.android.chat.adapter;

/**
 * 网络请求类回调接口
 * 
 * @ClassName: NewCallBackListener
 * @author yeliangliang
 * @date 2015-7-24 下午4:38:57
 */
public interface NetCallBackListener {
	/**
	 * 响应成功
	 *
	 * @author yeliangliang
	 * @date 2015-7-24 下午4:40:52
	 * @version V1.0
	 * @return void
	 */
	public void responseSuccess(int i,Object o);

	/**
	 * 请求失败
	 *
	 * @author yeliangliang
	 * @date 2015-7-24 下午4:41:14
	 * @version V1.0
	 * @return void
	 */
	public void responseFailed(int i);

	/**
	 * 请求出错
	 *
	 * @author yeliangliang
	 * @date 2015-7-24 下午4:41:22
	 * @version V1.0
	 * @return void
	 */
	public void responseError(int i);
}
