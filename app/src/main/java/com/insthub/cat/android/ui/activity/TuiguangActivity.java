package com.insthub.cat.android.ui.activity;

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

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.AndroidBug54971Workaround;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.StrUtil;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.ExtensionData;
import com.insthub.cat.android.module2.ExtensionPageListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.ExtensionAdapter;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 推广
 * Created by linux on 2017/6/28.
 */

public class TuiguangActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;


    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;
    @Bind(R.id.et_mobile)
    EditText etMobile;
    @Bind(R.id.bt_sumbit)
    Button btSumbit;

    private RecyclerView recyclerview;

    private ArrayList<ExtensionPageListData.DataBean.ListBean> dataList = new ArrayList<>();

    private ExtensionAdapter mExtensionAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        AndroidBug54971Workaround.assistActivity(this);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tuiguang;
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
        commonTitleBar.setTitle(R.string.title_tuiguang);
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        mPullToRefreshRecycle.setLastUpdatedLabel(TimeUtils.formateTime(System.currentTimeMillis(), TimeUtils.FROAMTE_YMHMS));
        mPullToRefreshRecycle.setPullToRefreshEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.DISABLED);
        mPullToRefreshRecycle.onRefreshComplete();
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(UIUtil.dpToPx(getResources(),10));
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mExtensionAdapter = new ExtensionAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mExtensionAdapter);
        recyclerview.addItemDecoration(itemDecoration);
        recyclerview.scrollToPosition(0);
        mPresenter.getExtensionPage();
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

        commonTitleBar.ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        btSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(etMobile.getText().toString()) || etMobile.getText().toString().length()!=11)
                {
                    ToastUtil.show(getActivity(),"请输入手机号码");
                    return ;
                }


//               if(!StrUtil.isMobileNo(etMobile.getText().toString()))
//               {
//                   ToastUtil.show(getActivity(),"请输入正确的手机号码");
//                   return ;
//               }


               showLoadDialog("正在提交数据");
                mPresenter.sumbitExtension(etMobile.getText().toString());

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
        if (object instanceof ExtensionPageListData) {
            ExtensionPageListData mExtensionPageListData = (ExtensionPageListData) object;
            dataList.clear();
            dataList.addAll(mExtensionPageListData.getData().getList());
            mExtensionAdapter.notifyDataSetChanged();
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


}
