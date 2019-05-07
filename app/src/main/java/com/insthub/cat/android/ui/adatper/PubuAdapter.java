package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.example.xlhratingbar_lib.XLHRatingBar;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.PubuListData;
import com.insthub.cat.android.ui.viewholder.PubuItemHolder;

import java.io.PipedOutputStream;
import java.util.List;

import butterknife.Bind;

/**
 * 瀑布列表
 * Created by linux on 2017/12/12.
 */

public class PubuAdapter extends BaseRecyclerAdapter<PubuItemHolder, PubuListData.DataBean.ListBean> {



    public PubuAdapter(Context context, List<PubuListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_pubu);
    }

    @Override
    public void onBindViewHolder(PubuItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                ;
        Glide.with(getContext().getApplicationContext()).asBitmap()
                .load(getDataItem(position).getLogo())
                .apply(requestOptions)
                .into(holder.ivExtionsion);

        holder.tvName.setText(getDataItem(position).getStore_name());

        holder.ratingBar.setCountSelected(5);

        holder.tvAddress.setText(getDataItem(position).getAddress());

        holder.tvService.setText("本所主要业务:"+getDataItem(position).getService_content());




    }

    @Override
    public PubuItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PubuItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false));
    }
}
