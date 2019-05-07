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
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.ScoreListData;
import com.insthub.cat.android.ui.viewholder.ScoreItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 积分记录
 * Created by linux on 2017/12/12.
 */

public class ScoreListAdapter extends BaseRecyclerAdapter<ScoreItemHolder, ScoreListData.DataBean.ListBean> {


    public ScoreListAdapter(Context context, List<ScoreListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_score);
    }

    @Override
    public void onBindViewHolder(ScoreItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


//
//        switch (getDataItem(position).getFaultState()) {
//            case 1:
//                holder.itemState.setText(R.string.state_pro_no);
//                break;
//            case 2:
//                holder.itemState.setText(R.string.state_pro_finish);
//                break;
//        }

//
//        Glide.with(getContext())
//                .load(getDataItem(position).getInvoice_image())
//                .transform(new GlideCircleTransform(getContext()))
//                .error(R.drawable.ic_ticket_no)
//                .placeholder(R.drawable.ic_ticket_no)
//                .into(holder.ivTicketHead);
//
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

        holder.itemTaxCode.setText("发票号:"+getDataItem(position).getTax_code());

        holder.itemTicketMoney.setText("税价合计:￥"+getDataItem(position).getMoney());


       // holder.itemTicketScore.setText("获取积分:"+getDataItem(position).);
    }

    @Override
    public ScoreItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScoreItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false));
    }
}
