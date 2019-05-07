package com.insthub.cat.android.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.common.android.futils.UIUtil;
import com.insthub.cat.android.R;

/**
 * Created by linux on 2017/12/22.
 */

public class MonthPopupWindow implements View.OnClickListener {


    private PopupWindow mPopupWindow;

    private TextView tvMonth0;
    private TextView tvMonth1;
    private TextView tvMonth2;
    private TextView tvMonth3;
    private TextView tvMonth4;
    private TextView tvMonth5;

    private SelectMonthCallback mSelectMonthCallback;


    public MonthPopupWindow(Context context,SelectMonthCallback callback)
    {

        mSelectMonthCallback = callback;
        View popupView = LayoutInflater.from(context).inflate(R.layout.layout_popup_top_right, null);

        tvMonth0 =(TextView)popupView.findViewById(R.id.tv_pre_month_0);
        tvMonth1 =(TextView)popupView.findViewById(R.id.tv_pre_month_1);
        tvMonth2 =(TextView)popupView.findViewById(R.id.tv_pre_month_2);
        tvMonth3 =(TextView)popupView.findViewById(R.id.tv_pre_month_3);
        tvMonth4 =(TextView)popupView.findViewById(R.id.tv_pre_month_4);
        tvMonth5 =(TextView)popupView.findViewById(R.id.tv_pre_month_5);

        tvMonth0.setTag(0);
        tvMonth1.setTag(-1);
        tvMonth2.setTag(-2);
        tvMonth3.setTag(-3);
        tvMonth4.setTag(-4);
        tvMonth5.setTag(-5);

        tvMonth0.setOnClickListener(this);
        tvMonth1.setOnClickListener(this);
        tvMonth2.setOnClickListener(this);
        tvMonth3.setOnClickListener(this);
        tvMonth4.setOnClickListener(this);
        tvMonth5.setOnClickListener(this);

        mPopupWindow = new PopupWindow(popupView, UIUtil.dpToPx(context.getResources(),100), ViewGroup.LayoutParams.WRAP_CONTENT, true);

        mPopupWindow.setAnimationStyle(R.style.popup_window_anim);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));

        mPopupWindow.setFocusable(true);

        mPopupWindow.setOutsideTouchable(true);

        mPopupWindow.update();

    }



    public void showView(View view)
    {
        mPopupWindow.showAsDropDown(view, -50, 20);
    }

    @Override
    public void onClick(View view) {


        int item = (int) view.getTag();
        mSelectMonthCallback.onSelectMonth(item);

        mPopupWindow.dismiss();
    }

    public interface  SelectMonthCallback
    {
        public void onSelectMonth(int item);
    }

}
