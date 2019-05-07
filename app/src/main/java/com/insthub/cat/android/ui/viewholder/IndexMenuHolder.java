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

public class IndexMenuHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.tv_menu_name)
    public TextView ivMenuName;


    @Bind(R.id.tv_menu_image)
    public ImageView ivMenuImage;



    public IndexMenuHolder(View itemView) {
        super(itemView);

    }
}
