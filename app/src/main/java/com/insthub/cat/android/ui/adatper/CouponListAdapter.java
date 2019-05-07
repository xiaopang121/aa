package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.CouponListData;
import com.insthub.cat.android.ui.viewholder.CouponItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 优惠券
 * Created by linux on 2017/12/12.
 */

public class CouponListAdapter extends BaseRecyclerAdapter<CouponItemHolder, CouponListData.DataBean.ListBean> {


    public CouponListAdapter(Context context, List<CouponListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_coupon);
    }

    @Override
    public void onBindViewHolder(CouponItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);



        switch (getDataItem(position).getState())
        {
            case 0://未使用
                holder.tvBg.setBackgroundColor(getContext().getResources().getColor(R.color.yellow_color_pressed));

                holder.tvCouponDate.setTextColor(Color.WHITE);
                holder.tvCouponMoney.setTextColor(Color.WHITE);
                holder.tvCouponTitle.setTextColor(Color.WHITE);
                holder.tvCouponUse.setTextColor(Color.WHITE);
                holder.tvMoney.setTextColor(Color.WHITE);
                holder.tvCouponV1.setTextColor(Color.WHITE);
                holder.tvUnite.setTextColor(Color.WHITE);

                // holder.tvCouponDate.setText(getDataItem(position).get);
                // holder.tvCouponMoney.setText();
                holder.tvCouponTitle.setText(getDataItem(position).getType());
                holder.tvCouponUse.setText("去使用");
                holder.tvMoney.setText(getDataItem(position).getMoney()+"");
                holder.tvCouponDate.setText("有效期:"+getDataItem(position).getUse_time());
                holder.tvCouponTitle.setText("全铺通用");
                holder.tvCouponMoney.setText("满"+getDataItem(position).getLimit_money()+"可用");

                break;
            case 1://已经使用
                holder.tvBg.setBackgroundColor(getContext().getResources().getColor(R.color.md_divider_black));

                holder.tvCouponDate.setTextColor(getContext().getResources().getColor(R.color.B_black_50));
                holder.tvCouponMoney.setTextColor(getContext().getResources().getColor(R.color.B_black_50));
                holder.tvCouponTitle.setTextColor(getContext().getResources().getColor(R.color.B_black_50));
                holder.tvCouponUse.setTextColor(getContext().getResources().getColor(R.color.B_black_50));
                holder.tvMoney.setTextColor(getContext().getResources().getColor(R.color.B_black_50));
                holder.tvUnite.setTextColor(getContext().getResources().getColor(R.color.B_black_50));
                holder.tvCouponV1.setTextColor(getContext().getResources().getColor(R.color.B_black_50));

                holder.tvCouponDate.setText("有效期:"+getDataItem(position).getUse_time());
                // holder.tvCouponMoney.setText("满"+getDataItem(position).get);
                // holder.tvCouponTitle.setText();
                holder.tvCouponUse.setText("已失效");
                holder.tvMoney.setText(getDataItem(position).getMoney()+"");
                holder.tvCouponTitle.setText("全铺通用");
                holder.tvCouponMoney.setText("满"+getDataItem(position).getLimit_money()+"可用");
                break;
        }

    }

    @Override
    public CouponItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CouponItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false));
    }
}
