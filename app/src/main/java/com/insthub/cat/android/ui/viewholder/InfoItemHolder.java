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

public class InfoItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.item_info_head)
    public ImageView ivInfoHead;

    @Bind(R.id.item_info_source)
    public TextView tvInfoSource;

    @Bind(R.id.item_info_title)
    public TextView tvInfoTitle;

    public InfoItemHolder(View itemView) {
        super(itemView);

    }
}
