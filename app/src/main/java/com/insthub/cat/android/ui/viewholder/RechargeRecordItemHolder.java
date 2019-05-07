package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class RechargeRecordItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.item_pay_type)
    TextView itemPayType;
    @Bind(R.id.item_pay_time)
    TextView itemPayTime;
    @Bind(R.id.item_pay_money)
    TextView itemPayMoney;

    public RechargeRecordItemHolder(View itemView) {
        super(itemView);

    }
}
