package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.dataloader.LocalXjMsgLoader;
import com.insthub.cat.android.event.XiaojingEvent;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.DeleteMyShopData;
import com.insthub.cat.android.module2.MyStoreData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.ShopAdapter;
import com.insthub.cat.android.ui.adatper.XiaojingmsgAdapter;
import com.insthub.cat.android.ui.dialog.CommonDialog;
import com.insthub.cat.android.ui.extend.PullToRefreshRecyclerViewExtends;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 小鲸消息
 * Created by linux on 2017/6/28.
 */

public class XjMsgListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View{


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerViewExtends mPullToRefreshRecycle;

    //berth
    private SwipeMenuRecyclerView recyclerview;

    private ArrayList<XiaojingEvent> dataList = new ArrayList<>();

    private XiaojingmsgAdapter mShopAdapter;


    private Handler mHandler = new Handler();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_xiaojing_list;
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
        commonTitleBar.setTitle("星客捕手");
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
        mShopAdapter = new XiaojingmsgAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mShopAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_1));
        recyclerview.scrollToPosition(0);
        mPullToRefreshRecycle.doPullRefreshing(true,100);

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

        recyclerview.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if(position<dataList.size())
                {
                    dataList.get(position).setState(0);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ConstantsKeys.KEY_DATA,dataList.get(position));
                    mShopAdapter.notifyDataSetChanged();
                    LocalXjMsgLoader.save(dataList.get(position),getActivity());
                    RxBusManager.getInstance().post( dataList.get(position));
                    startActivity(MessageXiaojingActivity.class,bundle);
                }
            }
        });


        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<SwipeMenuRecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<SwipeMenuRecyclerView> refreshView) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        dataList.clear();
                        dataList.addAll(LocalXjMsgLoader.findAll(getContext(),CacheManager.getInstance().getToken().getData().getUser_id()));
                        mShopAdapter.notifyDataSetChanged();
                        mPullToRefreshRecycle.onRefreshComplete();
                    }
                });


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<SwipeMenuRecyclerView> refreshView) {

            }
        });



        recyclerview.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftswipeMenu, SwipeMenu rightswipeMenu1, int i) {
                int width = getResources().getDimensionPixelSize(R.dimen.view_margin_70);
                int height = ViewGroup.LayoutParams.MATCH_PARENT;


                SwipeMenuItem addItem = new SwipeMenuItem(getActivity())
                        .setBackground(R.color.red)
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                rightswipeMenu1.addMenuItem(addItem); // 添加菜单到右侧。
            }
        });



        recyclerview.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();

                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

                if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                    LocalXjMsgLoader.deleteItem(getContext(),dataList.get(adapterPosition)._id);
                    dataList.remove(menuPosition);
                    mShopAdapter.notifyDataSetChanged();

                } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {


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


    }

    @Override
    public void showError(String msg,int code) {

        dismissLoadDialog();
        ToastUtil.show(this,msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }





}
