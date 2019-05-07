package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.insthub.cat.android.module2.FriendInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.model.SimpleCallback;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.model.Team;

import butterknife.Bind;


/**
 * 群申请
 * Created by linux on 2017/6/28.
 */

public class SearchGroupDetialActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


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



    private String teamId;
    private Team team;

    private FriendInfoData mFriendInfoData;



    public static void start(Context context, String teamId) {
        Intent intent = new Intent();
        intent.putExtra(ConstantsKeys.KEY_DATA, teamId);
        intent.setClass(context, SearchGroupDetialActivity.class);
        context.startActivity(intent);
    }
    private void parseIntentData() {
        teamId = getIntent().getStringExtra(ConstantsKeys.KEY_DATA);
    }

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
        commonTitleBar.setTitle("加入群组");

        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        parseIntentData();

        requestTeamInfo();

        btAdd.setText("申请加入");



    }


    /**
     * 查询群信息
     */
    private void requestTeamInfo() {
        Team t = NimUIKit.getTeamProvider().getTeamById(teamId);
        if (t != null) {
            updateTeamInfo(t);
        } else {
            NimUIKit.getTeamProvider().fetchTeamById(teamId, new SimpleCallback<Team>() {
                @Override
                public void onResult(boolean success, Team result, int code) {
                    if (success && result != null) {
                        updateTeamInfo(result);
                    }
                }
            });
        }
    }


    /**
     * 更新群信息
     *
     * @param t 群
     */
    private void updateTeamInfo(final Team t) {
        if (t == null) {
            ToastUtil.show(getContext(),R.string.team_not_exist);
            finish();
        } else {
            team = t;
            tvUsername.setText(team.getName());
            tvAddress.setText(team.getMemberCount() + "人");



        RequestOptions requestOptions2 = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.ic_default_head)
                .error(R.drawable.ic_default_head)
                ;
        Glide.with(getContext()).asBitmap()
                .load(team.getIcon())
                .apply(requestOptions2)
                .into(minePhoto);



            if (team.getType() == TeamTypeEnum.Advanced) {
                tvSex.setText(R.string.advanced_team);
            } else {
                tvSex.setText(R.string.normal_team);
            }
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


                showLoadDialog("申请加入");

                if (team != null) {
                    NIMClient.getService(TeamService.class).applyJoinTeam(team.getId(), null).setCallback(new RequestCallback<Team>() {
                        @Override
                        public void onSuccess(Team team) {
                            btAdd.setEnabled(false);
                            String toast = getString(R.string.team_join_success, team.getName());
                           ToastUtil.show(getContext(),toast);
                           dismissLoadDialog();
                           finish();
                        }

                        @Override
                        public void onFailed(int code) {

                            dismissLoadDialog();
                            if (code == 808) {
                                btAdd.setEnabled(false);

                                ToastUtil.show(getContext(), R.string.team_apply_to_join_send_success);
                            } else if (code == 809) {
                                btAdd.setEnabled(false);


                                ToastUtil.show(getContext(), R.string.has_exist_in_team);

                            } else if (code == 806) {
                                btAdd.setEnabled(false);

                                ToastUtil.show(getContext(), R.string.team_num_limit);

                            } else {
                                ToastUtil.show(getContext(), "操作失败");
                            }
                        }

                        @Override
                        public void onException(Throwable exception) {

                            exception.printStackTrace();
                        }
                    });
                }

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
}
