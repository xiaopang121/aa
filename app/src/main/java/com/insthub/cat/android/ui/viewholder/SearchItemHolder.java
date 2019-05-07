package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class SearchItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.item_search_title)
    public  TextView itemSearchTitle;



    public SearchItemHolder(View itemView) {
        super(itemView);

    }
}
