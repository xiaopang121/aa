package com.insthub.cat.android.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * User:macbook
 * DATE:2018/9/17 21:23
 * Desc:${DESC}
 */
public  class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position % 2 == 0) {
            outRect.left = 0;
            outRect.top = space;
            outRect.right = space / 2;
        } else {
            outRect.left = space / 2;
            outRect.top = space;
            outRect.right = 0;
        }
    }
}
