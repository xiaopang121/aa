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

public class PopupItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.tv_item_result_listview_name)
   public  TextView tvItemResultListviewName;

    public PopupItemHolder(View itemView) {
        super(itemView);

    }
}
