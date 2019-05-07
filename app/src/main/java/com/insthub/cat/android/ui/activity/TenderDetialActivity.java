package com.insthub.cat.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.LegalUserHelper;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.AnnexBean;
import com.insthub.cat.android.module.BidPriceDetialData;
import com.insthub.cat.android.module.TenderDetialData;
import com.insthub.cat.android.module.TenderListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.adatper.DocumentAdapter;
import com.insthub.cat.android.ui.widget.SpacesItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;
import com.insthub.cat.android.utils.Utils;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 招标详情
 * Created by linux on 2017/6/28.
 */

public class TenderDetialActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;


    @Bind(R.id.recycler_view)
    public RecyclerView recyclerview;
    @Bind(R.id.tv_bid_title)
    TextView tvBidTitle;


    @Bind(R.id.tv_date_time)
    TextView tvDateTime;

    @Bind(R.id.tv_city)
    TextView tvCity;

    @Bind(R.id.tv_type)
    TextView tvType;

    @Bind(R.id.tv_categary)
    TextView tvCategary;



    @Bind(R.id.tv_project_gaikuang)
    TextView tvProjectGaikuang;

    @Bind(R.id.tv_project_tbryaoqiu)
    TextView tvProjectTbryaoqiu;

    @Bind(R.id.tv_project_endtime)
    TextView tvProjectEndtime;

    @Bind(R.id.tv_project_starttime)
    TextView tvProjectStarttime;

    @Bind(R.id.tv_project_ticket)
    TextView tvProjectTicket;

    @Bind(R.id.tv_project_price)
    TextView tvProjectPrice;

    @Bind(R.id.tv_project_address)
    TextView tvProjectAddress;

    @Bind(R.id.tv_project_contract)
    TextView tvProjectContract;

    @Bind(R.id.tv_project_phone)
    TextView tvProjectPhone;

    @Bind(R.id.tv_project_detial)
    TextView tvProjectDetial;

    private ArrayList<AnnexBean> dataList = new ArrayList<>();

    private DocumentAdapter mDocumentAdapter;


    private TenderListData.DataBean.ListBean mBidPricteItem;

    private TenderDetialData mBidPriceDetialData;


    @Bind(R.id.tv_must_ask)
    TextView tvMustAsk;

    @Bind(R.id.tv_must_bid)
    TextView tvMustBid;
    @Bind(R.id.lly_buttom_view)
    LinearLayout llyButtomView;
    private int from;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tender_detial;
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

        mBidPricteItem = (TenderListData.DataBean.ListBean) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);

        from = getIntent().getExtras().getInt(ConstantsKeys.KEY_FROM);
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        commonTitleBar.setTitle("招标详情");
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
                if(mBidPricteItem.getJoin_count()==0)
                {
                    if(LegalUserHelper.isLegalUserStatus(getActivity()))
                    {

                        Bundle bundle  = new Bundle();
                        bundle.putSerializable(ConstantsKeys.KEY_DATA,mBidPricteItem);

                        Intent intent = new Intent(getActivity(),TenderPublishActivity.class);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,100);
                    }
                }else
                {
                    DialogUtils.showPromptDialog(getActivity()).show();
                }
            }
        });


        tvMustAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CacheManager.getInstance().getToken()==null)
                {
                    ToastUtil.show(getActivity(),"请先登录后再操作");
                    startActivity(WxLoginActivity.class);
                    return;
                }

                SessionHelper.startP2PSession(getActivity(), mBidPriceDetialData.getData().getAccid());
            }
        });


        tvMustBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(LegalUserHelper.isLegalUserStatus(getActivity()))
                {


                    if(CacheManager.getInstance().getUserInfo().getData().getHas_store()==0)
                    {
                        ToastUtil.show(getContext(),"请前往电脑端先开铺在操作");
                        return ;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ConstantsKeys.KEY_DATA, mBidPriceDetialData);
                    startActivity(TenderMustActivity.class, bundle);
                }


            }
        });

        mDocumentAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,dataList.get(position));
                startActivity(ShowPdfActivity.class,bundle);
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

        mPresenter.getTenderDetail(mBidPricteItem.getTender_id());

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


            tvBidTitle.setText(mBidPriceDetialData.getData().getTitle());

            tvDateTime.setText(mBidPriceDetialData.getData().getCreate_time());

            tvCity.setText(mBidPriceDetialData.getData().getCity());

            tvCategary.setText(mBidPriceDetialData.getData().getService());



            if(!TextUtils.isEmpty(mBidPriceDetialData.getData().getSurvey()))
            {
                tvProjectGaikuang.setText(mBidPriceDetialData.getData().getSurvey().replace(",","\n"));
            }




            StringBuffer yq = new StringBuffer();
            for(int x=0;x<mBidPriceDetialData.getData().getReq_list().length;x++)
            {
                yq.append((x+1)+"."+mBidPriceDetialData.getData().getReq_list()[x]);
                yq.append("\n");

            }

            tvProjectTbryaoqiu.setText(Html.fromHtml(yq.toString()));







            tvProjectEndtime.setText( mBidPriceDetialData.getData().getEnd_time());

            tvProjectStarttime.setText(mBidPriceDetialData.getData().getPub_time());

            String[]invoiceArray = getResources().getStringArray(R.array.invoice_array);
            tvProjectTicket.setText(invoiceArray[mBidPriceDetialData.getData().getInvoice_type()]);



            String[]taxArray = getResources().getStringArray(R.array.tax_array);
            String[]freightArray = getResources().getStringArray(R.array.freight_array);
            tvProjectPrice.setText(taxArray[mBidPriceDetialData.getData().getInclude_tax()]+"        "+freightArray[mBidPriceDetialData.getData().getInclude_freight()]);



            tvProjectAddress.setText(mBidPriceDetialData.getData().getCollect_address());


            tvProjectContract.setText(mBidPriceDetialData.getData().getCollect());


            tvProjectPhone.setText(Utils.hidePhone(mBidPriceDetialData.getData().getPhone()));


            tvProjectDetial.setText(mBidPriceDetialData.getData().getDetail());

             dataList.clear();

             dataList.addAll(mBidPriceDetialData.getData().getAnnex());

             for(int x=0;x<dataList.size();x++)
             {
                 if(TextUtils.isEmpty(dataList.get(x).getFile_name()) ||dataList.get(x).getFile_name().contains("null")||dataList.get(x).getUrl().contains("null"))
                 {
                     dataList.remove(x);
                     x--;
                 }
             }
              mDocumentAdapter.notifyDataSetChanged();


        }
    }


    @Override
    public void showSuccess(Object object) {


        if (object instanceof TenderDetialData) {

            mBidPriceDetialData = (TenderDetialData) object;
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
