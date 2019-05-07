package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.UploadImageInfoData;
import com.insthub.cat.android.ui.viewholder.UploadImageItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 上传图片适配器
 * Created by linux on 2017/12/12.
 */

public class UploadImageAdapter extends BaseRecyclerAdapter<UploadImageItemHolder, UploadImageInfoData.DataBean> {


    private Callback mCallback;

    private boolean isShowDelete=true;

    public UploadImageAdapter(Context context, List<UploadImageInfoData.DataBean> list) {
        super(context, list, R.layout.item_upload_image);

        mCallback = (Callback)context;
    }


    public void setShowDelete(boolean del)
    {
        isShowDelete = del;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(UploadImageItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);



        if(getDataItem(position).getType()==-1)
        {
            holder.ivItemDelete.setVisibility(View.GONE);
            holder.ivItemImage.setImageResource(R.drawable.ic_add_grey_600);
        }else
        {
            holder.ivItemDelete.setVisibility(View.VISIBLE);
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop();
            Glide.with(getContext().getApplicationContext()).asBitmap()
                    .load(getDataItem(position).getFull_path())
                    .apply(requestOptions)
                    .into(holder.ivItemImage);

            if(isShowDelete)
            {
                holder.ivItemDelete.setVisibility(View.VISIBLE);
            }else
            {
                holder.ivItemDelete.setVisibility(View.GONE);
            }

        }




        holder.ivItemDelete.setTag(position);
        holder.ivItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = (int)view.getTag();

                if(mCallback!=null)
                {
                    mCallback.delete(position);
                }
            }
        });





    }

    @Override
    public UploadImageItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UploadImageItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


    public interface  Callback
    {
        public void delete(int position);
    }



}
