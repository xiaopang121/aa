package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.event.LocationUpdateEvent;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.manager.DBManager;
import com.insthub.cat.android.module2.City;
import com.insthub.cat.android.module2.NewsListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.InfoAdapter;
import com.insthub.cat.android.ui.adatper.ResultListAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 街道选择
 * Created by linux on 2017/6/28.
 */

public class SelectStreatListActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.bt_right)
    Button btRight;
    @Bind(R.id.rll_right_content)
    RelativeLayout rllRightContent;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.listview_search_result)
    ListView listviewSearchResult;

    private ResultListAdapter mResultAdapter;

    private List<City> mAllCities=new ArrayList<>();

    private DBManager dbManager;


    private String city;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_select_streatcitys;
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

        city =getIntent().getExtras().getString(ConstantsKeys.KEY_DATA);

        city = city.replace("市","");
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        ivLeft.setImageResource(R.drawable.ic_arrow_back_white_24dp);
        tvTitle.setText("选择区域");

        mResultAdapter = new ResultListAdapter(this,mAllCities);

        listviewSearchResult.setAdapter(mResultAdapter);

        dbManager = new DBManager(getContext());
        mAllCities.clear();
        mAllCities.addAll( dbManager.searchDistarct(city));
        mResultAdapter.notifyDataSetChanged();
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



        listviewSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                BDLocationManager.getInstance().setLoationInfo(mAllCities.get(position));

                RxBusManager.getInstance().post(new LocationUpdateEvent());

                setResult(Activity.RESULT_OK);

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


    }


    @Override
    public void showError(String msg, int code) {
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
