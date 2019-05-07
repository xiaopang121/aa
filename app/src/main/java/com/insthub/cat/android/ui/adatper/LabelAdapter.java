package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.futils.TimeUtils;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.module2.MyStoreData;
import com.insthub.cat.android.ui.viewholder.LabelItemHolder;
import com.insthub.cat.android.ui.viewholder.ShopItemHolder;

import java.util.List;

/**
 * 店铺列表
 * Created by linux on 2017/12/12.
 */

public class LabelAdapter extends BaseRecyclerAdapter<LabelItemHolder, DiscoverLabelData.DataBean.LabelLv1Bean> {



    private LabelCallback mShopCallback;


    public LabelAdapter(Context context, List<DiscoverLabelData.DataBean.LabelLv1Bean> list) {
        super(context, list, R.layout.item_label);

        mShopCallback = (LabelCallback) context;

    }


    @Override
    public void onBindViewHolder(LabelItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.itemLabel1.setText(getDataItem(position).getLable_name());
        holder.setItemData(getDataItem(position),position,mShopCallback);

    }

    @Override
    public LabelItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LabelItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


    public interface LabelCallback {
        public void onClickEdit(int position ,int  child);

    }

}
