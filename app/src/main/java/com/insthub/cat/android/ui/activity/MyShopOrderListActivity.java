package com.insthub.cat.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.ServiceYanshouData;
import com.insthub.cat.android.module2.ShopOrderListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.adatper.ShopOrderAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 店铺订单
 * Created by linux on 2017/6/28.
 */

public class MyShopOrderListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View,ShopOrderAdapter.ShopOrderCallback {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;

    private RecyclerView recyclerview;

    private ArrayList<ShopOrderListData.DataBean.ListBean> dataList = new ArrayList<>();

    private ShopOrderAdapter mTaitouAdapter;


    private int page=1;
    private int showCount=20;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shop_order_list;
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
        commonTitleBar.setTitle(R.string.title_shop_order);
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
        mTaitouAdapter = new ShopOrderAdapter(this, dataList);
        recyclerview.setAdapter(mTaitouAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));
        recyclerview.scrollToPosition(0);


        mPullToRefreshRecycle.doPullRefreshing(true,200);


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




        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
                page=1;
                mPresenter.getStoreOrderList(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),page,showCount,"");

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
                page++;
                mPresenter.getStoreOrderList(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),page,showCount,"");

            }
        });



        mTaitouAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,dataList.get(i));
                startActivity(ShopOrderDetialActivity.class,bundle);
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

        mPullToRefreshRecycle.onRefreshComplete();

        if(object instanceof ShopOrderListData)
        {

            ShopOrderListData data = (ShopOrderListData)object;

            if(page==1)
            {
                dataList.clear();
            }

            dataList.addAll(data.getData().getList());
            mTaitouAdapter.notifyDataSetChanged();
        }




        if(object instanceof ServiceYanshouData)
        {
            ToastUtil.show(getContext(),"数据提交成功");
            dismissLoadDialog();
        }



    }


    @Override
    public void showError(String msg,int code) {
        mPullToRefreshRecycle.onRefreshComplete();
        ToastUtil.show(getActivity(), msg);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {
            //更新数据
        }
    }

    @Override
    public void applyYanshou(int positon) {

        showLoadDialog("正在申请客户验收");
        mPresenter.createOrderServiceYanshou(CacheManager.getInstance().getToken().getData().getUser_id(),
                CacheManager.getInstance().getToken().getData().getToken(),
                dataList.get(positon).getOrder_id());

    }

    @Override
    public void applyPay(int position) {
        showLoadDialog("正在申请客户付款");
    }


    @Override
    public void applayChat(int position) {
        SessionHelper.startP2PSession(getActivity(), dataList.get(position).getAccid());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
