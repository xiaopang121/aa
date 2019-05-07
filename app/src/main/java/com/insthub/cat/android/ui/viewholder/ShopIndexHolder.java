package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.widget.StepsView;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class ShopIndexHolder extends BaseRecyclerViewHolder {




    public ImageView ivImage;


    public ShopIndexHolder(View itemView,boolean isAtt) {
        super(itemView);

        if(isAtt)
        {
            ivImage = itemView.findViewById(R.id.iv_item_image);
        }

    }
}
