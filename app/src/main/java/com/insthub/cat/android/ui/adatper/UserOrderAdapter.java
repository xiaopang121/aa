package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.UseOrderListData;
import com.insthub.cat.android.module2.UserCommentListData;
import com.insthub.cat.android.ui.viewholder.UserOrderItemHolder;

import java.util.List;

/**
 * 用户订单列表
 * Created by linux on 2017/12/12.
 */

public class UserOrderAdapter extends BaseRecyclerAdapter<UserOrderItemHolder, UseOrderListData.DataBean.ListBean> {

    private UserOrderCallback mTaitouCallback;

    private int selectPositon = -1;

    public UserOrderAdapter(Context context, List<UseOrderListData.DataBean.ListBean> list, UserOrderCallback select) {
        super(context, list, R.layout.item_user_order);
        mTaitouCallback = select;

    }


    @Override
    public void onBindViewHolder(UserOrderItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        UseOrderListData.DataBean.ListBean itemData = getDataItem(position);

        switch (itemData.getState())
        {

            case 0:
            case 7:
                holder.itemOrderState.setText("未支付");
                holder.itemOrderComment.setVisibility(View.GONE);
                holder.itemOrderPay.setVisibility(View.VISIBLE);
                holder.itemOrderDelete.setVisibility(View.VISIBLE);
                holder.itemOrderFinish.setVisibility(View.GONE);
                holder.itemOrderPayServer.setVisibility(View.GONE);
                break;
            case 1:
                holder.itemOrderState.setText("进行中");
                holder.itemOrderDelete.setVisibility(View.GONE);
                holder.itemOrderComment.setVisibility(View.GONE);
                holder.itemOrderPay.setVisibility(View.GONE);
                holder.itemOrderFinish.setVisibility(View.GONE);
                holder.itemOrderPayServer.setVisibility(View.GONE);
                break;
            case 2:
                holder.itemOrderState.setText("待验收");
                holder.itemOrderFinish.setVisibility(View.VISIBLE);
                holder.itemOrderComment.setVisibility(View.GONE);
                holder.itemOrderPay.setVisibility(View.GONE);
                holder.itemOrderDelete.setVisibility(View.GONE);
                holder.itemOrderPayServer.setVisibility(View.GONE);
                break;
            case 3:
            case 4:
                holder.itemOrderState.setText("已完成");
                holder.itemOrderFinish.setVisibility(View.GONE);
                holder.itemOrderComment.setVisibility(View.GONE);
                holder.itemOrderPay.setVisibility(View.GONE);
                holder.itemOrderDelete.setVisibility(View.GONE);
                holder.itemOrderPayServer.setVisibility(View.VISIBLE);
                break;
            case 6:
            case 5:
            case 8:
                holder.itemOrderState.setText("已结束");
                holder.itemOrderFinish.setVisibility(View.GONE);
                holder.itemOrderComment.setVisibility(View.VISIBLE);
                holder.itemOrderPay.setVisibility(View.GONE);
                holder.itemOrderDelete.setVisibility(View.VISIBLE);
                holder.itemOrderPayServer.setVisibility(View.GONE);
                break;
        }



        RequestOptions requestOptions2 = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                ;
        Glide.with(getContext()).asBitmap()
                .load(itemData.getLogo())
                .apply(requestOptions2)
                .into( holder.ivCollectHead);


        holder.itemOrderShopName.setText(itemData.getStore_name());
        holder.itemOrderCreatetime.setText("下单时间:"+itemData.getCreate_time());
        holder.itemOrderServicetime.setText("服务时间:"+itemData.getService_time());
        holder.itemOrderDes.setText(itemData.getService_name());
        holder.tvServcePrice.setText("￥"+itemData.getService_price());
        holder.itemOrderDelete.setTag(position);


        holder.itemOrderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int item = (int) view.getTag();

                if (mTaitouCallback != null) {
                    mTaitouCallback.onDelete(item);
                }

            }
        });


        holder.itemOrderComment.setTag(position);
        holder.itemOrderComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int item = (int) view.getTag();

                if (mTaitouCallback != null) {
                    mTaitouCallback.onComment(item);
                }

            }
        });


        holder.itemOrderPay.setTag(position);
        holder.itemOrderPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int item = (int) view.getTag();

                if (mTaitouCallback != null) {
                    mTaitouCallback.onPay(item);
                }

            }
        });


        holder.itemOrderFinish.setTag(position);
        holder.itemOrderFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int item = (int) view.getTag();

                if (mTaitouCallback != null) {
                    mTaitouCallback.onApplyYanshou(item);
                }

            }
        });

        holder.itemOrderPayServer.setTag(position);
        holder.itemOrderPayServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int item = (int) view.getTag();

                if (mTaitouCallback != null) {
                    mTaitouCallback.onPayServer(item);
                }

            }
        });




        holder.itemOrderContract.setTag(position);
        holder.itemOrderContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int item = (int) view.getTag();

                if (mTaitouCallback != null) {
                    mTaitouCallback.onContract(item);
                }

            }
        });



        if(itemData.getType()==1)
        {
            holder.tvOrderSub.setVisibility(View.VISIBLE);
            holder.tvOrderSub.setTag(position);
            holder.tvOrderSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int item = (int) view.getTag();

                    if (mTaitouCallback != null) {
                        mTaitouCallback.onSubPrice(item);
                    }

                }
            });





        }else
        {
            holder.tvOrderSub.setVisibility(View.GONE);
        }


    }

    @Override
    public UserOrderItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserOrderItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


    public interface UserOrderCallback {
        public void onComment(int positon);

        public void onPay(int position);

        public void onDelete(int positon);

        public void onSubPrice(int position);

        public void onApplyYanshou(int position);

        public void onPayServer(int positon);

        public void onContract(int positon);
    }

}
