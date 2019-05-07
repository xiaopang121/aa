package com.insthub.cat.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.RaiseItemData;
import com.insthub.cat.android.module.TaitouListData;
import com.insthub.cat.android.module2.InvoiceApplyData;
import com.insthub.cat.android.module2.InvoiceTitleListData;
import com.insthub.cat.android.module2.SetDefaultTitleData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.TaitouAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import karics.library.zxing.android.CaptureActivity;


/**
 * 选择抬头
 * Created by linux on 2017/6/28.
 */

public class SelectTaitouActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View, TaitouAdapter.TaitouCallback {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.pull_to_refresh_recycle)
    PullToRefreshRecyclerView mPullToRefreshRecycle;


    @Bind(R.id.tv_input_money)
    EditText tvInputMoney;
    //berth
    private RecyclerView recyclerview;

    private ArrayList<InvoiceTitleListData.DataBean.ListBean> dataList = new ArrayList<>();

    private TaitouAdapter mTaitouAdapter;


    private int selectPositon = -1;
    private  int  defaultPosition=-1;

    private String qrResult ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_select_taitou;
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
        commonTitleBar.setTitle("选择发票抬头");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        commonTitleBar.setRighButtonTitle(R.string.title_sure);
        mPullToRefreshRecycle.setLastUpdatedLabel(TimeUtils.formateTime(System.currentTimeMillis(), TimeUtils.FROAMTE_YMHMS));
        mPullToRefreshRecycle.setPullToRefreshEnabled(true);
        mPullToRefreshRecycle.setMode(PullToRefreshBase.Mode.DISABLED);
        mPullToRefreshRecycle.onRefreshComplete();
        recyclerview = mPullToRefreshRecycle.getRefreshableView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        mTaitouAdapter = new TaitouAdapter(getActivity(), dataList, true);
        recyclerview.setAdapter(mTaitouAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, R.drawable.item_divider_black_3));
        recyclerview.scrollToPosition(0);

        qrResult = getIntent().getExtras().getString("codedContent");
        mPresenter.getMyInvoiceTitle(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());

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
            public void onClick(View view) {


                if (selectPositon == -1) {

                    ToastUtil.show(getActivity(), "请选择抬头");
                    return;
                }


//                if (TextUtils.isEmpty(tvInputMoney.getText().toString()) || tvInputMoney.getText().toString().equals(".")) {
//                    ToastUtil.show(getActivity(), "请输入发票金额");
//                    return;
//                }


                if(TextUtils.isEmpty(tvInputMoney.getText().toString()))
                {

                    tvInputMoney.setText("0");
                }

                showLoadDialog("正在提交开票申请");


                mPresenter.sumbitInvoiceApply(dataList.get(selectPositon).getTitle_id(),CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),qrResult,Float.valueOf(tvInputMoney.getText().toString()));


            }
        });


        mPullToRefreshRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {

            }
        });


        tvInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        mTaitouAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                selectPositon = i;
                mTaitouAdapter.setSelectPositon(i);
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
        if(object instanceof InvoiceTitleListData)
        {

            InvoiceTitleListData data = (InvoiceTitleListData)object;
            dataList.clear();
            dataList.addAll(data.getData().getList());

            for(int x=0;x<dataList.size();x++)
            {
                if(dataList.get(x).getIs_default()==1)
                {
                    selectPositon=x;
                    mTaitouAdapter.setSelectPositon(selectPositon);
                    break;
                }
            }

            mTaitouAdapter.notifyDataSetChanged();

        }
        if(object instanceof SetDefaultTitleData)
        {
            dismissLoadDialog();
            mPresenter.getMyInvoiceTitle(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());
            ToastUtil.show(this,"设置成功");

        }



        if(object instanceof InvoiceApplyData)  //开票
        {

            dismissLoadDialog();
            InvoiceApplyData mInvoiceApplyData = (InvoiceApplyData) object;
            startActivity(PullListActivity.class);
            setResult(BaseActivity.RESULT_OK);
            finish();

        }


    }

    @Override
    public void showError(String msg,int code) {

        dismissLoadDialog();
        mPullToRefreshRecycle.onRefreshComplete();
        ToastUtil.show(getActivity(), msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }

    @Override
    public void onDefault(int positon) {
        showLoadDialog("正在设置默认抬头");
        defaultPosition = positon;
        mPresenter.setDefaultTitle(CacheManager.getInstance().getToken().getData().getUser_id(),
                CacheManager.getInstance().getToken().getData().getToken(),
                dataList.get(positon).getTitle_id());
    }

    @Override
    public void onEdit(int position) {

    }

    @Override
    public void onDelete(int positon) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}
