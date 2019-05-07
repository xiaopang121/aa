package com.common.android.fui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * User:macbook
 * DATE:17/3/4 01:09
 * Desc:${DESC}
 */
public class CustomViewPager extends ViewPager {

    private boolean scrollble = false;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public boolean isScrollble() {
        return scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }

//    public void scrollTo(int x, int y){
//
//        if (scrollble){
//
//            super.scrollTo(x, y);
//
//        }
//
//    }

//



    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return scrollble&&super.onInterceptTouchEvent(arg0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return scrollble&&super.onTouchEvent(arg0);
    }
    
    

}