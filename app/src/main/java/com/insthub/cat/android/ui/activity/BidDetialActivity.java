package com.insthub.cat.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.LegalUserHelper;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.AnnexBean;
import com.insthub.cat.android.module.BidPriceDetialData;
import com.insthub.cat.android.module.BidPriceListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.adatper.DocumentAdapter;
import com.insthub.cat.android.ui.widget.SpacesItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 竞价详情
 * Created by linux on 2017/6/28.
 */

public class BidDetialActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;


    @Bind(R.id.recycler_view)
    public RecyclerView recyclerview;


    @Bind(R.id.lly_buttom_view)
    LinearLayout llyButtomView;

    private ArrayList<AnnexBean> dataList = new ArrayList<>();

    private DocumentAdapter mDocumentAdapter;


    private BidPriceListData.DataBean.ListBean mBidPricteItem;

    private BidPriceDetialData mBidPriceDetialData;


    @Bind(R.id.tv_bid_title)
    TextView tvTitle;

    @Bind(R.id.tv_date_time)
    TextView tvDateTime;


    @Bind(R.id.tv_city)
    TextView tvCity;

    @Bind(R.id.tv_categary)
    TextView tvCategary;

    //报名
    @Bind(R.id.tv_enroll)
    TextView tvEnroll;

    @Bind(R.id.tv_last_day)
    TextView tvLateTime;

    @Bind(R.id.tv_detial)
    TextView tvDetial;


    //报价要求
    @Bind(R.id.tv_bjyq)
    TextView tvBjyq;


    @Bind(R.id.tv_tran_type)
    TextView tvTranType;

    @Bind(R.id.tv_pay_type)
    TextView tvPayType;

    @Bind(R.id.tv_invoite_type)
    TextView tvInvoiteType;

    @Bind(R.id.tv_business_license)
    TextView tvBusinessLicense;


    @Bind(R.id.tv_task_info)
    TextView tvTaskInfo;


    @Bind(R.id.tv_must_ask)
    TextView tvMustAsk;

    @Bind(R.id.tv_must_bid)
    TextView tvMustBid;

    private int from;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_bid_detial;
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
        from = getIntent().getExtras().getInt(ConstantsKeys.KEY_FROM);
        mBidPricteItem = (BidPriceListData.DataBean.ListBean) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        commonTitleBar.setTitle("竞价详情");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        commonTitleBar.setRighButtonTitle("编辑");


        if (from == 1) {
            commonTitleBar.btRight.setVisibility(View.GONE);
            llyButtomView.setVisibility(View.VISIBLE);
        }

        if (from == 2) {
            commonTitleBar.btRight.setVisibility(View.VISIBLE);
            llyButtomView.setVisibility(View.GONE);
        }

        //精选
        GridLayoutManager jxlayoutManager = new GridLayoutManager(getActivity(), 2);
        SpacesItemDecoration jx2layoutManager = new SpacesItemDecoration(UIUtil.dpToPx(getResources(), 5));
        recyclerview.setLayoutManager(jxlayoutManager);
        recyclerview.setHasFixedSize(true);
        mDocumentAdapter = new DocumentAdapter(getActivity(), dataList);
        recyclerview.setAdapter(mDocumentAdapter);
        recyclerview.addItemDecoration(jx2layoutManager);
        recyclerview.scrollToPosition(0);

        loadata();


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

                if (mBidPricteItem.getJoin_count() == 0) {
                    if (LegalUserHelper.isLegalUserStatus(getActivity())) {

                        Bundle bundle = new Bundle();
                        bundle.putSerializable(ConstantsKeys.KEY_DATA, mBidPricteItem);

                        Intent intent = new Intent(getActivity(), PublishBidActivity.class);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 100);
                    }
                } else {
                    DialogUtils.showPromptDialog(getActivity()).show();
                }


            }
        });


        tvMustAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CacheManager.getInstance().getToken() == null) {
                    ToastUtil.show(getActivity(), "请先登录后再操作");
                    startActivity(WxLoginActivity.class);
                    return;
                }

                SessionHelper.startP2PSession(getActivity(), mBidPriceDetialData.getData().getAccid());
            }
        });


        tvMustBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (LegalUserHelper.isLegalUserStatus(getActivity())) {


                    if (CacheManager.getInstance().getUserInfo().getData().getHas_store() == 0) {
                        ToastUtil.show(getContext(), "请前往电脑端先开铺在操作");
                        return;
                    }

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ConstantsKeys.KEY_DATA, mBidPriceDetialData);
                    startActivityForResult(BidMustActivity.class, bundle, 100);
                }

            }
        });


        tvEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ConstantsKeys.KEY_DATA, mBidPriceDetialData);
                    startActivityForResult(BidMustActivity.class, bundle, 100);
                }
            }
        });


        mDocumentAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA, dataList.get(position));
                startActivity(ShowPdfActivity.class, bundle);
            }
        });


