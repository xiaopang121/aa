package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.FriendListData;
import com.insthub.cat.android.ui.viewholder.FriendItemHolder;

import java.util.List;

/**
 * 抬头列表
 * Created by linux on 2017/12/12.
 */

public class FriendAdapter extends BaseRecyclerAdapter<FriendItemHolder, FriendListData.DataBean> {



    public FriendAdapter(Context context, List<FriendListData.DataBean> list) {
        super(context, list, R.layout.item_friend);

    }





    @Override
    public void onBindViewHolder(FriendItemHolder holder, int position) {
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
    public FriendItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FriendItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }

}
