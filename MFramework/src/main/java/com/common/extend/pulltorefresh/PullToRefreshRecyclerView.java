package com.common.extend.pulltorefresh;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.common.android.R;
import com.common.extend.pulltorefresh.PullToRefreshBase;


/**
 * @author xuanyouwu
 * @email xuanyouwu@163.com
 * @time 2016-04-19 11:02
 * <p/>
 * 下拉刷新 上拉加载 自动刷新PullRecyclerView
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

//
//    @Override
//    public Orientation getPullToRefreshScrollDirection() {
//        return Orientation.VERTICAL;
//    }
//
//    @Override
//    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
//        RecyclerView view = new RecyclerView(context, attrs);
//        view.setId(R.id.recycleView);
//        return view;
//    }

//
//    @Override
//    protected boolean isReadyForPullStart() {
//        return isFirstItemVisible();
//    }
//
//    @Override
//    protected boolean isReadyForPullEnd() {
//        return isLastItemVisible();
//    }
//
//    /**
//     * @Description: 判断第一个条目是否完全可见
//     *
//     * @return boolean:
//     * @version 1.0
//     * @date 2015-9-23
//     * @Author zhou.wenkai
//     */
//    private boolean isFirstItemVisible() {
//        final RecyclerView.Adapter<?> adapter = getRefreshableView().getAdapter();
//
//        // 如果未设置Adapter或者Adapter没有数据可以下拉刷新
//        if (null == adapter || adapter.getItemCount() == 0) {
//            if (DEBUG) {
//                Log.d(LOG_TAG, "isFirstItemVisible. Empty View.");
//            }
//            return true;
//
//        } else {
//            // 第一个条目完全展示,可以刷新
//            if (getFirstVisiblePosition() == 0) {
//                return mRefreshableView.getChildAt(0).getTop() >= mRefreshableView
//                        .getTop();
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * @Description: 获取第一个可见子View的位置下标
//     *
//     * @return int: 位置
//     * @version 1.0
//     * @date 2015-9-23
//     * @Author zhou.wenkai
//     */
//    private int getFirstVisiblePosition() {
//        View firstVisibleChild = mRefreshableView.getChildAt(0);
//        return firstVisibleChild != null ? mRefreshableView
//                .getChildAdapterPosition(firstVisibleChild) : -1;
//    }
//
//    /**
//     * @Description: 判断最后一个条目是否完全可见
//     *
//     * @return boolean:
//     * @version 1.0
//     * @date 2015-9-23
//     * @Author zhou.wenkai
//     */
//    private boolean isLastItemVisible() {
//        final RecyclerView.Adapter<?> adapter = getRefreshableView().getAdapter();
//
//        // 如果未设置Adapter或者Adapter没有数据可以上拉刷新
//        if (null == adapter || adapter.getItemCount() == 0) {
//            if (DEBUG) {
//                Log.d(LOG_TAG, "isLastItemVisible. Empty View.");
//            }
//            return true;
//
//        } else {
//            // 最后一个条目View完全展示,可以刷新
//            int lastVisiblePosition = getLastVisiblePosition();
//            if(lastVisiblePosition >= mRefreshableView.getAdapter().getItemCount()-1) {
//                return mRefreshableView.getChildAt(
//                        mRefreshableView.getChildCount() - 1).getBottom() <= mRefreshableView
//                        .getBottom();
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * @Description: 获取最后一个可见子View的位置下标
//     *
//     * @return int: 位置
//     * @version 1.0
//     * @date 2015-9-23
//     * @Author zhou.wenkai
//     */
//    private int getLastVisiblePosition() {
//        View lastVisibleChild = mRefreshableView.getChildAt(mRefreshableView
//                .getChildCount() - 1);
//        return lastVisibleChild != null ? mRefreshableView
//                .getChildAdapterPosition(lastVisibleChild) : -1;
//    }
//

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView view = new RecyclerView(context, attrs);
        view.setId(R.id.recycleView);
        return view;
    }

    @Override
    protected boolean isReadyForPullEnd() {

        if(getRefreshableView().getChildCount()<=0)
        {
            return false;
        }
        int lastVisiblePosition = getRefreshableView().getChildAdapterPosition(getRefreshableView().getChildAt(getRefreshableView().getChildCount() -1));
        if ( lastVisiblePosition >= getRefreshableView().getAdapter().getItemCount()-1) {
            return getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1).getBottom() <= getRefreshableView().getBottom();
        }
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        if (getRefreshableView().getChildCount() <= 0)
            return true;
        int firstVisiblePosition = getRefreshableView().getChildAdapterPosition(getRefreshableView().getChildAt(0));
        if (firstVisiblePosition == 0)
            return getRefreshableView().getChildAt(0).getTop() == getRefreshableView().getPaddingTop();
        else
            return false;
    }

}