package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.widget.StepsView;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class TenderItemHolder extends BaseRecyclerViewHolder {





    @Bind(R.id.tv_title)
    public  TextView tvTitle;

    @Bind(R.id.tv_sub_title)
    public TextView tvSubTitle;
    @Bind(R.id.tv_last_day)
    public TextView tvLastDay;
    @Bind(R.id.tv_city)
    public  TextView tvCity;
    @Bind(R.id.tv_type)
    public  TextView tvType;
    @Bind(R.id.tv_categary)
    public  TextView tvCategary;


    public TenderItemHolder(View itemView) {
        super(itemView);

    }
}
