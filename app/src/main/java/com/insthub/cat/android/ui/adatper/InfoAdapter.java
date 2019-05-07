package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.InfoItemData;
import com.insthub.cat.android.module.RaiseItemData;
import com.insthub.cat.android.module2.NewsListData;
import com.insthub.cat.android.ui.viewholder.InfoItemHolder;
import com.insthub.cat.android.ui.viewholder.TaitouItemHolder;

import java.util.List;

/**
 * 抬头列表
 * Created by linux on 2017/12/12.
 */

public class InfoAdapter extends BaseRecyclerAdapter<InfoItemHolder,NewsListData.DataBean.ListBean> {


    public InfoAdapter(Context context, List<NewsListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_info);
    }

    @Override
    public void onBindViewHolder(InfoItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tvInfoTitle.setText(getDataItem(position).getTitle());

        Glide.with(getContext())
                    .load(getDataItem(position).getImage())
                    // .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .error(R.drawable.ic_default_head)
//                    .placeholder(R.drawable.ic_default_head)
                    .into(holder.ivInfoHead);


    }

    @Override
    public InfoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InfoItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
