package com.insthub.cat.android.ui.fragment.TicketFragment;

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
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.TickListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.activity.TicketDetialActivity;
import com.insthub.cat.android.ui.adatper.TickListAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * Created by linux on 2017/6/28.
 */

public class TicketFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;


    private RecyclerView recyclerview;

    private ArrayList<TickListData.DataBean.ListBean> dataList = new ArrayList<>();

    private TickListAdapter mTickListAdapter;

    private int type = -1;

    private int page=1;
    private int showCount=20;



    public static TicketFragment newInstance(int type) {
        TicketFragment mainFragment = new TicketFragment();
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

        mTickListAdapter = new TickListAdapter (getActivity(), dataList);
        recyclerview.setAdapter(mTickListAdapter);

        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));
        recyclerview.scrollToPosition(0);

    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {


                page = 1;
                mPresenter.getInvoiceLog(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),page,showCount,type);


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                page++;
                mPresenter.getInvoiceLog(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),page,showCount,type);
            }
        });


                mTickListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Bundle bundle =new Bundle();
                        bundle.putSerializable(ConstantsKeys.KEY_DATA,dataList.get(position));
                        startActivity(TicketDetialActivity.class,bundle);
                    }
                });
    }


    @Override
    protected void bindData() {
        super.bindData();


        if(dataList.isEmpty())
        {
            mPullToRefreshRecycle.doPullRefreshing(true,200);
        }
    }



    @Override
    public void onResume() {
        super.onResume();

        bindData();

    }

    @Override
    public void showSuccess(Object object) {

        mPullToRefreshRecycle.onRefreshComplete();

        if(object instanceof  TickListData)
        {
            TickListData data = (TickListData) object;


            if(page==1)
            {
                dataList.clear();
            }

            dataList.addAll(data.getData().getList());
            mTickListAdapter.notifyDataSetChanged();


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





}
