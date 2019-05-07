package com.insthub.cat.android.ui.fragment.MessageFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.android.flog.KLog;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.dataloader.LocalXjMsgLoader;
import com.insthub.cat.android.event.HongbaoEvent;
import com.insthub.cat.android.event.XiaojingEvent;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.FriendInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.nim.extension.GuessAttachment;
import com.insthub.cat.android.nim.extension.RedPacketAttachment;
import com.insthub.cat.android.nim.extension.RedPacketOpenedAttachment;
import com.insthub.cat.android.nim.extension.SnapChatAttachment;
import com.insthub.cat.android.nim.extension.StickerAttachment;
import com.insthub.cat.android.nim.helper.TeamCreateHelper;
import com.insthub.cat.android.nim.session.SessionHelper;
import com.insthub.cat.android.ui.activity.AddFriendActivity;
import com.insthub.cat.android.ui.activity.ContactActivity;
import com.insthub.cat.android.ui.activity.FriendAddActivity;
import com.insthub.cat.android.ui.activity.FriendDetialActivity;
import com.insthub.cat.android.ui.activity.MessageXiaojingActivity;
import com.insthub.cat.android.ui.activity.P2PChatActivity;
import com.insthub.cat.android.ui.activity.SystemMsgListActivity;
import com.insthub.cat.android.ui.activity.WxLoginActivity;
import com.insthub.cat.android.ui.activity.XiaojingpushouActivity;
import com.insthub.cat.android.ui.activity.XjMsgListActivity;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.model.contact.ContactChangedObserver;
import com.netease.nim.uikit.api.model.main.OnlineStateChangeObserver;
import com.netease.nim.uikit.api.model.team.TeamDataChangedObserver;
import com.netease.nim.uikit.api.model.team.TeamMemberDataChangedObserver;
import com.netease.nim.uikit.api.model.user.UserInfoObserver;
import com.netease.nim.uikit.business.contact.selector.activity.ContactSelectActivity;
import com.netease.nim.uikit.business.recent.RecentContactsCallback;
import com.netease.nim.uikit.business.recent.RecentContactsFragment;
import com.netease.nim.uikit.business.recent.TeamMemberAitHelper;
import com.netease.nim.uikit.business.recent.adapter.RecentContactAdapter;
import com.netease.nim.uikit.business.team.helper.TeamHelper;
import com.netease.nim.uikit.business.uinfo.UserInfoHelper;
import com.netease.nim.uikit.common.badger.Badger;
import com.netease.nim.uikit.common.ui.dialog.CustomAlertDialog;
import com.netease.nim.uikit.common.ui.drop.DropCover;
import com.netease.nim.uikit.common.ui.drop.DropManager;
import com.netease.nim.uikit.common.ui.recyclerview.listener.SimpleClickListener;
import com.netease.nim.uikit.impl.NimUIKitImpl;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.ClientType;
import com.netease.nimlib.sdk.auth.OnlineClient;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.SystemMessageObserver;
import com.netease.nimlib.sdk.msg.SystemMessageService;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.msg.model.SystemMessage;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.team.model.TeamMember;
import com.zaaach.toprightmenu.MenuItem;
import com.zaaach.toprightmenu.TopRightMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by linux on 2017/6/28.
 */

public class MessageFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View {


    //创建群
    private static final int REQUEST_CODE_ADVANCED = 2;
    private static final int REQUEST_CODE_NORMAL = 1;
    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    //添加
    @Bind(R.id.iv_right)
    ImageView ivAdd;

    //通讯录
    @Bind(R.id.bt_right)
    TextView tvContact;


    @Bind(R.id.rll_system_view)
    RelativeLayout rllSystemBar;

    @Bind(R.id.tab_new_msg_label)
    TextView tabNewMsgLable;


    @Bind(R.id.status_notify_bar)
     View notifyBar;

    @Bind(R.id.status_desc_label)
     TextView notifyBarText;

    // 同时在线的其他端的信息
    private List<OnlineClient> onlineClients;

    @Bind(R.id.multiport_notify_bar)
    View multiportBar;


