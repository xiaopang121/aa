package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.adapter.LBaseAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.HomeData;

/**
 * bannder adapter
 */
public class LocalImgAdapter2 implements LBaseAdapter<HomeData.DataBean.BannerListBean> {
    private Context mContext;

    private BannerCallback mBannerCallback;

    public LocalImgAdapter2(Context context, BannerCallback param) {
        mBannerCallback = param;
        mContext=context;
    }

    @Override
    public View getView(final LMBanners lBanners, final Context context, int position, HomeData.DataBean.BannerListBean data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_banner_item2, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_image);

        imageView.setTag(R.id.banner_item,position);
//
//        Glide.with(mContext)
//                .load(data.getImage())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//              //  .error(R.drawable.ic_default_head)
//              //  .placeholder(R.drawable.ic_default_head)
//                .into(imageView);


        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                ;

        //KLog.i("url=="+data.getImage());
        Glide.with(mContext).asBitmap()
                .load(data.getImage())
                .apply(requestOptions)
                .into(imageView);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mBannerCallback!=null)
                {
                    int position = (int) view.getTag(R.id.banner_item);
                    mBannerCallback.onClickItem(position);
                }

            }
        });

        return view;
    }


    public interface  BannerCallback
    {
        public void onClickItem(int position);
    }


}
