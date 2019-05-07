package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.module2.ExtensionData;
import com.insthub.cat.android.module2.ExtensionPageListData;
import com.insthub.cat.android.module2.PubuListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.ExtensionAdapter;
import com.insthub.cat.android.ui.adatper.PubuAdapter;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 瀑布吧
 * Created by linux on 2017/6/28.
 */

public class PullListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;


    @Bind(R.id.iv_left)
    ImageView ivLeft;



    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;

    private RecyclerView recyclerview;

    private ArrayList<PubuListData.DataBean.ListBean> dataList = new ArrayList<>();

    private PubuAdapter mExtensionAdapter;

    private int  page=1;
    private int show_count=20;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pubu_list;
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
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(UIUtil.dpToPx(getResources(),5));
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mExtensionAdapter = new PubuAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mExtensionAdapter);
        recyclerview.addItemDecoration(itemDecoration);
        recyclerview.scrollToPosition(0);

        mPullToRefreshRecycle.doPullRefreshing(true,200);

    }

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            if (position % 2 == 0) {
                outRect.left = 0;
                outRect.top = space;
                outRect.right = space / 2;
            } else {
                outRect.left = space / 2;
                outRect.top = space;
                outRect.right = 0;
            }
        }
    }


    @Override
    protected void bindEvent() {
        super.bindEvent();

        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {


                page=1;
                mPresenter.getPubuListData(page,show_count);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                page++;
                mPresenter.getPubuListData(page,show_count);
            }
        });


        mExtensionAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                DiscoverStoreData.DataBean.ListBean tem = new DiscoverStoreData.DataBean.ListBean();
                tem.setStore_id(dataList.get(position).getStore_id());
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,tem);
                Intent intent= new Intent(getActivity(),ShopDetialActivity.class);
                intent.putExtras(bundle);
                startActivityForResults(intent,100);

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


        if (object instanceof PubuListData) {
            PubuListData mPubuListData = (PubuListData) object;

            if(page==1)
            {
                dataList.clear();
            }
            dataList.addAll(mPubuListData.getData().getList());
            mExtensionAdapter.notifyDataSetChanged();
            mPullToRefreshRecycle.onRefreshComplete();
        }


        if (object instanceof ExtensionData) {

            ExtensionData mExtensionData = (ExtensionData)object;
            ToastUtil.show(getContext(),mExtensionData.getMessage());

        }
    }

    @Override
    public void showError(String msg, int code) {

        ToastUtil.show(this,msg);
        dismissLoadDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode==100)
        {

        }
    }
}
