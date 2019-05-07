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

public class HongbaoItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.iv_hongbao)
    public  ImageView ivHongbao;
    @Bind(R.id.iv_head)
    public  ImageView ivHead;
    @Bind(R.id.tv_name)
    public  TextView tvName;


    public HongbaoItemHolder(View itemView) {
        super(itemView);

    }
}
