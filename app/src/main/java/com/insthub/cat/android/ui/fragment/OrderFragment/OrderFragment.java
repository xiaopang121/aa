package com.insthub.cat.android.ui.fragment.OrderFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.DeleteCommentCallback;
import com.insthub.cat.android.helper.OrderApplyFinishCallback;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.ApplyYanshouData;
import com.insthub.cat.android.module2.DeleteUserOrderData;
import com.insthub.cat.android.module2.PaytoStoreData;
import com.insthub.cat.android.module2.ServiceYanshouData;
import com.insthub.cat.android.module2.UseOrderListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.activity.KanjiaOrderInfoActivity;
import com.insthub.cat.android.ui.activity.OrderPayActivity;
import com.insthub.cat.android.ui.activity.SumbitOrderCommentActivity;
import com.insthub.cat.android.ui.adatper.UserOrderAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 订单fragment
 * Created by linux on 2017/6/28.
 */

public class OrderFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View,UserOrderAdapter.UserOrderCallback {

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;

    private RecyclerView recyclerview;
    private ArrayList<UseOrderListData.DataBean.ListBean> dataList = new ArrayList<>();

    private UserOrderAdapter mUserOrderAdapter;

    private int page=1;
    private int  showCount =20;


    private int deletePositio=-1;

    private int applyYamshouPosition =-1;

    private int payService=-1;

    public static OrderFragment newInstance() {
        OrderFragment mainFragment = new OrderFragment();
        Bundle bundle = new Bundle();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_order;
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

        mPullToRefreshRecycle.setLastUpdatedLabel(TimeUtils.formateTime(System.currentTimeMillis(), TimeUtils.FROAMTE_YMHMS));
        mPullToRefreshRecycle.setPullToRefreshEnabled(true);
        mPullToRefreshRecycle.setScrollingWhileRefreshingEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.BOTH);
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mUserOrderAdapter = new UserOrderAdapter(getActivity(), dataList,OrderFragment.this);
        recyclerview.setAdapter(mUserOrderAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));

        mPullToRefreshRecycle.doPullRefreshing(true,200);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {

                page =1;
                mPresenter.getUserOrderList(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),page,showCount);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                //Toast.makeText(MainActivity.this, "上拉加载", Toast.LENGTH_SHORT).show();
                page ++;
                mPresenter.getUserOrderList(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),page,showCount);
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

        if(object instanceof  UseOrderListData)
        {
            UseOrderListData mUserCommentListData = (UseOrderListData) object;
            if(page==1)
            {
                dataList.clear();
            }

            dataList.addAll(mUserCommentListData.getData().getList());
            mUserOrderAdapter.notifyDataSetChanged();
        }



        if(object instanceof DeleteUserOrderData)
        {

            dismissLoadDialog();
            ToastUtil.show(getContext(),"删除成功");
            if(deletePositio!=-1)
            {
                dataList.remove(deletePositio);
                deletePositio=-1;
                mUserOrderAdapter.notifyDataSetChanged();
            }
        }


        if(object instanceof ApplyYanshouData)
        {
            dismissLoadDialog();
            ToastUtil.show(getContext(),"数据提交成功");
            mPullToRefreshRecycle.doPullRefreshing(true,100);

        }


        if(object instanceof PaytoStoreData)
        {
            dismissLoadDialog();
            ToastUtil.show(getContext(),"数据提交成功");
            mPullToRefreshRecycle.doPullRefreshing(true,100);
        }
    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        mPullToRefreshRecycle.onRefreshComplete();
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
        RxBusManager.getInstance().unSubscribe(this);
    }

    @Override
    public void onComment(int positon) {

        Intent intent = new Intent(getActivity(),SumbitOrderCommentActivity.class);
        intent.putExtra(ConstantsKeys.KEY_DATA,dataList.get(positon));
        startActivityForResults(intent,100);
    }

    @Override
    public void onPay(int position) {

         // SessionHelper.startP2PSession(getActivity(), CacheManager.getInstance().getToken().getData().getIm_code());


        Intent intent = new Intent(getActivity(),OrderPayActivity.class);
        intent.putExtra(ConstantsKeys.KEY_DATA,dataList.get(position));
        startActivityForResults(intent,100);

    }

    @Override
    public void onDelete(int positon) {

       deletePositio = positon;

        DialogUtils.showDeleteOrderDialog(getContext(), new DeleteCommentCallback() {
            @Override
            public void onClickSure(Dialog dialog , int position) {


                dialog.dismiss();
                showLoadDialog("正在删除订单");

                mPresenter.deleteUserOrder(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),dataList.get(position).getOrder_id());

            }
        },positon).show();

    }

    @Override
    public void onSubPrice(int position) {


        Bundle bundle =new Bundle();
        bundle.putSerializable(ConstantsKeys.KEY_DATA,dataList.get(position));
        startActivity(KanjiaOrderInfoActivity.class,bundle);


    }

    @Override
    public void onApplyYanshou(int position) {
        applyYamshouPosition =position;
        DialogUtils.showOrderApplyFinishDialog(getContext(), new OrderApplyFinishCallback() {
            @Override
            public void finish() {

                showLoadDialog("正在提交申请");

                mPresenter.createOrderYanshou(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),
                        dataList.get(applyYamshouPosition).getOrder_id()
                        );

            }
        }).show();

    }


    @Override
    public void onPayServer(int positon) {
        payService = positon;
        DialogUtils.showOrderApplyServiceDialog(getContext(), new OrderApplyFinishCallback() {
            @Override
            public void finish() {

                showLoadDialog("正在提交数据");


                mPresenter.createPayToStore(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),
                        dataList.get(payService).getOrder_id()
                );
            }
        }).show();





    }


    @Override
    public void onContract(int positon) {
        SessionHelper.startP2PSession(getActivity(), dataList.get(positon).getAccid());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        mPullToRefreshRecycle.doPullRefreshing(true,200);

        if(resultCode == Activity.RESULT_OK)
        {
            
        }


    }
}
