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

public class UserOrderItemHolder extends BaseRecyclerViewHolder {



    @Bind(R.id.item_order_contract)
    public TextView itemOrderContract;


    @Bind(R.id.item_order_shop_name)
    public TextView itemOrderShopName;
    @Bind(R.id.item_order_state)
    public TextView itemOrderState;
    @Bind(R.id.iv_collect_head)
    public ImageView ivCollectHead;
    @Bind(R.id.item_order_des)
    public TextView itemOrderDes;
    @Bind(R.id.item_order_createtime)
    public  TextView itemOrderCreatetime;
    @Bind(R.id.item_order_servicetime)
    public  TextView itemOrderServicetime;


    @Bind(R.id.item_order_delete)
    public TextView itemOrderDelete;

    @Bind(R.id.item_order_comment)
    public  TextView itemOrderComment;

    @Bind(R.id.item_order_pay)
    public  TextView itemOrderPay;

    @Bind(R.id.item_order_pay_server)
    public TextView itemOrderPayServer;


    //服务商申请验收
    @Bind(R.id.item_order_finish)
    public  TextView itemOrderFinish;


    @Bind(R.id.tv_service_price)
    public TextView tvServcePrice;


    @Bind(R.id.item_order_sub)
    public TextView tvOrderSub;


    public UserOrderItemHolder(View itemView) {
        super(itemView);

    }
}
