package com.insthub.cat.android.ui.fragment.CollectFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.fui.fragment.BaseFragment;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.CollectListData;
import com.insthub.cat.android.module2.DeleteCollectData;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.activity.ShopDetialActivity;
import com.insthub.cat.android.ui.adatper.CollectServiceAdapter;
import com.insthub.cat.android.ui.adatper.CollectStoreAdapter;
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
 * 订单fragment
 * Created by linux on 2017/6/28.
 */

public class CollectServiceFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View{

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerViewExtends mPullToRefreshRecycle;

    private SwipeMenuRecyclerView recyclerview;

    private ArrayList<CollectListData.DataBean.ServiceListBean> dataList = new ArrayList<>();

    private CollectServiceAdapter mTaitouAdapter;


    private int selectPositon = -1;

    private int deletePostion=-1;


    public static CollectServiceFragment newInstance() {
        CollectServiceFragment mainFragment = new CollectServiceFragment();
        return mainFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_collect;
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
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mPullToRefreshRecycle.onRefreshComplete();
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mTaitouAdapter = new CollectServiceAdapter(getActivity(), dataList, true);
        recyclerview.setAdapter(mTaitouAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));
        recyclerview.scrollToPosition(0);


    }

    @Override
    protected void bindEvent() {
        super.bindEvent();



        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<SwipeMenuRecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<SwipeMenuRecyclerView> pullToRefreshBase) {

                mPresenter.getUserCollect(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(), BDLocationManager.getInstance().getCurLocation().getLongitude(),
                        BDLocationManager.getInstance().getCurLocation().getLatitude());

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
//                rightswipeMenu1.addMenuItem(addItem); // 添加菜单到右侧。
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

                    deletePostion = adapterPosition;


                    showLoadDialog("正在删除");

                    mPresenter.deleteUserCollect(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),dataList.get(deletePostion).getStore_id());


                } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {


                }
            }
        });



        recyclerview.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if(position<dataList.size())
                {
                    DiscoverStoreData.DataBean.ListBean tem = new DiscoverStoreData.DataBean.ListBean();
                    tem.setStore_id(dataList.get(position).getService_id());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ConstantsKeys.KEY_DATA,tem);
                    startActivity(ShopDetialActivity.class,bundle);

                }
            }
        });


    }


    @Override
    protected void bindData() {
        super.bindData();

        if(dataList.isEmpty())
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
        dismissLoadDialog();
        mPullToRefreshRecycle.onRefreshComplete();

        if (object instanceof CollectListData) {

            CollectListData data = (CollectListData) object;
            dataList.clear();
            dataList.addAll(data.getData().getService_list());
            mTaitouAdapter.notifyDataSetChanged();
        }


        if(object instanceof DeleteCollectData)
        {
            ToastUtil.show(getContext(),"删除成功");
            dataList.remove(deletePostion);
            deletePostion=-1;
            mTaitouAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(),msg);
    }

}
