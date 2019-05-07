package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.IntentUtils;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.event.XiaojingEvent;
import com.insthub.cat.android.module2.UploadImageInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.adatper.UploadImageAdapter;
import com.insthub.cat.android.ui.widget.SpacesItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;

import java.util.LinkedList;

import butterknife.Bind;


/**
 * 小鲸消息
 * Created by linux on 2017/6/28.
 */

public class MessageXiaojingActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View,UploadImageAdapter.Callback{


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.tv_title2)
    TextView tvTitle;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.bt_call)
    ImageView btCall;


    @Bind(R.id.rv_image_list)
    public RecyclerView recyclerview;

    @Bind(R.id.bt_chat)
    ImageView btChat;

    private XiaojingEvent mXiaojingEvent;
    private LinkedList<UploadImageInfoData.DataBean> bannerImages = new LinkedList<>();
    private UploadImageAdapter mUploadImageAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_messagexiaojing;
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

        mXiaojingEvent = (XiaojingEvent)getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        commonTitleBar.setTitle("星客捕手");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        GridLayoutManager jxlayoutManager = new GridLayoutManager(getActivity(), 5);
        SpacesItemDecoration jx2layoutManager = new SpacesItemDecoration(UIUtil.dpToPx(getResources(), 5));
        recyclerview.setLayoutManager(jxlayoutManager);
        recyclerview.setHasFixedSize(true);
        mUploadImageAdapter = new UploadImageAdapter(this, bannerImages);

        recyclerview.setAdapter(mUploadImageAdapter);
        mUploadImageAdapter.setShowDelete(false);
        recyclerview.addItemDecoration(jx2layoutManager);
        recyclerview.scrollToPosition(0);



        if(!TextUtils.isEmpty(mXiaojingEvent.getImage()))
        {
            String[] imageList = mXiaojingEvent.getImage().split(",");

            for(String item:imageList)
            {
                UploadImageInfoData.DataBean temp = new UploadImageInfoData.DataBean();
                temp.setFull_path(item);
                bannerImages.add(temp);
            }

            mUploadImageAdapter.notifyDataSetChanged();

        }


        tvTitle.setText(mXiaojingEvent.getContent());

        tvContent.setText(mXiaojingEvent.getDetails());


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


        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentUtils.call(getActivity(),mXiaojingEvent.getPhone());
            }
        });


        btChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mXiaojingEvent.getIm_code()))
                {
                    ToastUtil.show(getActivity(),"无法联系对方");
                    return;
                }
                SessionHelper.startP2PSession(getActivity(),mXiaojingEvent.getIm_code());
            }

        });


        mUploadImageAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                DialogUtils.showLargeImage(getActivity(),bannerImages.get(position).getFull_path()).show();

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

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    public void delete(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
