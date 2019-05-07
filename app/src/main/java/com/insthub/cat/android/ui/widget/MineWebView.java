package com.insthub.cat.android.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by 011 on 2017/7/17.
 */

public class MineWebView extends WebView {

    public boolean mIgnoreTouchCancel;

    public MineWebView(Context context) {
        super(context);
    }

    public MineWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MineWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }






    public void ignoreTouchCancel(boolean val) {
        mIgnoreTouchCancel = val;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return ev.getAction() == MotionEvent.ACTION_CANCEL && mIgnoreTouchCancel || super.onTouchEvent(ev);
    }

}
