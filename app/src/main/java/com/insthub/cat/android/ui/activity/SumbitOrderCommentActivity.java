package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.example.xlhratingbar_lib.XLHRatingBar;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.SumbitFeedbackData;
import com.insthub.cat.android.module2.OrderCommentResultData;
import com.insthub.cat.android.module2.UseOrderListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import butterknife.Bind;


/**
 * 发布订单平路
 * Created by linux on 2017/6/28.
 */

public class SumbitOrderCommentActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.tv_content)
    EditText tvContent;
    @Bind(R.id.iv_comapnay_head)
    ImageView ivComapnayHead;
    @Bind(R.id.tv_company_name)
    TextView tvCompanyName;
    @Bind(R.id.ratingBar)
    XLHRatingBar ratingBar;
    @Bind(R.id.tv_score_state)
    TextView tvScoreState;
    @Bind(R.id.bt_sumbit)
    Button btSumbit;

    private UseOrderListData.DataBean.ListBean data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_publish_comment;
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
        commonTitleBar.setTitle(R.string.title_publish_comment);
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        data = (UseOrderListData.DataBean.ListBean)getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);

        RequestOptions requestOptions2 = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                ;
        Glide.with(getContext()).asBitmap()
                .load(data.getLogo())
                .apply(requestOptions2)
                .into( ivComapnayHead);


        tvCompanyName.setText(data.getStore_name());


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


        btSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(tvContent.getText().toString())) {
                    ToastUtil.show(getContext(), "请输入评价");
                    return;
                }

                showLoadDialog("正在提交评价");
                mPresenter.evaluateOrder(CacheManager.getInstance().getToken().getData().getUser_id(), CacheManager.getInstance().getToken().getData().getToken(),
                        data.getService_id(),ratingBar.getCountSelected(),
                        tvContent.getText().toString(),data.getOrder_id());
            }
        });


        tvContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

//                int length = tvContent.getText().length();
//                tvSize.setText(length + "/200");
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


        if (object instanceof OrderCommentResultData) {
            ToastUtil.show(this, "评价成功");
            Intent bundle  = new Intent();
            bundle.putExtra("ORDER_ID",data.getOrder_id());
            setResult(Activity.RESULT_OK,bundle);
            finish();
        }

    }

    @Override
    public void showError(String msg, int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(), msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


}
