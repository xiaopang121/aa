package com.common.android.fui.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;



public abstract class BasicListAdapter<V,T> extends BaseListAdapter {
    private int layoutId;
    private V viewHolder = null;

    public BasicListAdapter(Context context, List<T> list, int layoutId) {
        super(context, list);
        this.layoutId = layoutId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layoutId, parent, false);
        }
        viewHolder = build(convertView);
        setViewHolder(viewHolder,position, (T) getItem(position));
        return convertView;
    }

    protected abstract void setViewHolder(V holder,int position, T bean);

    protected abstract V createNewHolder(View container);

    protected V build(View container) {
        V viewHolder = (V) container.getTag();
        if (viewHolder == null) {
            viewHolder = createNewHolder(container);
            container.setTag(viewHolder);
        }
        return viewHolder;
    }
}
