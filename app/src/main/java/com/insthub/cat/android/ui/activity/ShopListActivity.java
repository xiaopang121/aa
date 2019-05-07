package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.RaiseItemData;
import com.insthub.cat.android.module.ShopListData;
import com.insthub.cat.android.module2.DeleteMyShopData;
import com.insthub.cat.android.module2.MyStoreData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.ScoreListAdapter;
import com.insthub.cat.android.ui.adatper.ShopAdapter;
import com.insthub.cat.android.ui.adatper.TaitouAdapter;
import com.insthub.cat.android.ui.dialog.CommonDialog;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 我的店铺
 * Created by linux on 2017/6/28.
 */

public class ShopListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View,ShopAdapter.ShopCallback{


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;

    //berth
    private RecyclerView recyclerview;

    private ArrayList<MyStoreData.DataBean> dataList = new ArrayList<>();

    private ShopAdapter mShopAdapter;




    @Bind(R.id.iv_logo)
    RoundedImageView ivLogo;
    @Bind(R.id.tv_app_name)
    TextView tvAppName;
    @Bind(R.id.bt_open)
    Button btOpen;

    @Bind(R.id.lly_no_shop)
    LinearLayout llyNoShop;

    private MyStoreData mMyStoreData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_myshop;
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
        commonTitleBar.setTitle(R.string.title_shop);
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);


        mPullToRefreshRecycle.setLastUpdatedLabel(TimeUtils.formateTime(System.currentTimeMillis(), TimeUtils.FROAMTE_YMHMS));
        mPullToRefreshRecycle.setPullToRefreshEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mPullToRefreshRecycle.onRefreshComplete();
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mShopAdapter = new ShopAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mShopAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));
        recyclerview.scrollToPosition(0);




//
//
//        dataList.add(new ShopListData.DataBean());
//        dataList.add(new ShopListData.DataBean());
//        llyNoShop.setVisibility(View.GONE);
//


        mPresenter.getMyStoreDetail(CacheManager.getInstance().getToken().getData().getUser_id(), CacheManager.getInstance().getToken().getData().getToken());
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        commonTitleBar.ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),ShopAddActivity.class);
                startActivityForResults(intent,100);

            }
        });


    }


    @Override
    protected void bindData() {
        super.bindData();


    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {

        dismissLoadDialog();

        if(object instanceof  MyStoreData)
        {
            mMyStoreData=(MyStoreData)object;

            dataList.clear();
            dataList.add(mMyStoreData.getData());
            mShopAdapter.notifyDataSetChanged();
            if(dataList.isEmpty())
            {
                llyNoShop.setVisibility(View.VISIBLE);
            }else
            {
                llyNoShop.setVisibility(View.GONE);
            }
        }


        if(object instanceof DeleteMyShopData)
        {


            dataList.clear();
            mShopAdapter.notifyDataSetChanged();

           if(dataList.isEmpty())
           {
               llyNoShop.setVisibility(View.VISIBLE);
           }else
           {
               llyNoShop.setVisibility(View.GONE);
           }
        }
    }

    @Override
    public void showError(String msg,int code) {

        dismissLoadDialog();
        ToastUtil.show(this,msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    public void onClickEdit(int position) {
        Intent intent = new Intent(getActivity(),ShopOpen1Activity.class);
        intent.putExtra(ConstantsKeys.KEY_DATA,dataList.get(position));
        startActivityForResults(intent,100);
    }

    @Override
    public void onClickDelete(final int position) {

        CommonDialog.Builder commonDialog = new CommonDialog.Builder(this);
        commonDialog.setTitle("提示");
        commonDialog.setMessage("确认要删除该店铺？");
        commonDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                showLoadDialog("正在删除店铺");
                mPresenter.delStore(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),dataList.get(position).getStore_id());
            }
        });
        commonDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        commonDialog.create().show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_CANCELED)
        {
           return;
        }

        dataList.clear();
        mShopAdapter.notifyDataSetChanged();

        if(dataList.isEmpty())
        {
            llyNoShop.setVisibility(View.VISIBLE);
        }else
        {
            llyNoShop.setVisibility(View.GONE);
        }

        mPresenter.getMyStoreDetail(CacheManager.getInstance().getToken().getData().getUser_id(), CacheManager.getInstance().getToken().getData().getToken());

    }





}
