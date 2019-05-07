package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.helper.GlideCircleTransform;
import com.insthub.cat.android.module2.TickListData;
import com.insthub.cat.android.ui.viewholder.TicketItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 发票
 * Created by linux on 2017/12/12.
 */

public class TickListAdapter extends BaseRecyclerAdapter<TicketItemHolder, TickListData.DataBean.ListBean> {



    public TickListAdapter(Context context, List<TickListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_ticket);
    }

    @Override
    public void onBindViewHolder(TicketItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


//        Glide.with(getContext())
//                .load(getDataItem(position).getInvoice_image())
//                .transform(new GlideCircleTransform(getContext()))
//                .error(R.drawable.ic_ticket_no)
//                .placeholder(R.drawable.ic_ticket_no)
//                .into(holder.ivTicketHead);
//

        RequestOptions requestOptions = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.ic_ticket_no)
                .error(R.drawable.ic_ticket_no)
                ;
        Glide.with(getContext().getApplicationContext()).asBitmap()
                .load(getDataItem(position).getInvoice_image())
                .apply(requestOptions)
                .into(holder.ivTicketHead);


        holder.itemTicketTime.setText("发票时间:"+getDataItem(position).getCreate_time());

        holder.itemTicketCode.setText("发票号:"+getDataItem(position).getInvoice_id());

        holder.itemTicketMoney.setText("税价合计:￥"+getDataItem(position).getMoney());

        holder.itemShuie.setText("税额:"+getDataItem(position).getTax());


     switch (getDataItem(position).getState())
     {
         case 0:
             holder.itemTicketState.setText("未报销");
             break;
         case 1:
             holder.itemTicketState.setText("已报销");
             break;
     }


    }

    @Override
    public TicketItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TicketItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false));
    }
}
