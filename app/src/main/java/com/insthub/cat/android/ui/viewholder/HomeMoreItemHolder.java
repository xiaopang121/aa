package com.insthub.cat.android.ui.viewholder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.common.android.futils.UIUtil;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.module2.LabelData;
import com.insthub.cat.android.ui.adatper.HomeJingxuanAdapter;
import com.insthub.cat.android.ui.adatper.HomeMoreChildLabelAdapter;
import com.insthub.cat.android.ui.adatper.HomeMoreLabelAdapter;
import com.insthub.cat.android.ui.widget.GridDividerItemDecoration;
import com.insthub.cat.android.ui.widget.GridSpacingItemDecoration;
import com.insthub.cat.android.ui.widget.RatingBar;
import com.insthub.cat.android.ui.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class HomeMoreItemHolder extends BaseRecyclerViewHolder {

    @Bind(R.id.item_parent_icon)
   public  RoundedImageView itemParentIcon;
    @Bind(R.id.item_parent_name)
    public TextView itemParentName;
    @Bind(R.id.rv_child_label)
    public  RecyclerView rvChildLabel;

    private ArrayList< DiscoverLabelData.DataBean.LabelLv1Bean.LabelLv2Bean> labelList = new ArrayList<>();
    private HomeMoreChildLabelAdapter mHomeMoreChildLabelAdapter;

    HomeMoreLabelAdapter.OnSelectLableCallback onItemClickListener;

    DiscoverLabelData.DataBean.LabelLv1Bean parentdata;

    public HomeMoreItemHolder(View itemView) {
        super(itemView);
        //精选
        GridLayoutManager jxlayoutManager = new GridLayoutManager(itemView.getContext(), 4);
        rvChildLabel.setLayoutManager(jxlayoutManager);
        rvChildLabel.setHasFixedSize(true);
        mHomeMoreChildLabelAdapter = new HomeMoreChildLabelAdapter(itemView.getContext(), labelList);
        rvChildLabel.setAdapter(mHomeMoreChildLabelAdapter);
        GridDividerItemDecoration jx2layoutManager = new GridDividerItemDecoration(UIUtil.dpToPx(itemView.getContext().getResources(), 1),itemView.getContext().getResources().getColor(R.color.B_black_10));
        rvChildLabel.addItemDecoration(jx2layoutManager);
        rvChildLabel.scrollToPosition(0);

        mHomeMoreChildLabelAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                if(onItemClickListener!=null)
                {
                    onItemClickListener.onSelect(parentdata,labelList.get(position));
                }
            }
        });

    }




    public void setChildLableList( DiscoverLabelData.DataBean.LabelLv1Bean parentData,List<DiscoverLabelData.DataBean.LabelLv1Bean.LabelLv2Bean> data )
    {
        parentdata = parentData;
        labelList.clear();
        labelList.addAll(data);
        mHomeMoreChildLabelAdapter.notifyDataSetChanged();

    }

    public void setOnClickListerer(   HomeMoreLabelAdapter.OnSelectLableCallback click)
    {
        onItemClickListener = click;

    }

}
