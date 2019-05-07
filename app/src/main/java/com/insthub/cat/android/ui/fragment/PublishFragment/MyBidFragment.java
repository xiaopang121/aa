package com.insthub.cat.android.ui.fragment.PublishFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.flog.KLog;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.DeleteCommentCallback;
import com.insthub.cat.android.helper.OrderApplyFinishCallback;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.BidPriceListData;
import com.insthub.cat.android.module.ChangePublisData;
import com.insthub.cat.android.module.DeletePublishData;
import com.insthub.cat.android.module2.ApplyYanshouData;
import com.insthub.cat.android.module2.DeleteUserOrderData;
import com.insthub.cat.android.module2.PaytoStoreData;
import com.insthub.cat.android.module2.UseOrderListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.activity.BidDetialActivity;
import com.insthub.cat.android.ui.activity.KanjiaOrderInfoActivity;
import com.insthub.cat.android.ui.activity.OrderPayActivity;
import com.insthub.cat.android.ui.activity.SumbitOrderCommentActivity;
import com.insthub.cat.android.ui.adatper.MyBidPublishAdapter;
import com.insthub.cat.android.ui.adatper.UserOrderAdapter;
import com.insthub.cat.android.ui.extend.PullToRefreshRecyclerViewExtends;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 订单fragment
 * Created by linux on 2017/6/28.
 */

public class MyBidFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerViewExtends mPullToRefreshRecycle;

    private SwipeMenuRecyclerView recyclerview;

    private MyBidPublishAdapter mAllPublishAdapter;

    private int deletePositio=-1;
    private int page;

    private int show_count=30;
    private ArrayList<BidPriceListData.DataBean.ListBean> dataList = new ArrayList<>();


    public static MyBidFragment newInstance() {
        MyBidFragment mainFragment = new MyBidFragment();
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
        return R.layout.fragment_my_publish_list;
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
        mAllPublishAdapter = new MyBidPublishAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mAllPublishAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));

        mPullToRefreshRecycle.doPullRefreshing(true,200);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<SwipeMenuRecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<SwipeMenuRecyclerView> pullToRefreshBase) {
                page=1;
                 mPresenter.getJingjialist(page,show_count,"",0,CacheManager.getInstance().getToken().getData().getUser_id());
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<SwipeMenuRecyclerView> pullToRefreshBase) {
                page++;
                mPresenter.getJingjialist(page,show_count,"",0,CacheManager.getInstance().getToken().getData().getUser_id());
            }
        });



        recyclerview.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftswipeMenu, SwipeMenu rightswipeMenu1, int i) {
                int width = getResources().getDimensionPixelSize(R.dimen.view_margin_70);
                int height = ViewGroup.LayoutParams.MATCH_PARENT;


                if(i ==MyBidPublishAdapter.OPEN_LAYOUT_TYPE)
                {

                    SwipeMenuItem openItem = new SwipeMenuItem(getActivity())
                            .setBackground(R.color.red)
                            .setText("开启")
                            .setTextColor(Color.WHITE)
                            .setWidth(width)
                            .setHeight(height);
                    rightswipeMenu1.addMenuItem(openItem);// 添加菜单到右侧。

                }


                if(i==MyBidPublishAdapter.CLOSE_LAYOUT_TYPE)
                {


                    SwipeMenuItem openItem = new SwipeMenuItem(getActivity())
                            .setBackground(R.color.red)
                            .setText("关闭")
                            .setTextColor(Color.WHITE)
                            .setWidth(width)
                            .setHeight(height);
                    rightswipeMenu1.addMenuItem(openItem);// 添加菜单到右侧。


                    SwipeMenuItem addItem = new SwipeMenuItem(getActivity())
                            .setBackground(R.color.B_black_50)
                            .setText("删除")
                            .setTextColor(Color.RED)
                            .setWidth(width)
                            .setHeight(height);
                    rightswipeMenu1.addMenuItem(addItem); // 添加菜单到右侧。

                }


                if(i==MyBidPublishAdapter.DELETE_LAYOUT_TYPE)
                {


                    SwipeMenuItem addItem = new SwipeMenuItem(getActivity())
                            .setBackground(R.color.B_black_50)
                            .setText("删除")
                            .setTextColor(Color.RED)
                            .setWidth(width)
                            .setHeight(height);
                    rightswipeMenu1.addMenuItem(addItem); // 添加菜单到右侧。



                }




            }
        });




        recyclerview.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();

                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

                KLog.i("menuPosition:"+menuPosition);

                if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {



                    if(dataList.get(adapterPosition).getState().equals("发布中")) {
                        if (menuPosition == 0) {
                            showLoadDialog("正在关闭发布");
                            mPresenter.changePublishState(CacheManager.getInstance().getToken().getData().getUser_id(),
                                    CacheManager.getInstance().getToken().getData().getToken(), dataList.get(adapterPosition).getTender_id(), String.valueOf("1"));

                        }


                        if (menuPosition == 1) {

                            if (dataList.get(adapterPosition).getJoin_count() > 0) {
                                ToastUtil.show(getContext(), "有用户参与该项目,无法删除,");
                                return;
                            }
                            deletePositio= adapterPosition;
                            showLoadDialog("正在删除发布");
                            mPresenter.deletePublishState(CacheManager.getInstance().getToken().getData().getUser_id(),
                                    CacheManager.getInstance().getToken().getData().getToken(), dataList.get(adapterPosition).getTender_id());

                        }
                    }


                    if(dataList.get(adapterPosition).getState().equals("已关闭"))
                    {

                        if(menuPosition==0)
                        {
                            showLoadDialog("正在打开发布");
                            mPresenter.changePublishState(CacheManager.getInstance().getToken().getData().getUser_id(),
                                    CacheManager.getInstance().getToken().getData().getToken(),dataList.get(adapterPosition).getTender_id(),String.valueOf("0"));
                        }



                    }



                    if(dataList.get(adapterPosition).getState().equals("已结束"))
                    {
                        showLoadDialog("正在删除发布");
                        deletePositio= adapterPosition;
                        mPresenter.deletePublishState(CacheManager.getInstance().getToken().getData().getUser_id(),
                                CacheManager.getInstance().getToken().getData().getToken(),dataList.get(adapterPosition).getTender_id());
                    }





                } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {


                }
            }
        });



        recyclerview.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if(position<dataList.size())
                {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ConstantsKeys.KEY_DATA,dataList.get(position));
                    bundle.putInt(ConstantsKeys.KEY_FROM,2);
                    startActivity(BidDetialActivity.class,bundle);
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


        mPullToRefreshRecycle.onRefreshComplete();

        mPullToRefreshRecycle.onRefreshComplete();

        if (object instanceof BidPriceListData) {

            BidPriceListData data = (BidPriceListData) object;

            if(page==1)
            {
                dataList.clear();
            }

            dataList.addAll(data.getData().getList());
            mAllPublishAdapter.notifyDataSetChanged();
        }


        if(object instanceof DeletePublishData)
        {

            dismissLoadDialog();
            ToastUtil.show(getContext(),"删除成功");
            if(deletePositio!=-1)
            {
                dataList.remove(deletePositio);
                deletePositio=-1;
                mAllPublishAdapter.notifyDataSetChanged();
            }
        }


        if(object instanceof ChangePublisData)
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        mPullToRefreshRecycle.doPullRefreshing(true,200);

        if(resultCode == Activity.RESULT_OK)
        {
            
        }


    }
}
