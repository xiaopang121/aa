package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.futils.TimeUtils;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.PintuanListData;
import com.insthub.cat.android.ui.viewholder.PintuanUserItemHolder;
import com.insthub.cat.android.utils.DeclareUtil;

import java.util.List;

import butterknife.Bind;

/**
 * 列表
 * Created by linux on 2017/12/12.
 */

public class PintuanAdatper extends BaseRecyclerAdapter<PintuanUserItemHolder, PintuanListData.DataBean.ListBean> {


    public PintuanAdatper(Context context, List<PintuanListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_pintuan_user);

    }


    @Override
    public void onBindViewHolder(PintuanUserItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        PintuanListData.DataBean.ListBean dataItem = getDataItem(position);

        Glide.with(getContext())
                .load(dataItem.getHead_image())
                .into(holder.ivPintuanHead);
        holder. tvPintuanName.setText(DeclareUtil.formatePhone(dataItem.getPhone()));
        String temp = "还差1人拼团";
        SpannableString spannableString = new SpannableString(temp);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getContext().getResources().getColor(R.color.red));
        spannableString.setSpan(colorSpan, 2, temp.length()-2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.tvPingtuanLastPeople.setText(spannableString);

        long endTime =  TimeUtils.parserTime(dataItem.getCreate_time(),TimeUtils.FROMATE_YMD);

        long distancTime = endTime+24*60*60*1000-System.currentTimeMillis();

        if(distancTime<0)
        {
            distancTime=0;
        }
//        String counteTime = TimeUtils.formateTime(distancTime,TimeUtils.FROMATE_HMS);
//        holder.tvPingtuanLastTime.setText("剩余 "+counteTime);

        holder.setDownCount(distancTime);
    }

    @Override
    public PintuanUserItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PintuanUserItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
