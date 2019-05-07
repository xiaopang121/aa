package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.ShopOrderListData;
import com.insthub.cat.android.module2.UnFinishOrderListData;
import com.insthub.cat.android.ui.viewholder.ShopOrderItemHolder;

import java.util.List;

/**
 * 店铺订单列表
 * Created by linux on 2017/12/12.
 */

public class ShopWithingOrderAdapter extends BaseRecyclerAdapter<ShopOrderItemHolder, UnFinishOrderListData.DataBean.ListBean> {


    private ShopOrderCallback mShopOrderCallback;

    public ShopWithingOrderAdapter(Context context, List<UnFinishOrderListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_shop_order);

        mShopOrderCallback = (ShopOrderCallback)context;
    }


    @Override
    public void onBindViewHolder(ShopOrderItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        String strtime = getContext().getResources().getString(R.string.field_time);
        holder.itemOrderTime.setText(strtime+"   "+getDataItem(position).getCreate_time());

        holder.itemOrderName.setText(getDataItem(position).getUser_name());


        holder.itemOrderMoney.setText("¥"+getDataItem(position).getService_price());


        holder.itemOrderService.setText(getDataItem(position).getService_name());

        holder.tvOrderApplyYanshou.setVisibility(View.GONE);
        holder.tvOrderApplyYanshou.setTag(position);

        holder.tvOrderApplyYanshou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = (int)view.getTag();

                if(mShopOrderCallback!=null)
                {
                    mShopOrderCallback.applyYanshou(pos);
                }
            }
        });

        holder.ivChat.setTag(position);
        holder.ivChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = (int)view.getTag();

                if(mShopOrderCallback!=null)
                {
                    mShopOrderCallback.onChat(pos);
                }
            }
        });


//
//        switch (getDataItem(position).getState())
//        {
//            case 0:
//
//                holder.itemOrderState.setText("交易进行中");
//                break;
//            case 1:
//                holder.itemOrderState.setText("已付款");
//                break;
//
//            case 2:
//                holder.itemOrderState.setText("已确认付款给商家");
//                break;
//        }

        switch (getDataItem(position).getState())
        {

            case 1:
            case 2:
                holder.itemOrderState.setText("付款成功");
                holder.tvOrderApplyYanshou.setVisibility(View.VISIBLE);
                break;
            case 3:
                holder.itemOrderState.setText("付款成功");
                //  holder.tvOrderApplyPay.setVisibility(View.GONE);
                break;

            case 4:
            case 5:
            case 8:
                holder.itemOrderState.setText("提现中");
                break;
            case 6:
            case 0:
            case 7:
                holder.itemOrderState.setText("订单结束");
                break;

        }


    }

    @Override
    public ShopOrderItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopOrderItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


    public interface ShopOrderCallback
    {
        public  void applyYanshou(int positon);

        public  void onChat(int positon);
    }



}
