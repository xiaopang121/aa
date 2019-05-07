package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.widget.RatingBar;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class HomerenqiItemHolder extends BaseRecyclerViewHolder {
    @Bind(R.id.iv_renqi_image)
   public  RoundedImageView ivRenqiImage;
    @Bind(R.id.tv_renqi_bang)
    public TextView tvRenqiBang;
    @Bind(R.id.tv_renqi_name)
    public TextView tvRenqiName;
    @Bind(R.id.tv_renqi_count)
    public  TextView tvRenqiCount;



    public HomerenqiItemHolder(View itemView) {
        super(itemView);

    }
}
