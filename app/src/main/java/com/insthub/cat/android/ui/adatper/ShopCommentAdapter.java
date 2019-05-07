package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.helper.GlideCircleTransform;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.CollectListData;
import com.insthub.cat.android.module2.ShopCommentList;
import com.insthub.cat.android.ui.viewholder.CollectItemHolder;
import com.insthub.cat.android.ui.viewholder.ShopCommentItemHolder;

import java.util.List;

/**
 * 商家评论适配器
 * Created by linux on 2017/12/12.
 */

public class ShopCommentAdapter extends BaseRecyclerAdapter<ShopCommentItemHolder, ShopCommentList.DataBean.ListBean> {


    public ShopCommentAdapter(Context context, List<ShopCommentList.DataBean.ListBean> list) {
        super(context, list, R.layout.item_shop_comment);

    }


    @Override
    public void onBindViewHolder(ShopCommentItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        ShopCommentList.DataBean.ListBean dataItem = getDataItem(position);

        holder.ivCommentHead.setImageResource(R.drawable.logo);
        if(!TextUtils.isEmpty(dataItem.getHead_image()))
        {

//            Glide.with(getContext())
//                    .load(dataItem.getHead_image())
//                    .transform(new GlideCircleTransform(getContext()))
//                    .error(R.drawable.logo)
//                    .placeholder(R.drawable.logo)
//                    .into(   holder.ivCommentHead);


            RequestOptions requestOptions = new RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    ;
            Glide.with(getContext().getApplicationContext()).asBitmap()
                    .load(dataItem.getHead_image())
                    .apply(requestOptions)
                    .into(holder.ivCommentHead);
        }

        holder.tvCommentName.setText(dataItem.getUser_name());

        holder.ratingBar.setCountSelected((int)dataItem.getScore());

        holder.tvCommentTime.setText(dataItem.getCreate_time());

        holder.tvCommentTime.setText(dataItem.getEvaluate_content());






    }

    @Override
    public ShopCommentItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopCommentItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
