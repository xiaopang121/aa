package com.insthub.cat.android.ui.fragment.HistoryFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.HongbaoDetailCallback;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.module2.HistoryRecordListData;
import com.insthub.cat.android.module2.OpenRedPackageData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.activity.ShopDetialActivity;
import com.insthub.cat.android.ui.activity.TicketDetialActivity;
import com.insthub.cat.android.ui.adatper.IncomeAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * Created by linux on 2017/6/28.
 */

public class IncomeFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View,IncomeAdapter.OpenHongbaoCallback {


    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;


    private RecyclerView recyclerview;

    private ArrayList<HistoryRecordListData.DataBean.ListBean> dataList = new ArrayList<>();

    private IncomeAdapter mTickListAdapter;

    private int type = -1;

    private int page=1;

    private int show_count=20;


    private int position=-1;

    public static IncomeFragment newInstance(int type) {
        IncomeFragment mainFragment = new IncomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantsKeys.KEY_DATA,type);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_ticket_list;
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

        type = getArguments().getInt(ConstantsKeys.KEY_DATA);
        mPullToRefreshRecycle.setLastUpdatedLabel(TimeUtils.formateTime(System.currentTimeMillis(), TimeUtils.FROAMTE_YMHMS));
        mPullToRefreshRecycle.setPullToRefreshEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshRecycle.onRefreshComplete();
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);

        mTickListAdapter = new IncomeAdapter (getActivity(), dataList,type,this);
        recyclerview.setAdapter(mTickListAdapter);

        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));
        recyclerview.scrollToPosition(0);
        mPullToRefreshRecycle.doPullRefreshing(true,200);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {

                page = 1;
                mPresenter.getMoneyLogs(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),page,show_count,type);



            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                page = 1;
                mPresenter.getMoneyLogs(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),page,show_count,type);


            }
        });


        mTickListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//
//                Bundle bundle =new Bundle();
//                bundle.putSerializable(ConstantsKeys.KEY_DATA,dataList.get(position));
//                startActivity(TicketDetialActivity.class,bundle);
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

        bindData();

    }

    @Override
    public void showSuccess(Object object) {

        mPullToRefreshRecycle.onRefreshComplete();

        if(object instanceof  HistoryRecordListData)
        {
            HistoryRecordListData data = (HistoryRecordListData) object;

            dataList.clear();
            dataList.addAll(data.getData().getList());
            mTickListAdapter.notifyDataSetChanged();


        }

        if(object instanceof OpenRedPackageData)
        {

            OpenRedPackageData  tt = (OpenRedPackageData)object;
            dismissLoadDialog();

            if(position==-1)
            {
              return;
            }

            HistoryRecordListData.DataBean.ListBean item =   dataList.get(position);
            mPullToRefreshRecycle.doPullRefreshing(true,200);
            position=-1;
            DialogUtils.showReceiveHongbaoDialog(getActivity(), item, new HongbaoDetailCallback() {
                @Override
                public void onClickSure(String storeId) {
                    DiscoverStoreData.DataBean.ListBean tem = new DiscoverStoreData.DataBean.ListBean();
                    tem.setStore_id(storeId);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ConstantsKeys.KEY_DATA,tem);
                    Intent intent= new Intent(getActivity(),ShopDetialActivity.class);
                    intent.putExtras(bundle);
                    startActivityForResults(intent,100);
                }
            }).show();




        }
    }

    @Override
    public void showError(String msg,int code) {
        mPullToRefreshRecycle.onRefreshComplete();
        dismissLoadDialog();
        ToastUtil.show(getActivity(), msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(int positon) {

        this.position = positon;
        showLoadDialog("正在拆红包");
        mPresenter.OpenRedPackageData(CacheManager.getInstance().getToken().getData().getUser_id(),
                CacheManager.getInstance().getToken().getData().getToken(),String.valueOf(dataList.get(positon).getRp_id()));

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode==100)
        {

        }
    }



}
