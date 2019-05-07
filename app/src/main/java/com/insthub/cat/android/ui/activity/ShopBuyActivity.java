package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.transformer.TransitionEffect;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.ServiceCallback;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.manager.theme.ThemeManager;
import com.insthub.cat.android.module.CouponListData;
import com.insthub.cat.android.module2.HomeData;
import com.insthub.cat.android.module2.StoreDetialData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.LocalImgAdapter;
import com.insthub.cat.android.utils.DialogUtils;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import cn.lankton.flowlayout.FlowLayout;


/**
 * 下订单
 * Created by linux on 2017/6/28.
 */

public class ShopBuyActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {

    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;

    @Bind(R.id.banners)
    LMBanners mLBanners;

    @Bind(R.id.flowView)
    FlowLayout mFlowLayout;

    @Bind(R.id.tv_s_year)
    TextView tvSYear;

    @Bind(R.id.tv_e_year)
    TextView tvEYear;


    @Bind(R.id.tv_select_coupon)
    TextView tvSelectCoupon;

    @Bind(R.id.rll_coupon_bar)
    RelativeLayout rllCouponBar;

    @Bind(R.id.tv_money)
    TextView tvMoney;

    @Bind(R.id.tv_market)
    EditText tvMarket;

    @Bind(R.id.tv_cancel)
    TextView tvCancel;

    @Bind(R.id.tv_sure)
    TextView tvSure;
    @Bind(R.id.lly_starttime_bar)
    LinearLayout llyStarttimeBar;
    @Bind(R.id.lly_endtime_bar)
    LinearLayout llyEndtimeBar;
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.tv_sub)
    ImageView tvSub;
    @Bind(R.id.tv_add)
    ImageView tvAdd;
    @Bind(R.id.tv_total_money)
    TextView tvTotalMoney;

    @Bind(R.id.tv_num_size)
    TextView tvTotalNum;

    private int defaultTag = 0;


    private String[] tagList;


    private int selectTimeType = 1;

    Date startDate, endDate;

    private ArrayList<HomeData.DataBean.BannerListBean> bannerList = new ArrayList<>();

    private StoreDetialData.DataBean dataBean;


    @Bind(R.id.iv_back)
    ImageView ivBack;

    private int num=1;

    //可用优惠券
    private CouponListData mCouponListData;

    private String deduction_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shop_buy;
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


        dataBean = (StoreDetialData.DataBean) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);


        bannerList.add(new HomeData.DataBean.BannerListBean(""));
        mLBanners.isGuide(false);//是否为引导页
        mLBanners.setAutoPlay(true);//自动播放
        mLBanners.setVertical(false);//是否垂直播放
        mLBanners.setScrollDurtion(2000);//两页切换时间
        mLBanners.setCanLoop(true);//循环播放
        mLBanners.setSelectIndicatorRes(R.drawable.guide_indicator_select);//选中的原点
        mLBanners.setUnSelectUnIndicatorRes(R.drawable.guide_indicator_unselect);//未选中的原点
        //若自定义原点到底部的距离,默认20,必须在setIndicatorWidth之前调用
        mLBanners.setIndicatorBottomPadding(10);
        mLBanners.setIndicatorWidth(5);//原点默认为5dp
        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Alpha);//选中喜欢的样式
