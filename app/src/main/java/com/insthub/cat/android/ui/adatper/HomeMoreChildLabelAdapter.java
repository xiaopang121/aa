package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.module2.LabelData;
import com.insthub.cat.android.ui.viewholder.HomeChildLabelItemHolder;
import com.insthub.cat.android.ui.viewholder.HomeMoreItemHolder;

import java.util.List;


public class HomeMoreChildLabelAdapter extends BaseRecyclerAdapter<HomeChildLabelItemHolder, DiscoverLabelData.DataBean.LabelLv1Bean.LabelLv2Bean> {




    public HomeMoreChildLabelAdapter(Context context, List<DiscoverLabelData.DataBean.LabelLv1Bean.LabelLv2Bean> list) {
        super(context, list, R.layout.item_more_child_label);

    }


    @Override
    public void onBindViewHolder(HomeChildLabelItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        DiscoverLabelData.DataBean.LabelLv1Bean.LabelLv2Bean dataItem = getDataItem(position);
        holder.tvItemChildName.setText(dataItem.getLable_name());


    }

    @Override
    public HomeChildLabelItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeChildLabelItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent,false));
    }
}