//        mTaitouAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
//
//                Intent intent = new Intent();
//
//                Bundle bundle =new Bundle();
//                intent.putExtra(ConstantsKeys.KEY_DATA,dataList.get(position));
//                intent.putExtras(bundle);
//                setResult(Activity.RESULT_OK,intent);
//                finish();
//            }
//        });

    }


    private void loadata() {


        showLoadDialog("正在查询详情");

        mPresenter.getJingjiaDetial(mBidPricteItem.getTender_id());

    }


    @Override
    protected void bindData() {
        super.bindData();
    }


    @Override
    public void onResume() {
        super.onResume();


    }


    private void updateInfo() {
        if (mBidPriceDetialData != null) {


            tvTitle.setText(mBidPriceDetialData.getData().getTitle());
            tvDateTime.setText(mBidPriceDetialData.getData().getCreate_time());
            tvCity.setText(mBidPriceDetialData.getData().getCity());
            tvCategary.setText(mBidPriceDetialData.getData().getService());
            long createTime = TimeUtils.parserTime(mBidPriceDetialData.getData().getEnd_time(), TimeUtils.FROAMTE_YMHMS);
            long l = createTime - System.currentTimeMillis();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

            tvLateTime.setText("距离截止日期还剩下" + day + "天" + hour + "小时");
            tvDetial.setText(mBidPriceDetialData.getData().getDetail());

            String[] taxArray = getResources().getStringArray(R.array.tax_array);
            String[] freightArray = getResources().getStringArray(R.array.freight_array);
            tvBjyq.setText(taxArray[mBidPriceDetialData.getData().getInclude_tax()] + "        " + freightArray[mBidPriceDetialData.getData().getInclude_freight()]);

            String[] tranArray = getResources().getStringArray(R.array.trans_array);
            tvTranType.setText(tranArray[mBidPriceDetialData.getData().getTrans_type()]);


            String[] payArray = getResources().getStringArray(R.array.pay_array);
            tvPayType.setText(payArray[mBidPriceDetialData.getData().getPay_type()]);


            String[] invoiceArray = getResources().getStringArray(R.array.invoice_array);
            tvInvoiteType.setText(invoiceArray[mBidPriceDetialData.getData().getInvoice_type()]);

            String[] businessLicenseArray = getResources().getStringArray(R.array.business_license_array);
            tvBusinessLicense.setText(businessLicenseArray[mBidPriceDetialData.getData().getBusiness_license()]);


            tvTaskInfo.setText(mBidPriceDetialData.getData().getAddress());

            dataList.clear();

            dataList.addAll(mBidPriceDetialData.getData().getAnnex());

            for (int x = 0; x < dataList.size(); x++) {
                if (TextUtils.isEmpty(dataList.get(x).getFile_name()) || dataList.get(x).getFile_name().contains("null") || dataList.get(x).getUrl().contains("null")) {
                    dataList.remove(x);
                    x--;
                }
            }
            mDocumentAdapter.notifyDataSetChanged();

        }
    }


    @Override
    public void showSuccess(Object object) {


        if (object instanceof BidPriceDetialData) {

            mBidPriceDetialData = (BidPriceDetialData) object;
            updateInfo();

            dismissLoadDialog();

        }


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

        loadata();
    }
}
