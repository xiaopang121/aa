package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.flog.KLog;
import com.common.android.fsp.SharedPreferencesUtil;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.extend.roundedimageview.RoundedImageView;
import com.google.gson.Gson;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.Constants;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.GlideCircleTransform;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.ModifyPwdData;
import com.insthub.cat.android.module.UploasImageData;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.dialog.ActionSheetDialog;
import com.insthub.cat.android.utils.FileUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.Iterator;
import java.util.List;

import butterknife.Bind;


/**
 * 账户信息
 * Created by linux on 2017/6/28.
 */

public class AccountActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    public static final int ACTIVITY_ALBUM_REQUESTCODE = 2000;

    public static final int ACTIVITY_CAMERA_REQUESTCODE = 2001;

    public static final int ACTIVITY_MODIFY_PHOTO_REQUESTCODE = 2002;
    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.my_center_icon)
    RoundedImageView myCenterIcon;
    @Bind(R.id.rll_view_head)
    RelativeLayout rllViewHead;

    @Bind(R.id.user_realname)
    TextView userRealname;

    @Bind(R.id.rll_real_name)
    RelativeLayout rllRealName;

    @Bind(R.id.user_mobile)
    TextView userMobile;

    @Bind(R.id.rll_mobile)
    RelativeLayout rllMobile;

    @Bind(R.id.user_gender)
    TextView userGender;
    @Bind(R.id.rll_gender)
    RelativeLayout rllGender;

    @Bind(R.id.user_hangye)
    TextView userHangye;
    @Bind(R.id.rll_hangye)
    RelativeLayout rllHangye;

    @Bind(R.id.user_zhiwei)
    TextView userZhiwei;
    @Bind(R.id.rll_zhiwei)
    RelativeLayout rllZhiwei;

    @Bind(R.id.user_bumen)
    TextView userBumen;
    @Bind(R.id.rll_bumen)
    RelativeLayout rllBumen;

    @Bind(R.id.user_email)
    TextView userEmail;
    @Bind(R.id.rll_email)
    RelativeLayout rllEmail;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            dismissLoadDialog();
            bindData();

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_center;
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
        commonTitleBar.setTitle(R.string.title_account_info);
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        commonTitleBar.setRighButtonTitle(R.string.title_edit);

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

                startActivityForResult(EditInfoActivity.class,100);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            bindData();
        }

    }



    @Override
    protected void bindData() {
        super.bindData();


        //设置头像
        if (!TextUtils.isEmpty(CacheManager.getInstance().getUserInfo().getData().getHead_image())) {
//            Glide.with(getActivity())
//                    .load(CacheManager.getInstance().getUserInfo().getData().getHead_image())
//                    // .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .transform(new GlideCircleTransform(getActivity()))
//                    .error(R.drawable.ic_default_head)
//                    .placeholder(R.drawable.ic_default_head)
//                    .into(myCenterIcon);

            RequestOptions requestOptions = new RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.ic_default_head)
                    .error(R.drawable.ic_default_head)
                    ;
            Glide.with(getContext().getApplicationContext()).asBitmap()
                    .load(CacheManager.getInstance().getUserInfo().getData().getHead_image())
                    .apply(requestOptions)
                    .into(myCenterIcon);


        } else {
            myCenterIcon.setImageResource(R.drawable.ic_default_head);
        }


        myCenterIcon.setImageResource(R.drawable.ic_default_head);

        userRealname.setText(CacheManager.getInstance().getUserInfo().getData().getUser_name());

        userMobile.setText(CacheManager.getInstance().getUserInfo().getData().getPhone());


        String[] gender = getResources().getStringArray(R.array.gender);



        if(TextUtils.isEmpty(CacheManager.getInstance().getUserInfo().getData().getSex()))
        {
            userGender.setText("未知");
        }else
        {
            userGender.setText(gender[Integer.valueOf(CacheManager.getInstance().getUserInfo().getData().getSex())]);
        }



        userHangye.setText(CacheManager.getInstance().getUserInfo().getData().getTrade());


        userZhiwei.setText(CacheManager.getInstance().getUserInfo().getData().getPosition());


        userBumen.setText(CacheManager.getInstance().getUserInfo().getData().getDep());

        userEmail.setText(CacheManager.getInstance().getUserInfo().getData().getEmail());



    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void showSuccess(Object object) {


        if (object instanceof UserInfoData) {

            dismissLoadDialog();
            UserInfoData data = (UserInfoData) object;
            CacheManager.getInstance().setUserInfo(data);
            bindData();
        }


    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(),msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


}
