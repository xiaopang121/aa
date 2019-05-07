package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.RaiseItemData;
import com.insthub.cat.android.ui.viewholder.TaitouItemHolder;

import java.util.List;

/**
 * 抬头列表
 * Created by linux on 2017/12/12.
 */

public class OrderAdapter extends BaseRecyclerAdapter<TaitouItemHolder, RaiseItemData> {

    private TaitouCallback mTaitouCallback;

    private boolean isSelect=false;

    private int  selectPositon=-1;

    public OrderAdapter(Context context, List<RaiseItemData> list, boolean select) {
        super(context, list, R.layout.item_taitou);
        mTaitouCallback = (TaitouCallback) context;

        isSelect = select;
    }



    public void setSelectPositon(int positon)
    {
        selectPositon = positon;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(TaitouItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


//        switch (getDataItem(position).getRentOutState())
//        {
//            case 1:
//
//                holder.ivBerthState.setText(R.string.berth_state_empty);
//                holder.ivBerthState.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.ic_lease_on),null,null,null);
//                break;
//            case 2:
//                holder.ivBerthState.setText(R.string.berth_state_work);
//                holder.ivBerthState.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.ic_lease_ok),null,null,null);
//                break;
//        }


        if(isSelect)
        {
            holder.ivTaitouEdit.setVisibility(View.INVISIBLE);
            holder.ivTaitouDelete.setVisibility(View.INVISIBLE);


            if(selectPositon == position)
            {
                holder.itemTaitouDefault.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.ic_circle_pressed),null,null,null);
            }else
            {
                holder.itemTaitouDefault.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.ic_circle_normal),null,null,null);
            }
        }





        holder.itemTaitouDefault.setTag(position);
        holder.itemTaitouDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int item = (int) view.getTag();

                if (mTaitouCallback != null) {
                    mTaitouCallback.onDefault(item);
                }

            }
        });


        holder.ivTaitouEdit.setTag(position);
        holder.ivTaitouEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int item = (int) view.getTag();

                if (mTaitouCallback != null) {
                    mTaitouCallback.onEdit(item);
                }

            }
        });


        holder.ivTaitouDelete.setTag(position);
        holder.ivTaitouDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int item = (int) view.getTag();

                if (mTaitouCallback != null) {
                    mTaitouCallback.onDelete(item);
                }

            }
        });

    }

    @Override
    public TaitouItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaitouItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


    public interface TaitouCallback {
        public void onDefault(int positon);

        public void onEdit(int position);

        public void onDelete(int positon);
    }

}
