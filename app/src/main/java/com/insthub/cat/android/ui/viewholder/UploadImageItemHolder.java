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

public class UploadImageItemHolder extends BaseRecyclerViewHolder {



    @Bind(R.id.iv_item_image)
  public   ImageView ivItemImage;
    @Bind(R.id.iv_item_delete)
    public ImageView ivItemDelete;
    public UploadImageItemHolder(View itemView) {
        super(itemView);

    }
}
