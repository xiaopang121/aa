package com.insthub.cat.android.ui.activity;



import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.Toast;

import com.common.android.futils.DialogUtils;

public class BaseActivity extends FragmentActivity {
	
	  private Dialog dialog;






	    public void showLoadingDialog(String msg)
	    {
	        dialog = DialogUtils.createDialog(this,"",msg);
	        dialog.show();
	    }


	    public void dismissDialog()
	    {
	        if(dialog!=null && dialog.isShowing())
	        {
	            dialog.dismiss();
	            dialog = null;
	        }
	    }


	    public void showToast(String msg)
	    {
	        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
	    }

	    
	    public FragmentActivity getActivity()
	    {
	    	return this;
	    }


	
	

}
