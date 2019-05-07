package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.widget.StepsView;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class OfferItemHolder extends BaseRecyclerViewHolder {





    @Bind(R.id.item_offer_title)
    public TextView itemOfferTitle;
    @Bind(R.id.item_order_type)
    public TextView itemOrderType;
    @Bind(R.id.item_order_state)
    public TextView itemOrderState;
    @Bind(R.id.item_offer_time)
    public  TextView itemOfferTime;
    @Bind(R.id.item_order_money)
    public TextView itemOrderMoney;


    public OfferItemHolder(View itemView) {
        super(itemView);

    }
}
