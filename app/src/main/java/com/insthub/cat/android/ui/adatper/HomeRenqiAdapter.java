package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.ui.viewholder.HomerenqiItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 品牌列表
 * Created by linux on 2017/12/12.
 */

public class HomeRenqiAdapter extends BaseRecyclerAdapter<HomerenqiItemHolder, HomeData.DataBean.RenqiListBean> {



    public HomeRenqiAdapter(Context context, List<HomeData.DataBean.RenqiListBean> list) {
        super(context, list, R.layout.item_renqi);
    }

    @Override
    public void onBindViewHolder(HomerenqiItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()

                ;
        Glide.with(getContext().getApplicationContext()).asBitmap()
                .load(getDataItem(position).getLogo())
                .apply(requestOptions)
                .into(holder.ivRenqiImage);

        holder.tvRenqiName.setText(getDataItem(position).getStore_name());

        holder.tvRenqiCount.setText("月销售"+getDataItem(position).getOrder_count()+"份");



    }

    @Override
    public HomerenqiItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomerenqiItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false));
    }
}
