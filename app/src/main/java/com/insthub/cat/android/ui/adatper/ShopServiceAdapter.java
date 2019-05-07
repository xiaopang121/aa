package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.module2.ShopDetialData;
import com.insthub.cat.android.ui.viewholder.JIngxuanItemHolder;
import com.insthub.cat.android.ui.viewholder.ShopServiceItemHolder;

import java.util.List;

/**
 * 精选列表
 * Created by linux on 2017/12/12.
 */

public class ShopServiceAdapter extends BaseRecyclerAdapter<ShopServiceItemHolder, ShopDetialData.DataBean.ServiceListBean> {


    public ShopServiceAdapter(Context context, List<ShopDetialData.DataBean.ServiceListBean> list) {
        super(context, list, R.layout.item_shop_service);
    }

    @Override
    public void onBindViewHolder(ShopServiceItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop();
        Glide.with(getContext().getApplicationContext()).asBitmap()
                .load(getDataItem(position).getImage())
                .apply(requestOptions)
                .into(holder.ivJinxuanImage);


        holder.tvJinxuanName.setText(getDataItem(position).getService_name());

        holder.tvJinxuanPrice.setText("¥" + getDataItem(position).getPrice());

      //  holder.tvSize.setText(getDataItem(position).get);


    }

    @Override
    public ShopServiceItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopServiceItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false));
    }
}
