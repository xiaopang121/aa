package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.widget.RatingBar;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class ActiveItemHolder extends BaseRecyclerViewHolder {

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
//    @Bind(R.id.item_collect_seconds)
//    public  TextView itemCollectSeconds;

    @Bind(R.id.tv_oauth)
    public  TextView tvOauth;
    @Bind(R.id.tv_save)
    public  TextView tvSave;
    @Bind(R.id.tv_recom)
    public  TextView tvRecom;

    @Bind(R.id.item_kanji_size)
    public  TextView tvKanjiaSize;
    @Bind(R.id.item_cur_price)
    public  TextView tvCurPrice;
    @Bind(R.id.item_old_price)
    public  TextView tvOldPrice;
    @Bind(R.id.item_do)
    public  TextView itemDo;


    @Bind(R.id.item_kanji_num)
    public  TextView itemNum;

    @Bind(R.id.rll_ratebar)
    public RelativeLayout rll_ratebar;

    @Bind(R.id.item_kanji_rate)
    public  TextView itemRate;

    @Bind(R.id.project_progress1)
    public ProgressBar progressBar;



    public ActiveItemHolder(View itemView) {
        super(itemView);

    }
}
