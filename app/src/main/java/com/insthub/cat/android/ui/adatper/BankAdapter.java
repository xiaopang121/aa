package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.BankListData;
import com.insthub.cat.android.module.RaiseItemData;
import com.insthub.cat.android.ui.viewholder.BankItemHolder;
import com.insthub.cat.android.ui.viewholder.TaitouItemHolder;

import java.util.List;

/**
 * 银行卡适配器
 * Created by linux on 2017/12/12.
 */

public class BankAdapter extends BaseRecyclerAdapter<BankItemHolder, BankListData.DataBean.ListBean> {


    public BankAdapter(Context context, List<BankListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_bank);


    }



    @Override
    public void onBindViewHolder(BankItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);




        if(getDataItem(position).getBank().startsWith("农业"))
        {
            holder.ivType.setImageResource(R.drawable.ic_bank_nong);

        }else if(getDataItem(position).getBank().startsWith("中国"))
        {
            holder.ivType.setImageResource(R.drawable.ic_bank_china);
        }else if(getDataItem(position).getBank().startsWith("工商"))
        {
            holder.ivType.setImageResource(R.drawable.ic_bank_gong);
        }else if(getDataItem(position).getBank().startsWith("建设"))
        {
            holder.ivType.setImageResource(R.drawable.ic_bank_jianshe);
        }else if(getDataItem(position).getBank().startsWith("交通"))
        {
            holder.ivType.setImageResource(R.drawable.ic_bank_jiaotong);
        }


        holder.tvCaNum.setText(getDataItem(position).getBank_account());


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
    public BankItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BankItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }




}
