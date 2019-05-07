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

public class XiaojingItemHolder extends BaseRecyclerViewHolder {




    @Bind(R.id.item_state)
    public TextView itemState;
    @Bind(R.id.item_title)
    public TextView itemTitle;

    public XiaojingItemHolder(View itemView) {
        super(itemView);

    }
}
