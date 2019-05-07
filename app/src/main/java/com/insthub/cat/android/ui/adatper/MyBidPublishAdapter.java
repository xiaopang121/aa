package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.BidPriceListData;
import com.insthub.cat.android.module2.ActionListData;
import com.insthub.cat.android.ui.viewholder.ActiveItemHolder;
import com.insthub.cat.android.ui.viewholder.MyPublishItemHolder;
import com.insthub.cat.android.utils.DistanceUtil;

import java.util.List;

import butterknife.Bind;

/**
 * 列表
 * Created by linux on 2017/12/12.
 */

public class MyBidPublishAdapter extends BaseRecyclerAdapter<MyPublishItemHolder, BidPriceListData.DataBean.ListBean> {


    public static final int OPEN_LAYOUT_TYPE = 3000;

    public static final int CLOSE_LAYOUT_TYPE = 4000;


    public static final int DELETE_LAYOUT_TYPE = 5000;

    public MyBidPublishAdapter(Context context, List<BidPriceListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_publish);

    }


    @Override
    public void onBindViewHolder(MyPublishItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        BidPriceListData.DataBean.ListBean dataItem = getDataItem(position);

        holder.ivPublishType.setText("竞");
        holder.ivPublishType.setBackgroundResource(R.drawable.ic_type_jingjia);
        holder.tvPublishTitle.setText(dataItem.getTitle());
        holder.tvPublishState.setText(dataItem.getState());
        holder.tvPublishEndtime.setText(dataItem.getEnd_time());
        holder.tvPeoples.setText(dataItem.getJoin_count()+"人");


    }

    @Override
    public MyPublishItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyPublishItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


    @Override
    public int getItemViewType(int position) {


        if(getDataItem(position).getState().equals("发布中"))
        {
            return CLOSE_LAYOUT_TYPE;
        }

        if(getDataItem(position).getState().equals("已关闭"))
        {
            return OPEN_LAYOUT_TYPE;
        }


        return DELETE_LAYOUT_TYPE;
    }



}
