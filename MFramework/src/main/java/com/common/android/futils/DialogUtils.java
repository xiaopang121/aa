package com.common.android.futils;





import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.common.android.R;


public class DialogUtils {
	
	
/*	mpopupWindow.setWidth(LayoutParams.MATCH_PARENT);
	mpopupWindow.setHeight(LayoutParams.MATCH_PARENT);
	mpopupWindow.setBackgroundDrawable(new BitmapDrawable());*/

	private Dialog dialog;


	public static Dialog createDialog(Context mContext,String title,String message)
	{
		Dialog  dialog = new Dialog(mContext, R.style.dialog);
		int marginPx = UIUtil.dpToPx(mContext.getResources(), 80);
		//int dialogWidth = Utils.dpToPx(mContext.getResources(), mContext.getResources().getDimension(R.dimen.default_dialog_height_width));
		ViewGroup.LayoutParams pMLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_default, null);
		((TextView)mView.findViewById(R.id.tv_dialog_message)).setText(message);
		dialog.setCanceledOnTouchOutside(false);
		dialog.addContentView(mView, pMLayoutParams);
		return dialog;
		
	}





	public static Dialog create()
	{

         return null;
	}

	
	

	

}
