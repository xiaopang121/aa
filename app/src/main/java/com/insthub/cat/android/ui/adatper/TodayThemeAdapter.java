package com.insthub.cat.android.ui.adatper;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.futils.ScreenInfo;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.TodayThemeListData;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.ui.viewholder.ThemeItemHolder;

import java.util.List;

/**
 * 今日主题
 * Created by linux on 2017/12/12.
 */

public class TodayThemeAdapter extends BaseRecyclerAdapter<ThemeItemHolder, HomeData.DataBean.ThemeListBean> {


    public TodayThemeAdapter(Context context, List<HomeData.DataBean.ThemeListBean> list) {
        super(context, list, R.layout.item_today_theme);
    }

    @Override
    public void onBindViewHolder(ThemeItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        //设置头像
        if (!TextUtils.isEmpty(getDataItem(position).getImage())) {
//            Glide.with(getContext())
//                    .load(getDataItem(position).getImage())
//                    .error(R.drawable.ic_theme1)
//                    .placeholder(R.drawable.ic_theme1)
//                    .into(holder.imageView);

            RequestOptions requestOptions = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_theme1)
                    .error(R.drawable.ic_theme1)
                    ;
            Glide.with(getContext().getApplicationContext()).asBitmap()
                    .load(getDataItem(position).getImage())
                    .apply(requestOptions)
                    .into(holder.imageView);


        } else {
            holder.imageView.setImageResource(R.drawable.ic_theme1);
        }


        int width =  ScreenInfo.getScreenWidth((Activity)getContext())/4;
//        ViewGroup.LayoutParams param = holder.imageView.getLayoutParams();
//        param.width = width;
//        holder.imageView.setLayoutParams(param);

        ViewGroup.LayoutParams param = holder.itemView.getLayoutParams();
        param.width = width;
        holder.itemView.setLayoutParams(param);

    }

    @Override
    public ThemeItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemeItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent,false));
    }
}
