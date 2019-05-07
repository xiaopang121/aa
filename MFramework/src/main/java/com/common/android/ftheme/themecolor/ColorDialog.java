package com.common.android.ftheme.themecolor;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.common.android.R;
import com.common.android.ftheme.ThemeHelper;
import com.common.android.fui.fragment.BaseDialogFragment;
import butterknife.Bind;

/**
 * User:macbook
 * DATE:2017/4/17 16:14
 * Desc:${DESC}
 */

public class ColorDialog extends BaseDialogFragment {

    ColorPickerView  mColorPickerView;

    private TextView btPositive,btnNegative;

    private ClickListener  mClickListener;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mColorPickerView = (ColorPickerView) view.findViewById(R.id.colorpickerview);
        mColorPickerView.setColor(ThemeHelper.getTheme(getActivity()),ThemeHelper.getThemeDarkColor(getActivity()),ThemeHelper.getThemeLightColor(getActivity()));
        mColorPickerView.showAlpha(true);
        mColorPickerView.showHex(true);
        mColorPickerView.showPreview(true);

        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Window window = getDialog().getWindow();
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            }
        });


        btPositive = (TextView)view.findViewById(R.id.btnPositive);
        btPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int [] color = mColorPickerView.getColor();
//
//                ThemeHelper.setTheme(getActivity(),color[0],color[1],color[2]);

                if(mClickListener!=null)
                {
                    mClickListener.onConfirm(color[0],color[1],color[2]);
                }

                dismiss();
            }
        });

        btnNegative = (TextView)view.findViewById(R.id.btnNegative);
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });






    }

    @Override
    public void bindPresenter() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_colordialog2;
    }

    public void setClickListener(ClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface ClickListener {
        void onConfirm(int currentTheme,int themeDark,int themeLight);
    }
}
