package com.insthub.cat.android.chat.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * User:macbook
 * DATE:17/3/4 01:09
 * Desc:${DESC}
 */
public class MyViewPager extends ViewPager {

    private boolean scrollble = false;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public boolean isScrollble() {
        return scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }

    public void scrollTo(int x, int y){  

        if (scrollble){  

            super.scrollTo(x, y);  

        }  

    }  

//    
    
    

}