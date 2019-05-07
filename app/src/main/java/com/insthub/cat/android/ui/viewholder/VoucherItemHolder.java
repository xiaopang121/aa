package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class VoucherItemHolder extends BaseRecyclerViewHolder {



    @Bind(R.id.tv_money)
    public  TextView tvMoney;

    @Bind(R.id.tv_coupon_use)
    public TextView tvCouponUse;

    @Bind(R.id.tv_coupon_title)
    public TextView tvCouponTitle;

    @Bind(R.id.tv_coupon_money)
    public TextView tvCouponMoney;

    @Bind(R.id.tv_coupon_date)
    public TextView tvCouponDate;


    @Bind(R.id.rll_bg)
    public RelativeLayout tvBg;

    @Bind(R.id.tv_coupon_v1)
    public  TextView tvCouponV1;

    @Bind(R.id.tv_unit)
    public  TextView tvUnite;



    public VoucherItemHolder(View itemView) {
        super(itemView);

    }
}
