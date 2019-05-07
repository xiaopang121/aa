package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.SearchListData;
import com.insthub.cat.android.ui.viewholder.SearchItemHolder;

import java.util.List;

/**
 * 搜索列表
 * Created by linux on 2017/12/12.
 */

public class SearchAdapter extends BaseRecyclerAdapter<SearchItemHolder, SearchListData.DataBean.ListBean> {



    public SearchAdapter(Context context, List<SearchListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_search);

    }


    @Override
    public void onBindViewHolder(SearchItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


            holder.itemSearchTitle.setText(getDataItem(position).getGs_name());


    }

    @Override
    public SearchItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent,false));
    }



}
