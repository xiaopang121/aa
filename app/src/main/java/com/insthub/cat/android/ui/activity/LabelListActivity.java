package com.insthub.cat.android.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.module2.DiscoverStoreData;
import com.insthub.cat.android.module2.NearbyData;
import com.insthub.cat.android.module2.SameLevelLabelData;
import com.insthub.cat.android.module2.SortData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.DiscoverAdapter;
import com.insthub.cat.android.ui.adatper.ListDropDownAdapter;
import com.insthub.cat.android.ui.adatper.ListDropDownAdapter2;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.ui.widget.DropDownMenu;
import com.insthub.cat.android.utils.LocationUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 更多
 * Created by linux on 2017/6/28.
 */

public class LabelListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {



    @Bind(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    View contentView;

    PullToRefreshRecyclerView mPullToRefreshRecycle;
    private RecyclerView recyclerview;

    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;

    private ArrayList<DiscoverStoreData.DataBean.ListBean> dataList = new ArrayList<>();
    private DiscoverAdapter mTaitouAdapter;

    DiscoverLabelData.DataBean.LabelLv1Bean.LabelLv2Bean childData;

    private List<View> popupViews = new ArrayList<>();


    private String  headers[]=new String[]{"分类","附近","排序"};


    private ListDropDownAdapter sameAdapter;
    private SameLevelLabelData mSameLevelLabelData;
    private LinkedList<String> sameList = new LinkedList<>();
    private int selectSamePostion=0;



    private NearbyData mNearbyData;
    private List<String> distanceList = new ArrayList<>();
    private List<String> areaList = new ArrayList<>();
    private int selectDistancePostion =0;
    private int selectAreaPosition =0;

    private ListDropDownAdapter distanceAdapter;
    private ListDropDownAdapter2 areaAdapter;
    private TextView tvNearBy;
    private RecyclerView rvCityList;
    private RecyclerView rvDistanceList;


    private ListDropDownAdapter sortAdapter;
    private SortData mSortData;
    private List<String> sortList = new ArrayList<>();
    private int selectSortPostion=0;


    private int page=1;
    private int show_count=20;
    private double lat,lng;
    private String label_id2="";

    private String area,distancez,sort_rule;

    private boolean isInit=false;



    @Override
    protected int getLayoutResId() {
        return R.layout.acitivity_label_list;
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

        childData = (DiscoverLabelData.DataBean.LabelLv1Bean.LabelLv2Bean)getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);

        label_id2 = childData.getLabel_id()+"";
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();

        contentView = LayoutInflater.from(this).inflate(R.layout.layout_content,null);
        mPullToRefreshRecycle = (PullToRefreshRecyclerView) contentView.findViewById(R.id.pull_to_refresh_recycle);
        mPullToRefreshRecycle.setLastUpdatedLabel(TimeUtils.formateTime(System.currentTimeMillis(), TimeUtils.FROAMTE_YMHMS));
        mPullToRefreshRecycle.setPullToRefreshEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
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





        //分类
        RecyclerView  rvLeaseView = new RecyclerView(this);
        rvLeaseView.setBackgroundColor(Color.WHITE);
        sameAdapter = new ListDropDownAdapter(this,sameList);
        LinearLayoutManager leaseManager = new LinearLayoutManager(getActivity());
        leaseManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvLeaseView.setLayoutManager(leaseManager);
        rvLeaseView.setHasFixedSize(true);
        rvLeaseView.setAdapter(sameAdapter);
        rvLeaseView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black));
        rvLeaseView.scrollToPosition(0);



        View  nearByView = LayoutInflater.from(getContext()).inflate(R.layout.layout_popup_nearby,null);

        tvNearBy=nearByView.findViewById(R.id.tv_nearby);
        rvDistanceList = nearByView.findViewById(R.id.rv_distrance_list);
        rvCityList = nearByView.findViewById(R.id.rv_city_list);

        //距离
        //rvDistanceList = new RecyclerView(this);
        rvDistanceList.setBackgroundColor(Color.WHITE);
        distanceAdapter = new ListDropDownAdapter(this, distanceList);
        LinearLayoutManager sizeManager = new LinearLayoutManager(getActivity());
        sizeManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDistanceList.setLayoutManager(sizeManager);
        rvDistanceList.setHasFixedSize(true);
        rvDistanceList.setAdapter(distanceAdapter);
        rvDistanceList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black));
        rvDistanceList.scrollToPosition(0);

        //区域
        rvCityList.setBackgroundColor(Color.WHITE);
        areaAdapter = new ListDropDownAdapter2(this, areaList);
        LinearLayoutManager cityManager = new LinearLayoutManager(getActivity());
        sizeManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCityList.setLayoutManager(cityManager);
        rvCityList.setHasFixedSize(true);
        rvCityList.setAdapter(areaAdapter);
        rvCityList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black));
        rvCityList.scrollToPosition(0);



        //价格
        RecyclerView  rvPriceView = new RecyclerView(this);
        rvPriceView.setBackgroundColor(Color.WHITE);
        sortAdapter = new ListDropDownAdapter(this,sortList);
        LinearLayoutManager priceManager = new LinearLayoutManager(getActivity());
        priceManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPriceView.setLayoutManager(priceManager);
        rvPriceView.setHasFixedSize(true);
        rvPriceView.setAdapter(sortAdapter);
        rvPriceView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black));
        rvPriceView.scrollToPosition(0);

        popupViews.add(rvLeaseView);
        popupViews.add(nearByView);
        popupViews.add(rvPriceView);

        lat = BDLocationManager.getInstance().getCurLocation().getLatitude();
        lng = BDLocationManager.getInstance().getCurLocation().getLongitude();

        BDLocationManager.getInstance().addCallback(mCityNameStatus);



        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);

        //获取同级参数
        mPresenter.getSameLevelLabel(childData.getLabel_id()+"");

        //获取附近参数
        mPresenter.getNearyByParam(BDLocationManager.getInstance().getCurLocation().getLatitude(),BDLocationManager.getInstance().getCurLocation().getLongitude());

        mPresenter.getSortRule();


        mPullToRefreshRecycle.doPullRefreshing(true,100);

    }

    @Override
    protected void bindEvent() {
        super.bindEvent();


        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
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




        sameAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                selectSamePostion = i;

                if(i==0)
                {
                    mDropDownMenu.setTabText(sameList.get(0));
                    label_id2="";
                }else
                {

                    label_id2 =String.valueOf( mSameLevelLabelData.getData().getList().get(i-1).getLabel_id());
                    mDropDownMenu.setTabText(mSameLevelLabelData.getData().getList().get(i-1).getLable_name());
                }

                mDropDownMenu.closeMenu();
                mPullToRefreshRecycle.doPullRefreshing(true,200);
            }
        });



        areaAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                selectAreaPosition =i;
                area  =mNearbyData.getData().getList_area().get(i);
                mDropDownMenu.setTabText(areaList.get(i));
                mDropDownMenu.closeMenu();
                mPullToRefreshRecycle.doPullRefreshing(true,200);
            }
        });

        distanceAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                selectDistancePostion =i;
                distancez = mNearbyData.getData().getList_distance().get(i).getParam_key();
                mDropDownMenu.setTabText(distanceList.get(i));
                mDropDownMenu.closeMenu();
                mPullToRefreshRecycle.doPullRefreshing(true,200);
            }
        });



        sortAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                selectSortPostion =i;
                sort_rule = mSortData.getData().getList().get(i).getSort_rule_key();
                mDropDownMenu.setTabText(sortList.get(i));
                mDropDownMenu.closeMenu();
                mPullToRefreshRecycle.doPullRefreshing(true,200);
            }
        });



        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
                 page=1;
                loadata();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {

                 page++;
                loadata();
            }
        });

    }



    private void loadata()
    {
        mPresenter.searchServerList("",lat,lng,childData.getParent_id()+"",label_id2,page,show_count,area,distancez,sort_rule);
    }



    @Override
    protected void bindData() {
        super.bindData();

        mPresenter.getDiscoverLabel();
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void showSuccess(Object object) {



        if (object instanceof SameLevelLabelData) {

            mSameLevelLabelData = (SameLevelLabelData) object;
            sameList.clear();
            int totalSize=0;
            for(int x=0;x<mSameLevelLabelData.getData().getList().size();x++)
            {
                sameList.add(new String(mSameLevelLabelData.getData().getList().get(x).getLable_name()+" ("+mSameLevelLabelData.getData().getList().get(x).getStore_count()+")"));
                totalSize = totalSize+mSameLevelLabelData.getData().getList().get(x).getStore_count();
            }
            sameList.add(0,new String("全部 ("+totalSize+")"));
            sameAdapter.notifyDataSetChanged();
        }


        if(object instanceof NearbyData)
        {
            mNearbyData = (NearbyData)object;
            distanceList.clear();

            for(int x=0;x<mNearbyData.getData().getList_distance().size();x++)
            {
                distanceList.add(new String(mNearbyData.getData().getList_distance().get(x).getParam_value()));
            }
            distanceAdapter.notifyDataSetChanged();

            areaList.clear();
            areaList.addAll(mNearbyData.getData().getList_area());


        }


        if(object instanceof SortData)
        {
            mSortData = (SortData)object;

            for(int x=0;x<mSortData.getData().getList().size();x++)
            {
                sortList.add(new String(mSortData.getData().getList().get(x).getSort_rule_value()));
            }
            sortAdapter.notifyDataSetChanged();
        }



        if(object instanceof DiscoverStoreData)
        {

            DiscoverStoreData mDiscoverStoreData = (DiscoverStoreData)object;
            if(page==1)
            {
                dataList.clear();
            }

            dataList.addAll(mDiscoverStoreData.getData().getList());
            mPullToRefreshRecycle.onRefreshComplete();
            mTaitouAdapter.notifyDataSetChanged();
        }




    }

    @Override
    public void showError(String msg, int code) {

        mPullToRefreshRecycle.onRefreshComplete();
        dismissLoadDialog();
        ToastUtil.show(getActivity(), msg);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 定位回调
     */
    private LocationUtils.CityNameStatus mCityNameStatus = new LocationUtils.CityNameStatus() {

        @Override
        public void detecting() {

        }

        @Override
        public void update(String parmaCity, BDLocation aMapLocation, BDLocation location) {

            lat = location.getLatitude();
            lng = location.getLongitude();

        }
    };

}
