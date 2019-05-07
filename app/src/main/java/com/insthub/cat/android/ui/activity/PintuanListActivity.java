package com.insthub.cat.android.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.module2.ActionListData;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.module2.PintuanListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.ActiveAdapter;
import com.insthub.cat.android.ui.extend.PullToRefreshRecyclerViewExtends;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 拼团列表
 * Created by linux on 2017/6/28.
 */

public class PintuanListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View{


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerViewExtends mPullToRefreshRecycle;



    private SwipeMenuRecyclerView recyclerview;

    private ArrayList<ActionListData.DataBean.ListBean> dataList = new ArrayList<>();

    private ActiveAdapter mTaitouAdapter;

    private String activity_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_action;
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

        activity_id =String.valueOf(getIntent().getExtras().getInt(ConstantsKeys.KEY_DATA));
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        commonTitleBar.setTitle("拼团列表");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        mPullToRefreshRecycle.setLastUpdatedLabel(TimeUtils.formateTime(System.currentTimeMillis(), TimeUtils.FROAMTE_YMHMS));
        mPullToRefreshRecycle.setPullToRefreshEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshRecycle.onRefreshComplete();
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mTaitouAdapter = new ActiveAdapter(getActivity(), dataList, true);
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


        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<SwipeMenuRecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<SwipeMenuRecyclerView> pullToRefreshBase) {
                mPresenter.getPintuanList(activity_id);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<SwipeMenuRecyclerView> pullToRefreshBase) {

            }
        });


        recyclerview.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftswipeMenu, SwipeMenu rightswipeMenu1, int i) {
                int width = getResources().getDimensionPixelSize(R.dimen.view_margin_70);
                int height = ViewGroup.LayoutParams.MATCH_PARENT;

//                SwipeMenuItem addItem = new SwipeMenuItem(getActivity())
//                        .setBackground(R.color.red)
//                        .setText("删除")
//                        .setTextColor(Color.WHITE)
//                        .setWidth(width)
//                        .setHeight(height);
              //  rightswipeMenu1.addMenuItem(addItem); // 添加菜单到右侧。
            }
        });





        recyclerview.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if(position<dataList.size())
                {


                }
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

        if (object instanceof PintuanListData) {

            PintuanListData data = (PintuanListData) object;
//            dataList.clear();
//            dataList.addAll(data.getData().getList());
            mTaitouAdapter.notifyDataSetChanged();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            //更新数据
        }
    }
}