    // 置顶功能可直接使用，也可作为思路，供开发者充分利用RecentContact的tag字段
    public static final long RECENT_TAG_STICKY = 1; // 联系人置顶tag

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.emptyBg)
     View emptyBg;

    @Bind(R.id.message_list_empty_hint)
    TextView emptyHint;



    @Bind(R.id.rll_xiaojing_view)
    RelativeLayout rllXiaojingView;

    @Bind(R.id.tab_xj_msg_label)
    TextView tvXijingMsgLabel;



    // data
    private List<RecentContact> items = new ArrayList<>();

    private Map<String, RecentContact> cached = new HashMap<>(3); // 暂缓刷上列表的数据（未读数红点拖拽动画运行时用）

    private RecentContactAdapter adapter;

    private boolean msgLoaded = false;

    private RecentContactsCallback callback;

    private UserInfoObserver userInfoObserver;


    private Handler mHandler = new Handler(){};


    private List<RecentContact> loadedRecents = new ArrayList<>();



    public static MessageFragment newInstance() {
        MessageFragment mainFragment = new MessageFragment();
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
        return R.layout.fragment_msg;
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
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();

        ivAdd.setImageResource(R.drawable.ic_add_white_24dp);
        ivAdd.setVisibility(View.VISIBLE);
        tvContact.setVisibility(View.VISIBLE);

        adapter = new RecentContactAdapter(recyclerView, items);
        initCallBack();
        adapter.setCallback(callback);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        requestMessages(true);

    }


    @Override
    protected void bindEvent() {
        super.bindEvent();

        registerObservers(true);

        registerDropCompletedListener(true);

        //监听在线状态
        registerOnlineStateChangeListener(true);

        registerSystemMessage(true);

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              //  SessionHelper.startP2PSession(getActivity(), CacheManager.getInstance().getToken().getData().getIm_code());

                TopRightMenu mTopRightMenu = new TopRightMenu(getActivity());
                List<MenuItem> menuItems = new ArrayList<>();
                menuItems.add(new MenuItem(0, "加好友"));
                menuItems.add(new MenuItem(0, "创建群"));
                mTopRightMenu
                        .setHeight(UIUtil.dpToPx(getResources(),100))     //默认高度480
                        .setWidth(320)      //默认宽度wrap_content
                        .showIcon(false)     //显示菜单图标，默认为true
                        .dimBackground(true)        //背景变暗，默认为true
                        .needAnimationStyle(true)   //显示动画，默认为true
                        .setAnimationStyle(R.style.TRM_ANIM_STYLE)
                        .addMenuList(menuItems)
                        .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                            @Override
                            public void onMenuItemClick(int position) {


                                if(CacheManager.getInstance().getToken()==null)
                                {

                                    ToastUtil.show(getContext(),"请先登录");
                                    startActivity(WxLoginActivity.class);
                                    return ;
                                }


                                if(position==0)
                                {
                                    if(CacheManager.getInstance().getToken()!=null)
                                    {
                                        startActivity(AddFriendActivity.class);
                                    }
                                }


                                if(position==1) //创建群
                                {
                                    if(CacheManager.getInstance().getToken()!=null)
                                    {
                                        ContactSelectActivity.Option advancedOption = TeamHelper.getCreateContactSelectOption(null, 50);
                                        NimUIKit.startContactSelector(getActivity(), advancedOption, REQUEST_CODE_ADVANCED);
                                    }
                                }

                            }
                        })
                        .showAsDropDown(ivAdd, -220, 0);  //带偏移量



            }
        });

        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CacheManager.getInstance().getToken()!=null)
                {
                    startActivity(ContactActivity.class);
                }else {
                    ToastUtil.show(getActivity(),"请先登录");
                    startActivity(WxLoginActivity.class);
                }

            }
        });



        recyclerView.addOnItemTouchListener(touchListener);

        // ios style
        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        // drop listener
        DropManager.getInstance().setDropListener(new DropManager.IDropListener() {
            @Override
            public void onDropBegin() {
                touchListener.setShouldDetectGesture(false);
            }

            @Override
            public void onDropEnd() {
                touchListener.setShouldDetectGesture(true);
            }
        });


        rllSystemBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(CacheManager.getInstance().getToken()!=null)
                {
                    startActivity(SystemMsgListActivity.class);
                }else {
                    ToastUtil.show(getActivity(),"请先登录");
                    startActivity(WxLoginActivity.class);
                }
            }
        });


        rllXiaojingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(CacheManager.getInstance().getToken()!=null)
                {
                    startActivity(XjMsgListActivity.class);
                }else {
                    ToastUtil.show(getActivity(),"请先登录");
                    startActivity(WxLoginActivity.class);
                }



                //XjMsgListActivity
            }
        });




        //注册 小鲸事件
        subscribeXJMSGEvent();
