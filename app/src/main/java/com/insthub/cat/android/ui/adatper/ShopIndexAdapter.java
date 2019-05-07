package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.ui.viewholder.HomerenqiItemHolder;
import com.insthub.cat.android.ui.viewholder.ShopIndexHolder;

import java.util.List;

/**
 * 品牌列表
 * Created by linux on 2017/12/12.
 */

public class ShopIndexAdapter extends BaseRecyclerAdapter<ShopIndexHolder, String> {



    public ShopIndexAdapter(Context context, List<String> list) {
        super(context, list, R.layout.item_shop_index);
    }

    @Override
    public void onBindViewHolder(ShopIndexHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        if(getItemViewType(position)==TYPE_NORMAL)
        {
            RequestOptions requestOptions = new RequestOptions()
                    .fitCenter()

                    ;
            Glide.with(getContext().getApplicationContext()).asBitmap()
                    .load(getDataItem(position-1))
                    .apply(requestOptions)
                    .into(holder.ivImage);

        }


    }

    @Override
    public ShopIndexHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==TYPE_NORMAL)
        {
            return new ShopIndexHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false),true);
        }else
        {
            return new ShopIndexHolder(mHeaderViews.get(BASE_ITEM_TYPE_HEADER),false);
        }


    }


    @Override
    public int getItemViewType(int position) {
        if (getHeadersCount() == 0 && getFootersCount() == 0) {
            return TYPE_NORMAL;
        }


        if (getHeadersCount() > 0 && position < getHeadersCount()) {
            return BASE_ITEM_TYPE_HEADER;
        }


        if (getFootersCount() > 0 && position >= (getHeadersCount() + getDataList().size())) {
            return BASE_ITEM_TYPE_FOOTER;
        }

        return TYPE_NORMAL;
    }
}
