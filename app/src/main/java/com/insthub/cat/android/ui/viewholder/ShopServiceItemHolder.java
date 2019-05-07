package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class ShopServiceItemHolder extends BaseRecyclerViewHolder {

    @Bind(R.id.iv_jinxuan_image)
   public RoundedImageView ivJinxuanImage;
    @Bind(R.id.tv_jinxuan_name)
    public  TextView tvJinxuanName;
    @Bind(R.id.tv_jinxuan_price)
    public TextView tvJinxuanPrice;

    @Bind(R.id.tv_jinxuan_size)
    public TextView tvSize;

    public ShopServiceItemHolder(View itemView) {
        super(itemView);

    }
}
