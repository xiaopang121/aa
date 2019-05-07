package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class ShopItemHolder extends BaseRecyclerViewHolder {

    @Bind(R.id.item_shop_name)
  public  TextView itemShopName;
    @Bind(R.id.item_shop_categary)
    public TextView itemShopCategary;
    @Bind(R.id.item_shop_state)
    public  TextView itemShopState;
    @Bind(R.id.item_shop_time)
    public  TextView itemShopTime;
    @Bind(R.id.bt_shop_modify)
    public  Button btShopModify;
    @Bind(R.id.bt_shop_delete)
    public  Button btShopDelete;

    public ShopItemHolder(View itemView) {
        super(itemView);

    }
}
