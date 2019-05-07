package com.insthub.cat.android.ui.fragment.LabelFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.common.android.flog.KLog;
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
import com.insthub.cat.android.ui.activity.ShopDetialActivity;
import com.insthub.cat.android.ui.adatper.DiscoverAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;
import com.insthub.cat.android.utils.LocationUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.lankton.flowlayout.FlowLayout;


/**
 * 发现
 * Created by linux on 2017/6/28.
 */

public class LabelFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View,ServiceCallback{

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;

    private RecyclerView recyclerview;

    private ArrayList<DiscoverStoreData.DataBean.ListBean> dataList = new ArrayList<>();

    private ArrayList<DiscoverLabelData.DataBean.LabelLv1Bean.LabelLv2Bean> tagList = new ArrayList<>();

    private DiscoverAdapter mTaitouAdapter;

    private DiscoverLabelData.DataBean.LabelLv1Bean data;

    private int defaultTag=-1;
    private int page=1;
    private int show_count=20;

    private boolean isFresh=false;


    @Bind(R.id.flowView)
    FlowLayout mFlowLayout;

    private BDLocation mBDLocation;



    public static LabelFragment newInstance(DiscoverLabelData.DataBean.LabelLv1Bean data) {
        LabelFragment mainFragment = new LabelFragment();
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

        data =(DiscoverLabelData.DataBean.LabelLv1Bean)getArguments().getSerializable(ConstantsKeys.KEY_DATA);
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
        buildTagList();

    }

    @Override
    protected void bindEvent() {
        super.bindEvent();


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

                isFresh=true;
                page=1;


                String label2 ="";

                if(defaultTag>=0 && defaultTag<data.getLabel_lv2().size())
                {
                    label2=String.valueOf(data.getLabel_lv2().get(defaultTag).getLabel_id());
                }
                KLog.i("label2："+label2);


                if(mBDLocation==null)
                {
                    ToastUtil.show(getContext(),"定位失败请检查网络");
                    mPullToRefreshRecycle.onRefreshComplete();
                    return ;
                }

              //  mPresenter.getStoreByLabel(String.valueOf(data.getLabel_id()),label2,String.valueOf(page),String.valueOf(show_count), mBDLocation.getLongitude(),mBDLocation.getLatitude());



                mPresenter.searchServerList("",mBDLocation.getLatitude(),mBDLocation.getLongitude(),String.valueOf(data.getLabel_id()),label2,page,show_count,"","","");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                page++;
                isFresh=false;
                String label2 ="";

                if(defaultTag>=0 && defaultTag<data.getLabel_lv2().size())
                {
                    label2=String.valueOf(data.getLabel_lv2().get(defaultTag).getLabel_id());
                }

               // mPresenter.getStoreByLabel(String.valueOf(data.getLabel_id()),label2,String.valueOf(page),String.valueOf(show_count), BDLocationManager.getInstance().getCurLocation().getLongitude(),BDLocationManager.getInstance().getCurLocation().getLatitude());

                mPresenter.searchServerList("",mBDLocation.getLatitude(),mBDLocation.getLongitude(),String.valueOf(data.getLabel_id()),label2,page,show_count,"","","");

            }
        });
    }


    @Override
    public void onService(int position) {

        startActivity(ServiceChatActivity.class);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser&&dataList.isEmpty() && mPullToRefreshRecycle!=null)
        {
            mPullToRefreshRecycle.doPullRefreshing(true,200);
        }

    }

    @Override
    protected void bindData() {
        super.bindData();

        if(dataList.isEmpty() && mPullToRefreshRecycle!=null)
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

        if(object instanceof DiscoverStoreData)
        {

            DiscoverStoreData mDiscoverStoreData = (DiscoverStoreData)object;
            if(isFresh)
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

    private void buildTagList()
    {

        mFlowLayout.removeAllViews();

        if(data.getLabel_lv2().size()==0)
        {
            mFlowLayout.setVisibility(View.GONE);
        }else
        {
            mFlowLayout.setVisibility(View.VISIBLE);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(UIUtil.dpToPx(getResources(), 10), 0, UIUtil.dpToPx(getResources(), 10), 0);
            for (int x=0;x<data.getLabel_lv2().size();x++) {
                TextView textView = buildLabel(data.getLabel_lv2().get(x).getLable_name());
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
