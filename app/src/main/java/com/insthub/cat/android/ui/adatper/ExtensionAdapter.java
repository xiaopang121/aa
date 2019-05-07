package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.helper.GlideCircleTransform;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.CouponListData;
import com.insthub.cat.android.module2.ExtensionPageListData;
import com.insthub.cat.android.ui.viewholder.CouponItemHolder;
import com.insthub.cat.android.ui.viewholder.ExtensionItemHolder;

import java.util.List;

/**
 * 优惠券
 * Created by linux on 2017/12/12.
 */

public class ExtensionAdapter extends BaseRecyclerAdapter<ExtensionItemHolder, ExtensionPageListData.DataBean.ListBean> {


    public ExtensionAdapter(Context context, List<ExtensionPageListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_tuiguang);
    }

    @Override
    public void onBindViewHolder(ExtensionItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        Glide.with(getContext())
                .load(getDataItem(position).getImage())
                // .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivExtionsion);


    }

    @Override
    public ExtensionItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExtensionItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false));
    }
}
