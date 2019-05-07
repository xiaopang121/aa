package com.insthub.cat.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
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
import com.insthub.cat.android.module2.DeleteInvoiceData;
import com.insthub.cat.android.module.RaiseItemData;
import com.insthub.cat.android.module.TaitouListData;
import com.insthub.cat.android.module2.InvoiceTitleListData;
import com.insthub.cat.android.module2.SetDefaultTitleData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.TaitouAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 抬头列表
 * Created by linux on 2017/6/28.
 */

public class TaitouListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View ,TaitouAdapter.TaitouCallback {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;

    @Bind(R.id.tv_add_taitou)
    TextView tvAddTaitou;

    //berth
    private RecyclerView recyclerview;

    private ArrayList<InvoiceTitleListData.DataBean.ListBean> dataList = new ArrayList<>();

    private TaitouAdapter mTaitouAdapter;

    private int deletePosition=-1;


    private  int  defaultPosition=-1;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_taitou_list;
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
        commonTitleBar.setTitle("抬头管理");
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
        mTaitouAdapter = new TaitouAdapter(getActivity(), dataList,false);
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



        tvAddTaitou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(EditTaitouActivity.class,1000);
            }
        });


        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
                mPresenter.getMyInvoiceTitle(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {

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
        mPullToRefreshRecycle.onRefreshComplete();

        if(object instanceof InvoiceTitleListData)
        {

            InvoiceTitleListData data = (InvoiceTitleListData)object;
            dataList.clear();
            dataList.addAll(data.getData().getList());
            mTaitouAdapter.notifyDataSetChanged();
        }



        if(object instanceof DeleteInvoiceData)
        {
            ToastUtil.show(this,"抬头删除成功");

            if(deletePosition!=-1)
            {
                dataList.remove(deletePosition);
                deletePosition = -1;
                mTaitouAdapter.notifyDataSetChanged();
            }
        }


        if(object instanceof  SetDefaultTitleData)
        {
            mPresenter.getMyInvoiceTitle(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());
            ToastUtil.show(this,"设置成功");

        }



    }


    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        mPullToRefreshRecycle.onRefreshComplete();
        ToastUtil.show(getActivity(), msg);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }

    @Override
    public void onDefault(int positon) {
        showLoadDialog("正在设置默认抬头");
        defaultPosition = positon;
        mPresenter.setDefaultTitle(CacheManager.getInstance().getToken().getData().getUser_id(),
                CacheManager.getInstance().getToken().getData().getToken(),
                dataList.get(positon).getTitle_id());
    }

    @Override
    public void onEdit(int position) {


        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantsKeys.KEY_DATA,dataList.get(position));

        Intent intent = new Intent(this,EditTaitouActivity.class);

        intent.putExtras(bundle);

        startActivityForResult(intent,100);
    }

    @Override
    public void onDelete(int positon) {

        showLoadDialog("正在删除");
        deletePosition = positon;
        mPresenter.deleteRaiseByRaiseId(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),
                dataList.get(positon).getTitle_id());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {
            mPresenter.getMyInvoiceTitle(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());
        }
    }
}
