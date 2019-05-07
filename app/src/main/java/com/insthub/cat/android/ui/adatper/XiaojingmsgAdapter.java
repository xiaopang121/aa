package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.event.XiaojingEvent;
import com.insthub.cat.android.ui.viewholder.XiaojingItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 店铺列表
 * Created by linux on 2017/12/12.
 */

public class XiaojingmsgAdapter extends BaseRecyclerAdapter<XiaojingItemHolder, XiaojingEvent> {




    public XiaojingmsgAdapter(Context context, List<XiaojingEvent> list) {
        super(context, list, R.layout.item_xiaojing);

    }


    @Override
    public void onBindViewHolder(XiaojingItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        switch (getDataItem(position).getState())
        {
            case 0:

                holder.itemState.setText("已读");
                holder.itemState.setTextColor(getContext().getResources().getColor(R.color.B_black_30));
                break;
            case 1:
                holder.itemState.setText("未读");
                holder.itemState.setTextColor(getContext().getResources().getColor(R.color.theme_color_primary));
                break;
        }


        holder.itemTitle.setText(getDataItem(position).getContent());

    }

    @Override
    public XiaojingItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new XiaojingItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
