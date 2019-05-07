package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.FinishOrderListData;
import com.insthub.cat.android.ui.viewholder.ShopWishdrawHasItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 店铺订单列表
 * Created by linux on 2017/12/12.
 */

public class WithdrawHasAdapter extends BaseRecyclerAdapter<ShopWishdrawHasItemHolder, FinishOrderListData.DataBean.ListBean> {



    @Bind(R.id.item_withdraw_money)
    TextView itemWithdrawMoney;
    @Bind(R.id.item_withdraw_time)
    TextView itemWithdrawTime;

    @Bind(R.id.item_withdraw_name)
    TextView itemWithdrawName;
    @Bind(R.id.item_create_time)
    TextView itemCreateTime;
    @Bind(R.id.item_withdraw_service)
    TextView itemWithdrawService;
    @Bind(R.id.item_withdraw_money2)
    TextView itemWithdrawMoney2;
    private int type = 0;

    private WidthdrawCallback callback;

    public WithdrawHasAdapter(Context context, List<FinishOrderListData.DataBean.ListBean> list, int type,WidthdrawCallback paramCallback) {
        super(context, list, R.layout.item_withdraw_has);
        this.type = type;
        callback =paramCallback;

    }


    @Override
    public void onBindViewHolder(ShopWishdrawHasItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        if (type == 0) {

            holder.itemWithdrawMoney.setText("¥" + getDataItem(position).getService_price());

            holder.itemWithdrawName.setText(getDataItem(position).getUser_name());

            holder.itemWithdrawService.setText(getDataItem(position).getService_name());

            holder.itemWithdrawMoney2.setText("¥" + getDataItem(position).getService_price());

            holder.itemWithdrawTime.setText(getDataItem(position).getCreate_time());

            holder.itemCreateTime.setText(getDataItem(position).getCreate_time());
            holder.btApply.setVisibility(View.GONE);

        } else  if(type==1){

            holder.itemWithdrawMoney.setVisibility(View.GONE);
            holder.btApply.setVisibility(View.VISIBLE);

            holder.itemWithdraw1.setText("未提现");

            holder.itemWithdrawName.setText(getDataItem(position).getUser_name());

            holder.itemWithdrawService.setText(getDataItem(position).getService_name());

            holder.itemWithdrawMoney2.setText("¥" + getDataItem(position).getService_price());

            holder.itemWithdrawTime.setVisibility(View.GONE);

            holder.itemCreateTime.setText(getDataItem(position).getCreate_time());

            holder.btApply.setTag(position);
            holder.btApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int item = (int)v.getTag();
                    if(callback!=null)
                    {
                        callback.onApply(item);
                    }
                }
            });
        }


        holder.ivChat.setTag(position);
        holder.ivChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int item = (int)v.getTag();
                if(callback!=null)
                {
                    callback.onChat(item);
                }
            }
        });


    }

    @Override
    public ShopWishdrawHasItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopWishdrawHasItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }




   public interface  WidthdrawCallback
    {
        public void onApply(int position);

        public void onChat(int position);
    }
}