//        fragment.setCallback(new RecentContactsCallback() {
//            @Override
//            public void onRecentContactsLoaded() {
//                // 最近联系人列表加载完毕
//            }
//
//            @Override
//            public void onUnreadCountChange(int unreadCount) {
//
//                // ReminderManager.getInstance().updateSessionUnreadNum(unreadCount);
//            }
//
//            @Override
//            public void onItemClick(RecentContact recent) {
//                // 回调函数，以供打开会话窗口时传入定制化参数，或者做其他动作
//                switch (recent.getSessionType()) {
//                    case P2P:
//                        SessionHelper.startP2PSession(getActivity(), recent.getContactId());
//                        break;
//                    case Team: //群组
//                        // SessionHelper.startTeamSession(getActivity(), recent.getContactId());
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//            @Override
//            public String getDigestOfAttachment(RecentContact recentContact, MsgAttachment attachment) {
//                // 设置自定义消息的摘要消息，展示在最近联系人列表的消息缩略栏上
//                // 当然，你也可以自定义一些内建消息的缩略语，例如图片，语音，音视频会话等，自定义的缩略语会被优先使用。
//                if (attachment instanceof GuessAttachment) {
//                    GuessAttachment guess = (GuessAttachment) attachment;
//                    return guess.getValue().getDesc();
//                } else if (attachment instanceof StickerAttachment) {
//                    return "[贴图]";
//                } else if (attachment instanceof SnapChatAttachment) {
//                    return "[阅后即焚]";
//                } else if (attachment instanceof RedPacketAttachment) {
//                    return "[红包]";
//                } else if (attachment instanceof RedPacketOpenedAttachment) {
//                    return ((RedPacketOpenedAttachment) attachment).getDesc(recentContact.getSessionType(), recentContact.getContactId());
//                }
//
//                return null;
//            }
//
//            @Override
//            public String getDigestOfTipMsg(RecentContact recent) {
//                String msgId = recent.getRecentMessageId();
//                List<String> uuids = new ArrayList<>(1);
//                uuids.add(msgId);
//                List<IMMessage> msgs = NIMClient.getService(MsgService.class).queryMessageListByUuidBlock(uuids);
//                if (msgs != null && !msgs.isEmpty()) {
//                    IMMessage msg = msgs.get(0);
//                    Map<String, Object> content = msg.getRemoteExtension();
//                    if (content != null && !content.isEmpty()) {
//                        return (String) content.get("content");
//                    }
//                }
//
//                return null;
//            }
//        });






    }

    @Override
    protected void bindData() {
        super.bindData();
        getSystemFriendMsgCount();
        requestMessages(true);
        KLog.i("bindData刷新消息列表");

    }

    @Override
    public void onResume() {
        super.onResume();


        KLog.i("onResume 刷新消息列表");
    }


     //加载消息
    private void requestMessages(boolean delay) {



        if(CacheManager.getInstance().getToken()==null)
        {
            msgLoaded=false;
            items.clear();
            adapter.notifyDataSetChanged();
            cacheMessages.clear();
            refreshMessages(true);
            return;
        }

        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
//                if (msgLoaded) {
//                    return;
//                }
                // 查询最近联系人列表数据
                NIMClient.getService(MsgService.class).queryRecentContacts().setCallback(new RequestCallbackWrapper<List<RecentContact>>() {

                    @Override
                    public void onResult(int code, List<RecentContact> recents, Throwable exception) {
                        if (code != ResponseCode.RES_SUCCESS || recents == null) {
                            return;
                        }

                        KLog.i("返回最近联系人 数据111111");
                        loadedRecents = recents;
                        // 初次加载，更新离线的消息中是否有@我的消息
                        for (RecentContact loadedRecent : loadedRecents) {
                            if (loadedRecent.getSessionType() == SessionTypeEnum.Team) {
                                updateOfflineContactAited(loadedRecent);
                            }
                        }
                        // 此处如果是界面刚初始化，为了防止界面卡顿，可先在后台把需要显示的用户资料和群组资料在后台加载好，然后再刷新界面
                        //
                        msgLoaded = true;
                        if (isAdded()) {
                            onRecentContactsLoaded();
                        }
                    }
                });
            }
        }, delay ? 250 : 0);
    }


    //加载完成
    private void onRecentContactsLoaded() {
        items.clear();
        if (loadedRecents != null) {
            items.addAll(loadedRecents);
            loadedRecents = null;
        }
        refreshMessages(true);

        if (callback != null) {
            callback.onRecentContactsLoaded();
        }
    }







    private void initCallBack() {
        if (callback != null) {
            return;
        }
        callback = new RecentContactsCallback() {
            @Override
            public void onRecentContactsLoaded() {

            }

            @Override
            public void onUnreadCountChange(int unreadCount) {

            }

            @Override
            public void onItemClick(RecentContact recent) {


                if (recent.getSessionType() == SessionTypeEnum.Team) {

                    NimUIKit.startTeamSession(getActivity(), recent.getContactId());

                } else if (recent.getSessionType() == SessionTypeEnum.P2P) {

                    NimUIKit.startP2PSession(getActivity(), recent.getContactId());
                }
            }

            @Override
            public String getDigestOfAttachment(RecentContact recentContact, MsgAttachment attachment) {
                return null;
            }

            @Override
            public String getDigestOfTipMsg(RecentContact recent) {
                return null;
            }
        };
    }

    private SimpleClickListener<RecentContactAdapter> touchListener = new SimpleClickListener<RecentContactAdapter>() {
        @Override
        public void onItemClick(RecentContactAdapter adapter, View view, int position) {
            if (callback != null) {
                RecentContact recent = adapter.getItem(position);
                callback.onItemClick(recent);
            }
        }

        @Override
        public void onItemLongClick(RecentContactAdapter adapter, View view, int position) {
            showLongClickMenu(adapter.getItem(position), position);
        }

        @Override
        public void onItemChildClick(RecentContactAdapter adapter, View view, int position) {

        }

        @Override
        public void onItemChildLongClick(RecentContactAdapter adapter, View view, int position) {

        }
    };

    /**
     * 在线状态
     * @param register
     */
    private void registerOnlineStateChangeListener(boolean register) {
        if (!NimUIKitImpl.enableOnlineState()) {
            return;
        }
        NimUIKitImpl.getOnlineStateChangeObservable().registerOnlineStateChangeListeners(onlineStateChangeObserver, register);
    }

    OnlineStateChangeObserver onlineStateChangeObserver = new OnlineStateChangeObserver() {
        @Override
        public void onlineStateChange(Set<String> accounts) {
            notifyDataSetChanged();
        }
    };


    /**
     * 长按事件
     * @param recent
     * @param position
     */
    private void showLongClickMenu(final RecentContact recent, final int position) {
        CustomAlertDialog alertDialog = new CustomAlertDialog(getActivity());
        alertDialog.setTitle(UserInfoHelper.getUserTitleName(recent.getContactId(), recent.getSessionType()));
        String title = getString(com.netease.nim.uikit.R.string.main_msg_list_delete_chatting);


        boolean isMyFriend = NIMClient.getService(FriendService.class).isMyFriend(recent.getContactId());

        if(!isMyFriend)
        {
            alertDialog.addItem("添加好友", new CustomAlertDialog.onSeparateItemClickListener() {
                @Override
                public void onClick() {
                    // 删除会话，删除后，消息历史被一起删除

                    FriendInfoData.DataBean mFriendInfoData = new FriendInfoData.DataBean();
                    mFriendInfoData.setAccid(recent.getContactId());
                    mFriendInfoData.setUser_name(recent.getFromNick());

                    FriendInfoData item = new FriendInfoData();
                    item.setData(mFriendInfoData);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ConstantsKeys.KEY_DATA,item);
                    startActivity(FriendDetialActivity.class,bundle);
                }
            });

        }


        alertDialog.addItem(title, new CustomAlertDialog.onSeparateItemClickListener() {
            @Override
            public void onClick() {
                // 删除会话，删除后，消息历史被一起删除
                NIMClient.getService(MsgService.class).deleteRecentContact(recent);
                NIMClient.getService(MsgService.class).clearChattingHistory(recent.getContactId(), recent.getSessionType());
                adapter.remove(position);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        refreshMessages(true);
                    }
                });
            }
        });

        title = (isTagSet(recent, RECENT_TAG_STICKY) ? getString(com.netease.nim.uikit.R.string.main_msg_list_clear_sticky_on_top) : getString(com.netease.nim.uikit.R.string.main_msg_list_sticky_on_top));
        alertDialog.addItem(title, new CustomAlertDialog.onSeparateItemClickListener() {
            @Override
            public void onClick() {
                if (isTagSet(recent, RECENT_TAG_STICKY)) {
                    removeTag(recent, RECENT_TAG_STICKY);
                } else {
                    addTag(recent, RECENT_TAG_STICKY);
                }
                NIMClient.getService(MsgService.class).updateRecent(recent);

                refreshMessages(false);
            }
        });

        alertDialog.addItem("删除该聊天（仅服务器）", new CustomAlertDialog.onSeparateItemClickListener() {
            @Override
            public void onClick() {
                NIMClient.getService(MsgService.class)
                        .deleteRoamingRecentContact(recent.getContactId(), recent.getSessionType())
                        .setCallback(new RequestCallback<Void>() {
                            @Override
                            public void onSuccess(Void param) {
                                Toast.makeText(getActivity(), "delete success", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailed(int code) {
                                Toast.makeText(getActivity(), "delete failed, code:" + code, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onException(Throwable exception) {

                            }
                        });
            }
        });
        alertDialog.show();
    }





    private void addTag(RecentContact recent, long tag) {
        tag = recent.getTag() | tag;
        recent.setTag(tag);
    }

    private void removeTag(RecentContact recent, long tag) {
        tag = recent.getTag() & ~tag;
        recent.setTag(tag);
    }

    private boolean isTagSet(RecentContact recent, long tag) {
        return (recent.getTag() & tag) == tag;
    }





//    /**
//     * 用户状态变化
//     */
//    Observer<StatusCode> userStatusObserver = new Observer<StatusCode>() {
//
//        @Override
//        public void onEvent(StatusCode code) {
//
//            KLog.i("111111666666");
//
//
//            if (code.wontAutoLogin()) {
//                //kickOut(code);
//                //登出
//                cacheMessages.clear();
//                refreshMessages(true);
//
//            } else {
//                cacheMessages.clear();
//                refreshMessages(true);
//                if (code == StatusCode.NET_BROKEN) {
//                    notifyBar.setVisibility(View.VISIBLE);
//                    notifyBarText.setText(R.string.net_broken);
//                } else if (code == StatusCode.UNLOGIN) {
//                    notifyBar.setVisibility(View.VISIBLE);
//                    notifyBarText.setText(R.string.nim_status_unlogin);
//                    requestMessages(true);
//                } else if (code == StatusCode.CONNECTING) {
//                    notifyBar.setVisibility(View.VISIBLE);
//                    notifyBarText.setText(R.string.nim_status_connecting);
//                } else if (code == StatusCode.LOGINING) {
//                    notifyBar.setVisibility(View.VISIBLE);
//                    notifyBarText.setText(R.string.nim_status_logining);
//                } else
//                {
//                    notifyBar.setVisibility(View.GONE);
//
//                }
//            }
//        }
//    };



    private void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
        boolean empty = items.isEmpty() && msgLoaded;
        emptyBg.setVisibility(empty ? View.VISIBLE : View.GONE);
//        emptyHint.setHint("还没有会话，在通讯录中找个人聊聊吧！");
    }

//    private void kickOut(StatusCode code) {
//        Preferences.saveUserToken("");
//
//        if (code == StatusCode.PWD_ERROR) {
//            LogUtil.e("Auth", "user password error");
//            Toast.makeText(getActivity(), R.string.login_failed, Toast.LENGTH_SHORT).show();
//        } else {
//            LogUtil.i("Auth", "Kicked!");
//        }
//        onLogout();
//    }
//
//    // 注销
//    private void onLogout() {
//        // 清理缓存&注销监听&清除状态
//        LogoutHelper.logout();
//
//        LoginActivity.start(getActivity(), true);
//        getActivity().finish();
//    }
//


    Observer<List<OnlineClient>> clientsObserver = new Observer<List<OnlineClient>>() {
        @Override
        public void onEvent(List<OnlineClient> onlineClients) {
            MessageFragment.this.onlineClients = onlineClients;
            if (onlineClients == null || onlineClients.size() == 0) {
                multiportBar.setVisibility(View.GONE);
                items.clear();
                adapter.notifyDataSetChanged();
            } else {
                multiportBar.setVisibility(View.GONE);
                TextView status = (TextView) multiportBar.findViewById(R.id.multiport_desc_label);
                OnlineClient client = onlineClients.get(0);
                switch (client.getClientType()) {
                    case ClientType.Windows:
                    case ClientType.MAC:
                        // status.setText(getString(R.string.multiport_logging) + getString(R.string.computer_version));
                        break;
                    case ClientType.Web:
                        // status.setText(getString(R.string.multiport_logging) + getString(R.string.web_version));
                        break;
                    case ClientType.iOS:
                    case ClientType.Android:
                        //  status.setText(getString(R.string.multiport_logging) + getString(R.string.mobile_version));
                        break;
                    default:
                        multiportBar.setVisibility(View.GONE);
                        break;
                }
            }
        }
    };






    @Override
    public void onDestroy() {
        super.onDestroy();
        registerObservers(false);
        registerSystemMessage(false);
    }

    @Override
    public void showSuccess(Object object) {

    }

    @Override
    public void showError(String msg, int code) {
        dismissLoadDialog();
        ToastUtil.show(getContext(), msg);
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







    private void refreshMessages(boolean unreadChanged) {
        sortRecentContacts(items);
        notifyDataSetChanged();
        if (unreadChanged) {

            // 方式一：累加每个最近联系人的未读（快）

            int unreadNum = 0;
            for (RecentContact r : items) {
                unreadNum += r.getUnreadCount();
            }

            // 方式二：直接从SDK读取（相对慢）
            //int unreadNum = NIMClient.getService(MsgService.class).getTotalUnreadCount();

            if (callback != null) {
                callback.onUnreadCountChange(unreadNum);
            }

            Badger.updateBadgerCount(unreadNum);
        }
    }








    /**
     * ********************** 收消息，处理状态变化 ************************
     */
    private void registerObservers(boolean register) {

        //登录  在线状态
        NIMClient.getService(AuthServiceObserver.class).observeOtherClients(clientsObserver, register);
//        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(userStatusObserver, register);
        MsgServiceObserve service = NIMClient.getService(MsgServiceObserve.class);
        service.observeReceiveMessage(messageReceiverObserver, register);
        service.observeRecentContact(messageObserver, register);
        service.observeMsgStatus(statusObserver, register);
        service.observeRecentContactDeleted(deleteObserver, register);
        registerTeamUpdateObserver(register);
        registerTeamMemberUpdateObserver(register);
        NimUIKit.getContactChangedObservable().registerObserver(friendDataChangedObserver, register);
        if (register) {
            registerUserInfoObserver();
        } else {
            unregisterUserInfoObserver();
        }
    }

    /**
     * 注册群信息&群成员更新监听
     */
    private void registerTeamUpdateObserver(boolean register) {
        NimUIKit.getTeamChangedObservable().registerTeamDataChangedObserver(teamDataChangedObserver, register);
    }

    private void registerTeamMemberUpdateObserver(boolean register) {
        NimUIKit.getTeamChangedObservable().registerTeamMemberDataChangedObserver(teamMemberDataChangedObserver, register);
    }

    private void registerDropCompletedListener(boolean register) {
        if (register) {
            DropManager.getInstance().addDropCompletedListener(dropCompletedListener);
        } else {
            DropManager.getInstance().removeDropCompletedListener(dropCompletedListener);
        }
    }

    // 暂存消息，当RecentContact 监听回来时使用，结束后清掉
    private Map<String, Set<IMMessage>> cacheMessages = new HashMap<>();

    //监听在线消息中是否有@我
    private Observer<List<IMMessage>> messageReceiverObserver = new Observer<List<IMMessage>>() {
        @Override
        public void onEvent(List<IMMessage> imMessages) {
            if (imMessages != null) {
                for (IMMessage imMessage : imMessages) {
                    if (!TeamMemberAitHelper.isAitMessage(imMessage)) {
                        continue;
                    }
                    Set<IMMessage> cacheMessageSet = cacheMessages.get(imMessage.getSessionId());
                    if (cacheMessageSet == null) {
                        cacheMessageSet = new HashSet<>();
                        cacheMessages.put(imMessage.getSessionId(), cacheMessageSet);
                    }
                    cacheMessageSet.add(imMessage);
                }
            }
        }
    };

    Observer<List<RecentContact>> messageObserver = new Observer<List<RecentContact>>() {
        @Override
        public void onEvent(List<RecentContact> recentContacts) {
            if (!DropManager.getInstance().isTouchable()) {
                // 正在拖拽红点，缓存数据
                for (RecentContact r : recentContacts) {
                    cached.put(r.getContactId(), r);
                }

                return;
            }

            onRecentContactChanged(recentContacts);
        }
    };

    private void onRecentContactChanged(List<RecentContact> recentContacts) {
        int index;
        for (RecentContact r : recentContacts) {
            index = -1;
            for (int i = 0; i < items.size(); i++) {
                if (r.getContactId().equals(items.get(i).getContactId())
                        && r.getSessionType() == (items.get(i).getSessionType())) {
                    index = i;
                    break;
                }
            }

            if (index >= 0) {
                items.remove(index);
            }

            items.add(r);
            if (r.getSessionType() == SessionTypeEnum.Team && cacheMessages.get(r.getContactId()) != null) {
                TeamMemberAitHelper.setRecentContactAited(r, cacheMessages.get(r.getContactId()));
            }
        }
        cacheMessages.clear();
        refreshMessages(true);
    }

    DropCover.IDropCompletedListener dropCompletedListener = new DropCover.IDropCompletedListener() {
        @Override
        public void onCompleted(Object id, boolean explosive) {
            if (cached != null && !cached.isEmpty()) {
                // 红点爆裂，已经要清除未读，不需要再刷cached
                if (explosive) {
                    if (id instanceof RecentContact) {
                        RecentContact r = (RecentContact) id;
                        cached.remove(r.getContactId());
                    } else if (id instanceof String && ((String) id).contentEquals("0")) {
                        cached.clear();
                    }
                }

                // 刷cached
                if (!cached.isEmpty()) {
                    List<RecentContact> recentContacts = new ArrayList<>(cached.size());
                    recentContacts.addAll(cached.values());
                    cached.clear();

                    onRecentContactChanged(recentContacts);
                }
            }
        }
    };

    Observer<IMMessage> statusObserver = new Observer<IMMessage>() {
        @Override
        public void onEvent(IMMessage message) {
            int index = getItemIndex(message.getUuid());
            if (index >= 0 && index < items.size()) {
                RecentContact item = items.get(index);
                item.setMsgStatus(message.getStatus());
                refreshViewHolderByIndex(index);
            }
        }
    };

    Observer<RecentContact> deleteObserver = new Observer<RecentContact>() {
        @Override
        public void onEvent(RecentContact recentContact) {
            if (recentContact != null) {
                for (RecentContact item : items) {
                    if (TextUtils.equals(item.getContactId(), recentContact.getContactId())
                            && item.getSessionType() == recentContact.getSessionType()) {
                        items.remove(item);
                        refreshMessages(true);
                        break;
                    }
                }
            } else {
                items.clear();
                refreshMessages(true);
            }
        }
    };

    TeamDataChangedObserver teamDataChangedObserver = new TeamDataChangedObserver() {

        @Override
        public void onUpdateTeams(List<Team> teams) {
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onRemoveTeam(Team team) {

        }
    };

    TeamMemberDataChangedObserver teamMemberDataChangedObserver = new TeamMemberDataChangedObserver() {
        @Override
        public void onUpdateTeamMember(List<TeamMember> members) {
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onRemoveTeamMember(List<TeamMember> member) {

        }
    };

    private int getItemIndex(String uuid) {
        for (int i = 0; i < items.size(); i++) {
            RecentContact item = items.get(i);
            if (TextUtils.equals(item.getRecentMessageId(), uuid)) {
                return i;
            }
        }

        return -1;
    }

    protected void refreshViewHolderByIndex(final int index) {
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                adapter.notifyItemChanged(index);
            }
        });
    }

    public void setCallback(RecentContactsCallback callback) {
        this.callback = callback;
    }

    private void registerUserInfoObserver() {
        if (userInfoObserver == null) {
            userInfoObserver = new UserInfoObserver() {
                @Override
                public void onUserInfoChanged(List<String> accounts) {
                    refreshMessages(false);
                }
            };
        }
        NimUIKit.getUserInfoObservable().registerObserver(userInfoObserver, true);
    }

    private void unregisterUserInfoObserver() {
        if (userInfoObserver != null) {
            NimUIKit.getUserInfoObservable().registerObserver(userInfoObserver, false);
        }
    }

    ContactChangedObserver friendDataChangedObserver = new ContactChangedObserver() {
        @Override
        public void onAddedOrUpdatedFriends(List<String> accounts) {
            refreshMessages(false);
        }

        @Override
        public void onDeletedFriends(List<String> accounts) {
            refreshMessages(false);
        }

        @Override
        public void onAddUserToBlackList(List<String> account) {
            refreshMessages(false);
        }

        @Override
        public void onRemoveUserFromBlackList(List<String> account) {
            refreshMessages(false);
        }
    };

    private void updateOfflineContactAited(final RecentContact recentContact) {
        if (recentContact == null || recentContact.getSessionType() != SessionTypeEnum.Team
                || recentContact.getUnreadCount() <= 0) {
            return;
        }

        // 锚点
        List<String> uuid = new ArrayList<>(1);
        uuid.add(recentContact.getRecentMessageId());

        List<IMMessage> messages = NIMClient.getService(MsgService.class).queryMessageListByUuidBlock(uuid);

        if (messages == null || messages.size() < 1) {
            return;
        }
        final IMMessage anchor = messages.get(0);

        // 查未读消息
        NIMClient.getService(MsgService.class).queryMessageListEx(anchor, QueryDirectionEnum.QUERY_OLD,
                recentContact.getUnreadCount() - 1, false).setCallback(new RequestCallbackWrapper<List<IMMessage>>() {

            @Override
            public void onResult(int code, List<IMMessage> result, Throwable exception) {
                if (code == ResponseCode.RES_SUCCESS && result != null) {
                    result.add(0, anchor);
                    Set<IMMessage> messages = null;
                    // 过滤存在的@我的消息
                    for (IMMessage msg : result) {
                        if (TeamMemberAitHelper.isAitMessage(msg)) {
                            if (messages == null) {
                                messages = new HashSet<>();
                            }
                            messages.add(msg);
                        }
                    }

                    // 更新并展示
                    if (messages != null) {
                        TeamMemberAitHelper.setRecentContactAited(recentContact, messages);
                        notifyDataSetChanged();
                    }
                }
            }
        });

    }

    /**
     * **************************** 监听系统消息 ***********************************
     */


    private void registerSystemMessage(boolean enable)
    {

        NIMClient.getService(SystemMessageObserver.class)
                .observeReceiveSystemMsg(new Observer<SystemMessage>() {
                    @Override
                    public void onEvent(SystemMessage message) {
                        // 收到系统通知，可以做相应操作
                        getSystemFriendMsgCount();

                        KLog.i("收到系统通知111111");

                    }
                }, enable);


        //监听系统未读消息数量
        NIMClient.getService(SystemMessageObserver.class)
                .observeUnreadCountChange(sysMsgUnreadCountChangedObserver, enable);

    }


    /**
     * 获取好友关系消息数量总和
     */
    private void getSystemFriendMsgCount()
    {
        List<SystemMessageType> types = new ArrayList<>();
        types.add(SystemMessageType.AddFriend);

        // 查询“添加好友”类型的系统通知未读数总和
        int unread = NIMClient.getService(SystemMessageService.class)
                .querySystemMessageUnreadCountByType(types);

        if(unread>0)
        {
            tabNewMsgLable.setVisibility(View.VISIBLE);
            tabNewMsgLable.setText(String.valueOf(unread)+"");
        }else
        {
            tabNewMsgLable.setVisibility(View.GONE);
        }

    }


    private Observer<Integer> sysMsgUnreadCountChangedObserver = new Observer<Integer>() {
        @Override
        public void onEvent(Integer unreadCount) {
            // 更新未读数变化
            KLog.i("界面更新未读消息：");
            getSystemFriendMsgCount();
        }
    };








    /**
     * **************************** 排序 ***********************************
     */
    private void sortRecentContacts(List<RecentContact> list) {
        if (list.size() == 0) {
            return;
        }
        Collections.sort(list, comp);
    }

    private static Comparator<RecentContact> comp = new Comparator<RecentContact>() {

        @Override
        public int compare(RecentContact o1, RecentContact o2) {
            // 先比较置顶tag
            long sticky = (o1.getTag() & RECENT_TAG_STICKY) - (o2.getTag() & RECENT_TAG_STICKY);
            if (sticky != 0) {
                return sticky > 0 ? -1 : 1;
            } else {
                long time = o1.getTime() - o2.getTime();
                return time == 0 ? 0 : (time > 0 ? -1 : 1);
            }
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_NORMAL) {
                final ArrayList<String> selected = data.getStringArrayListExtra(ContactSelectActivity.RESULT_DATA);
                if (selected != null && !selected.isEmpty()) {
                    TeamCreateHelper.createNormalTeam(getActivity(), selected, false, null);
                } else {
                   ToastUtil.show(getContext(),"请选择至少一个联系人！");
                }
            } else if (requestCode == REQUEST_CODE_ADVANCED) {
                final ArrayList<String> selected = data.getStringArrayListExtra(ContactSelectActivity.RESULT_DATA);
                TeamCreateHelper.createAdvancedTeam(getActivity(), selected);
            }
        }
    }







    /**
     * 小鲸消息事件
     */
    private void subscribeXJMSGEvent() {
        Subscription subscription = RxBusManager.getInstance()
                .toObservable(XiaojingEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<XiaojingEvent>() {
                    @Override
                    public void call(XiaojingEvent event) {

                        if(CacheManager.getInstance().getToken()!=null)
                        {

                              int size = LocalXjMsgLoader.getUnReadCount(getContext(),CacheManager.getInstance().getToken().getData().getUser_id());

                              if(size>0)
                              {
                                  tvXijingMsgLabel.setText(size+"");
                                  tvXijingMsgLabel.setVisibility(View.VISIBLE);
                              }else
                              {
                                  tvXijingMsgLabel.setVisibility(View.GONE);
                              }
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        throwable.printStackTrace();
                    }
                });
        RxBusManager.getInstance().addSubscription(this, subscription);
    }




}
