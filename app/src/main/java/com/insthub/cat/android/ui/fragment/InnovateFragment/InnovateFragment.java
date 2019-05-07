package com.insthub.cat.android.ui.fragment.InnovateFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.common.android.flog.KLog;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.ServiceCallback;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.module.InnovateListData;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.activity.InnovateDetialActivity;
import com.insthub.cat.android.ui.activity.ServiceChatActivity;
import com.insthub.cat.android.ui.activity.ShopDetialActivity;
import com.insthub.cat.android.ui.adatper.DiscoverAdapter;
import com.insthub.cat.android.ui.adatper.InnovateAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.utils.LocationUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.lankton.flowlayout.FlowLayout;


/**
 * 征集Fragment
 * Created by linux on 2017/6/28.
 */

public class InnovateFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View,ServiceCallback{

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;
    private RecyclerView recyclerview;
    private ArrayList<InnovateListData.DataBean.ListBean> dataList = new ArrayList<>();
    private InnovateAdapter mInnovateAdapter;

    private int type =0;
    private int page=1;
    private int show_count=20;



    public static InnovateFragment newInstance(int  type) {
        InnovateFragment mainFragment = new InnovateFragment();
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
        return R.layout.fragment_innovate;
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

        type =getArguments().getInt(ConstantsKeys.KEY_DATA);
        mPullToRefreshRecycle.setLastUpdatedLabel(TimeUtils.formateTime(System.currentTimeMillis(), TimeUtils.FROAMTE_YMHMS));
        mPullToRefreshRecycle.setPullToRefreshEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshRecycle.onRefreshComplete();
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mInnovateAdapter = new InnovateAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mInnovateAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_transt));
        recyclerview.scrollToPosition(0);



    }

    @Override
    protected void bindEvent() {
        super.bindEvent();


        mInnovateAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                Bundle bundle  = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,dataList.get(i));
                bundle.putInt(ConstantsKeys.KEY_FROM,1);
                startActivity(InnovateDetialActivity.class,bundle);
            }
        });


        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {


                page=1;
                mPresenter.getInnovateList(page,show_count,type,"");

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                page++;
                mPresenter.getInnovateList(page,show_count,type,"");

            }
        });
    }


    @Override
    public void onService(int position) {

        startActivity(ServiceChatActivity.class);
    }

    @Override
    protected void bindData() {
        super.bindData();

        if(dataList.isEmpty() && mPullToRefreshRecycle!=null)
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

        if(object instanceof InnovateListData)
        {

            InnovateListData mDiscoverStoreData = (InnovateListData)object;
            if(page==1)
            {
                dataList.clear();
            }

            dataList.addAll(mDiscoverStoreData.getData().getList());
        }
        mPullToRefreshRecycle.onRefreshComplete();
        mInnovateAdapter.notifyDataSetChanged();

    }

    @Override
    public void showError(String msg,int code) {

        mPullToRefreshRecycle.onRefreshComplete();
        dismissLoadDialog();
        ToastUtil.show(getActivity(),msg);
    }


}
