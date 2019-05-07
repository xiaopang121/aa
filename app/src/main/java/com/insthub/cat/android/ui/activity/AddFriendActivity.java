package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.module2.FriendInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.model.Team;

import butterknife.Bind;


/**
 * 添加好友
 * Created by linux on 2017/6/28.
 */

public class AddFriendActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.bt_search)
    Button btSearch;

    @Bind(R.id.tv_search)
    EditText tvSearch;

    @Bind(R.id.tv_search_person)
    TextView tvSearchPerson;

    @Bind(R.id.tv_search_group)
    TextView tvSearchGroup;

    @Bind(R.id.lly_find_bar)
    LinearLayout  llyFindBar;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_friend;
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
        llyFindBar.setVisibility(View.GONE);
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


        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                llyFindBar.setVisibility(View.VISIBLE);
                tvSearchPerson.setText("找人："+tvSearch.getText().toString());
                tvSearchGroup.setText("找群："+tvSearch.getText().toString());
            }
        });



        tvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                if(TextUtils.isEmpty(tvSearch.getText().toString()))
                {
                    llyFindBar.setVisibility(View.GONE);
                }
                else
                {
                    tvSearchPerson.setText("找人："+tvSearch.getText().toString());
                    tvSearchGroup.setText("找群："+tvSearch.getText().toString());
                }

            }
        });


        tvSearchPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadDialog("正在查询数据");
                mPresenter.getImInfo("","",tvSearch.getText().toString());

            }
        });


        tvSearchGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadDialog("正在查询数据");
                NIMClient.getService(TeamService.class).searchTeam(tvSearch.getText().toString()).setCallback(new RequestCallback<Team>() {
                    @Override
                    public void onSuccess(Team team)
                    {

                        dismissLoadDialog();
                        updateTeamInfo(team);
                    }

                    @Override
                    public void onFailed(int code) {
                        dismissLoadDialog();
                        if (code == 803) {
                            ToastUtil.show(getContext(),R.string.team_number_not_exist);
                        } else {

                            ToastUtil.show(getContext(),"搜索失败");
                        }
                    }

                    @Override
                    public void onException(Throwable exception) {
                        dismissLoadDialog();
                        ToastUtil.show(getContext(),"搜索失败");
                    }
                });
            }
        });

    }
    /**
     * 搜索群组成功的回调
     *
     * @param team 群
     */
    private void updateTeamInfo(Team team) {
        if (team.getId().equals(tvSearch.getText().toString())) {
            SearchGroupDetialActivity.start(getContext(), team.getId());

        }

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


        dismissLoadDialog();


        if(object instanceof  FriendInfoData)
        {
            FriendInfoData mFriendInfoData = (FriendInfoData)object;
            Bundle bundle = new Bundle();
            bundle.putSerializable(ConstantsKeys.KEY_DATA,mFriendInfoData);
            startActivity(FriendDetialActivity.class,bundle);

        }

    }

    @Override
    public void showError(String msg, int code) {
        dismissLoadDialog();
        ToastUtil.show(this,msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


}
