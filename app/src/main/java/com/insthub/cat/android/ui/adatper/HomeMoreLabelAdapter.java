package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.module2.LabelData;
import com.insthub.cat.android.ui.viewholder.DiscoverItemHolder;
import com.insthub.cat.android.ui.viewholder.HomeMoreItemHolder;
import com.insthub.cat.android.utils.DistanceUtil;

import java.util.List;

import butterknife.Bind;


public class HomeMoreLabelAdapter extends BaseRecyclerAdapter<HomeMoreItemHolder, DiscoverLabelData.DataBean.LabelLv1Bean> {



    OnSelectLableCallback onItemClickListener;


    public HomeMoreLabelAdapter(Context context, List<DiscoverLabelData.DataBean.LabelLv1Bean> list) {
        super(context, list, R.layout.item_more_label);

    }



    public void setOnClickCallback(OnSelectLableCallback callback)
    {
        onItemClickListener = callback;
    }


    @Override
    public void onBindViewHolder(HomeMoreItemHolder holder, int position) {
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

        DiscoverLabelData.DataBean.LabelLv1Bean dataItem = getDataItem(position);

        RequestOptions requestOptions = new RequestOptions()
                .circleCrop();
        Glide.with(getContext().getApplicationContext()).asBitmap()
                .load(dataItem.getImage())
                .apply(requestOptions)
                .into(holder.itemParentIcon);


        holder.itemParentName.setText(dataItem.getLable_name());
        holder.setChildLableList(dataItem,dataItem.getLabel_lv2());

        holder.setOnClickListerer(onItemClickListener);


    }

    @Override
    public HomeMoreItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeMoreItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent,false));
    }



    public interface  OnSelectLableCallback
    {
        public void onSelect(DiscoverLabelData.DataBean.LabelLv1Bean parentData,DiscoverLabelData.DataBean.LabelLv1Bean.LabelLv2Bean childData);
    }

}
