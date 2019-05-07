package com.insthub.cat.android.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.common.android.fui.adapter.BaseRecyclerAdapter;

/**
 * User:macbook
 * DATE:2017/5/10 09:36
 * Desc:${DESC}
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge=true;
    BaseRecyclerAdapter adapter;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge, BaseRecyclerAdapter adapter) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
        this.adapter = adapter;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position

        spanCount =  adapter.isHeaderViewPos(position)||adapter.isFooterViewPos(position) ? 1:spanCount;

        if(spanCount==1)
        {
            outRect.left=0;
            outRect.right=0;
            outRect.top=0;
            outRect.bottom=0;
            return;
        }

        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}