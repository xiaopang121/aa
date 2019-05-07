package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
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
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.DiscoverAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.utils.LocationUtils;

import java.util.ArrayList;

import butterknife.Bind;
import cn.lankton.flowlayout.FlowLayout;


/**
 * 推广
 * Created by linux on 2017/6/28.
 */

public class IndexTuiguangActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View,ServiceCallback{

    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;


    @Bind(R.id.tv_search)
    TextView tvSearch;


    private RecyclerView recyclerview;

    private ArrayList<DiscoverStoreData.DataBean.ListBean> dataList = new ArrayList<>();

    private ArrayList<DiscoverLabelData.DataBean.LabelLv1Bean.LabelLv2Bean> tagList = new ArrayList<>();

    private DiscoverAdapter mTaitouAdapter;

    private DiscoverLabelData mDiscoverLabelData;

    private int defaultTag=-1;
    private int page=1;
    private int show_count=20;



    @Bind(R.id.flowView)
    FlowLayout mFlowLayout;

    private BDLocation mBDLocation;

    private HomeData.DataBean.ServiceTypeListBean  mServiceTypeListBean;


    @Override
    protected int getLayoutResId() {
        return R.layout.acitivity_index_tuiguang;
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
        mServiceTypeListBean = (HomeData.DataBean.ServiceTypeListBean)getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        commonTitleBar.setTitle(mServiceTypeListBean.getName());
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
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
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));
        recyclerview.scrollToPosition(0);

        BDLocationManager.getInstance().addCallback(mCityNameStatus);
        mBDLocation =  BDLocationManager.getInstance().getCurLocation();



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


        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ShopSearchActivity.class);
            }
        });

        mTaitouAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {


                DiscoverStoreData.DataBean.ListBean tem = new DiscoverStoreData.DataBean.ListBean();
                tem.setStore_id(dataList.get(i).getService_id());
                Bundle bundle  = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,tem);
                startActivity(ShopDetialActivity.class,bundle);
            }
        });


        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {


                page=1;

                String label2 ="";

                if(defaultTag>=0 && defaultTag<tagList.size())
                {
                    label2=String.valueOf(tagList.get(defaultTag).getLabel_id());
                }
                KLog.i("label2："+label2);


                if(mBDLocation==null)
                {
                    ToastUtil.show(getContext(),"定位失败请检查网络");
                    mPullToRefreshRecycle.onRefreshComplete();
                    return ;
                }

             //   mPresenter.getStoreByLabel(String.valueOf(mServiceTypeListBean.getLabel_id()),label2,String.valueOf(page),String.valueOf(show_count), mBDLocation.getLongitude(),mBDLocation.getLatitude());

                mPresenter.searchServerList("",mBDLocation.getLatitude(),mBDLocation.getLongitude(),String.valueOf(mServiceTypeListBean.getLabel_id()),label2,page,show_count,"","","");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                page++;

                String label2 ="";

                if(defaultTag>=0 && defaultTag<tagList.size())
                {
                    label2=String.valueOf(tagList.get(defaultTag).getLabel_id());
                }

               // mPresenter.getStoreByLabel(String.valueOf(mServiceTypeListBean.getLabel_id()),label2,String.valueOf(page),String.valueOf(show_count), BDLocationManager.getInstance().getCurLocation().getLongitude(),BDLocationManager.getInstance().getCurLocation().getLatitude());

                mPresenter.searchServerList("",mBDLocation.getLatitude(),mBDLocation.getLongitude(),String.valueOf(mServiceTypeListBean.getLabel_id()),label2,page,show_count,"","","");
            }
        });
    }


    @Override
    public void onService(int position) {

        startActivity(ServiceChatActivity.class);
    }

    @Override
    protected void bindData() {
        super.bindData();

        mPresenter.getDiscoverLabel();

//        if(dataList.isEmpty() && mPullToRefreshRecycle!=null)
//        {
//            mPullToRefreshRecycle.doPullRefreshing(true,200);
//        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void showSuccess(Object object) {


        if(object instanceof DiscoverLabelData)
        {
            mDiscoverLabelData = (DiscoverLabelData)object;


            for(int x=0;x<mDiscoverLabelData.getData().getLabel_lv1().size();x++)
            {
                if(mDiscoverLabelData.getData().getLabel_lv1().get(x).getLabel_id()==mServiceTypeListBean.getLabel_id())
                {
                    tagList.clear();
                    tagList.addAll(mDiscoverLabelData.getData().getLabel_lv1().get(x).getLabel_lv2());
                    buildTagList();
                    break;
                }
            }

            mPullToRefreshRecycle.doPullRefreshing(true,200);

        }


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

        mPullToRefreshRecycle.onRefreshComplete();
        dismissLoadDialog();
        ToastUtil.show(getActivity(),msg);
    }


    private void buildTagList()
    {

        mFlowLayout.removeAllViews();

        if(tagList.size()==0)
        {
            mFlowLayout.setVisibility(View.GONE);
        }else
        {
            mFlowLayout.setVisibility(View.VISIBLE);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(UIUtil.dpToPx(getResources(), 10), 0, UIUtil.dpToPx(getResources(), 10), 0);
            for (int x=0;x<tagList.size();x++) {
                TextView textView = buildLabel(tagList.get(x).getLable_name());
                textView.setTag(x);
                textView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        int item = (int) arg0.getTag();
                        defaultTag = item;
                        buildTagList();

                        mPullToRefreshRecycle.doPullRefreshing(true,200);
                    }
                });
                if(x==defaultTag)
                {
                    textView.setBackgroundResource(R.drawable.label_bg);
                    textView.setTextColor(getResources().getColor(R.color.white));
                }else {
                    textView.setBackgroundResource(R.drawable.label_bg_normal);
                    textView.setTextColor(getResources().getColor(R.color.B_black_70));
                }
                mFlowLayout.addView(textView,lp);
            }
        }

    }


    private TextView buildLabel(String text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textView.setPadding(UIUtil.dpToPx(getResources(), 5),
                (int) UIUtil.dpToPx(getResources(), 5),
                (int) UIUtil.dpToPx(getResources(), 10),
                (int) UIUtil.dpToPx(getResources(), 5));

        return textView;
    }


    /**
     * 定位回调
     */
    private LocationUtils.CityNameStatus mCityNameStatus = new LocationUtils.CityNameStatus() {

        @Override
        public void detecting() {

        }

        @Override
        public void update(String parmaCity, BDLocation aMapLocation,BDLocation location2) {

            mBDLocation = location2;
            if(aMapLocation!=null)
            {
                mBDLocation = aMapLocation;
            }

//            if(dataList.isEmpty())
//            {
//                mPullToRefreshRecycle.doPullRefreshing(true,200);
//            }


        }
    };

}
