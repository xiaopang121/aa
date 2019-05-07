package com.insthub.cat.android.ui.fragment.MessageFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.rxanroid.schedulers.AndroidSchedulers;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.dataloader.LocalXjMsgLoader;
import com.insthub.cat.android.event.XiaojingEvent;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.activity.WxLoginActivity;
import com.insthub.cat.android.ui.activity.XjMsgListActivity;

import butterknife.Bind;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * message界面包含星客捕手消息
 * 以及聊天界面消息
 * 聊天界面消息需要注意要左滑删除功能
 * 需要包含判断如果没有登录需要弹出弹窗，只有登录确定提示
 * dialog_dengle
 */
public class Fragment_message extends BaseFragment<MVPPresenter,MVPModel>implements
        MVPContract.View {
    @Bind(R.id.rll_xiaojing_view)
    RelativeLayout rllXiaojingView;

    @Bind(R.id.tab_xj_msg_label)
    TextView tvXijingMsgLabel;

    public  static Fragment_message newInstance(){
        Fragment_message mainFragment=new Fragment_message();
        Bundle bundle =new Bundle();
        mainFragment.setArguments(bundle);
        return  mainFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    protected  int getLayoutResId(){
        return  R.layout.fragment_message;
 }

    protected int bindColorPrimary(){
        return 0;
 }
 public void  initPresenter(){
        mPresenter.setVM(this,mModel);
 }

    @Override
    protected void bindEvent() {
        super.bindEvent();

              rllXiaojingView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      if (CacheManager.getInstance().getToken()!=null){
                          startActivity(XjMsgListActivity.class);
                      }else {
                          ToastUtil.show(getActivity(),"请登录");
                          startActivity(WxLoginActivity.class);
                      }
                  }
              });
              subscribeXJESGEvent();
        }
        private void subscribeXJESGEvent(){
            Subscription subscription=RxBusManager.getInstance()
                    .toObservable(XiaojingEvent.class)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<XiaojingEvent>() {
                        @Override
                        public void call(XiaojingEvent xiaojingEvent) {
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
            RxBusManager.getInstance().addSubscription(this,subscription);
        }

    @Override
    public void showSuccess(Object object) {

    }

    @Override
    public void showError(String msg, int code) {

    }
}





















