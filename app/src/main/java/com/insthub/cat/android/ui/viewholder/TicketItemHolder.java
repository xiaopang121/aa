package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class TicketItemHolder extends BaseRecyclerViewHolder {

    @Bind(R.id.iv_ticket_head)
   public ImageView ivTicketHead;
    @Bind(R.id.item_ticket_time)
    public  TextView itemTicketTime;
    @Bind(R.id.item_ticket_code)
    public   TextView itemTicketCode;
    @Bind(R.id.item_ticket_money)
    public   TextView itemTicketMoney;
    @Bind(R.id.item_ticket_state)
    public  TextView itemTicketState;
    @Bind(R.id.item_shuie)
    public  TextView itemShuie;

    public TicketItemHolder(View itemView) {
        super(itemView);

    }
}
