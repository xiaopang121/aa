package com.common.android.fui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.common.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 圆圈式的指示器
 */
public class CircleNavigator extends View implements ViewPager.OnPageChangeListener {
    private int mRadius;
    private int mCircleColor;

    private int mStrokeWidth;
    private int mCircleSpacing;
    private int mCurrentIndex;
    private int mTotalCount;
    private Interpolator mStartInterpolator = new LinearInterpolator();

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<PointF> mCirclePoints = new ArrayList<PointF>();
    private float mIndicatorX;

    //字体
    private final static int TEXT_SIZE = 20;
    private ViewPager mViewpager;
    private int mIndicatorMargin=10;
    private int mIndicatorWidth;
    private int mIndicatorHeight;
    private String title[];
    private TextPaint textPaint;
    private int scrollX;

    private int mTouchSlop;

    private boolean mFollowTouch = true;    // 是否跟随手指滑动
    private int mNormalCircleColor = Color.LTGRAY;
    private int mSelectedCircleColor = Color.GRAY;

    private ViewPager.OnPageChangeListener listener;
    //判断是否绘制标题
    private boolean isDrawTitle=true;



    public CircleNavigator(Context context) {
        super(context);
        init(context);
    }


    public CircleNavigator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mRadius = dip2px(2);
        mCircleSpacing = dip2px(4);
        mStrokeWidth = dip2px(0.5f);
        mCircleColor = context.getResources().getColor(R.color.white_trans60);


        setPadding(0, dip2px(TEXT_SIZE + 8), 0, 0);
        setBackgroundColor(0x00000000);

        textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(dip2px(TEXT_SIZE));
        textPaint.setTextAlign(Paint.Align.CENTER);

    }



    public void setViewPager(ViewPager viewPager) {
        mViewpager = viewPager;
        mViewpager.setOnPageChangeListener(this);
        notifyDataSetChanged();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = width;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = mTotalCount * mRadius * 2 + (mTotalCount - 1) * mCircleSpacing + getPaddingLeft() + getPaddingRight() + mStrokeWidth * 2;
                break;
            default:
                break;
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = height;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = mRadius * 2 + mStrokeWidth * 2 + getPaddingTop() + getPaddingBottom();
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {


        if(isDrawTitle)
        {
            drawaTitle(canvas);
        }
        mPaint.setColor(mNormalCircleColor);
        drawCircles(canvas);
        mPaint.setColor(mCircleColor);
        drawIndicator(canvas);
    }


    /**
     * 绘制标题
     * @param canvas
     */
    private void drawaTitle(Canvas canvas)
    {
        int width = getWidth();
        int center = width/2;
        int x, y;
        int alpha;
        int alphaOffsetXMax = (int) (center * 1.0f);

        float ratio = (scrollX * 1.0f) / mViewpager.getWidth();

        for(int i =0; i<title.length; i++) {
            x = (int) (i * width - ratio * width + center);
            y = (int) (getHeight()*0.5f + dip2px(TEXT_SIZE + 8)*0.25f);
            int alphaOffsetX = Math.abs(x-center);

            if(alphaOffsetX > alphaOffsetXMax) {
                alpha = 0;
            } else {
                alpha = (int) ((1.0f - ((alphaOffsetX * 1.0f) / alphaOffsetXMax)) * 255);
            }
            textPaint.setAlpha(alpha);
            canvas.drawText(title[i], x, y, textPaint);
        }

    }


    private void drawCircles(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        for (int i = 0, j = mCirclePoints.size(); i < j; i++) {
            PointF pointF = mCirclePoints.get(i);
            canvas.drawCircle(pointF.x, pointF.y, mRadius, mPaint);
        }
    }

    private void drawIndicator(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        if (mCirclePoints.size() > 0) {
            canvas.drawCircle(mIndicatorX, (int) (getHeight() - mIndicatorMargin), mRadius, mPaint);
        }
    }

    private void prepareCirclePoints() {


        try {
            mCirclePoints.clear();
            if (mTotalCount > 0) {
                int y = (int) (getHeight() - mIndicatorMargin);
                int centerSpacing = mRadius * 2 + mCircleSpacing;
                int startX = getWidth()/2-(mTotalCount*centerSpacing-mCircleSpacing)/2+mRadius;

                //(int) (i * width - ratio * width + center);


                //int startX = mRadius + (int) (mStrokeWidth / 2.0f + 0.5f) + getPaddingLeft();
                for (int i = 0; i < mTotalCount; i++) {
                    PointF pointF = new PointF(startX, y);
                    mCirclePoints.add(pointF);
                    startX += centerSpacing;
                }
                mIndicatorX = mCirclePoints.get(mCurrentIndex).x;
            }
        }catch (RuntimeException e)
        {
            //notifyDataSetChanged();
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mCirclePoints.isEmpty()) {
            return;
        }

        int currentPosition = Math.min(mCirclePoints.size() - 1, position);
        int nextPosition = Math.min(mCirclePoints.size() - 1, position + 1);
        PointF current = mCirclePoints.get(currentPosition);
        PointF next = mCirclePoints.get(nextPosition);

        mIndicatorX = current.x + (next.x - current.x) * mStartInterpolator.getInterpolation(positionOffset);
        scrollX = position * mViewpager.getWidth() + positionOffsetPixels;
        invalidate();

        if(listener!=null)
        {
            listener.onPageScrolled( position,  positionOffset, positionOffsetPixels);
        }
    }


    @Override
    public void onPageSelected(int position) {
        mCurrentIndex = position;
        if (!mFollowTouch) {
            mIndicatorX = mCirclePoints.get(mCurrentIndex).x;
            invalidate();
        }

        if(listener!=null)
        {
            listener.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(listener!=null)
        {
            listener.onPageScrollStateChanged(state);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        prepareCirclePoints();
    }



    public void notifyDataSetChanged() {

        this.mTotalCount = mViewpager.getAdapter().getCount();

        this.title = new String[mViewpager.getAdapter().getCount()];
        for(int i=0; i<title.length; i++) {
            title[i] = String.valueOf(mViewpager.getAdapter().getPageTitle(i));
        }
        prepareCirclePoints();
        invalidate();
    }



    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int radius) {
        mRadius = radius;
        prepareCirclePoints();
        invalidate();
    }

    public int getCircleColor() {
        return mCircleColor;
    }

    public void setCircleColor(int circleColor) {
        mCircleColor = circleColor;
        invalidate();
    }


    public void setNormalColor(int normalColor)
    {
        mNormalCircleColor = normalColor;
        invalidate();

    }

    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        mStrokeWidth = strokeWidth;
        invalidate();
    }

    public int getCircleSpacing() {
        return mCircleSpacing;
    }

    public void setCircleSpacing(int circleSpacing) {
        mCircleSpacing = circleSpacing;
        prepareCirclePoints();
        invalidate();
    }

    public Interpolator getStartInterpolator() {
        return mStartInterpolator;
    }

    public void setStartInterpolator(Interpolator startInterpolator) {
        mStartInterpolator = startInterpolator;
        if (mStartInterpolator == null) {
            mStartInterpolator = new LinearInterpolator();
        }
    }

    public int getCircleCount() {
        return mTotalCount;
    }

    public void setCircleCount(int count) {
        mTotalCount = count;  // 此处不调用invalidate，让外部调用notifyDataSetChanged
    }




    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        this.listener = listener;
    }

    public void setEnableDrawTitle(boolean isDraw)
    {
        this.isDrawTitle = isDraw;
    }

}
