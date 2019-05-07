package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.UserCommentListData;
import com.insthub.cat.android.ui.viewholder.CommentItemHolder;

import java.util.List;

/**
 * 评论
 * Created by linux on 2017/12/12.
 */

public class CommentAdapter extends BaseRecyclerAdapter<CommentItemHolder,UserCommentListData.DataBean.ListBean> {



    private CommentCallback mCommentCallback;

    public CommentAdapter(Context context,CommentCallback parm, List<UserCommentListData.DataBean.ListBean> list) {
        super(context, list, R.layout.item_comment);
        mCommentCallback = parm;
    }


    @Override
    public void onBindViewHolder(CommentItemHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        UserCommentListData.DataBean.ListBean  dataItem = getDataItem(position);


        holder.itemBrowse.setText(dataItem.getViews()+"");


        holder.itemTitle.setText(dataItem.getUser_name());

        holder.itemDate.setText(dataItem.getCreate_time());

        holder.ratingBar.setCountSelected(dataItem.getScore());


        holder.itemContent.setText(dataItem.getEvaluate_content());




        RequestOptions requestOptions = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.ic_default_head)
                .error(R.drawable.ic_default_head)
                ;
        Glide.with(getContext()).asBitmap()
                .load(dataItem.getHead_image())
                .apply(requestOptions)
                .into(holder.ivHead);







        RequestOptions requestOptions2 = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                ;
        Glide.with(getContext()).asBitmap()
                .load(dataItem.getHead_image())
                .apply(requestOptions2)
                .into(holder.itemCompanyHead);


        holder.itemCompanyName.setText(dataItem.getStore_name());

        holder.itemDelete.setTag(position);

        holder.itemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int)view.getTag();

                if(mCommentCallback!=null)
                {
                    mCommentCallback.onDelete(position);
                }
            }
        });




    }

    @Override
    public CommentItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


    public interface CommentCallback {

        public void onDelete(int positon);
    }

}