//        mLBanners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));//自定义样式
        mLBanners.setDurtion(3000);//轮播切换时间
        mLBanners.showIndicatorLayout();//显示原点
        mLBanners.setIndicatorPosition(LMBanners.IndicaTorPosition.BOTTOM_MID);//设置原点显示位置
        mLBanners.setAdapter(new LocalImgAdapter(this, null), bannerList);
        //本地用法


        mPresenter.getDiscountList(CacheManager.getInstance().getToken().getData().getUser_id(), CacheManager.getInstance().getToken().getData().getToken());


    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        tvSYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTimeType = 1;
                showTimePickerView();
            }
        });


        tvEYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTimeType = 2;
                showTimePickerView();
            }
        });


        rllCouponBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mCouponListData != null) {
                    DialogUtils.showCouponDialog(getActivity(), mCouponListData.getData().getList(), new ServiceCallback() {
                        @Override
                        public void onService(int position) {


                            if (position >= 0) {
                                deduction_id = mCouponListData.getData().getList().get(position).getDeduction_id();
                                tvSelectCoupon.setText("满" + mCouponListData.getData().getList().get(position).getLimit_money() + "优惠" + mCouponListData.getData().getList().get(position).getMoney() + "元");
                            }

                        }
                    }).show();
                }
            }
        });


        tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(num>1)
                {
                    num--;
                }

                accountTotalMoney();
            }
        });


        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;

                accountTotalMoney();
            }
        });



        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (defaultTag == -1) {
                    ToastUtil.show(getActivity(), "请选择服务名称");
                    return;
                }


                if (startDate == null || endDate == null) {
                    ToastUtil.show(getActivity(), "请设置服务时间");
                    return;
                }


                if (startDate.getTime() > endDate.getTime()) {
                    ToastUtil.show(getActivity(), "请设置正确的服务时间");
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString(ShopMakeOrderActivity.KEY_SERVICE, tagList[defaultTag]);
                bundle.putLong(ShopMakeOrderActivity.KEY_START_TIME, startDate.getTime());
                bundle.putLong(ShopMakeOrderActivity.KEY_END_TIME, endDate.getTime());
                bundle.putString(ShopMakeOrderActivity.KEY_MARKET, tvMarket.getText().toString());
                bundle.putString(ShopMakeOrderActivity.KEY_MARKET, tvMarket.getText().toString());
                bundle.putString(ShopMakeOrderActivity.KEY_COUPON, deduction_id);
                bundle.putInt(ShopMakeOrderActivity.KEY_NUM, num);
                bundle.putSerializable(ConstantsKeys.KEY_DATA, dataBean);
                Intent intent = new Intent(getActivity(), ShopMakeOrderActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 100);

            }
        });

    }


    @Override
    protected void bindData() {
        super.bindData();

        bannerList.clear();
        if (dataBean.getBanner_list().size() > 0) {
            for (String item : dataBean.getBanner_list()) {
                bannerList.add(new HomeData.DataBean.BannerListBean(item));
            }
        }


        if(bannerList.isEmpty())
        {
            bannerList.add(new HomeData.DataBean.BannerListBean(""));
        }
        mLBanners.setAdapter(new LocalImgAdapter(getActivity(), null), bannerList);

        tvMoney.setText(dataBean.getPrice() + "");

        tagList = dataBean.getService_name().split(",");


        tvNum.setText("x"+num);
        buildTagList();
        accountTotalMoney();

    }


    private void buildTagList() {

        mFlowLayout.removeAllViews();
        if (tagList != null) {
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(UIUtil.dpToPx(getResources(), 5), 0, 0, 0);
            for (int x = 0; x < tagList.length; x++) {
                TextView textView = buildLabel(tagList[x]);
                textView.setTag(x);
                textView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        int item = (int) arg0.getTag();
                        defaultTag = item;
                        buildTagList();
                    }
                });
                if (x == defaultTag) {
                    textView.setBackgroundResource(R.drawable.label_bg);
                    textView.setTextColor(getResources().getColor(R.color.white));
                } else {
                    textView.setBackgroundResource(R.drawable.label_bg_normal);
                    textView.setTextColor(getResources().getColor(R.color.B_black_70));
                }
                mFlowLayout.addView(textView, lp);
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


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {
        if (object instanceof CouponListData) {
            mCouponListData = (CouponListData) object;


            for (int x = 0; x < mCouponListData.getData().getList().size(); x++) {

                float limitMoney = 0;

                if (!TextUtils.isEmpty(mCouponListData.getData().getList().get(x).getLimit_money())) {
                    limitMoney = Float.valueOf(mCouponListData.getData().getList().get(x).getLimit_money());
                }

                if (dataBean.getPrice() < limitMoney) {
                    mCouponListData.getData().getList().remove(x);
                }
            }


        }

    }

    @Override
    public void showError(String msg, int code) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    private void showTimePickerView() {


        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调

                if (selectTimeType == 1) {
                    startDate = date;
                    String strstartDate = TimeUtils.formateTime(date.getTime(), TimeUtils.FROMATE_YMD);
                    tvSYear.setText(strstartDate);


                }


                if (selectTimeType == 2) {
                    endDate = date;
                    String strendDate = TimeUtils.formateTime(date.getTime(), TimeUtils.FROMATE_YMD);
                    tvEYear.setText(strendDate);

                }

            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
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



    private void accountTotalMoney()
    {
        tvTotalMoney.setText((num*dataBean.getPrice())+"");

        tvNum.setText("x"+num);

        tvTotalNum.setText(num+"");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            finish();
        }
    }

}
