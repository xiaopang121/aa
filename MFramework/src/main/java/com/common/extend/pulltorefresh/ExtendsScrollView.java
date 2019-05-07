package com.common.extend.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;


/**
 * 自定义ScrollView
 *
 * @author 万万
 * @date 2013-09-13
 */
public class ExtendsScrollView extends ScrollView {

    private OnScrollYListener mListener;
    public ExtendsScrollView(Context context) {
        super(context);
    }

    public ExtendsScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendsScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }



    public void init(Context context, OnScrollYListener onScrollYListener) {

        mListener = onScrollYListener;

    }

    public void init(Context context, OnScrollYListener onScrollYListener,int endY) {
        // set scroll mode
        mListener = onScrollYListener;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        if(canTouchY < t){
//            super.onScrollChanged(l, canTouchY, oldl, oldt);
//        }else {
//            super.onScrollChanged(l, t, oldl, oldt);
//        }
        super.onScrollChanged(l, t, oldl, oldt);
        Log.e("onScrollChanged","l = " + l + "t = " + t + "oldl = " + oldl + "oldt = " + oldt );
        if(mListener != null){
            mListener.OnScrollY(getScrollY());
        }

    }

//
//
//    private float y1,y2;
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//            //当手指按下的时候
//            y1 = event.getY();
//        }
//        if(event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_MOVE) {
//            //当手指离开的时候
//            y2 = event.getY();
//        }
//        if(y1 - y2 >20 ) {//向上滑
//            if(canTouchY > getScrollY()+10){
//                onTouchEvent(event);
//                return super.onInterceptTouchEvent(event);
//            }else {
//                return false;
//            }
//        } else if(y2 - y1>20) {//向下滑
//            if(canTouchY > getScrollY()-10){
//                onTouchEvent(event);
//                return super.onInterceptTouchEvent(event);
//            }else {
//                return false;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        return super.onTouchEvent(ev);
//    }
//
    /**
     * ScrollY监听
     * @author markmjw
     */
    public interface OnScrollYListener {
        /**
         * 翻转回调方法
         */
        public void OnScrollY(int scrollY);
    }
//
////    @Override
////    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        int high = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
////                MeasureSpec.AT_MOST);
////        super.onMeasure(widthMeasureSpec, high);
////    }
//
////    @Override
////    public boolean dispatchTouchEvent(MotionEvent ev) {
////        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
////            return true; // 禁止滑动
////        }
////        return super.dispatchTouchEvent(ev);
////    }
//
//
////    @Override
////    public boolean isFocused() {
////        //return super.isFocused();
////
////        return isFouce;
////    }
////
////
////    public void setFouce(boolean isFouce)
////    {
////        this.isFouce = isFouce;
////    }

}
