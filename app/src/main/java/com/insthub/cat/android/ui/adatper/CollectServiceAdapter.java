package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.CollectListData;
import com.insthub.cat.android.ui.viewholder.CollectItemHolder;
import com.insthub.cat.android.utils.DistanceUtil;

import java.util.List;

/**
 * 列表
 * Created by linux on 2017/12/12.
 */

public class CollectServiceAdapter extends BaseRecyclerAdapter<CollectItemHolder, CollectListData.DataBean.ServiceListBean> {


    public CollectServiceAdapter(Context context, List<CollectListData.DataBean.ServiceListBean> list, boolean select) {
        super(context, list, R.layout.item_collect);

    }


    @Override
    public void onBindViewHolder(CollectItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


//        switch (getDataItem(position).getRentOutState())
//        {
//            case 1:
//
//                holder.ivBerthState.setText(R.string.berth_state_empty);
//                holder.ivBerthState.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.ic_lease_on),null,null,null);
//                break;
//            case 2:
//                holder.ivBerthState.setText(R.string.berth_state_work);
//                holder.ivBerthState.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.ic_lease_ok),null,null,null);
//                break;
//        }

        CollectListData.DataBean.ServiceListBean dataItem = getDataItem(position);

        holder.ivCollectHead.setImageResource(R.drawable.logo);
        if(!TextUtils.isEmpty(dataItem.getLogo()))
        {

//            Glide.with(getContext())
//                    .load(dataItem.getLogo())
//                    .transform(new GlideCircleTransform(getContext()))
//                    .error(R.drawable.logo)
//                    .placeholder(R.drawable.logo)
//                    .into(holder.ivCollectHead);


            RequestOptions requestOptions = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    ;
            Glide.with(getContext().getApplicationContext()).asBitmap()
                    .load(dataItem.getLogo())
                    .apply(requestOptions)
                    .into(holder.ivCollectHead);
        }


        holder.itemCollectTitle.setText(dataItem.getStore_name());
        holder.itemCollectType.setText(dataItem.getService_name());
        holder.tvCollectAddress.setText("");
        holder.tvCollectDistance.setText(DistanceUtil.getDistance(dataItem.getDistance()));
        holder.itemCollectSeconds.setText(dataItem.getOrder_count() + "次合作");
        holder.ratingBar.setStar(dataItem.getScore());
        holder.ratingBar.setClickable(false);

        if(!TextUtils.isEmpty(dataItem.getIs_auth()) && dataItem.getIs_auth().equals("1"))
        {
            holder.tvOauth.setVisibility(View.VISIBLE);
        }else {
            holder.tvOauth.setVisibility(View.GONE);
        }



        if(!TextUtils.isEmpty(dataItem.getIs_save())&&dataItem.getIs_save().equals("1"))
        {
            holder.tvSave.setVisibility(View.VISIBLE);
        }else {
            holder.tvSave.setVisibility(View.GONE);
        }




        if(!TextUtils.isEmpty(dataItem.getIs_recom()) && dataItem.getIs_recom().equals("1"))
        {
            holder.tvRecom.setVisibility(View.VISIBLE);
        }else {
            holder.tvRecom.setVisibility(View.GONE);
        }



    }

    @Override
    public CollectItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
