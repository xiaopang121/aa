package com.insthub.cat.android.ui.fragment.ActionFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.manager.theme.ThemeManager;
import com.insthub.cat.android.module2.CreateActivityData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import org.w3c.dom.Text;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 评论fragment
 * Created by linux on 2017/6/28.
 */

public class ActionFragment1 extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.tv_shop_name)
    EditText tvShopName;

    @Bind(R.id.tv_goods_market_price)
    EditText tvGoodsMarketPrice;

    @Bind(R.id.tv_goods_lower_price)
    EditText tvGoodsLowerPrice;

    @Bind(R.id.tv_goods_kan_price)
    EditText tvGoodsKanPrice;

    @Bind(R.id.tv_start_time)
    TextView tvStartTime;

    @Bind(R.id.tv_end_time)
    TextView tvEndTime;

    @Bind(R.id.tv_number)
    EditText tvNumber;

    @Bind(R.id.tv_extra)
    EditText tvExtra;

    @Bind(R.id.login)
    Button btSumbit;



    private Date  startDate,endDate;
    private int selectTimeType;

    public static ActionFragment1 newInstance() {
        ActionFragment1 mainFragment = new ActionFragment1();
        Bundle bundle = new Bundle();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_action1;
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


        tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTimeType=1;
                showTimePickerView();
            }
        });

        tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTimeType=2;
                showTimePickerView();
            }
        });


        btSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(tvShopName.getText().toString()))
                {
                    ToastUtil.show(getActivity(),"请输入店铺名称");
                    return ;
                }


                if(TextUtils.isEmpty(tvGoodsMarketPrice.getText().toString()))
                {
                    ToastUtil.show(getActivity(),"请输入商品原价");
                    return ;
                }




                if(TextUtils.isEmpty(tvGoodsLowerPrice.getText().toString()))
                {
                    ToastUtil.show(getActivity(),"请输入商品最低价格");
                    return ;
                }




                if(TextUtils.isEmpty(tvGoodsKanPrice.getText().toString()))
                {
                    ToastUtil.show(getActivity(),"请输入砍价金额");
                    return ;
                }


                if(TextUtils.isEmpty(tvStartTime.getText().toString()))
                {
                    ToastUtil.show(getActivity(),"请选择开始日期");
                    return ;
                }

                if(TextUtils.isEmpty(tvEndTime.getText().toString()))
                {
                    ToastUtil.show(getActivity(),"请选择结束日期");
                    return ;
                }


                if(startDate.getTime()>endDate.getTime())
                {
                    ToastUtil.show(getActivity(),"开始日期不能大于 结束日期");
                    return ;
                }


                if(TextUtils.isEmpty(tvNumber.getText().toString()))
                {
                    ToastUtil.show(getActivity(),"请输入发起人数量");
                    return ;
                }




                showLoadDialog("正在提交数据");

                mPresenter.createActivity(CacheManager.getInstance().getToken().getData().getUser_id(),
                                          CacheManager.getInstance().getToken().getData().getToken(),
                                          1,
                        tvShopName.getText().toString(),
                        tvGoodsMarketPrice.getText().toString(),
                        tvGoodsLowerPrice.getText().toString(),
                        tvStartTime.getText().toString(),
                        tvEndTime.getText().toString(),
                        tvGoodsKanPrice.getText().toString(),
                        tvNumber.getText().toString(),tvExtra.getText().toString(),"","","");


            }
        });

    }

    @Override
    protected void bindEvent() {
        super.bindEvent();


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


        if(object instanceof CreateActivityData)
        {
            dismissLoadDialog();
            CreateActivityData data =(CreateActivityData) object;
            ToastUtil.show(getActivity(), data.getMessage());
        }
    }

    @Override
    public void showError(String msg, int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(), msg);
    }


    private void showTimePickerView() {


        TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调

                if (selectTimeType == 1) {
                    String strstartDate = TimeUtils.formateTime(date.getTime(), TimeUtils.FROMATE_YMD);
                    tvStartTime.setText(strstartDate);
                    startDate = date;
                }


                if (selectTimeType == 2) {
                    endDate = date;
                    String strendDate = TimeUtils.formateTime(date.getTime(), TimeUtils.FROMATE_YMD);
                    tvEndTime.setText(strendDate);
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
                .setTextColorCenter(ThemeManager.getCurrentThemeColor(getActivity())) //设置选中项文字颜色
                .setTitleColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.WHITE)//确定按钮文字颜色
                .setCancelColor(Color.WHITE)//取消按钮文字颜色
                .setTitleBgColor(ThemeManager.getCurrentThemeColor(getActivity()))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();

        pvTime.show();


    }



}
