package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class BankItemHolder extends BaseRecyclerViewHolder {




    @Bind(R.id.iv_bank_type)
    public ImageView ivType;

    @Bind(R.id.tv_banknum)
    public  TextView tvCaNum;
    public BankItemHolder(View itemView) {
        super(itemView);

    }
}
