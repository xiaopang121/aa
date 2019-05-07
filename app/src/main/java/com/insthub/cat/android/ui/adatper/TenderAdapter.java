package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.futils.TimeUtils;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.TenderListData;
import com.insthub.cat.android.ui.viewholder.TenderItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 竞价列表适配器
 * Created by linux on 2017/12/12.
 */

public class TenderAdapter extends BaseRecyclerAdapter<TenderItemHolder, TenderListData.DataBean.ListBean> {




    public TenderAdapter(Context context, List<TenderListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_tender);


    }


    @Override
    public void onBindViewHolder(TenderItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tvTitle.setText((position+1)+"."+getDataItem(position).getTitle());

        holder.tvCity.setText(getDataItem(position).getCity());

        holder.tvCategary.setText(getDataItem(position).getService());

       long createTime =  TimeUtils.parserTime(getDataItem(position).getCreate_time(),TimeUtils.FROAMTE_YMHMS);


        holder.tvLastDay.setText(getDataItem(position).getCreate_time_desc());

        holder.tvSubTitle.setText(getDataItem(position).getDetail());


    }

    @Override
    public TenderItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TenderItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
