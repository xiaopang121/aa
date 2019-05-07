package com.insthub.cat.android.ui.fragment.UserFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.flog.KLog;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.ToastUtil;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.helper.LegalUserHelper;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.activity.AccountActivity;
import com.insthub.cat.android.ui.activity.CollectActivity;
import com.insthub.cat.android.ui.activity.CommentListActivity;
import com.insthub.cat.android.ui.activity.MemberServiceActivity;
import com.insthub.cat.android.ui.activity.MyCouponListActivity;
import com.insthub.cat.android.ui.activity.MyOfferActivity;
import com.insthub.cat.android.ui.activity.MyShopOrderListActivity;
import com.insthub.cat.android.ui.activity.MyVoucherListActivity;
import com.insthub.cat.android.ui.activity.MyWallettActivity;
import com.insthub.cat.android.ui.activity.ScoreListActivity;
import com.insthub.cat.android.ui.activity.ServiceActivity;
import com.insthub.cat.android.ui.activity.SetActionActivity;
import com.insthub.cat.android.ui.activity.SettingActivity;
import com.insthub.cat.android.ui.activity.ShareActivity;
import com.insthub.cat.android.ui.activity.ShopOauthActivity;
import com.insthub.cat.android.ui.activity.ShopWithdrawActivity;
import com.insthub.cat.android.ui.activity.TuiguangActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by linux on 2017/6/28.
 */

public class BusinessFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.mine_photo)
    RoundedImageView detailHead;

    @Bind(R.id.tv_username)
    TextView userNickname;

    @Bind(R.id.tv_userinfo)
    TextView tvUserinfo;


    @Bind(R.id.mine_ic_notifications)
    ImageView ivSetting;



    @Bind(R.id.lly_my_score)
    LinearLayout llyMyScore;

    @Bind(R.id.tv_score_size)
    TextView tvScoreSize;


    @Bind(R.id.lly_my_repacket)
    LinearLayout llyMyRepacket;

    @Bind(R.id.tv_repacket_money)
    TextView tvRepacketMoney;


    @Bind(R.id.lly_my_coupon)
    LinearLayout llyMyCoupon;
    @Bind(R.id.tv_coupon_size)
    TextView tvCouponSize;


    @Bind(R.id.lly_dy_coupon)
    LinearLayout llyDyQuan;

    @Bind(R.id.tv_dyj_size)
    TextView tvMyDyquan;


    @Bind(R.id.tv_shop_oauth)
    TextView tvShopOauth;

//    @Bind(R.id.tv_shop_manager)
//    TextView tvShopManager;


    @Bind(R.id.tv_extension)
    TextView tvShopExtension;

    @Bind(R.id.tv_shop_order)
    TextView tvShopOrder;

    @Bind(R.id.tv_shop_withdraw)
    TextView tvShopWithdraw;

    @Bind(R.id.tv_action)
    TextView tvActionManager;
    @Bind(R.id.tv_member_service)
    TextView tvMemberService;


    @Bind(R.id.tv_my_offer)
    TextView tvMyOffer;

    @Bind(R.id.tv_kaipu_word)
    TextView tvKaipuword;

    @Bind(R.id.tv_service)
    TextView tvService;


    boolean isRefresh;

    public static BusinessFragment newInstance() {
        BusinessFragment mainFragment = new BusinessFragment();
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
        return R.layout.fragment_business;
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


    }


    @Override
    protected void bindEvent() {
        super.bindEvent();

        detailHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    startActivity(AccountActivity.class);
                }


            }
        });


        //设置
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    startActivity(SettingActivity.class);
                }


            }
        });




        llyMyScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    startActivity(ScoreListActivity.class);
                }


            }
        });


        llyMyRepacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    startActivity(MyWallettActivity.class);
                }

            }
        });


        llyMyCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    startActivity(MyCouponListActivity.class);
                }

            }
        });


        llyDyQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    startActivity(MyVoucherListActivity.class);
                }

            }
        });


        tvShopOauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    startActivity(ShopOauthActivity.class);
                }

            }
        });

