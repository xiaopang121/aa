package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.google.gson.Gson;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.Constants;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.manager.DBManager;
import com.insthub.cat.android.manager.theme.ThemeManager;
import com.insthub.cat.android.module.BidPriceListData;
import com.insthub.cat.android.module.CreateJingjiaData;
import com.insthub.cat.android.module.JingjiaDraftData;
import com.insthub.cat.android.module.LabelListData;
import com.insthub.cat.android.module2.UploadImageInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.UploadImageAdapter;
import com.insthub.cat.android.ui.dialog.ActionSheetDialog;
import com.insthub.cat.android.ui.widget.SelectCategoryPopupWindow;
import com.insthub.cat.android.ui.widget.SelectCommonWindow;
import com.insthub.cat.android.ui.widget.SpacesItemDecoration;
import com.insthub.cat.android.utils.FileUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;


/**
 * 发布竞价
 * Created by linux on 2017/6/28.
 */

public class PublishBidActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View, UploadImageAdapter.Callback {

    public static final int ACTIVITY_ALBUM_REQUESTCODE = 2000;

    public static final int ACTIVITY_CAMERA_REQUESTCODE = 2001;

    public static final int ACTIVITY_MODIFY_PHOTO_REQUESTCODE = 2002;

    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;


    @Bind(R.id.rv_image_list)
    public RecyclerView recyclerview;


    @Bind(R.id.tv_category_content)
    public TextView tvCategoryContent;


    @Bind(R.id.tv_drow_category)
    public TextView tvDrowCategory;

    @Bind(R.id.tv_end_time)
    TextView tvEndTime;

    @Bind(R.id.tv_publish_rule)
    TextView tvPublishRule;


    @Bind(R.id.et_address_detial)
    EditText etAddressDetial;

    @Bind(R.id.rb_paytype_1)
    RadioButton rbPaytype1;
    @Bind(R.id.rb_paytype_2)
    RadioButton rbPaytype2;
    @Bind(R.id.rb_ticket_1)
    RadioButton rbTicket1;
    @Bind(R.id.rb_ticket_2)
    RadioButton rbTicket2;


