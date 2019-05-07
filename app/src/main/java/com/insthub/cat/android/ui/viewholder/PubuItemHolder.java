package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.example.xlhratingbar_lib.XLHRatingBar;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class PubuItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.image_extionsion)
    public ImageView ivExtionsion;

    @Bind(R.id.tv_name)
    public  TextView tvName;
    @Bind(R.id.ratingBar)
    public  XLHRatingBar ratingBar;
    @Bind(R.id.tv_address)
    public  TextView tvAddress;
    @Bind(R.id.tv_service)
    public  TextView tvService;


    public PubuItemHolder(View itemView) {
        super(itemView);

    }
}
