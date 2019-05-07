package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;

import org.w3c.dom.Text;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class ShopOrderItemHolder extends BaseRecyclerViewHolder {

    @Bind(R.id.item_order_time)
    public TextView itemOrderTime;
    @Bind(R.id.item_order_state)
    public TextView itemOrderState;
    @Bind(R.id.item_order_name)
    public TextView itemOrderName;
    @Bind(R.id.item_order_service)
    public  TextView itemOrderService;
    @Bind(R.id.item_order_money)
    public TextView itemOrderMoney;

    @Bind(R.id.item_order_project_state)
    public TextView tvOrderProjectState;

//    @Bind(R.id.item_order_pay)
//    public TextView tvOrderApplyPay;


    @Bind(R.id.item_order_yanshou)
    public TextView tvOrderApplyYanshou;

    @Bind(R.id.iv_chat)
    public ImageView ivChat;



    public ShopOrderItemHolder(View itemView) {
        super(itemView);

    }
}
