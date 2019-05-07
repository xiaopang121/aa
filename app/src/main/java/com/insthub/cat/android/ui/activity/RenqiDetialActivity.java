package com.insthub.cat.android.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.CollectServiceData;
import com.insthub.cat.android.module2.CollectStoreData;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.module2.ShopDetialData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.CommonFragmentAdatper;
import com.insthub.cat.android.ui.adatper.LocalImgAdapter;
import com.insthub.cat.android.ui.fragment.ShopIndexFragment.ShopIndexFragment;
import com.insthub.cat.android.ui.fragment.ShopServiceFragment.ShopServiceFragment;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.lang.ref.WeakReference;

import butterknife.Bind;


/**
 * 人气榜详情
 * Created by linux on 2017/6/28.
 */

public class RenqiDetialActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View, LocalImgAdapter.BannerCallback {


    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;

    @Bind(R.id.iv_head_bg)
    ImageView ivHeadBg;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Bind(R.id.tv_shop_name)
    TextView tvShopName;

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_collect)
    TextView tvCollect;
    @Bind(R.id.tv_collect_size)
    TextView tvCollectSize;
    @Bind(R.id.tv_index)
    TextView tvIndex;
    @Bind(R.id.tv_service)
    TextView tvService;

    @Bind(R.id.root_view)
    RelativeLayout rootView;


    private DiscoverStoreData.DataBean.ListBean data;

    private ShopDetialData mShopDetialData;

    private CommonFragmentAdatper adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_renqi_detial;
    }

    @Override
    protected int bindColorPrimary() {
        return 0;
    }


    @Override
    protected void bindPresenter() {
        super.bindPresenter();
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void bindViewById() {
        super.bindViewById();
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        data = (DiscoverStoreData.DataBean.ListBean) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);


    }


    private void updateView() {

        if (mShopDetialData != null) {

            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .error(R.drawable.ic_renqi_bg)
                    .placeholder(R.drawable.ic_renqi_bg);
//            Glide.with(getContext().getApplicationContext()).asBitmap()
//                    .load(mShopDetialData.getData().getBackground_image())
//                    .apply(requestOptions)
//                    .into(ivHeadBg);


            Glide.with(getContext().getApplicationContext()).asBitmap()
                    .load(mShopDetialData.getData().getBackground_image())
                    .apply(requestOptions)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            ivHeadBg.setImageBitmap(resource);
                        }
                    });


            tvShopName.setText(mShopDetialData.getData().getStore_name());

            tvCollectSize.setText("收藏人数"+mShopDetialData.getData().getCollect_count());


            adapter = new CommonFragmentAdatper(getSupportFragmentManager());
            adapter.addFragment(ShopIndexFragment.newInstance(mShopDetialData), this.getString(R.string.menu_index));
            adapter.addFragment(ShopServiceFragment.newInstance(mShopDetialData), this.getString(R.string.menu_discover));
            viewPager.setAdapter(adapter);

            switch (mShopDetialData.getData().getIs_collect())
            {
                case 0:

                    tvCollect.setText("未收藏");
                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_collect_smaill_normal),null,null,null);
                    break;
                case 1:

                    tvCollect.setText("已收藏");
                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_collect_smaill_pressed),null,null,null);
                    break;
            }


        }


    }


    @Override
    protected void bindEvent() {
        super.bindEvent();


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        tvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CacheManager.getInstance().getToken()==null)
                {

                    ToastUtil.show(getActivity(),"请先登录后再操作");
                    startActivity(WxLoginActivity.class);
                    return;
                }

                showLoadDialog("正在提交数据");
                if(CacheManager.getInstance().getToken()!=null)
                {
                    mPresenter.collecStore(CacheManager.getInstance().getToken().getData().getUser_id(),
                            CacheManager.getInstance().getToken().getData().getToken(),data.getStore_id());
                }

            }
        });



        tvIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                tvIndex.setTextColor(getResources().getColor(R.color.yellow_color_pressed));
                tvService.setTextColor(Color.WHITE);
            }
        });


        tvService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                tvService.setTextColor(getResources().getColor(R.color.yellow_color_pressed));
                tvIndex.setTextColor(Color.WHITE);
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position)
                {
                    case 0:
                        tvIndex.setTextColor(getResources().getColor(R.color.yellow_color_pressed));
                        tvService.setTextColor(Color.WHITE);
                        break;
                    case 1:
                        tvService.setTextColor(getResources().getColor(R.color.yellow_color_pressed));
                        tvIndex.setTextColor(Color.WHITE);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    @Override
    protected void bindData() {
        super.bindData();


        showLoadDialog("正在加载数据");
        mPresenter.getShopDetial(data.getStore_id());

//
//        if(CacheManager.getInstance().getToken()!=null)
//        {
//            mPresenter.collecService(CacheManager.getInstance().getToken().getData().getUser_id(),
//                    CacheManager.getInstance().getToken().getData().getToken(),data.getStore_id());
//        }

    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {

        dismissLoadDialog();
        if (object instanceof ShopDetialData) {
            mShopDetialData = (ShopDetialData) object;
            updateView();
        }


        if(object instanceof CollectStoreData)
        {
            CollectStoreData mCollectServiceData = (CollectStoreData)object;
            switch (mCollectServiceData.getData().getIs_collect())
            {
                case 0:

                    tvCollect.setText("未收藏");
                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_collect_smaill_normal),null,null,null);
                    break;
                case 1:

                    tvCollect.setText("已收藏");
                    tvCollect.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_collect_smaill_pressed),null,null,null);
                    break;
            }

        }


    }

    @Override
    public void showError(String msg, int code) {
        dismissLoadDialog();
        ToastUtil.show(this, msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            UMShareAPI.get(this).release();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }


        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    public void onClickItem(int position) {

    }


    private static class CustomShareListener implements UMShareListener {

        private WeakReference<RenqiDetialActivity> mActivity;

        private CustomShareListener(RenqiDetialActivity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {


            Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mActivity.get(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
