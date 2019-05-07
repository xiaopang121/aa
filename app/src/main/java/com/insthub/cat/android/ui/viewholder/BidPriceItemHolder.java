package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.widget.StepsView;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class BidPriceItemHolder extends BaseRecyclerViewHolder {





    @Bind(R.id.tv_bid_title)
    public  TextView tvBidTitle;


    @Bind(R.id.stepsView)
    public StepsView mStepsView;

    @Bind(R.id.tv_last_day)
    public TextView tvLastDay;

    @Bind(R.id.tv_city)
    public  TextView tvCity;

    @Bind(R.id.tv_type)
    public  TextView tvType;

    @Bind(R.id.tv_categary)
    public  TextView tvCategary;

    public BidPriceItemHolder(View itemView) {
        super(itemView);

    }
}
