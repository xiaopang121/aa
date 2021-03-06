package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.RedPackageListData;
import com.insthub.cat.android.ui.viewholder.HongbaoItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 红包
 * Created by linux on 2017/12/12.
 */

public class HongbaoAdapter extends BaseRecyclerAdapter<HongbaoItemHolder, RedPackageListData.DataBean.ListBean> {




    public HongbaoAdapter(Context context, List<RedPackageListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_hongbao);
    }

    @Override
    public void onBindViewHolder(HongbaoItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        RequestOptions requestOptions = new RequestOptions()
                .circleCrop()
                .error(R.drawable.ic_default_head)
                .placeholder(R.drawable.ic_default_head)
                ;
        Glide.with(getContext().getApplicationContext()).asBitmap()
                .load(getDataItem(position).getLogo())
                .apply(requestOptions)
                .into(holder.ivHead);

        holder.tvName.setText(getDataItem(position).getStore_name());
//
//        holder.ratingBar.setCountSelected(5);
//
//        holder.tvAddress.setText(getDataItem(position).getAddress());
//
//        holder.tvService.setText("本所主要业务:"+getDataItem(position).getService_content());


    }

    @Override
    public HongbaoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HongbaoItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false));
    }
}
