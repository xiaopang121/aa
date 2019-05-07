package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.CouponListData;
import com.insthub.cat.android.ui.viewholder.CouponItemHolder;
import com.insthub.cat.android.ui.viewholder.SelectCouponItemHolder;

import java.util.List;

/**
 * 优惠券
 * Created by linux on 2017/12/12.
 */

public class SelectCouponListAdapter extends BaseRecyclerAdapter<SelectCouponItemHolder, CouponListData.DataBean.ListBean> {


    private int selctPostion=-1;


    public void setSelect(int item)
    {
        selctPostion = item;
        notifyDataSetChanged();
    }


    public int getSelect()
    {
        return selctPostion;
    }



    public SelectCouponListAdapter(Context context, List<CouponListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_select_coupon);
    }

    @Override
    public void onBindViewHolder(SelectCouponItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tvContent.setText("满"+getDataItem(position).getLimit_money()+"优惠"+getDataItem(position).getMoney()+"元");

        if(selctPostion==position)
        {
            holder.tvContent.setCompoundDrawablesWithIntrinsicBounds(null,null,getContext().getResources().getDrawable(R.drawable.ic_circle_pressed),null);
        }else
        {
            holder.tvContent.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        }




    }

    @Override
    public SelectCouponItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectCouponItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false));
    }
}
