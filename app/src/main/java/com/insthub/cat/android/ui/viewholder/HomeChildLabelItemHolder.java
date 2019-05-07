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

public class HomeChildLabelItemHolder extends BaseRecyclerViewHolder {

    @Bind(R.id.item_child_name)
    public TextView tvItemChildName;

    public HomeChildLabelItemHolder(View itemView) {
        super(itemView);

    }
}
