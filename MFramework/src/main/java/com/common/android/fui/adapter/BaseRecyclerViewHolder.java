package com.common.android.fui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by macbook on 16/6/2.
 *  * RecyclerView;
 */
public abstract  class  BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
