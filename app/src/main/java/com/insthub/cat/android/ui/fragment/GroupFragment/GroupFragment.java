package com.insthub.cat.android.ui.fragment.GroupFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.futils.TimeUtils;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.extend.pulltorefresh.PullToRefreshBase;
import com.common.extend.pulltorefresh.PullToRefreshRecyclerView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.helper.ServiceCallback;
import com.insthub.cat.android.module2.GroupListData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.activity.ServiceChatActivity;
import com.insthub.cat.android.ui.adatper.GroupAdapter;
import com.insthub.cat.android.ui.widget.DividerItemDecoration;
import com.insthub.cat.android.utils.DialogUtils;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.model.team.TeamDataChangedObserver;
import com.netease.nim.uikit.business.contact.core.item.AbsContactItem;
import com.netease.nim.uikit.business.contact.core.item.ContactItem;
import com.netease.nim.uikit.business.contact.core.item.ItemTypes;
import com.netease.nim.uikit.business.contact.core.model.ContactDataAdapter;
import com.netease.nim.uikit.business.contact.core.model.ContactGroupStrategy;
import com.netease.nim.uikit.business.contact.core.provider.ContactDataProvider;
import com.netease.nim.uikit.business.contact.core.query.IContactDataProvider;
import com.netease.nim.uikit.business.contact.core.viewholder.ContactHolder;
import com.netease.nim.uikit.business.contact.core.viewholder.LabelHolder;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.model.Team;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 群组列表
 * Created by linux on 2017/6/28.
 */

public class GroupFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View,
        AdapterView.OnItemClickListener{


    @Bind(R.id.group_list)
    ListView lvContacts;

    private ContactDataAdapter adapter;

    private int itemType= ItemTypes.TEAMS.ADVANCED_TEAM;

    public static GroupFragment newInstance() {
        GroupFragment mainFragment = new GroupFragment();
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
        return R.layout.fragment_group;
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

        GroupStrategy groupStrategy = new GroupStrategy();
        IContactDataProvider dataProvider = new ContactDataProvider(itemType);

        adapter = new ContactDataAdapter(getContext(), groupStrategy, dataProvider) {
            @Override
            protected List<AbsContactItem> onNonDataItems() {
                return null;
            }

            @Override
            protected void onPreReady() {
            }

            @Override
            protected void onPostLoad(boolean empty, String queryText, boolean all) {
            }
        };
        adapter.addViewHolder(ItemTypes.LABEL, LabelHolder.class);
        adapter.addViewHolder(ItemTypes.TEAM, ContactHolder.class);

        lvContacts.setAdapter(adapter);
        lvContacts.setOnItemClickListener(this);
        lvContacts.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                UIUtil.hideKeyboard(getActivity());
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });

        // load data
        int count = NIMClient.getService(TeamService.class).queryTeamCountByTypeBlock(itemType == ItemTypes.TEAMS
                .ADVANCED_TEAM ? TeamTypeEnum.Advanced : TeamTypeEnum.Normal);
        if (count == 0) {
            if (itemType == ItemTypes.TEAMS.ADVANCED_TEAM) {

                ToastUtil.show(getContext(), R.string.no_team);
            } else if (itemType == ItemTypes.TEAMS.NORMAL_TEAM) {

                ToastUtil.show(getContext(), R.string.no_normal_team);
            }
        }

        adapter.load(true);

        registerTeamUpdateObserver(true);
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

    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(),msg);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        registerTeamUpdateObserver(false);
        ButterKnife.unbind(this);
    }


    private static class GroupStrategy extends ContactGroupStrategy {
        GroupStrategy() {
            add(ContactGroupStrategy.GROUP_NULL, 0, ""); // 默认分组
        }

        @Override
        public String belongs(AbsContactItem item) {
            switch (item.getItemType()) {
                case ItemTypes.TEAM:
                    return GROUP_NULL;
                default:
                    return null;
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AbsContactItem item = (AbsContactItem) adapter.getItem(position);
        switch (item.getItemType()) {
            case ItemTypes.TEAM:
                SessionHelper.startTeamSession(getContext(), ((ContactItem) item).getContact().getContactId());
                break;
        }
    }


    private void registerTeamUpdateObserver(boolean register) {
        NimUIKit.getTeamChangedObservable().registerTeamDataChangedObserver(teamDataChangedObserver, register);
    }

    TeamDataChangedObserver teamDataChangedObserver = new TeamDataChangedObserver() {
        @Override
        public void onUpdateTeams(List<Team> teams) {
            adapter.load(true);
        }

        @Override
        public void onRemoveTeam(Team team) {
            adapter.load(true);
        }
    };

}
