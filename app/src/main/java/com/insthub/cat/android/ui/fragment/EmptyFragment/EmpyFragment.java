package com.insthub.cat.android.ui.fragment.EmptyFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.ServiceCallback;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.activity.ServiceChatActivity;
import com.insthub.cat.android.ui.adatper.DiscoverAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.lankton.flowlayout.FlowLayout;


/**
 * 发现
 * Created by linux on 2017/6/28.
 */

public class EmpyFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View,ServiceCallback{




    public static EmpyFragment newInstance(DiscoverLabelData.DataBean.LabelLv1Bean data) {
        EmpyFragment mainFragment = new EmpyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantsKeys.KEY_DATA,data);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_labe;
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


    }

    @Override
    protected void bindEvent() {
        super.bindEvent();


    }


    @Override
    public void onService(int position) {

        startActivity(ServiceChatActivity.class);
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
    }

}
