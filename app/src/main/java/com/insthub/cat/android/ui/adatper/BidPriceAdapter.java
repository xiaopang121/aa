package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.flog.KLog;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.futils.TimeUtils;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.BidPriceListData;
import com.insthub.cat.android.ui.viewholder.BidPriceItemHolder;
import com.insthub.cat.android.ui.widget.StepsView;

import java.util.List;

import butterknife.Bind;

/**
 * 竞价列表适配器
 * Created by linux on 2017/12/12.
 */

public class BidPriceAdapter extends BaseRecyclerAdapter<BidPriceItemHolder, BidPriceListData.DataBean.ListBean> {


    String[] labels =new String[100];

    public BidPriceAdapter(Context context, List<BidPriceListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_bidprice);


    }


    @Override
    public void onBindViewHolder(BidPriceItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tvBidTitle.setText(getDataItem(position).getTitle());

        holder.tvCity.setText(getDataItem(position).getCity());

        holder.tvLastDay.setText("剩余"+getDataItem(position).getRe_days()+"天");

        holder.tvCategary.setText(getDataItem(position).getService());

        String[] labels3 = getContext().getResources().getStringArray(R.array.labels2);

        String[] labels =new String[100];

        labels[0] = labels3[0]+"\n"+TimeUtils.parserTime(getDataItem(position).getCreate_time(),TimeUtils.FROAMTE_YMHMS,TimeUtils.FROMATE_MD_ZH)+"\n"+TimeUtils.parserTime(getDataItem(position).getCreate_time(),TimeUtils.FROAMTE_YMHMS,TimeUtils.FROMATE_HM);


        if(!TextUtils.isEmpty(TimeUtils.parserTime(getDataItem(position).getEnd_time(),TimeUtils.FROAMTE_YMHMS,TimeUtils.FROMATE_MD_ZH)))
        {
            labels[99] = labels3[2]+"\n"+TimeUtils.parserTime(getDataItem(position).getEnd_time(),TimeUtils.FROAMTE_YMHMS,TimeUtils.FROMATE_MD_ZH)+"\n"+TimeUtils.parserTime(getDataItem(position).getEnd_time(),TimeUtils.FROAMTE_YMHMS,TimeUtils.FROMATE_HM);


        }else
        {
            labels[99] = labels3[2]+"\n"+TimeUtils.parserTime(getDataItem(position).getEnd_time(),TimeUtils.FROMATE_YMD,TimeUtils.FROMATE_MD_ZH)+"\n"+TimeUtils.parserTime(getDataItem(position).getEnd_time(),TimeUtils.FROMATE_YMD,TimeUtils.FROMATE_HM);

        }

        long  startTime = TimeUtils.parserTime(getDataItem(position).getCreate_time(),TimeUtils.FROAMTE_YMHMS);

        long endTime =0;

        if(!TextUtils.isEmpty(TimeUtils.parserTime(getDataItem(position).getEnd_time(),TimeUtils.FROAMTE_YMHMS,TimeUtils.FROMATE_MD_ZH)))
        {
            endTime =   TimeUtils.parserTime(getDataItem(position).getEnd_time(),TimeUtils.FROAMTE_YMHMS);

        }else {
            endTime = TimeUtils.parserTime(getDataItem(position).getEnd_time(), TimeUtils.FROMATE_YMD);

        }

        int days = Math.abs((int)((endTime - startTime)/(24*60*60*1000)))+1;


        int post = (int)(days - getDataItem(position).getRe_days())*100/days ;

        if(post>0)
        {
            post--;
        }

        KLog.i("days:"+days);
        KLog.i("last:"+getDataItem(position).getRe_days());
        KLog.i("post:"+post);
        holder.mStepsView.setCompletedPosition(post)
                .setLabels(labels)
                .setCircleRadius(20)
                .setLabelTextSize(12)
                .setHideCirclePosition(1,98)
                .setHideProgressText(true).drawView();
    }




    @Override
    public BidPriceItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BidPriceItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
