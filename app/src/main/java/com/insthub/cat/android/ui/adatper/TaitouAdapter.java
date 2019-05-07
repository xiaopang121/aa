package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.RaiseItemData;
import com.insthub.cat.android.module.TaitouListData;
import com.insthub.cat.android.module2.InvoiceTitleListData;
import com.insthub.cat.android.ui.viewholder.TaitouItemHolder;

import java.util.List;

/**
 * 抬头列表
 * Created by linux on 2017/12/12.
 */

public class TaitouAdapter extends BaseRecyclerAdapter<TaitouItemHolder, InvoiceTitleListData.DataBean.ListBean> {

    private TaitouCallback mTaitouCallback;

    private boolean isSelect=false;

    private int  selectPositon=-1;

    public TaitouAdapter(Context context, List<InvoiceTitleListData.DataBean.ListBean> list, boolean select) {
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


        switch (getDataItem(position).getType())
        {
            case 0:

                holder.itemTaitouTitle.setText(getDataItem(position).getTitle_name());
                holder.itemTaitouType.setText("(企业)");
                break;
            case 1:
                holder.itemTaitouTitle.setText(getDataItem(position).getUser_name());
                holder.itemTaitouType.setText("(个人)");
                break;
        }


        if(isSelect)
        {
            holder.ivTaitouEdit.setVisibility(View.INVISIBLE);
            holder.ivTaitouDelete.setVisibility(View.INVISIBLE);


            if(selectPositon == position)
            {
                holder.itemView.setBackgroundColor(getContext().getResources().getColor(R.color.black_trans10));
            }else
            { holder.itemView.setBackgroundColor(getContext().getResources().getColor(R.color.transparent));

            }
        }



        if(getDataItem(position).getIs_default()==1)
        {
            holder.itemTaitouDefault.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.ic_circle_pressed),null,null,null);
        }else
        {
            holder.itemTaitouDefault.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.ic_circle_normal),null,null,null);
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
