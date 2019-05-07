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

public class ThemeItemHolder extends BaseRecyclerViewHolder {

    @Bind(R.id.iv_image)
   public ImageView imageView;


    public ThemeItemHolder(View itemView) {
        super(itemView);

    }
}
