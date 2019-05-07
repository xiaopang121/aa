package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.BidPriceListData;
import com.insthub.cat.android.ui.viewholder.BidPriceItemHolder;

import java.util.List;

/**
 * 竞价列表适配器
 * Created by linux on 2017/12/12.
 */

public class SpinnerPopAdapter extends BaseRecyclerAdapter<BidPriceItemHolder, String> {


    public SpinnerPopAdapter(Context context, List<String> list) {
        super(context, list, R.layout.item_bidprice);


    }



    @Override
    public void onBindViewHolder(BidPriceItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);




    }

    @Override
    public BidPriceItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BidPriceItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }




}
