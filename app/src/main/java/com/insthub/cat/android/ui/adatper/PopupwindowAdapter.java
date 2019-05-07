package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.City;
import com.insthub.cat.android.ui.viewholder.PopupItemHolder;

import java.util.List;

import butterknife.Bind;


public class PopupwindowAdapter extends BaseRecyclerAdapter<PopupItemHolder, City> {




    public PopupwindowAdapter(Context context, List<City> list) {
        super(context, list, R.layout.cp_item_city);

    }


    @Override
    public void onBindViewHolder(PopupItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tvItemResultListviewName.setText(getDataItem(position).getName());


    }

    @Override
    public PopupItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopupItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(),parent,false));
    }

}
