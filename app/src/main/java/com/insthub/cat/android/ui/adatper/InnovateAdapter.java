package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.common.android.flog.KLog;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.InnovateListData;
import com.insthub.cat.android.ui.viewholder.InnovateItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 竞价列表适配器
 * Created by linux on 2017/12/12.
 */

public class InnovateAdapter extends BaseRecyclerAdapter<InnovateItemHolder, InnovateListData.DataBean.ListBean> {



    public InnovateAdapter(Context context, List<InnovateListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_innovate);


    }


    @Override
    public void onBindViewHolder(final InnovateItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tvTitle.setText(getDataItem(position).getTitle());

        holder.tvMoney.setText("酬金："+getDataItem(position).getMoney());

        RequestOptions requestOptions = new RequestOptions();

        Glide.with(getContext().getApplicationContext()).asBitmap()
                .load(getDataItem(position).getImage())
                .apply(requestOptions)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        holder.lluBg.setImageBitmap(resource);
                    }
                });

        KLog.i("IMAGE:"+getDataItem(position).getImage());


        switch (getDataItem(position).getState())
        {
            case "0":
                holder.tvInnvoteState.setText("征集中");
                break;
            case "1":
                holder.tvInnvoteState.setText("设计中");
                break;
            case "2":
                holder.tvInnvoteState.setText("已完成");
                break;
        }


    }

    @Override
    public InnovateItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InnovateItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
