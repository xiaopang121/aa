package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.CollectListData;
import com.insthub.cat.android.module2.ActionListData;
import com.insthub.cat.android.ui.activity.KanjiaDetialActivity;
import com.insthub.cat.android.ui.activity.MiaoshaDetialActivity;
import com.insthub.cat.android.ui.viewholder.ActiveItemHolder;
import com.insthub.cat.android.ui.viewholder.CollectItemHolder;
import com.insthub.cat.android.utils.DistanceUtil;

import java.util.List;

/**
 * 列表
 * Created by linux on 2017/12/12.
 */

public class ActiveAdapter extends BaseRecyclerAdapter<ActiveItemHolder, ActionListData.DataBean.ListBean> {


    public ActiveAdapter(Context context, List<ActionListData.DataBean.ListBean> list, boolean select) {
        super(context, list, R.layout.item_active);

    }


    @Override
    public void onBindViewHolder(ActiveItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);




        ActionListData.DataBean.ListBean dataItem = getDataItem(position);
        holder.itemCollectTitle.setText(dataItem.getActivity_name());
        holder.tvCurPrice.setText(dataItem.getDiscount_price()+"元");
        holder.tvOldPrice.setText(dataItem.getOld_price());
        holder.tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.rll_ratebar.setVisibility(View.INVISIBLE);
        holder.itemNum.setVisibility(View.INVISIBLE);
        holder.tvKanjiaSize.setVisibility(View.INVISIBLE);
        holder.tvCollectDistance.setText(DistanceUtil.getDistance(dataItem.getDistance()));

        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                ;
        Glide.with(getContext().getApplicationContext()).asBitmap()
                .load(dataItem.getLogo())
                .apply(requestOptions)
                .into(holder.ivCollectHead);



        switch(dataItem.getType())
        {
            case 1:
               holder.itemDo.setText("去砍价");
                holder.tvKanjiaSize.setVisibility(View.VISIBLE);
                holder.tvKanjiaSize.setText(dataItem.getInitiator_num()+"人砍价成功");
                break;
            case 2:
                holder.itemDo.setText("去秒杀");
                holder.itemNum.setText("已抢"+dataItem.getTotal_discount_num()+"件");
                int rate = (int)(dataItem.getSold_percent()*100);
                holder.itemRate.setText(rate+"%");
                holder.progressBar.setProgress(rate);
                holder.rll_ratebar.setVisibility(View.VISIBLE);
                holder.itemNum.setVisibility(View.VISIBLE);
                holder.tvKanjiaSize.setVisibility(View.INVISIBLE);
                break;
            case 3:
                holder.itemDo.setText("去团购");
                holder.tvKanjiaSize.setVisibility(View.VISIBLE);
                holder.tvKanjiaSize.setText(dataItem.getNum()+"人已成功团购");
                break;
            case 4:
                holder.itemDo.setText("幸运大抽奖");
                holder.tvKanjiaSize.setVisibility(View.VISIBLE);
                holder.tvCurPrice.setText(dataItem.getMoney()+"元");
                holder.tvKanjiaSize.setText(dataItem.getInitiator_num()+"人砍价成功");
                break;
        }


//
//
//        holder.itemCollectTitle.setText(dataItem.getStore_name());
//        holder.itemCollectType.setText(dataItem.getService_content());
//        holder.tvCollectAddress.setText("");
//        holder.tvCollectDistance.setText(DistanceUtil.getDistance(dataItem.getDistance()));
//        holder.itemCollectSeconds.setText(dataItem.getOrder_count() + "次合作");
//        holder.ratingBar.setStar(dataItem.getScore());
//
//
//        if(!TextUtils.isEmpty(dataItem.getIs_auth()) && dataItem.getIs_auth().equals("1"))
//        {
//            holder.tvOauth.setVisibility(View.VISIBLE);
//        }else {
//            holder.tvOauth.setVisibility(View.GONE);
//        }
//
//
//
//        if(!TextUtils.isEmpty(dataItem.getIs_save())&&dataItem.getIs_save().equals("1"))
//        {
//            holder.tvSave.setVisibility(View.VISIBLE);
//        }else {
//            holder.tvSave.setVisibility(View.GONE);
//        }
//
//
//
//
//        if(!TextUtils.isEmpty(dataItem.getIs_recom()) && dataItem.getIs_recom().equals("1"))
//        {
//            holder.tvRecom.setVisibility(View.VISIBLE);
//        }else {
//            holder.tvRecom.setVisibility(View.GONE);
//        }



    }

    @Override
    public ActiveItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActiveItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
