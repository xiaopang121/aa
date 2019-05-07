package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.widget.StepsView;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class InnovateItemHolder extends BaseRecyclerViewHolder {



    @Bind(R.id.tv_bid_title)
   public  TextView tvBidTitle;
    @Bind(R.id.tv_innvote_state)
    public  TextView tvInnvoteState;
    @Bind(R.id.tv_title)
    public  TextView tvTitle;
    @Bind(R.id.tv_money)
    public   TextView tvMoney;
    @Bind(R.id.llu_bg)
    public ImageView lluBg;



    public InnovateItemHolder(View itemView) {
        super(itemView);
    }
}