//
//        tvShopManager.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                if(LegalUserHelper.isLegalUserStatus(getActivity()))
////                {
////                    startActivity(ShopListActivity.class);
////                }
////
//
//                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
//                    startActivity(ShopManager2Activity.class);
//                }
//
//
//            }
//        });


        tvShopExtension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    startActivity(TuiguangActivity.class);
                }
            }
        });


        tvShopOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    startActivity(MyShopOrderListActivity.class);
                }

            }
        });


        tvShopWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    startActivity(ShopWithdrawActivity.class);
                }

            }
        });




        tvActionManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(LegalUserHelper.isLegalUserStatus(getActivity()))
//                {
//                    startActivity(ActionActivity.class);
//                }
                startActivity(SetActionActivity.class);

            }
        });


        //会员服务
        tvMemberService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LegalUserHelper.isLegalUserStatus(getActivity()))
                {

                     startActivity(MemberServiceActivity.class);
                }
            }
        });


        //我的报价
        tvMyOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LegalUserHelper.isLegalUserStatus(getActivity()))
                {

                    startActivity(MyOfferActivity.class);
                }

            }
        });


        tvService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (LegalUserHelper.isLegalUserStatus(getActivity())) {
                    SessionHelper.startP2PSession(getActivity(), CacheManager.getInstance().getUserInfo().getData().getIm_code());
                }


            }
        });

    }


    @Override
    protected void bindData() {
        super.bindData();

        KLog.i("刷新商户信息");



        refreshData();

        if(CacheManager.getInstance().getToken()!=null)
        {
            mPresenter.getUserInfo(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());
        }
    }




    private void refreshData()
    {
        if (CacheManager.getInstance().getUserInfo() != null) {
            userNickname.setText(CacheManager.getInstance().getUserInfo().getData().getUser_name());

            //设置头像
            if (!TextUtils.isEmpty(CacheManager.getInstance().getUserInfo().getData().getHead_image())) {
//                Glide.with(getActivity())
//                        .load(CacheManager.getInstance().getUserInfo().getData().getHead_image())
//                        // .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .transform(new GlideCircleTransform(getActivity()))
//                        .error(R.drawable.ic_default_head)
//                        .placeholder(R.drawable.ic_default_head)
//                        .into(detailHead);


                RequestOptions requestOptions2 = new RequestOptions()
                        .circleCrop()
                        .placeholder(R.drawable.ic_default_head)
                        .error(R.drawable.ic_default_head);
                Glide.with(getContext()).asBitmap()
                        .load(CacheManager.getInstance().getUserInfo().getData().getHead_image())
                        .apply(requestOptions2)
                        .into(detailHead);


            } else {
                detailHead.setImageResource(R.drawable.ic_default_head);
            }

            tvScoreSize.setText(CacheManager.getInstance().getUserInfo().getData().getIntegral() + "分");

            tvRepacketMoney.setText(CacheManager.getInstance().getUserInfo().getData().getBalance() + "元");

            tvCouponSize.setText(CacheManager.getInstance().getUserInfo().getData().getCoupon() + "张待使用");


            tvMyDyquan.setText(CacheManager.getInstance().getUserInfo().getData().getDeduction() + "张");

            if(!TextUtils.isEmpty(CacheManager.getInstance().getUserInfo().getData().getKaipu_words()))
            {
                tvKaipuword.setText("("+CacheManager.getInstance().getUserInfo().getData().getKaipu_words()+")");
            }else {

                tvKaipuword.setText("( 开铺请到:www.qifuxingqiu.com)");
            }


        } else {
            userNickname.setText("登录|注册");
            detailHead.setImageResource(R.drawable.ic_default_head);

            tvKaipuword.setText("");
        }

    }


    @Override
    public void onResume() {
        super.onResume();

      //  bindData();

    }

    @Override
    public void showSuccess(Object object) {

        dismissLoadDialog();
        if (object instanceof UserInfoData) {
            UserInfoData data = (UserInfoData) object;
            CacheManager.getInstance().setUserInfo(data);
            refreshData();
        }


    }

    @Override
    public void showError(String msg, int code) {
        dismissLoadDialog();
        ToastUtil.show(getContext(), msg);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
