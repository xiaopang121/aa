package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.BankListData;
import com.insthub.cat.android.ui.viewholder.BankItemHolder;
import com.insthub.cat.android.ui.viewholder.OtheryqItemHolder;

import java.util.List;

/**
 * 银行卡适配器
 * Created by linux on 2017/12/12.
 */

public class OtherYqAdapter extends BaseRecyclerAdapter<OtheryqItemHolder,String> {


    public OtherYqAdapter(Context context, List<String> list) {
        super(context, list, R.layout.item_other_yq);


    }



    @Override
    public void onBindViewHolder(OtheryqItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tvTitle.setText(getDataItem(position));




    }

    @Override
    public OtheryqItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OtheryqItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }




}
