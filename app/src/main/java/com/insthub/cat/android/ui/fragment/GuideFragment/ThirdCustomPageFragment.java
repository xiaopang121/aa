package com.insthub.cat.android.ui.fragment.GuideFragment;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.common.android.fsp.SharedPreferencesUtil;
import com.common.android.fui.fragment.BaseFragment;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.ui.activity.GuilderActivity;
import com.insthub.cat.android.ui.activity.MainActivity;

import butterknife.Bind;

/**
 * Created by linux on 2017/7/13.
 */

public class ThirdCustomPageFragment extends BaseFragment {

    @Bind(R.id.bt_useapp)
    public Button btUseApp;


    @Bind(R.id.sub)
    public TextView tvSubTitle;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_page_third;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void bindViewById() {

    }

    @Override
    protected void bindEvent() {


        btUseApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferencesUtil.getInstance().putBoolean(ConstantsKeys.KEY_FIRST_USE,true);
                startActivity(MainActivity.class);
                ((GuilderActivity)getActivity()).defaultFinish();
            }
        });

    }

    @Override
    protected void unbindEvent() {

    }

    @Override
    protected void bindData() {

        if(!isPrepare)
        {
            return ;
        }
        try {
            AssetManager mgr = getActivity().getAssets();
//            Typeface fontFace = Typeface.createFromAsset(mgr, "fonts/Roboto-Thin.ttf");
//            tvVersion.setText("1.0");
//            tvVersion.setTypeface(fontFace);

            Typeface fontFace2 = Typeface.createFromAsset(mgr, "fonts/weac_slogan.ttf");
            tvSubTitle.setTypeface(fontFace2);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    protected int bindColorPrimary() {
        return 0;
    }
}