    private LinkedList<UploadImageInfoData.DataBean> bannerImages = new LinkedList<>();
    private UploadImageAdapter mUploadImageAdapter;
    private String imagePath = "";
    private Handler mHandler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mUploadImageAdapter.notifyDataSetChanged();
        }
    };


    @Bind(R.id.tv_select_provicne)
    TextView tvSelectProvince;

    @Bind(R.id.tv_select_city)
    TextView tvSelectCity;


    @Bind(R.id.tv_select_district)
    TextView tvSelectDistrict;


    DBManager mDBManager;

    @Bind(R.id.tv_data_save)
    TextView tvDataSave;

    @Bind(R.id.tv_data_public)
    TextView tvDataPublic;

    @Bind(R.id.et_title)
    EditText tvTitle;

    @Bind(R.id.tv_title_size)
    TextView tvTitleSize;

    @Bind(R.id.et_detial)
    EditText etDetial;
    @Bind(R.id.tv_detial_size)
    TextView tvDetialSize;


    @Bind(R.id.check_tax)
    CheckBox checkTax;

    @Bind(R.id.check_price)
    CheckBox checkPrice;

    @Bind(R.id.radigroup_trade)
    RadioGroup radioGroupTrade;

    @Bind(R.id.trade_danbao)
    RadioButton tradeDanbao;

    @Bind(R.id.trade_other)
    RadioButton tradeOther;


    @Bind(R.id.radigroup_paytype)
    RadioGroup radioGroupPayType;

    @Bind(R.id.radigroup_ticket)
    RadioGroup radioGroupTicket;

    @Bind(R.id.check_zhizhao)
    CheckBox checkBoxZhizhao;


    @Bind(R.id.check_rule)
    CheckBox checkRule;


    private LabelListData mLabelListData;
    private int label1Postion = -1, label2Positon = -1;


    // 1保存  2 发布
    private int state;

    //交易方式
    private int trans_type = -1;

    //支付方式
    private int pay_type = -1;

    //发票方式
    private int invoice_type = -1;

    //草稿数据
    private JingjiaDraftData mJingjiaDraftData;


    private BidPriceListData.DataBean.ListBean mBidPricteItem;



    @Bind(R.id.rb_check_categary)
    RadioButton rbCheckCategary;
    @Bind(R.id.rb_check_product)
    RadioButton rbCheckProduct;
    @Bind(R.id.et_prodcut_name)
    EditText etProdcutName;
    @Bind(R.id.rb_check_select)
    RadioGroup rb_check_group;
    @Bind(R.id.lly_headview)
    public LinearLayout llyHeadView;
    private int service_type=0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_publish_bid;
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


        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey(ConstantsKeys.KEY_DATA)) {
            mBidPricteItem = (BidPriceListData.DataBean.ListBean) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
        }


        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        commonTitleBar.setTitle("发布竞价");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        bannerImages.add(new UploadImageInfoData.DataBean(-1));
        GridLayoutManager jxlayoutManager = new GridLayoutManager(getActivity(), 3);
        SpacesItemDecoration jx2layoutManager = new SpacesItemDecoration(UIUtil.dpToPx(getResources(), 5));
        recyclerview.setLayoutManager(jxlayoutManager);
        recyclerview.setHasFixedSize(true);
        mUploadImageAdapter = new UploadImageAdapter(this, bannerImages);
        recyclerview.setAdapter(mUploadImageAdapter);
        recyclerview.addItemDecoration(jx2layoutManager);
        recyclerview.scrollToPosition(0);
        mDBManager = new DBManager(this);

        mPresenter.getLabelList();

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


        rb_check_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i)
                {
                    case R.id.rb_check_categary:
                        llyHeadView.setVisibility(View.VISIBLE);
                        etProdcutName.setVisibility(View.GONE);
                        service_type=0;
                        break;

                    case R.id.rb_check_product:
                        llyHeadView.setVisibility(View.GONE);
                        etProdcutName.setVisibility(View.VISIBLE);
                        service_type=1;
                        break;
                }
            }
        });


        mUploadImageAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                if (bannerImages.get(position).getType() == -1) {

                    showChangeIcon();
                }

            }
        });

        tvDrowCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLabelListData == null) {
                    ToastUtil.show(getContext(), "没有分类数据");
                    return;
                }

                final SelectCategoryPopupWindow mSelectCategoryPopupWindow = new SelectCategoryPopupWindow(getActivity());

                mSelectCategoryPopupWindow.setData(mLabelListData, label1Postion, label2Positon);

                mSelectCategoryPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        label1Postion = mSelectCategoryPopupWindow.getLabel1Postion();
                        label2Positon = mSelectCategoryPopupWindow.getLabel2Position();

                        StringBuffer sb = new StringBuffer();
                        if (label1Postion >= 0) {
                            sb.append(mLabelListData.getData().getLabel_lv1().get(label1Postion).getLable_name());
                            sb.append("     ");
                        }


                        if (label1Postion >= 0 && label2Positon >= 0) {
                            sb.append(mLabelListData.getData().getLabel_lv1().get(label1Postion).getLabel_lv2().get(label2Positon).getLable_name());
                        }

                        tvCategoryContent.setText(sb.toString());
                    }
                });
                mSelectCategoryPopupWindow.show(llyHeadView);
            }
        });


        tvTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                tvTitleSize.setText(tvTitle.getText().length() + "/60");
            }
        });


        etDetial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                tvDetialSize.setText(tvTitle.getText().length() + "/200");
            }
        });


        tvSelectProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectCommonWindow mSelectCategoryPopupWindow = new SelectCommonWindow(getContext(), new SelectCommonWindow.SelectCityCallback() {
                    @Override
                    public void onSelectCity(String city) {
                        tvSelectProvince.setText(city);

                    }
                });
                mSelectCategoryPopupWindow.setData(mDBManager.searchProvicne());
                mSelectCategoryPopupWindow.show(tvSelectProvince);
            }
        });


        tvSelectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(tvSelectProvince.getText().toString())) {
                    ToastUtil.show(getContext(), "请选择省份");
                    return;
                }
                SelectCommonWindow mSelectCategoryPopupWindow = new SelectCommonWindow(getContext(), new SelectCommonWindow.SelectCityCallback() {
                    @Override
                    public void onSelectCity(String city) {
                        tvSelectCity.setText(city);
                    }
                });

                mSelectCategoryPopupWindow.setData(mDBManager.searchCitysByProvicne(tvSelectProvince.getText().toString()));
                mSelectCategoryPopupWindow.show(tvSelectCity);
            }
        });


        tvSelectDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(tvSelectCity.getText().toString())) {
                    ToastUtil.show(getContext(), "请选择城市");
                    return;
                }

                SelectCommonWindow mSelectCategoryPopupWindow = new SelectCommonWindow(getContext(), new SelectCommonWindow.SelectCityCallback() {
                    @Override
                    public void onSelectCity(String city) {
                        tvSelectDistrict.setText(city);
                    }
                });

                mSelectCategoryPopupWindow.setData(mDBManager.searchDistrictByCity(tvSelectCity.getText().toString()));
                mSelectCategoryPopupWindow.show(tvSelectDistrict);
            }
        });


        tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerView();
            }
        });


        radioGroupTrade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.trade_danbao:

                        trans_type = 0;
                        break;
                    case R.id.trade_other:
                        trans_type = 1;
                        break;
                }
            }
        });


        radioGroupPayType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_paytype_1:

                        pay_type = 0;
                        break;
                    case R.id.rb_paytype_2:
                        pay_type = 1;
                        break;
                }
            }
        });


        radioGroupTicket.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_ticket_1:

                        invoice_type = 0;
                        break;
                    case R.id.rb_ticket_2:
                        invoice_type = 1;
                        break;
                }
            }
        });


        //发布规则
        tvPublishRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(RuleActivity.class);
            }
        });


        //保存草稿
        tvDataSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                state = 0;
                sumbitData();

            }
        });


        //发布
        tvDataPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                state = 1;
                sumbitData();
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


    //提交数据
    private void sumbitData() {



        if(service_type==0)
        {

            if (label1Postion == -1) {
                ToastUtil.show(getContext(), "请选择分类");
                return;
            }

            if (label2Positon == -1) {
                ToastUtil.show(getContext(), "请选择分类");
                return;
            }
        }


        if(service_type==1)
        {
            if(TextUtils.isEmpty(etProdcutName.getText().toString()))
            {
                ToastUtil.show(getContext(), "请输入产品名称／服务名称");
                return;
            }
        }


        //判断标题
        String title = tvTitle.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            ToastUtil.show(getContext(), "请输入标题");
            return;
        }

        //判断详情
        String detial = etDetial.getText().toString().trim();
        if (TextUtils.isEmpty(detial)) {
            ToastUtil.show(getContext(), "请输入详情");
            return;
        }


        //判断详情
        String ednTime = tvEndTime.getText().toString().trim();
        if (TextUtils.isEmpty(ednTime)) {
            ToastUtil.show(getContext(), "请选择结束时间");
            return;
        }


        //判断详情
        String addressDetail = etAddressDetial.getText().toString().trim();
        if (TextUtils.isEmpty(addressDetail)) {
            ToastUtil.show(getContext(), "请输入详细地址");
            return;
        }

        if (TextUtils.isEmpty(tvSelectProvince.getText().toString().trim())) {
            ToastUtil.show(getContext(), "请选择省份");
            return;
        }


        if (TextUtils.isEmpty(tvSelectCity.getText().toString().trim())) {
            ToastUtil.show(getContext(), "请选择城市");
            return;
        }

        if (TextUtils.isEmpty(tvSelectDistrict.getText().toString().trim())) {
            ToastUtil.show(getContext(), "请选择区域");
            return;
        }


        StringBuffer imageBuffer = new StringBuffer();

        for (int x = 0; x < bannerImages.size(); x++) {

            if (bannerImages.get(x).getType() == -1) {
                continue;
            }
            imageBuffer.append(bannerImages.get(x).getSave_path());
            if (x != bannerImages.size() - 1) {
                imageBuffer.append(",");
            }
        }


        if (trans_type == -1) {
            ToastUtil.show(getContext(), "请选择交易方式");
            return;
        }


        if (pay_type == -1) {
            ToastUtil.show(getContext(), "请选择支付方式");
            return;
        }


        if (invoice_type == -1) {
            ToastUtil.show(getContext(), "请选择发票类型");
            return;
        }


        if (!checkRule.isChecked()) {
            ToastUtil.show(getContext(), "请阅读发布协议");
            return;
        }


        String tenderId = "";

        if (mJingjiaDraftData != null) {
            tenderId = mJingjiaDraftData.getData().getTender_id();
        }


        showLoadDialog("正在提交数据");

        if(service_type==0)
        {

            mPresenter.createJingjia(CacheManager.getInstance().getToken().getData().getUser_id(),
                    CacheManager.getInstance().getToken().getData().getToken(),
                    String.valueOf(mLabelListData.getData().getLabel_lv1().get(label1Postion).getLabel_id()),
                    String.valueOf(mLabelListData.getData().getLabel_lv1().get(label1Postion).getLabel_lv2().get(label2Positon).getLabel_id()),
                    title,
                    detial,
                    ednTime,
                    addressDetail,
                    checkTax.isChecked() ? 1 : 0,
                    checkPrice.isChecked() ? 1 : 0,
                    trans_type,
                    pay_type,
                    invoice_type,
                    checkBoxZhizhao.isChecked() ? 1 : 0,
                    imageBuffer.toString(),
                    String.valueOf(state),
                    tvSelectProvince.getText().toString().trim(),
                    tvSelectCity.getText().toString().trim(),
                    tvSelectDistrict.getText().toString().trim(),
                    tenderId,
                    service_type,etProdcutName.getText().toString()

            );

        }


        if(service_type==1)
        {

            mPresenter.createJingjia(CacheManager.getInstance().getToken().getData().getUser_id(),
                    CacheManager.getInstance().getToken().getData().getToken(),
                    "",
                   "",
                    title,
                    detial,
                    ednTime,
                    addressDetail,
                    checkTax.isChecked() ? 1 : 0,
                    checkPrice.isChecked() ? 1 : 0,
                    trans_type,
                    pay_type,
                    invoice_type,
                    checkBoxZhizhao.isChecked() ? 1 : 0,
                    imageBuffer.toString(),
                    String.valueOf(state),
                    tvSelectProvince.getText().toString().trim(),
                    tvSelectCity.getText().toString().trim(),
                    tvSelectDistrict.getText().toString().trim(),
                    tenderId,
                    service_type,etProdcutName.getText().toString()

            );

        }



    }


    //
    private void initDraftData() {
        if (mJingjiaDraftData != null && mJingjiaDraftData.getData() != null) {


            switch (mJingjiaDraftData.getData().getService_type())
            {
                case 0:
                    rb_check_group.check(R.id.rb_check_categary);


                    StringBuffer category = new StringBuffer();

                    if(!TextUtils.isEmpty(mJingjiaDraftData.getData().getLabel_lv1())&& !TextUtils.isEmpty(mJingjiaDraftData.getData().getLabel_lv2()))
                    {

                        int  label_v1 = Integer.valueOf(mJingjiaDraftData.getData().getLabel_lv1());

                        int  label_v2 = Integer.valueOf(mJingjiaDraftData.getData().getLabel_lv2());
                        for(int x=0;x<mLabelListData.getData().getLabel_lv1().size();x++)
                        {
                            if(mLabelListData.getData().getLabel_lv1().get(x).getLabel_id()==label_v1)
                            {
                                label1Postion =x;
                                category.append(mLabelListData.getData().getLabel_lv1().get(x).getLable_name()).append("  ");
                                for(int y=0;y<mLabelListData.getData().getLabel_lv1().get(x).getLabel_lv2().size();y++)
                                {
                                    if(mLabelListData.getData().getLabel_lv1().get(x).getLabel_lv2().get(y).getLabel_id()==label_v2)
                                    {
                                        category.append(mLabelListData.getData().getLabel_lv1().get(x).getLabel_lv2().get(y).getLable_name());
                                        label2Positon=y;
                                        break;
                                    }

                                }

                                break;
                            }
                        }
                    }

                    tvCategoryContent.setText(category);

                    break;
                case 1:
                    rb_check_group.check(R.id.rb_check_product);
                    etProdcutName.setText(mJingjiaDraftData.getData().getService_content());
                    break;
            }


            tvTitle.setText(mJingjiaDraftData.getData().getTitle());
            etDetial.setText(mJingjiaDraftData.getData().getDetail());
            tvEndTime.setText(mJingjiaDraftData.getData().getEnd_time());

            etAddressDetial.setText(mJingjiaDraftData.getData().getAddress());

            tvSelectProvince.setText(mJingjiaDraftData.getData().getProvince());
            tvSelectCity.setText(mJingjiaDraftData.getData().getCity());
            tvSelectDistrict.setText(mJingjiaDraftData.getData().getArea());


            List<String> imageList = mJingjiaDraftData.getData().getAnnex();
            bannerImages.clear();
            if (imageList != null && imageList.size() > 0) {

                for (int x = 0; x < imageList.size(); x++) {
                    if (imageList.get(x).equals("null") || TextUtils.isEmpty(imageList.get(x).trim())) {
                        continue;
                    }
                    UploadImageInfoData.DataBean item = new UploadImageInfoData.DataBean();
                    item.setSave_path(imageList.get(x));
                    item.setFull_path(Constants.IMAGE_URL_PREFEX + imageList.get(x));

                    bannerImages.add(item);
                }
            }
            if (bannerImages.size() < 9) {
                bannerImages.add(new UploadImageInfoData.DataBean(-1));
            }

            mHandler.sendEmptyMessage(0);


            //判断是否包含税价
            switch (mJingjiaDraftData.getData().getInclude_tax()) {
                case 0:
                    checkTax.setChecked(false);
                    break;
                case 1:

                    checkTax.setChecked(true);
                    break;
            }


            //判断是否包含运费
            switch (mJingjiaDraftData.getData().getInclude_freight()) {
                case 0:
                    checkPrice.setChecked(false);
                    break;
                case 1:
                    checkPrice.setChecked(true);
                    break;
            }


            //判断交易方式
            trans_type = mJingjiaDraftData.getData().getTrans_type();
            switch (trans_type) {
                case 0:

                    radioGroupTrade.check(R.id.trade_danbao);
                    break;
                case 1:
                    radioGroupTrade.check(R.id.trade_other);
                    break;
            }


            //判断支付方式
            pay_type = mJingjiaDraftData.getData().getPay_type();
            switch (pay_type) {
                case 0:

                    radioGroupPayType.check(R.id.rb_paytype_1);
                    break;
                case 1:
                    radioGroupPayType.check(R.id.rb_paytype_2);
                    break;
            }


            //判断发票要求
            invoice_type = mJingjiaDraftData.getData().getInvoice_type();
            switch (invoice_type) {
                case 0:

                    radioGroupTicket.check(R.id.rb_ticket_1);
                    break;
                case 1:
                    radioGroupTicket.check(R.id.rb_ticket_2);
                    break;
            }


            //判断营业执照

            if (mJingjiaDraftData.getData().getBusiness_license() == 1) {
                checkBoxZhizhao.setChecked(true);
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {

                case ACTIVITY_ALBUM_REQUESTCODE:
                    if (resultCode == Activity.RESULT_OK) {
                        cutPhoto(data.getData(), true);
                    }
                    break;
                case ACTIVITY_CAMERA_REQUESTCODE:
                    if (resultCode == Activity.RESULT_OK) {

                        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                        bitmapOptions.inSampleSize = 2;
                        int degree = FileUtil.readPictureDegree(imagePath);
                        Bitmap cameraBitmap = BitmapFactory.decodeFile(imagePath, bitmapOptions);
                        cameraBitmap = FileUtil.rotaingImageView(degree, cameraBitmap);
                        FileUtil.saveCutBitmapForCache(this, cameraBitmap);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            cutPhoto(FileProvider.getUriForFile(getActivity(), "com.insthub.cat.android.provider", new File(imagePath)), true);
                        } else {
                            cutPhoto(Uri.fromFile(new File(imagePath)), true);
                        }

                    }
                    break;
                case ACTIVITY_MODIFY_PHOTO_REQUESTCODE:
                    // NOTICE: 上传头像接口ok


                    uploadUserHead(imagePath);


                    break;
            }

        }

    }

    @Override
    public void showSuccess(Object object) {

        dismissLoadDialog();
        if (object instanceof LabelListData) {
            mLabelListData = (LabelListData) object;


            if (mBidPricteItem != null) {
                mPresenter.getJingjiaDraft(CacheManager.getInstance().getToken().getData().getUser_id(), CacheManager.getInstance().getToken().getData().getToken(), mBidPricteItem.getTender_id());
            }


        }


        if (object instanceof CreateJingjiaData) {

            switch (state) {
                case 0:
                    ToastUtil.show(getContext(), "保存草稿成功");
                    break;
                case 1:
                    ToastUtil.show(getContext(), "发布成功");
                    break;
            }
            setResult(Activity.RESULT_OK);
            finish();

        }

        if (object instanceof JingjiaDraftData) {
            mJingjiaDraftData = (JingjiaDraftData) object;
            initDraftData();
        }

    }

    @Override
    public void showError(String msg, int code) {

    }


    private void showChangeIcon() {

        getPhotoPath();
        new ActionSheetDialog(getActivity())
                .builder()
                .setTitle("选择获取图片的方式")
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("相机", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {


                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用android自带的照相机

                                Uri imageUri = null;
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                                    imageUri = FileProvider.getUriForFile(getActivity(), "com.insthub.cat.android.provider", new File(imagePath));
                                } else {
                                    imageUri = Uri.fromFile(new File(imagePath));
                                }
                                List resInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                                if (resInfoList.size() == 0) {
                                    ToastUtil.show(getActivity(), "没有合适的相机应用程序");
                                    return;
                                }
                                Iterator resInfoIterator = resInfoList.iterator();
                                while (resInfoIterator.hasNext()) {
                                    ResolveInfo resolveInfo = (ResolveInfo) resInfoIterator.next();
                                    String packageName = resolveInfo.activityInfo.packageName;
                                    KLog.i("packName:" + packageName);
                                    grantUriPermission(packageName, imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                }
                                KLog.i("url:" + imageUri.toString());

                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                                startActivityForResult(intent, ACTIVITY_CAMERA_REQUESTCODE);


                            }
                        })
                .addSheetItem("图库", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent i = new Intent(Intent.ACTION_PICK, null);// 调用android的图库
                                i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(i, ACTIVITY_ALBUM_REQUESTCODE);

                            }
                        }).show();

        //          }
        //      });
    }


    public void getPhotoPath() {
        if (!TextUtils.isEmpty(imagePath)) {

            try {
                File file = new File(imagePath);
                file.delete();

            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }

        imagePath = FileUtil.getHeadPhotoDir() + System.currentTimeMillis() + ".png";
    }


    /**
     * 裁剪图片
     */
    public void cutPhoto(Uri uri, boolean isHeadPic) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        if (isHeadPic) {
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 1000);
            intent.putExtra("outputY", 1000);

            intent.putExtra("scale", true);
            //只能设置成false，k920无法返回
            intent.putExtra("return-data", false);

            Uri tempUrl = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tempUrl = FileProvider.getUriForFile(getActivity(), "com.insthub.cat.android.provider", new File(imagePath));

            } else {
                tempUrl = Uri.fromFile(new File(imagePath));
            }

            List resInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if (resInfoList.size() == 0) {
                ToastUtil.show(getActivity(), "没有合适的相机应用程序");
                return;
            }
            Iterator resInfoIterator = resInfoList.iterator();
            while (resInfoIterator.hasNext()) {
                ResolveInfo resolveInfo = (ResolveInfo) resInfoIterator.next();
                String packageName = resolveInfo.activityInfo.packageName;
                grantUriPermission(packageName, tempUrl, Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }

            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUrl);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
            intent.putExtra("noFaceDetection", true);
        } else {
            // 是否保留比例
            intent.putExtra("scale", "true");
            intent.putExtra("output", Uri.fromFile(FileUtil.getWallPaperFile()));
        }

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, ACTIVITY_MODIFY_PHOTO_REQUESTCODE);
    }


    /**
     * 上传图片
     */
    public void uploadUserHead(String imagePath) {

        showLoadDialog("正在上传数据");
        RequestParams params = new RequestParams();
        params.addHeader("Connection", "Keep-Alive");
        // params.addHeader("Content-Type", "multipart/form-data;charset=utf-8");
        params.addBodyParameter("content_type", "head");
        params.addBodyParameter("file", new File(imagePath), "image/png");

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, Constants.SERVICE_URL_PREFEX + "upload/save.do", params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String s = responseInfo.result;
                KLog.i("content", s);
                //headImage
                try {
                    Gson gson = new Gson();
                    UploadImageInfoData logo = gson.fromJson(s, UploadImageInfoData.class);
                    bannerImages.removeLast();
                    bannerImages.add(logo.getData());
                    if (bannerImages.size() < 9) {
                        bannerImages.add(new UploadImageInfoData.DataBean(-1));
                    }

                    mHandler.sendEmptyMessage(0);
                    dismissLoadDialog();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {

                dismissLoadDialog();
            }
        });
    }


    @Override
    public void delete(int position) {

        bannerImages.remove(position);
        mUploadImageAdapter.notifyDataSetChanged();
    }


    private void showTimePickerView() {

        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调


                tvEndTime.setText(TimeUtils.formateTime(date.getTime(), TimeUtils.FROAMTE_YMHMS));


            }
        })
                .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setSubCalSize(12)
                .setTitleSize(16)//标题文字大小
                .setDividerColor(getResources().getColor(R.color.md_divider_black))
                .setTitleText("选择时间")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setDividerColor(getResources().getColor(R.color.md_divider_black))
                .setTextColorCenter(ThemeManager.getCurrentThemeColor(this)) //设置选中项文字颜色
                .setTitleColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.WHITE)//确定按钮文字颜色
                .setCancelColor(Color.WHITE)//取消按钮文字颜色
                .setTitleBgColor(ThemeManager.getCurrentThemeColor(this))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();


        pvTime.show();


    }


}
