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

public class MyPublishItemHolder extends BaseRecyclerViewHolder {

    @Bind(R.id.iv_publish_type)
    public TextView ivPublishType;
    @Bind(R.id.tv_publish_state)
    public TextView tvPublishState;
    @Bind(R.id.tv_publish_title)
    public TextView tvPublishTitle;
    @Bind(R.id.tv_publish_endtime)
    public TextView tvPublishEndtime;
    @Bind(R.id.tv_peoples)
    public TextView tvPeoples;


    public MyPublishItemHolder(View itemView) {
        super(itemView);

    }
}
