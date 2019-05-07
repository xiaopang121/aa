package com.insthub.cat.android.ui.fragment.GuideFragment;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

import com.common.android.fui.fragment.BaseFragment;
import com.insthub.cat.android.R;

import butterknife.Bind;

/**
 * Created by linux on 2017/7/13.
 */

public class FirstCustomPageFragment extends BaseFragment{
    @Bind(R.id.sub)
    public TextView tvSubTitle;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_page_first;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void bindViewById() {

    }

    @Override
    protected void bindEvent() {

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
            Typeface fontFace = Typeface.createFromAsset(mgr, "fonts/weac_slogan.ttf");
            tvSubTitle.setTypeface(fontFace);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    protected int bindColorPrimary() {
        return 0;
    }
}
