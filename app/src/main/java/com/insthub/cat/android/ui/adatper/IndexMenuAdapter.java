package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.ui.viewholder.IndexMenuHolder;

import java.util.List;

/**
 * 首页菜单
 * Created by linux on 2017/12/12.
 */

public class IndexMenuAdapter extends BaseRecyclerAdapter<IndexMenuHolder,HomeData.DataBean.ServiceTypeListBean> {



    public IndexMenuAdapter(Context context, List<HomeData.DataBean.ServiceTypeListBean> list) {
        super(context, list, R.layout.item_index_menu);
    }

    @Override
    public void onBindViewHolder(IndexMenuHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        RequestOptions requestOptions = new RequestOptions().centerInside();
        Glide.with(getContext()).asBitmap()
                .load(getDataItem(position).getImage())
                .apply(requestOptions)
                .into(holder.ivMenuImage);

        holder.ivMenuName.setText(getDataItem(position).getName());

    }

    @Override
    public IndexMenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IndexMenuHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false));
    }
}
