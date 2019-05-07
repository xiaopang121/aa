package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.module2.LabelData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.HomeMoreLabelAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 更多
 * Created by linux on 2017/6/28.
 */

public class IndexMenuMoreActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;

    private RecyclerView recyclerview;
    private ArrayList<DiscoverLabelData.DataBean.LabelLv1Bean> dataList = new ArrayList<>();
    private HomeMoreLabelAdapter mHomeMoreLabelAdapter;


    @Override
    protected int getLayoutResId() {
        return R.layout.acitivity_index_more;
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
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.DISABLED);
        mPullToRefreshRecycle.onRefreshComplete();
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mHomeMoreLabelAdapter = new HomeMoreLabelAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mHomeMoreLabelAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_transt));
        recyclerview.scrollToPosition(0);

    }

    @Override
    protected void bindEvent() {
        super.bindEvent();


        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {

            }
        });


        mHomeMoreLabelAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });


        mHomeMoreLabelAdapter.setOnClickCallback(new HomeMoreLabelAdapter.OnSelectLableCallback() {
            @Override
            public void onSelect(DiscoverLabelData.DataBean.LabelLv1Bean parentData, DiscoverLabelData.DataBean.LabelLv1Bean.LabelLv2Bean childData) {

                Bundle bundle  = new Bundle();
                 bundle.putSerializable(ConstantsKeys.KEY_DATA,childData);
                startActivity(LabelListActivity.class,bundle);
            }
        });
    }


    @Override
    protected void bindData() {
        super.bindData();

        mPresenter.getDiscoverLabel();
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void showSuccess(Object object) {

        if (object instanceof DiscoverLabelData) {

            DiscoverLabelData mDiscoverStoreData = (DiscoverLabelData) object;
            dataList.clear();
            dataList.addAll(mDiscoverStoreData.getData().getLabel_lv1());
        }
        mHomeMoreLabelAdapter.notifyDataSetChanged();

    }

    @Override
    public void showError(String msg, int code) {

        mPullToRefreshRecycle.onRefreshComplete();
        dismissLoadDialog();
        ToastUtil.show(getActivity(), msg);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
