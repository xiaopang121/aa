package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.flog.KLog;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.ui.viewholder.HomerenqiItemHolder;
import com.insthub.cat.android.ui.viewholder.JIngxuanItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 精选列表
 * Created by linux on 2017/12/12.
 */

public class HomeJingxuanAdapter extends BaseRecyclerAdapter<JIngxuanItemHolder, HomeData.DataBean.JingxuanListBean> {


    public HomeJingxuanAdapter(Context context, List<HomeData.DataBean.JingxuanListBean> list) {
        super(context, list, R.layout.item_jingxuan);
    }

    @Override
    public void onBindViewHolder(JIngxuanItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);


        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop();
        Glide.with(getContext().getApplicationContext()).asBitmap()
                .load(getDataItem(position).getService_image())
                .apply(requestOptions)
                .into(holder.ivJinxuanImage);


        holder.tvJinxuanName.setText(getDataItem(position).getService_name());

        holder.tvJinxuanPrice.setText("¥" + getDataItem(position).getPrice());


    }

    @Override
    public JIngxuanItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JIngxuanItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent, false));
    }
}
