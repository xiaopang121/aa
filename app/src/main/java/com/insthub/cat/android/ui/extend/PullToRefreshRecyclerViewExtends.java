package com.insthub.cat.android.ui.extend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;


/**
 * Created by linux on 2017/8/10.
 */

public class PullToRefreshRecyclerViewExtends extends PullToRefreshBase<SwipeMenuRecyclerView> {
    public PullToRefreshRecyclerViewExtends(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerViewExtends(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerViewExtends(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerViewExtends(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected SwipeMenuRecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        SwipeMenuRecyclerView view = new SwipeMenuRecyclerView(context, attrs);
        return view;
    }

    protected boolean isReadyForPullStart() {
        return this.isFirstItemVisible();
    }

    protected boolean isReadyForPullEnd() {
        return this.isLastItemVisible();
    }

    private boolean isFirstItemVisible() {
        RecyclerView.Adapter<?> adapter = ((SwipeMenuRecyclerView)this.getRefreshableView()).getAdapter();
        if(null != adapter && adapter.getItemCount() != 0) {
            return this.getFirstVisiblePosition() == 0?((SwipeMenuRecyclerView)this.mRefreshableView).getChildAt(0).getTop() >= ((RecyclerView)this.mRefreshableView).getTop():false;
        } else {
            Log.d("PullToRefresh", "isFirstItemVisible. Empty View.");
            return true;
        }
    }

    private int getFirstVisiblePosition() {
        View firstVisibleChild = ((SwipeMenuRecyclerView)this.mRefreshableView).getChildAt(0);
        return firstVisibleChild != null?((SwipeMenuRecyclerView)this.mRefreshableView).getChildAdapterPosition(firstVisibleChild):-1;
    }

    private boolean isLastItemVisible() {
        RecyclerView.Adapter<?> adapter = ((SwipeMenuRecyclerView)this.getRefreshableView()).getAdapter();
        if(null != adapter && adapter.getItemCount() != 0) {
            int lastVisiblePosition = this.getLastVisiblePosition();
            return lastVisiblePosition >= ((SwipeMenuRecyclerView)this.mRefreshableView).getAdapter().getItemCount() - 1?((SwipeMenuRecyclerView)this.mRefreshableView).getChildAt(((RecyclerView)this.mRefreshableView).getChildCount() - 1).getBottom() <= ((RecyclerView)this.mRefreshableView).getBottom():false;
        } else {
            Log.d("PullToRefresh", "isLastItemVisible. Empty View.");
            return true;
        }
    }

    private int getLastVisiblePosition() {
        View lastVisibleChild = ((SwipeMenuRecyclerView)this.mRefreshableView).getChildAt(((RecyclerView)this.mRefreshableView).getChildCount() - 1);
        return lastVisibleChild != null?((SwipeMenuRecyclerView)this.mRefreshableView).getChildAdapterPosition(lastVisibleChild):-1;
    }
}
