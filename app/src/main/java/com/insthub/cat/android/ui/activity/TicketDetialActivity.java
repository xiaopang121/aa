package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.GlideCircleTransform;
import com.insthub.cat.android.module2.TickListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 发票详情
 * Created by linux on 2017/6/28.
 */

public class TicketDetialActivity extends Activity {


    CommonTitleBar commonTitleBar;

    ImageView ivTicketImage;



    private TickListData.DataBean.ListBean data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detial);
        bindViewById();

    }


    private void bindViewById() {

        data = (TickListData.DataBean.ListBean) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);


        int statubar = ScreenInfo.getStatusBarHeight(this);
        commonTitleBar = (CommonTitleBar)findViewById(R.id.common_title_bar);
        ivTicketImage = (ImageView) findViewById(R.id.iv_ticket_image);
//        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
//        lp.height = lp.height + statubar;
//        commonTitleBar.setPadding(0, statubar, 0, 0);
//        commonTitleBar.setLayoutParams(lp);
//        commonTitleBar.invalidate();
        commonTitleBar.setTitle("发票详情");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        bindEvent();
        bindData();
    }

    private void bindEvent() {
        commonTitleBar.ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void bindData() {

        Glide.with(this)
                .load(data.getInvoice_image())
                .into(ivTicketImage);

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
