package com.insthub.cat.android.ui.fragment.ShopIndexFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.DeleteCommentCallback;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.DeleteCommentData;
import com.insthub.cat.android.module2.ShopDetialData;
import com.insthub.cat.android.module2.UserCommentListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.CommentAdapter;
import com.insthub.cat.android.ui.adatper.ShopIndexAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 评论fragment
 * Created by linux on 2017/6/28.
 */

public class ShopIndexFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View {

    @Bind(R.id.rv_index_menu)
    RecyclerView recyclerview;

    private View headView;
    TextView tvDesc;
    private ArrayList<String> dataList = new ArrayList<>();
    private ShopIndexAdapter mCommentAdapter;
    private ShopDetialData mShopDetialData;


    public static ShopIndexFragment newInstance(ShopDetialData data) {
        ShopIndexFragment mainFragment = new ShopIndexFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantsKeys.KEY_DATA,data);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShopDetialData = (ShopDetialData)getArguments().getSerializable(ConstantsKeys.KEY_DATA);

    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_shop_index;
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(false);
        mCommentAdapter = new ShopIndexAdapter(getActivity(),dataList);
        recyclerview.setAdapter(mCommentAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_transt));


        headView = LayoutInflater.from(getContext()).inflate(R.layout.layout_renqi_head,null);
        tvDesc = headView.findViewById(R.id.tv_desc);
        tvDesc.setText(mShopDetialData.getData().getDesc_detail());
        mCommentAdapter.addHeaderView(headView);


        if(dataList.isEmpty())
        {
            dataList.addAll(mShopDetialData.getData().getDesc_image_list());
            mCommentAdapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();


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

    }

}
