package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
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
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.DeleteMyShopData;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.module2.MyStoreData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.LabelAdapter;
import com.insthub.cat.android.ui.adatper.ShopAdapter;
import com.insthub.cat.android.ui.dialog.CommonDialog;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 店铺类别
 * Created by linux on 2017/6/28.
 */

public class ShopCategoryListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View,LabelAdapter.LabelCallback {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;

    //berth
    private RecyclerView recyclerview;

    private ArrayList<DiscoverLabelData.DataBean.LabelLv1Bean> dataList = new ArrayList<>();

    private LabelAdapter mShopAdapter;

    private DiscoverLabelData mDiscoverLabelData;

    private int currentSelectPosition=-1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_select_shop_categary;
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
        commonTitleBar.setTitle("选择开店类别");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        commonTitleBar.setRighButtonTitle(R.string.title_finish);


        mPullToRefreshRecycle.setLastUpdatedLabel(TimeUtils.formateTime(System.currentTimeMillis(), TimeUtils.FROAMTE_YMHMS));
        mPullToRefreshRecycle.setPullToRefreshEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.DISABLED);
        mPullToRefreshRecycle.onRefreshComplete();
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mShopAdapter = new LabelAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mShopAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));
        recyclerview.scrollToPosition(0);

        mPresenter.getDiscoverLabel();
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

        commonTitleBar.btRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(mDiscoverLabelData==null)
                {
                    return;
                }
                if(currentSelectPosition==-1)
                {
                    ToastUtil.show(getActivity(),"请选择开店类别");
                    return ;
                }

                boolean hasSelect=false;
                for (int x=0;x<mDiscoverLabelData.getData().getLabel_lv1().get(currentSelectPosition).getLabel_lv2().size();x++)
                {

                    if( mDiscoverLabelData.getData().getLabel_lv1().get(currentSelectPosition).getLabel_lv2().get(x).isSelect())
                    {
                        hasSelect=true;
                        break;
                    }
                }

                if(!hasSelect)
                {
                    ToastUtil.show(getActivity(),"请选择开店类别");
                    return ;
                }

                Intent intent  = new Intent();
                intent.putExtra(ConstantsKeys.KEY_DATA, mDiscoverLabelData.getData().getLabel_lv1().get(currentSelectPosition));
                setResult(Activity.RESULT_OK,intent);
                finish();

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
        if(object instanceof DiscoverLabelData)
        {
            mDiscoverLabelData = (DiscoverLabelData)object;
            dataList.clear();
            dataList.addAll(mDiscoverLabelData.getData().getLabel_lv1());
            mShopAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void showError(String msg,int code) {
        mPullToRefreshRecycle.onRefreshComplete();
        ToastUtil.show(this,msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    public void onClickEdit(int postion,int label1 ) {

        if(mDiscoverLabelData.getData().getLabel_lv1().get(postion).getLabel_lv2().get(label1).isSelect())
        {
            mDiscoverLabelData.getData().getLabel_lv1().get(postion).getLabel_lv2().get(label1).setSelect(false);
        }else
        {
            mDiscoverLabelData.getData().getLabel_lv1().get(postion).getLabel_lv2().get(label1).setSelect(true);

            if(postion!=currentSelectPosition)
            {
                if(currentSelectPosition>=0)
                {
                    for (int x=0;x<mDiscoverLabelData.getData().getLabel_lv1().get(currentSelectPosition).getLabel_lv2().size();x++)
                    {
                        mDiscoverLabelData.getData().getLabel_lv1().get(currentSelectPosition).getLabel_lv2().get(x).setSelect(false);
                    }
                }
                currentSelectPosition=postion;
            }
        }


        mShopAdapter.notifyDataSetChanged();

    }
}
