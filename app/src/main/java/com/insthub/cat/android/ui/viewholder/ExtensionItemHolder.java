package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class ExtensionItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.image_extionsion)
    public ImageView ivExtionsion;



    public ExtensionItemHolder(View itemView) {
        super(itemView);

    }
}
