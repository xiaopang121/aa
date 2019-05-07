package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.module.SumbitFeedbackData;
import com.insthub.cat.android.module2.FriendInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.friend.constant.VerifyType;
import com.netease.nimlib.sdk.friend.model.AddFriendData;

import butterknife.Bind;


/**
 * 添加好友
 * Created by linux on 2017/6/28.
 */

public class FriendAddActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;


    @Bind(R.id.mine_photo)
    RoundedImageView minePhoto;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.et_remark)
    EditText etRemark;
    @Bind(R.id.et_group)
    TextView etGroup;
    @Bind(R.id.rll_select_group)
    LinearLayout rllSelectGroup;


    @Bind(R.id.et_oauth_msg)
    TextView tvOautMsg;


    private FriendInfoData mFriendInfoData;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_addfriend;
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
        commonTitleBar.setTitle("添加好友");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        commonTitleBar.setRighButtonTitle(R.string.feed_submit);

        mFriendInfoData = (FriendInfoData) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);


        tvUsername.setText(mFriendInfoData.getData().getUser_name());


        RequestOptions requestOptions2 = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.ic_default_head)
                .error(R.drawable.ic_default_head);
        Glide.with(getContext()).asBitmap()
                .load(mFriendInfoData.getData().getHead_image())
                .apply(requestOptions2)
                .into(minePhoto);


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

                final VerifyType verifyType = VerifyType.VERIFY_REQUEST; // 发起好友验证请求
                String msg =tvOautMsg.getText().toString();

                showLoadDialog("发起好友邀请！");


                NIMClient.getService(FriendService.class).addFriend(new AddFriendData(mFriendInfoData.getData().getAccid(), verifyType, msg)).setCallback(new RequestCallback<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dismissLoadDialog();

                        ToastUtil.show(getContext(),"邀请发送成功！");
                        setResult(Activity.RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onFailed(int i) {
                        dismissLoadDialog();

                        ToastUtil.show(getContext(),"请求异常！");
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        dismissLoadDialog();

                        ToastUtil.show(getContext(),"请求异常！");
                    }
                });
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


        if (object instanceof SumbitFeedbackData) {
            ToastUtil.show(this, "反馈提交成功");
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
