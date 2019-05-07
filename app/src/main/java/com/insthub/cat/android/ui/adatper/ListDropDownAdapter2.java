package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.viewholder.DropDownItemHolder;

import java.util.List;

/**
 * 下来适配器
 * Created by linux on 2017/12/12.
 */

public class ListDropDownAdapter2 extends BaseRecyclerAdapter<DropDownItemHolder,String> {


    public ListDropDownAdapter2(Context context, List<String > list) {
        super(context, list, R.layout.item_drap_down2);
    }

    @Override
    public void onBindViewHolder(DropDownItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.tv_item_value.setText(getDataItem(position));
    }

    @Override
    public DropDownItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DropDownItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(),parent,false));
    }
}
