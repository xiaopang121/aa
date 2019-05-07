package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.futils.TimeUtils;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.ShopListData;
import com.insthub.cat.android.module2.MyStoreData;
import com.insthub.cat.android.ui.viewholder.ShopItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 店铺列表
 * Created by linux on 2017/12/12.
 */

public class ShopAdapter extends BaseRecyclerAdapter<ShopItemHolder, MyStoreData.DataBean> {



    private ShopCallback mShopCallback;


    public ShopAdapter(Context context, List<MyStoreData.DataBean> list) {
        super(context, list, R.layout.item_shop);

        mShopCallback = (ShopCallback) context;

    }


    @Override
    public void onBindViewHolder(ShopItemHolder holder, int position) {
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

        String [] list = getDataItem(position).getService_content().split(",");

        StringBuffer sb = new StringBuffer();

        int x=list.length ;


        if(x>3)
        {
           x =3;
        }


        for(int item = 0;item<x;item++)
        {
            sb.append(list[item]);

            if(item<x-1)
            {
               sb.append("，");
            }
        }

        sb.append("...");

        holder.itemShopName.setText(getDataItem(position).getStore_name());
        holder.itemShopCategary.setText(sb.toString());

//
//        holder.itemShopState.setText(getDataItem(position).get);


        holder.itemShopTime.setText(TimeUtils.formateTime(System.currentTimeMillis(),TimeUtils.FROMATE_HM));

        holder.btShopDelete.setTag(position);
        holder.btShopModify.setTag(position);
        holder.btShopDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = (int) view.getTag();

                if(mShopCallback!=null)
                {
                    mShopCallback.onClickDelete(pos);
                }

            }
        });


        holder.btShopModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if(mShopCallback!=null)
                {
                    mShopCallback.onClickEdit(pos);
                }
            }
        });

    }

    @Override
    public ShopItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


    public interface ShopCallback {
        public void onClickEdit(int position);

        public void onClickDelete(int position);
    }

}
