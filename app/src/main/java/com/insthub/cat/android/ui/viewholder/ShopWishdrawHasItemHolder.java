package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class ShopWishdrawHasItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.item_withdraw_money)
    public  TextView itemWithdrawMoney;
    @Bind(R.id.item_withdraw_time)
    public   TextView itemWithdrawTime;
    @Bind(R.id.item_withdraw_name)
    public   TextView itemWithdrawName;
    @Bind(R.id.item_withdraw_service)
    public   TextView itemWithdrawService;
    @Bind(R.id.item_withdraw_money2)
    public   TextView itemWithdrawMoney2;

    @Bind(R.id.item_create_time)
    public  TextView itemCreateTime;


    @Bind(R.id.bt_apply)
    public Button btApply;

    @Bind(R.id.item_withdraw_1)
    public   TextView itemWithdraw1;

    @Bind(R.id.iv_chat)
    public ImageView ivChat;





    public ShopWishdrawHasItemHolder(View itemView) {
        super(itemView);

    }
}
