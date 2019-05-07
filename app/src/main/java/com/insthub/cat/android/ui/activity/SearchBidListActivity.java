package com.insthub.cat.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.insthub.cat.android.helper.LegalUserHelper;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.module.BidPriceListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.BidPriceAdapter;
import com.insthub.cat.android.ui.viewholder.DashlineItemDivider;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 竞价列表
 * Created by linux on 2017/6/28.
 */

public class SearchBidListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {



    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;


    @Bind(R.id.iv_left)
    ImageView ivLeft;

    @Bind(R.id.bt_left)
    Button btLeft;

    @Bind(R.id.iv_right)
    ImageView ivRight;

    @Bind(R.id.bt_right)
    Button btRight;

    @Bind(R.id.tv_title)
    EditText tvSearchContent;


    @Bind(R.id.title_bar)
    RelativeLayout commonTitleBar;

    @Bind(R.id.iv_cancel)
    ImageView ivCancel;



    private RecyclerView recyclerview;

    private ArrayList<BidPriceListData.DataBean.ListBean> dataList = new ArrayList<>();

    private BidPriceAdapter mTaitouAdapter;


    private int page=1;
    private int page_count=20;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search_bid;
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
        mPullToRefreshRecycle.setLastUpdatedLabel(TimeUtils.formateTime(System.currentTimeMillis(), TimeUtils.FROAMTE_YMHMS));
        mPullToRefreshRecycle.setPullToRefreshEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshRecycle.onRefreshComplete();
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mTaitouAdapter = new BidPriceAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mTaitouAdapter);
        recyclerview.addItemDecoration(new DashlineItemDivider());
        recyclerview.scrollToPosition(0);




    }

    @Override
    protected void bindEvent() {
        super.bindEvent();


        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        btRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(tvSearchContent.getText().toString()))
                {
                    ToastUtil.show(getActivity(),"请输入搜索内容");
                    return;
                }
                showLoadDialog("正在搜索");
                page=1;
                loadata();
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



        mTaitouAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                Bundle bundle =new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,dataList.get(position));
                bundle.putInt(ConstantsKeys.KEY_FROM,1);
                startActivity(BidDetialActivity.class,bundle);
            }
        });

    }


    private void loadata()
    {

        mPresenter.getJingjialist(page,page_count,tvSearchContent.getText().toString(),0,"");
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

        if (object instanceof BidPriceListData) {

            BidPriceListData data = (BidPriceListData) object;

            if(page==1)
            {
                dataList.clear();
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
