package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.SumbitFeedbackData;
import com.insthub.cat.android.module2.FriendInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.friend.model.AddFriendData;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 好友申请
 * Created by linux on 2017/6/28.
 */

public class FriendDetialActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.center_view)

    View centerView;
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
    @Bind(R.id.bt_add)
    Button btAdd;


    private FriendInfoData mFriendInfoData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_friend_detial;
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
        commonTitleBar.setTitle("详细信息");

        mFriendInfoData = (FriendInfoData) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);

        tvUsername.setText(mFriendInfoData.getData().getUser_name());



        RequestOptions requestOptions2 = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.ic_default_head)
                .error(R.drawable.ic_default_head)
                ;
        Glide.with(getContext()).asBitmap()
                .load(mFriendInfoData.getData().getHead_image())
                .apply(requestOptions2)
                .into(minePhoto);


        if(TextUtils.isEmpty(mFriendInfoData.getData().getHead_image()))
        {
            getUserInfo();
        }


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



        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // showLoadDialog("正在添加好友");

                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,mFriendInfoData);

                Intent intent = new Intent(getActivity(),FriendAddActivity.class);

                intent.putExtras(bundle);
                startActivityForResult(intent,100);

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
        dismissLoadDialog();
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


        if(resultCode== Activity.RESULT_OK)
        {
            setResult(Activity.RESULT_OK);
            finish();
        }

    }



    private void  getUserInfo()
    {
        ArrayList<String>  accountList = new ArrayList<>();
        accountList.add(mFriendInfoData.getData().getAccid());
        NIMClient.getService(UserService.class).fetchUserInfo(accountList).setCallback(new RequestCallback<List<NimUserInfo>>() {
            @Override
            public void onSuccess(List<NimUserInfo> nimUserInfos) {

              String head  = (String)nimUserInfos.get(0).getAvatar();
                KLog.i("头像"+head);
                mFriendInfoData.getData().setHead_image(head);
                RequestOptions requestOptions2 = new RequestOptions()
                        .circleCrop()
                        .placeholder(R.drawable.ic_default_head)
                        .error(R.drawable.ic_default_head)
                        ;
                Glide.with(getContext()).asBitmap()
                        .load(mFriendInfoData.getData().getHead_image())
                        .apply(requestOptions2)
                        .into(minePhoto);
            }

            @Override
            public void onFailed(int i) {

            }

            @Override
            public void onException(Throwable throwable) {

            }
        });
    }

}
