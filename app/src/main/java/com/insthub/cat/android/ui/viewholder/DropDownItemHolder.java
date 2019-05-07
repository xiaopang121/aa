package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class DropDownItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.tv_item_value)
   public  TextView tv_item_value;
    public DropDownItemHolder(View itemView) {
        super(itemView);

    }
}
