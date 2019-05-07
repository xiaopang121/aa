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

public class StationItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.tv_station_num)
   public TextView tvStationNum;
    @Bind(R.id.tv_station_name)
    public TextView tvStationName;
    @Bind(R.id.bt_item_delete)
    public  Button btItemDelete;
    @Bind(R.id.bt_item_use)
    public  Button btItemUse;

    public StationItemHolder(View itemView) {
        super(itemView);

    }
}
