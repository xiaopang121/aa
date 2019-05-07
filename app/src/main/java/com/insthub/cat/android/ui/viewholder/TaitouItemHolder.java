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

public class TaitouItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.item_taitou_title)
   public  TextView itemTaitouTitle;
    @Bind(R.id.iv_taitou_edit)
    public ImageView ivTaitouEdit;
    @Bind(R.id.item_taitou_default)
    public  TextView itemTaitouDefault;
    @Bind(R.id.iv_taitou_delete)
    public  ImageView ivTaitouDelete;

    @Bind(R.id.item_taitou_type)
    public TextView itemTaitouType;

    public TaitouItemHolder(View itemView) {
        super(itemView);

    }
}
