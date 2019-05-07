package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.LabelListData;
import com.insthub.cat.android.module2.City;
import com.insthub.cat.android.ui.viewholder.PopupItemHolder;

import java.util.List;


public class PopupwindowLabel1Adapter extends BaseRecyclerAdapter<PopupItemHolder, LabelListData.DataBean.LabelLv1Bean> {




    public PopupwindowLabel1Adapter(Context context, List<LabelListData.DataBean.LabelLv1Bean> list) {
        super(context, list, R.layout.cp_item_city);

    }


    @Override
    public void onBindViewHolder(PopupItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tvItemResultListviewName.setText(getDataItem(position).getLable_name());


    }

    @Override
    public PopupItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopupItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(),parent,false));
    }

}
