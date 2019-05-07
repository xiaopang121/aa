package com.common.android.fui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;




public abstract class BaseListAdapter extends BaseAdapter {
    protected LayoutInflater inflater;
    protected Context context;
    protected List<?> list;

    public BaseListAdapter(Context context, List<?> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    public BaseListAdapter(LayoutInflater inflater, List<?> list) {
        this.context = inflater.getContext();
        this.inflater = inflater;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
}
