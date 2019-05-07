package com.common.android.fui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by macbook on 16/6/2.
 * 适用一般的ListView BASEAdapter;
 */
public abstract  class BaseViewHolder  {
   protected View itemView;
    public BaseViewHolder(View itemView) {
        this.itemView = itemView;
        ButterKnife.bind(this,itemView);

    }
    protected Context getContent()
    {
        return itemView.getContext();
    }
}
