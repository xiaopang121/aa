package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.UserDeductionData;
import com.insthub.cat.android.ui.viewholder.VoucherItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 抵用券
 * Created by linux on 2017/12/12.
 */

public class VoucherListAdapter extends BaseRecyclerAdapter<VoucherItemHolder, UserDeductionData.DataBean.ListBean> {


    private int type;

    /**
     * use_time :
     * state : 0
     * create_time : 2018-04-09 15:39:51
     * limit_money : 5000.00
     * money : 200.00
     * user_id : 1c62fe0f9b6f46b8805d43a7c93ab2ce
     * type : 0
     * deduction_id : 2
     * limit_time :
     */

    public VoucherListAdapter(Context context, List<UserDeductionData.DataBean.ListBean> list,int type) {
        super(context, list, R.layout.item_vouchert);

        this.type = type;
    }

    @Override
    public void onBindViewHolder(VoucherItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        if(type==0)
        {
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

        if(type==1) //低佣金
        {
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
                    holder.tvCouponTitle.setText("推广金");
                    holder.tvCouponUse.setText("去使用");
                    holder.tvMoney.setText(getDataItem(position).getMoney()+"");
                    holder.tvCouponDate.setText("有效期:"+getDataItem(position).getUse_time());
                    holder.tvCouponMoney.setText("仅限店铺推广使用");

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

                    holder.tvCouponTitle.setText("推广金");
                    holder.tvCouponDate.setText("有效期:"+getDataItem(position).getUse_time());
                    // holder.tvCouponMoney.setText("满"+getDataItem(position).get);
                    // holder.tvCouponTitle.setText();
                    holder.tvCouponUse.setText("已失效");
                    holder.tvMoney.setText(getDataItem(position).getMoney()+"");
                    holder.tvCouponMoney.setText("仅限店铺推广使用");
                    break;
            }
        }


    }

    @Override
    public VoucherItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VoucherItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false));
    }
}
