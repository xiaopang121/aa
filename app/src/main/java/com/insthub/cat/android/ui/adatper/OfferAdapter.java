package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.OfferListData;
import com.insthub.cat.android.ui.viewholder.OfferItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 我的报价
 * Created by linux on 2017/12/12.
 */

public class OfferAdapter extends BaseRecyclerAdapter<OfferItemHolder, OfferListData.DataBean.ListBean> {



    public OfferAdapter(Context context, List<OfferListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_offer);


    }


    @Override
    public void onBindViewHolder(OfferItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.itemOfferTitle.setText(getDataItem(position).getTitle());



        holder.itemOrderMoney.setText(getDataItem(position).getPrice()+"");



        holder.itemOfferTime.setText(getDataItem(position).getFinish_time());

          switch (getDataItem(position).getState())
          {
              case 0:
                  holder.itemOrderState.setText("竞标中");
                  break;
              case 1:
                  holder.itemOrderState.setText("中标");
                  break;
              case 2:
                  holder.itemOrderState.setText("未中标");
                  break;
          }

        switch (getDataItem(position).getState())
        {
            case 0:
                holder.itemOrderType.setText("竞价");
                break;
            case 1:
                holder.itemOrderType.setText("招标");
                break;
            case 2:
                holder.itemOrderType.setText("创新集");
                break;
        }

    }

    @Override
    public OfferItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OfferItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
