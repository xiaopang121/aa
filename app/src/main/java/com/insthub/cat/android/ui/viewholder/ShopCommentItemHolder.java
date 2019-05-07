package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.common.extend.roundedimageview.RoundedImageView;
import com.example.xlhratingbar_lib.XLHRatingBar;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class ShopCommentItemHolder extends BaseRecyclerViewHolder {

 @Bind(R.id.iv_comment_head)
 public RoundedImageView ivCommentHead;

 @Bind(R.id.tv_comment_name)
 public TextView tvCommentName;

 @Bind(R.id.ratingBar)
 public XLHRatingBar ratingBar;

 @Bind(R.id.tv_comment_time)
 public TextView tvCommentTime;

 @Bind(R.id.tv_comment_content)
 public TextView tvCommentContent;


    public ShopCommentItemHolder(View itemView) {
        super(itemView);

    }
}
