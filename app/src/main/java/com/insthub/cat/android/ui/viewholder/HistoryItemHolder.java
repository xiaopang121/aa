package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class HistoryItemHolder extends BaseRecyclerViewHolder {



    @Bind(R.id.bt_receiver)
    public Button btReceiver;
    @Bind(R.id.tv_money)
    public TextView tvMoney;
    @Bind(R.id.item_taitou_title)
    public TextView itemTaitouTitle;
    @Bind(R.id.item_collect_seconds)
    public TextView itemCollectSeconds;

    @Bind(R.id.lly_money_bar)
    public LinearLayout llyMoneyBar;


    public HistoryItemHolder(View itemView) {
        super(itemView);

    }
}
