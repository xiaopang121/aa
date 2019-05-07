package com.insthub.cat.android.ui.fragment.HomeFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.common.android.flog.KLog;
import com.common.android.ftheme.widgets.TintRadioButton;
import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.fui.widget.CustomViewPager;
import com.common.android.futils.UIUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.event.ActionProductEvent;
import com.insthub.cat.android.ui.adatper.CommonFragmentAdatper;
import com.insthub.cat.android.ui.fragment.DiscoverFragment.DiscoverFragment;
import com.insthub.cat.android.ui.fragment.IndexFragment.IndexFragment;
import com.insthub.cat.android.ui.fragment.MessageFragment.Fragment_message;
import com.insthub.cat.android.ui.fragment.MessageFragment.MessageFragment;
import com.insthub.cat.android.ui.fragment.UserFragment.UserFragment;
import com.insthub.cat.android.ui.widget.BadgeView;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.SystemMessageObserver;
import com.netease.nimlib.sdk.msg.SystemMessageService;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MessageReceipt;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.msg.model.SystemMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by linux on 2017/6/28.
 */

public class HomeFragment extends BaseFragment {


    @Bind(R.id.viewpager)
    CustomViewPager viewpager;

    @Bind(R.id.menu_group)
    RadioGroup mMenuRadioGroup;
    @Bind(R.id.menu_local)
    TintRadioButton menuLocal;
    @Bind(R.id.menu_discover)
    TintRadioButton menuDiscover;
    @Bind(R.id.menu_msg)
    TintRadioButton menuMsg;
    @Bind(R.id.menu_me)
    TintRadioButton menuMe;


    private TextView tx;


    //未读消息数量显示
    private BadgeView nuReadMsgBadgeView;


    private    CommonFragmentAdatper adapter;


    public static HomeFragment newInstance() {
        HomeFragment mainFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void bindViewById() {

        if (viewpager != null) {
            setupViewPager(viewpager);
            viewpager.setOffscreenPageLimit(4);
            viewpager.setCurrentItem(0);
            // viewpager.setScrollble(false);
        }
        getSystemFriendMsgCount();
    }

    private void setupViewPager(ViewPager viewPager) {
         adapter = new CommonFragmentAdatper(getChildFragmentManager());
        adapter.addFragment(IndexFragment.newInstance(), this.getString(R.string.menu_index));
        adapter.addFragment(DiscoverFragment.newInstance(), this.getString(R.string.menu_discover));
        adapter.addFragment(Fragment_message.newInstance(), this.getString(R.string.menu_msg));
        adapter.addFragment(UserFragment.newInstance(), this.getString(R.string.menu_user));
        viewPager.setAdapter(adapter);
    }


    @Override
    protected void bindEvent() {
        registerSystemMessage(true);
        mMenuRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.menu_local:
                        viewpager.setCurrentItem(0);
                        menuMsg.setChecked(false);
                        break;
                    case R.id.menu_discover:
                        viewpager.setCurrentItem(1);
                        menuMsg.setChecked(false);
                        break;
                    case R.id.menu_msg:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.menu_me:
                        viewpager.setCurrentItem(3);
                        menuMsg.setChecked(false);
                        break;
                }
            }
        });


        menuMsg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    viewpager.setCurrentItem(2);
                    mMenuRadioGroup.check(R.id.menu_msg);
                }
            }
        });


        subscribeActionProductEvent();

    }

    @Override
    protected int bindColorPrimary() {
        return R.color.theme_color_primary;
    }

    private void registerSystemMessage(boolean enable)
    {

        NIMClient.getService(SystemMessageObserver.class)
                .observeReceiveSystemMsg(new Observer<SystemMessage>() {
                    @Override
                    public void onEvent(SystemMessage message) {
                        // 收到系统通知，可以做相应操作
                        getSystemFriendMsgCount();
                    }
                }, enable);




        //监听系统未读消息数量
        NIMClient.getService(SystemMessageObserver.class)
                .observeUnreadCountChange(sysMsgUnreadCountChangedObserver, enable);


        NIMClient.getService(MsgServiceObserve.class).observeMsgStatus(new Observer<IMMessage>() {
            @Override
            public void onEvent(IMMessage imMessage) {
                getSystemFriendMsgCount();
            }
        },true);


        NIMClient.getService(MsgServiceObserve.class).observeMessageReceipt(new Observer<List<MessageReceipt>>() {
            @Override
            public void onEvent(List<MessageReceipt> messageReceipts) {

                getSystemFriendMsgCount();
            }
        },true);

        //  注册/注销观察者
        NIMClient.getService(MsgServiceObserve.class)
                .observeRecentContact(messageObserver, true);

    }



    Observer<List<RecentContact>> messageObserver =
            new Observer<List<RecentContact>>() {
                @Override
                public void onEvent(List<RecentContact> messages) {

                    getSystemFriendMsgCount();
                }
            };



    private Observer<Integer> sysMsgUnreadCountChangedObserver = new Observer<Integer>() {
        @Override
        public void onEvent(Integer unreadCount) {
            // 更新未读数变化

            KLog.i("更新buttomview  消息数量");
            getSystemFriendMsgCount();
        }
    };


    @Override
    public void onResume() {
        super.onResume();


    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser)
        {
            getSystemFriendMsgCount();
        }
    }

    /**
     * 获取好友关系消息数量总和
     */
    private void getSystemFriendMsgCount()
    {
        int unread = NIMClient.getService(SystemMessageService.class)
                .querySystemMessageUnreadCountBlock();


        unread = unread+   NIMClient.getService(MsgService.class).getTotalUnreadCount();

        if(nuReadMsgBadgeView==null){
            nuReadMsgBadgeView = new BadgeView(getActivity());
            nuReadMsgBadgeView.setFocusable(false);
            nuReadMsgBadgeView.setBackground(10, Color.parseColor("#FF0000"));
            nuReadMsgBadgeView.setBadgeGravity(Gravity.RIGHT | Gravity.TOP);
            nuReadMsgBadgeView.setBadgeMargin(0,0, UIUtil.dpToPx(getResources(),3),0);
            nuReadMsgBadgeView.setTargetView(menuMsg);
            nuReadMsgBadgeView.setShadowLayer(2, -1, -1, Color.GRAY);
            nuReadMsgBadgeView.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {

                                   mMenuRadioGroup.check(R.id.menu_msg);
                               }
                           });

        }

        KLog.i("更新buttomview  消息数量:"+unread);



        if(unread>0)
        {
            nuReadMsgBadgeView.setVisibility(View.VISIBLE);
            nuReadMsgBadgeView.setBadgeCount(unread>99 ? 99 :unread);
        }else
        {
            nuReadMsgBadgeView.setVisibility(View.GONE);
            //nuReadMsgBadgeView.setOnClickListener(null);
        }

    }

    /**
     * 注册活动产品事件
     */
    private void subscribeActionProductEvent() {
        Subscription subscription = RxBusManager.getInstance()
                .toObservable(ActionProductEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .debounce(1, TimeUnit.SECONDS)
                .subscribe(new Action1<ActionProductEvent>() {
                    @Override
                    public void call(ActionProductEvent event) {


                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        RxBusManager.getInstance().addSubscription(this, subscription);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        registerSystemMessage(false);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        KLog.i("home fragment onactivity result");
        if(adapter!=null)
        {
            for(int x=0;x<adapter.ugetFragments().size();x++)
            {
                adapter.ugetFragments().get(x).onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
