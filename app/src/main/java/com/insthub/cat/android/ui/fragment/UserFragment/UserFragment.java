package com.insthub.cat.android.ui.fragment.UserFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.common.android.fui.fragment.BaseFragment;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CustomViewPager;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.CommonFragmentAdatper;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by linux on 2017/6/28.
 */

public class UserFragment extends BaseFragment<MVPPresenter, MVPModel> implements MVPContract.View {


    @Bind(R.id.viewpager)
    CustomViewPager vitePager;
    @Bind(R.id.iv_person)
    ImageView ivPerson;
    @Bind(R.id.iv_business)
    ImageView ivBusiness;

    private CommonFragmentAdatper mGuidePagerAdapter;
    private String[] titles;

    public static UserFragment newInstance() {
        UserFragment mainFragment = new UserFragment();
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
        return R.layout.fragment_user;
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

        titles = new String[]{"用户", "商户"};
        mGuidePagerAdapter = new CommonFragmentAdatper(getChildFragmentManager());

        mGuidePagerAdapter.addFragment(PersonFragment.newInstance(), titles[0]);
        mGuidePagerAdapter.addFragment(BusinessFragment.newInstance(), titles[1]);
        vitePager.setOffscreenPageLimit(3);
        vitePager.setScrollble(true);
        vitePager.setAdapter(mGuidePagerAdapter);
        vitePager.setCurrentItem(0);
    }


    @Override
    protected void bindEvent() {
        super.bindEvent();


        ivPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vitePager.setCurrentItem(0);
            }
        });


        ivBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vitePager.setCurrentItem(1);
            }
        });


        vitePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position)
                {
                    case 0:
                        ivPerson.setVisibility(View.GONE);
                        ivBusiness.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        ivPerson.setVisibility(View.VISIBLE);
                        ivBusiness.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
