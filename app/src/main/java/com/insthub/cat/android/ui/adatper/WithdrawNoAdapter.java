package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.ShopOrderListData;
import com.insthub.cat.android.ui.viewholder.ShopWishdrawNoItemHolder;

import java.util.List;

/**
 * 店铺订单列表
 * Created by linux on 2017/12/12.
 */

public class WithdrawNoAdapter extends BaseRecyclerAdapter<ShopWishdrawNoItemHolder, ShopOrderListData.DataBean.ListBean> {




    public WithdrawNoAdapter(Context context, List<ShopOrderListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_withdraw_no);

    }


    @Override
    public void onBindViewHolder(ShopWishdrawNoItemHolder holder, int position) {
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
    public ShopWishdrawNoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopWishdrawNoItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
