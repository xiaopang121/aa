package com.insthub.cat.android.ui.fragment.ShopFinishWithdrawFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.fui.fragment.BaseFragment;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.FinishOrderListData;
import com.insthub.cat.android.module2.OrderWithdrawData;
import com.insthub.cat.android.module2.ShopOrderListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.adatper.WithdrawHasAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 订单fragment
 * Created by linux on 2017/6/28.
 */

public class ShopFinishWithdrawFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View,WithdrawHasAdapter.WidthdrawCallback {

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;

    private RecyclerView recyclerview;

    private ArrayList<FinishOrderListData.DataBean.ListBean> dataList = new ArrayList<>();

    private WithdrawHasAdapter mTaitouAdapter;

    private int page=1;
    private int showCount=20;

    private int type;


    public static ShopFinishWithdrawFragment newInstance(int type) {
        ShopFinishWithdrawFragment mainFragment = new ShopFinishWithdrawFragment();
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
        return R.layout.fragment_withdraw_finish;
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
        mTaitouAdapter = new WithdrawHasAdapter(getActivity(), dataList,type,this);
        recyclerview.setAdapter(mTaitouAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));
        recyclerview.scrollToPosition(0);


    }

    @Override
    protected void bindEvent() {
        super.bindEvent();


        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {

                page=1;
                mPresenter.getFinishedOrderList(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),
                        page,showCount,type,"");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {

                page++;
                mPresenter.getFinishedOrderList(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),
                        page,showCount,type,"");
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

    }


    @Override
    public void showSuccess(Object object) {
        mPullToRefreshRecycle.onRefreshComplete();

        if(object instanceof FinishOrderListData)
        {

            FinishOrderListData data = (FinishOrderListData)object;

            if(page==1)
            {
                dataList.clear();
            }

            dataList.addAll(data.getData().getList());
            mTaitouAdapter.notifyDataSetChanged();
        }



        if(object instanceof OrderWithdrawData)
        {
            ToastUtil.show(getContext(),"数据提交成功");
            dismissLoadDialog();

            mPullToRefreshRecycle.doPullRefreshing(true,200);

        }
    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(),msg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onApply(int position) {

       // DialogUtils.showWithdrawApplayDialog(getActivity()).show();

        showLoadDialog("正在提交数据");
        mPresenter.sumbitStoreWithdraw(CacheManager.getInstance().getToken().getData().getUser_id(),
                CacheManager.getInstance().getToken().getData().getToken(),dataList.get(position).getOrder_id(),"");

    }

    @Override
    public void onChat(int position) {
        SessionHelper.startP2PSession(getActivity(), dataList.get(position).getAccid());
    }
}
