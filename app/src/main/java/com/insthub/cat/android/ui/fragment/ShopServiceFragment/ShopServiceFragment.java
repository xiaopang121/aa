package com.insthub.cat.android.ui.fragment.ShopServiceFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.IntentUtils;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.DeleteCommentCallback;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.DeleteCommentData;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.module2.ShopDetialData;
import com.insthub.cat.android.module2.UserCommentListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.activity.ShopDetialActivity;
import com.insthub.cat.android.ui.activity.WxLoginActivity;
import com.insthub.cat.android.ui.adatper.CommentAdapter;
import com.insthub.cat.android.ui.adatper.ShopServiceAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.ui.widget.SpacesItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 店铺服务
 * Created by linux on 2017/6/28.
 */

public class ShopServiceFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View {



    @Bind(R.id.rv_index_menu)
     RecyclerView recyclerview;
    private ArrayList<ShopDetialData.DataBean.ServiceListBean> dataList = new ArrayList<>();

    private ShopServiceAdapter mCommentAdapter;
    private ShopDetialData mShopDetialData;


    @Bind(R.id.rll_share)
    RelativeLayout rllShare;
    @Bind(R.id.rll_contract_server)
    RelativeLayout rllContractServer;

    UMShareListener mShareListener;


    public static ShopServiceFragment newInstance(ShopDetialData data) {
        ShopServiceFragment mainFragment = new ShopServiceFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantsKeys.KEY_DATA,data);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShopDetialData = (ShopDetialData)getArguments().getSerializable(ConstantsKeys.KEY_DATA);

    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_shop_service;
    }

    @Override
    protected int bindColorPrimary() {
        return 0;
    }


    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void bindViewById() {
        super.bindViewById();

        GridLayoutManager jxlayoutManager = new GridLayoutManager(getActivity(), 2);
        SpacesItemDecoration jx2layoutManager = new SpacesItemDecoration(UIUtil.dpToPx(getResources(), 5));
        recyclerview.setLayoutManager(jxlayoutManager);
        recyclerview.setHasFixedSize(true);
        mCommentAdapter = new ShopServiceAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mCommentAdapter);
        recyclerview.addItemDecoration(jx2layoutManager);

    }

    @Override
    protected void bindEvent() {
        super.bindEvent();





        rllShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mShopDetialData==null)
                {
                    ToastUtil.show(getContext(),"分享失败，暂无数据");
                    return;
                }


                mShareListener = new CustomShareListener(getActivity());

                ShareBoardConfig mShareBoardConfig = new ShareBoardConfig();
                mShareBoardConfig.setCancelButtonVisibility(false);
                mShareBoardConfig.setTitleVisibility(false);
                mShareBoardConfig.setIndicatorVisibility(false);
                mShareListener = new CustomShareListener(getActivity());
                ShareAction mShareAction = new ShareAction(getActivity()).setDisplayList(
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                                if (snsPlatform.mShowWord.equals("复制链接")) {
                                    ClipboardManager cm = (ClipboardManager)getActivity(). getSystemService(Context.CLIPBOARD_SERVICE);
                                    // 将文本内容放到系统剪贴板里。
                                    cm.setText(mShopDetialData.getData().getShare().getShare_image());
                                    ToastUtil.show(getActivity(),"复制链接成功");

                                }else
                                {
                                    UMWeb web = new UMWeb(mShopDetialData.getData().getShare().getShare_url());
                                    web.setTitle(mShopDetialData.getData().getShare().getShare_title());
                                    web.setDescription("");
                                    web.setThumb(new UMImage(getActivity(),mShopDetialData.getData().getShare().getShare_image()));
                                    new ShareAction(getActivity()).withMedia(web)
                                            .setPlatform(share_media)
                                            .setCallback(mShareListener)
                                            .share();
                                }


                            }
                        });
                mShareAction.open(mShareBoardConfig);



            }
        });



        rllContractServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//
//                if(TextUtils.isEmpty(mShopDetialData.getData().getPhone()))
//                {
//                    ToastUtil.show(getActivity(),"该商户暂无联系方式");
//                    return ;
//                }
//                IntentUtils.call(getActivity(),mShopDetialData.getData().getPhone());

                if(CacheManager.getInstance().getToken()==null)
                {
                    ToastUtil.show(getActivity(),"请先登录后再操作");
                    startActivity(WxLoginActivity.class);
                    return;
                }


                if(!TextUtils.isEmpty( mShopDetialData.getData().getAccid()))
                {
                    SessionHelper.startP2PSession(getActivity(), mShopDetialData.getData().getAccid());
                }




            }
        });


        mCommentAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                DiscoverStoreData.DataBean.ListBean tem = new DiscoverStoreData.DataBean.ListBean();
                tem.setStore_id(dataList.get(position).getService_id());
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,tem);
                startActivity(ShopDetialActivity.class,bundle);
            }
        });



    }


    @Override
    protected void bindData() {
        super.bindData();
        if(dataList.isEmpty())
        {
            dataList.addAll(mShopDetialData.getData().getService_list());
            mCommentAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void showSuccess(Object object) {


    }

    @Override
    public void showError(String msg,int code) {


    }


    private static class CustomShareListener implements UMShareListener {

        private WeakReference<ShopDetialActivity> mActivity;

        private CustomShareListener(Activity activity) {
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


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
//    }
}
