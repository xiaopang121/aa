package com.common.android.fui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.common.android.R;
import com.common.android.fui.activity.BaseModel;
import com.common.android.fui.activity.BasePresenter;
import com.common.android.futils.DialogUtils;
import com.common.android.fui.widget.ToastView;
import com.common.android.futils.TUtil;

import butterknife.ButterKnife;


/**
 * @fileName BaseFragment.java
 * @description Fragment基类
 */
public abstract class BaseFragment <T extends BasePresenter, E extends BaseModel>extends Fragment {

	protected View mMainView;
	protected Dialog mLoadDialog;
	public T mPresenter;
	public E mModel;

	//当前界面是否已经显示
	public boolean isVisiable;
	//UI 是否已经初始化完成
	public boolean isPrepare;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Activity重新创建时可以不完全销毁Fragmen
		//setRetainInstance(true);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		//在这里绑定数据

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return createView(getLayoutResId(),inflater,container);
	}



	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if(getUserVisibleHint())
		{
			isVisiable = true;
			lazyLoad();
		}else
		{
			hideLoad();
			isVisiable=false;
		}

	}
	
	protected  View createView(int resourceId,LayoutInflater inflater,ViewGroup container)
	{
	     mMainView = inflater.inflate(resourceId, container, false);
		 ButterKnife.bind(this,mMainView);
		 mPresenter = TUtil.getT(this, 0);
		 mModel= TUtil.getT(this,1);
			if(mPresenter!=null){
				mPresenter.context=this.getActivity();
			}
		 initPresenter();
	     bindViewById();
	     bindEvent();
		 isPrepare =true;
		lazyLoad();
         return mMainView;
	}

	//简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
	protected  void initPresenter(){}

	/**
	 * 当前Fragment是否已添�?
	 */
	private boolean isAddeded;

	public void setIsAddeded(boolean arg0) {
		isAddeded = arg0;
	}

	public boolean isAddeded() {
		// TODO Auto-generated method stub
		return isAddeded || super.isAdded();
	}


	/***
	 * 延迟加载
	 */
	protected  void lazyLoad(){
		if(isPrepare && isVisiable)
		{
			bindData();
		}
	}

	/**
	 * 绑定布局资源
	 * @return
	 */
	protected abstract int  getLayoutResId();


	/** 绑定界面UI **/
	protected  void bindViewById(){}

	/** 界面数据初始�? **/
	protected  void bindData(){}

	/**
	 * Fragment不显示事件
	 */
	protected  void hideLoad(){

	};


	/** 界面UI事件监听 **/
	protected  void bindEvent(){}

	/**
	 * 注销事件绑定
	 */
	protected   void unbindEvent(){}

	


	protected  abstract  int bindColorPrimary();


	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		getActivity().startActivity(intent);
		getActivity().overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);
	}

	/** 通过Action跳转界面 **/
	protected void startActivity(String action) {
		startActivity(action, null);
	}

	
	/**
	 * requestCode 跳转
	 */
	protected void startActivityForResults(Intent intent,int requestCode)
	{
		startActivityForResult(intent, requestCode);
		getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}
	
	/** 含有Bundle通过Action跳转界面 **/
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		getActivity().startActivity(intent);
		getActivity().overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);
	}


	/**
	 * 显示加载�?
	 * @param msg
	 */
	protected Dialog showLoadDialog(String msg)
	{
		mLoadDialog = DialogUtils.createDialog(getActivity(), "", msg);
		if(!mLoadDialog.isShowing())
		{
			mLoadDialog.show();
			mLoadDialog.setCanceledOnTouchOutside(false);
		}
		return mLoadDialog;
	}


	/**
	 * 关闭加载框
	 */
	protected void dismissLoadDialog()
	{
		if(mLoadDialog!=null && mLoadDialog.isShowing())
		{
			mLoadDialog.dismiss();
		}
	}
	protected void gone(final View... views) {
		if (views != null && views.length > 0) {
			for (View view : views) {
				if (view != null) {
					view.setVisibility(View.GONE);
				}
			}
		}
	}

	protected void visible(final View... views) {
		if (views != null && views.length > 0) {
			for (View view : views) {
				if (view != null) {
					view.setVisibility(View.VISIBLE);
				}
			}
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unbindEvent();
		ButterKnife.unbind(this);
	}
}
