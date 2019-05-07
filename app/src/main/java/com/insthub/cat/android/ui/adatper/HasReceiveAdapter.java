package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.CollectListData;
import com.insthub.cat.android.ui.viewholder.CollectItemHolder;

import java.util.List;

/**
 * 已领取
 * Created by linux on 2017/12/12.
 */

public class HasReceiveAdapter extends BaseRecyclerAdapter<CollectItemHolder, CollectListData.DataBean> {



    public HasReceiveAdapter(Context context, List<CollectListData.DataBean> list, boolean select) {
        super(context, list, R.layout.item_income_record);

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



    }

    @Override
    public CollectItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
