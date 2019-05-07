package com.insthub.cat.android.ui.fragment.CommentFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.helper.DeleteCommentCallback;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.UserCommentListData;
import com.insthub.cat.android.module2.DeleteCommentData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.CommentAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 评论fragment
 * Created by linux on 2017/6/28.
 */

public class CommentFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View,CommentAdapter.CommentCallback {


    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;

    private RecyclerView recyclerview;
    private ArrayList<UserCommentListData.DataBean.ListBean> dataList = new ArrayList<>();

    private CommentAdapter mCommentAdapter;

    private int page=1;
    private int  showCount =20;


    private int deletePositio=-1;

    public static CommentFragment newInstance() {
        CommentFragment mainFragment = new CommentFragment();
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
        return R.layout.fragment_comment;
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
        mCommentAdapter = new CommentAdapter(getActivity(),CommentFragment.this, dataList);
        recyclerview.setAdapter(mCommentAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));

        mPullToRefreshRecycle.doPullRefreshing(true,200);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();




        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {

                page =1;
                mPresenter.getUserommentList(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),page,showCount);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                //Toast.makeText(MainActivity.this, "上拉加载", Toast.LENGTH_SHORT).show();
                page ++;
                mPresenter.getUserommentList(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),page,showCount);
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

        if(object instanceof  UserCommentListData)
        {
            UserCommentListData mUserCommentListData = (UserCommentListData) object;
            if(page==1)
            {
                dataList.clear();
            }

            dataList.addAll(mUserCommentListData.getData().getList());
            mCommentAdapter.notifyDataSetChanged();
        }



        if(object instanceof DeleteCommentData)
        {

            ToastUtil.show(getContext(),"删除成功");
            if(deletePositio!=-1)
            {
                dataList.remove(deletePositio);
                deletePositio=-1;
                mCommentAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        mPullToRefreshRecycle.onRefreshComplete();
        ToastUtil.show(getActivity(), msg);
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
    public void onDelete(int positon) {

     final   Dialog dialog =  DialogUtils.showDeleteCommentDialog(getContext(), new DeleteCommentCallback() {
            @Override
            public void onClickSure(Dialog dialog ,int position) {
                deletePositio = position;
                 dialog.dismiss();
                 showLoadDialog("正在删除评价！");
                mPresenter.deleteUserComment(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken(),dataList.get(position).getEvaluate_id());
            }
        },positon);

        dialog.show();
    }
}
