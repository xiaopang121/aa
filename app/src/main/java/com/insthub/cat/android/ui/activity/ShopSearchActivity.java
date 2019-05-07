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

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.DiscoverAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 店铺搜索界面
 * Created by linux on 2017/6/28.
 */

public class ShopSearchActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {

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
    private ArrayList<DiscoverStoreData.DataBean.ListBean> dataList = new ArrayList<>();
    private DiscoverAdapter mTaitouAdapter;
    private int  selectPostion=-1;

    private int page=1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search_list;
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
//        ivLeft.setImageResource(R.drawable.ic_arrow_back_grey_800_24dp);
//        ivLeft.setVisibility(View.VISIBLE);

        btRight.setVisibility(View.VISIBLE);
        btRight.setText("搜索");
        mPullToRefreshRecycle.setLastUpdatedLabel(TimeUtils.formateTime(System.currentTimeMillis(), TimeUtils.FROAMTE_YMHMS));
        mPullToRefreshRecycle.setPullToRefreshEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshRecycle.onRefreshComplete();
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mTaitouAdapter = new DiscoverAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mTaitouAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.menu_item_divider_black_1));
        recyclerview.scrollToPosition(0);


        // mPullToRefreshRecycle.doPullRefreshing(true, 200);

       // mPresenter.historySearch(CacheManager.getInstance().getUserInfo().getData().getBstore_id(),CacheManager.getInstance().getUserInfo().getData().getToken());
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

                if(BDLocationManager.getInstance().getCurLocation()==null)
                {

                }
                mPresenter.getStoreByWords(String.valueOf(page),tvSearchContent.getText().toString(), BDLocationManager.getInstance().getCurLocation().getLongitude(),BDLocationManager.getInstance().getCurLocation().getLatitude());
            }
        });



        mTaitouAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                selectPostion=i;
//                Bundle bundle  = new Bundle();
//                bundle.putSerializable(ConstantsKeys.KEY_DATA,dataList.get(i));
//                startActivity(ShopDetialActivity.class,bundle);

                DiscoverStoreData.DataBean.ListBean tem = new DiscoverStoreData.DataBean.ListBean();
                tem.setStore_id(dataList.get(i).getService_id());
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,tem);
                startActivity(ShopDetialActivity.class,bundle);


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
        if(object instanceof DiscoverStoreData)
        {

            DiscoverStoreData mDiscoverStoreData = (DiscoverStoreData)object;
            if(page==1)
            {
                dataList.clear();
            }

            dataList.addAll(mDiscoverStoreData.getData().getList());
        }
        mPullToRefreshRecycle.onRefreshComplete();
        mTaitouAdapter.notifyDataSetChanged();

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
