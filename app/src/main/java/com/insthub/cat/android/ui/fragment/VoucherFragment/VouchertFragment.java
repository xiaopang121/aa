package com.insthub.cat.android.ui.fragment.VoucherFragment;

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
import com.insthub.cat.android.module.VoucherListData;
import com.insthub.cat.android.module2.UserDeductionData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.VoucherListAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 我的抵用金
 * Created by linux on 2017/6/28.
 */

public class VouchertFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;

    private RecyclerView recyclerview;
    private ArrayList<UserDeductionData.DataBean.ListBean> dataList = new ArrayList<>();

    private VoucherListAdapter mTaitouAdapter;

    private int type;

    private int page=1;

    private int show_count=20;

    public static VouchertFragment newInstance(int type) {
        VouchertFragment mainFragment = new VouchertFragment();
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
        return R.layout.fragment_vouchert;
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
        mPullToRefreshRecycle.setScrollingWhileRefreshingEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.BOTH);
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mTaitouAdapter = new VoucherListAdapter(getActivity(),dataList,type);
        recyclerview.setAdapter(mTaitouAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));

        mPullToRefreshRecycle.doPullRefreshing(true,200);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();


        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {

                page = 1;
                mPresenter.getUserDeduction(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),page,show_count,type);



            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                page++;
                mPresenter.getUserDeduction(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),page,show_count,type);


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

        if(object instanceof UserDeductionData)
        {


            UserDeductionData data = (UserDeductionData)object;

            if(page==1)
            {     dataList.clear();

            }

           dataList.addAll(data.getData().getList());
            mTaitouAdapter.notifyDataSetChanged();
        }





    }


    @Override
    public void showError(String msg,int code) {
        mPullToRefreshRecycle.onRefreshComplete();
        ToastUtil.show(getActivity(), msg);
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

}
