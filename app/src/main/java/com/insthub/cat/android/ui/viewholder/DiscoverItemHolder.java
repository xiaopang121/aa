package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.example.xlhratingbar_lib.XLHRatingBar;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.widget.RatingBar;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class DiscoverItemHolder extends BaseRecyclerViewHolder {

    @Bind(R.id.iv_collect_head)
   public ImageView ivCollectHead;
    @Bind(R.id.tv_collect_distance)
    public TextView tvCollectDistance;
    @Bind(R.id.tv_collect_address)
    public TextView tvCollectAddress;
    @Bind(R.id.item_taitou_title)
    public  TextView itemCollectTitle;
    @Bind(R.id.ratingBar)
    public RatingBar ratingBar;
    @Bind(R.id.item_collect_type)
    public TextView itemCollectType;
    @Bind(R.id.item_collect_seconds)
    public  TextView itemCollectSeconds;
 @Bind(R.id.tv_oauth)
 public  TextView tvOauth;
 @Bind(R.id.tv_save)
 public  TextView tvSave;
 @Bind(R.id.tv_recom)
 public  TextView tvRecom;

    public DiscoverItemHolder(View itemView) {
        super(itemView);

    }
}
