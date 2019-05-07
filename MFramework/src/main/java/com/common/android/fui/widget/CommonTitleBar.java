package com.common.android.fui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.R;
import com.common.android.ftheme.widgets.TintRelativeLayout;
import com.common.android.fsp.SharedPreferencesUtil;
import com.common.android.futils.UIUtil;


/**
 * Created by macbook on 16/7/20.
 *
 */
public class CommonTitleBar extends TintRelativeLayout {

    public ImageView ivLeft, ivRight;
    public Button btLeft,btRight;
    public TextView tvTitle;
    public View mView;

    public RelativeLayout mTintRelativeLayout;

    public CommonTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    public CommonTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CommonTitleBar(Context context) {
        super(context);
        initView();
    }


    public void initView() {

        mView = LayoutInflater.from(getContext()).inflate(R.layout.common_title_bar, null);
        ivLeft = (ImageView) mView.findViewById(R.id.iv_left);
        ivRight = (ImageView) mView.findViewById(R.id.iv_right);
        btLeft = (Button) mView.findViewById(R.id.bt_left);
        btRight = (Button) mView.findViewById(R.id.bt_right);
        tvTitle = (TextView)mView.findViewById(R.id.tv_title);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        addView(mView, lp);
    }



    public void setLeftImageMenu(int res)
    {
        ivLeft.setImageResource(res);
        ivLeft.setVisibility(View.VISIBLE);
    }


    public void setRighImageMenu(int res)
    {
        ivRight.setImageResource(res);
        ivRight.setVisibility(View.VISIBLE);
    }


    public void setRighButtonTitle(int res)
    {
        btRight.setText(res);
        btRight.setVisibility(View.VISIBLE);
    }

    public void setRighButtonTitle(String res)
    {
        btRight.setText(res);
        btRight.setVisibility(View.VISIBLE);
    }


    public void setLeftButtonTitle(int res)
    {
        btLeft.setText(res);
        btLeft.setVisibility(View.VISIBLE);
    }


    public void setLeftButtonTitle(String  res)
    {
        btLeft.setText(res);
        btLeft.setVisibility(View.VISIBLE);
    }

    public void setTitle(int res)
    {
        tvTitle.setText(res);
    }

    public void setTitle(String res)
    {
        tvTitle.setText(res);
    }



}
