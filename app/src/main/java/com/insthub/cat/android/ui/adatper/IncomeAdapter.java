package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.HistoryRecordListData;
import com.insthub.cat.android.ui.activity.HombaoActivity;
import com.insthub.cat.android.ui.viewholder.CollectItemHolder;
import com.insthub.cat.android.ui.viewholder.HistoryItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 已领取
 * Created by linux on 2017/12/12.
 */

public class IncomeAdapter extends BaseRecyclerAdapter<HistoryItemHolder, HistoryRecordListData.DataBean.ListBean> {


    int type;

    private OpenHongbaoCallback  mOpenHongbaoCallback;

    public IncomeAdapter(Context context, List<HistoryRecordListData.DataBean.ListBean> list,int type,OpenHongbaoCallback callbc) {
        super(context, list, R.layout.item_no_receiver);

        this.type = type;
        this.mOpenHongbaoCallback = callbc;
    }


    @Override
    public void onBindViewHolder(HistoryItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);




        if(type ==0)
        {
            holder.llyMoneyBar.setVisibility(View.GONE);
            holder.btReceiver.setVisibility(View.VISIBLE);
            holder.itemTaitouTitle.setText(getDataItem(position).getTitle());
            holder.itemCollectSeconds.setText(getDataItem(position).getCreate_time());
        }



        if(type ==1)
        {
            holder.llyMoneyBar.setVisibility(View.VISIBLE);
            holder.btReceiver.setVisibility(View.GONE);
            holder.itemTaitouTitle.setText(getDataItem(position).getTitle());
            holder.itemCollectSeconds.setText(getDataItem(position).getCreate_time());
            holder.tvMoney.setText(getDataItem(position).getMoney());

        }


        if(type ==2)
        {
            holder.llyMoneyBar.setVisibility(View.VISIBLE);
            holder.btReceiver.setVisibility(View.GONE);
            holder.itemTaitouTitle.setText(getDataItem(position).getTitle());
            holder.itemCollectSeconds.setText(getDataItem(position).getCreate_time());
            holder.tvMoney.setText(getDataItem(position).getMoney());
        }

        holder.btReceiver.setTag(position);
        holder.btReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int item = (int)v.getTag();
                if(mOpenHongbaoCallback!=null)
                {
                    mOpenHongbaoCallback.onClick(item);
                }
            }
        });

    }

    @Override
    public HistoryItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }

    public interface OpenHongbaoCallback
    {
         public void onClick(int positon);
    }
}
