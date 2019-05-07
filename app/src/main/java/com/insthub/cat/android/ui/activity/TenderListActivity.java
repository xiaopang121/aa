package com.insthub.cat.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.LegalUserHelper;
import com.insthub.cat.android.module.BidPriceListData;
import com.insthub.cat.android.module.TenderListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.BidPriceAdapter;
import com.insthub.cat.android.ui.adatper.TenderAdapter;
import com.insthub.cat.android.ui.viewholder.DashlineItemDivider;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 招标列表
 * Created by linux on 2017/6/28.
 */

public class TenderListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;

    @Bind(R.id.tv_search)
    EditText tvSearch;



    private RecyclerView recyclerview;

    private ArrayList<TenderListData.DataBean.ListBean> dataList = new ArrayList<>();

    private TenderAdapter mTenderAdapter;


    private int page=1;
    private int page_count=20;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tender_list;
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
        commonTitleBar.setTitle("招标");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
      //  commonTitleBar.setRighButtonTitle(R.string.title_bid_tender);
        mPullToRefreshRecycle.setLastUpdatedLabel(TimeUtils.formateTime(System.currentTimeMillis(), TimeUtils.FROAMTE_YMHMS));
        mPullToRefreshRecycle.setPullToRefreshEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshRecycle.onRefreshComplete();
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mTenderAdapter = new TenderAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mTenderAdapter);
        recyclerview.addItemDecoration(new DashlineItemDivider());
        recyclerview.scrollToPosition(0);
        loadata();



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

        commonTitleBar.btRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(LegalUserHelper.isLegalUserStatus(getActivity()))
                {
                    startActivityForResult(TenderPublishActivity.class,100);
                }
            }
        });

        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {

                page=1;
                loadata();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
                page++;
                loadata();
            }
        });


        mTenderAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                Bundle bundle =new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,dataList.get(position));
                bundle.putInt(ConstantsKeys.KEY_FROM,1);
                startActivity(TenderDetialActivity.class,bundle);
            }
        });


        tvSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String userInput = tvSearch.getText().toString();

                    if(TextUtils.isEmpty(tvSearch.getText().toString()))
                    {

                        return true;
                    }

                    page = 1;
                    loadata();

                    return true;
                }
                return false;
            }
        });


    }


    private void loadata()
    {

        mPresenter.getTenderList(page,page_count,tvSearch.getText().toString(),"");
        UIUtil.hideKeyboard(this);
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

        if (object instanceof TenderListData) {

            TenderListData data = (TenderListData) object;
            if(page==1)
            {
                dataList.clear();
            }
            dataList.addAll(data.getData().getList());
            mTenderAdapter.notifyDataSetChanged();
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

        loadata();
    }
}
