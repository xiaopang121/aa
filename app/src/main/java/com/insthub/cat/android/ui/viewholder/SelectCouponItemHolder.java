package com.insthub.cat.android.ui.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class SelectCouponItemHolder extends BaseRecyclerViewHolder {



    @Bind(R.id.tv_content)
    public TextView tvContent;


    public SelectCouponItemHolder(View itemView) {
        super(itemView);

    }
}
