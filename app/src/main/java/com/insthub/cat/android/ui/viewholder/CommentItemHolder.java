package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.example.xlhratingbar_lib.XLHRatingBar;
import com.insthub.cat.android.R;

import butterknife.Bind;
import retrofit2.http.Field;

/**
 * Created by linux on 2017/12/12.
 */

public class CommentItemHolder extends BaseRecyclerViewHolder {

    @Bind(R.id.iv_ticket_state)
    public  ImageView ivHead;

    @Bind(R.id.item_title)
    public TextView itemTitle;

    @Bind(R.id.item_date)
    public TextView itemDate;

    @Bind(R.id.rll_view1)
    public RelativeLayout rllView1;


    @Bind(R.id.tv_business_title)
    public TextView tvBusinessTitle;


    @Bind(R.id.ratingBar)
    public XLHRatingBar ratingBar;

    @Bind(R.id.lly_view2)
    public LinearLayout llyView2;

    @Bind(R.id.item_content)
    public TextView itemContent;

    @Bind(R.id.item_company_head)
    public ImageView itemCompanyHead;

    @Bind(R.id.item_company_name)
    public  TextView itemCompanyName;

    @Bind(R.id.rll_view3)
    public RelativeLayout rllView3;

    @Bind(R.id.item_browse)
    public TextView itemBrowse;

    @Bind(R.id.item_delete)
    public TextView itemDelete;

    @Bind(R.id.item_company_des)
    public TextView itemCompanyDes;

    public CommentItemHolder(View itemView) {
        super(itemView);

    }
}
