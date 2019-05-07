package com.insthub.cat.android.manager.theme;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.util.TypedValue;
import com.common.android.ftheme.ThemeHelper;
import com.common.android.ftheme.utils.ThemeUtils;
import com.insthub.cat.android.R;



import java.util.HashMap;

/**
 * 主题管理
 */
public class ThemeManager implements ThemeUtils.switchColor{

    public static ThemeManager _manager;

    public static HashMap<Integer,Boolean> flitColor = new HashMap<>();



    static {
//        flitColor.put(R.color.main_white,true);
//        flitColor.put(R.color.B_black_10,true);
    }


    public static ThemeManager getInstance()
    {

        if(_manager==null)
        {
            _manager = new ThemeManager();
        }
        return _manager;
    }



    public ThemeManager()
    {

        ThemeUtils.setSwitchColor(this);
    }



    @Override
    public int replaceColorById(Context context, @ColorRes int colorId) {
        if (ThemeHelper.isDefaultTheme(context) || flitColor.containsKey(colorId)) {
            return context.getResources().getColor(colorId);
        }
        int themeColor = getTheme(context);
        if (themeColor != 0) {
            colorId = getThemeColorId(context, colorId, themeColor);
        }
        return colorId;
    }

    @ColorInt
    @Override
    public int replaceColor(Context context, @ColorInt int originColor) {
        if (ThemeHelper.isDefaultTheme(context) || flitColor.containsKey(originColor)) {
            return originColor;
        }
        int themeColor = getTheme(context);
        int colorId = -1;

        if (themeColor != 0) {
            colorId = getThemeColor(context, originColor, themeColor);
        }
        return colorId != -1 ? colorId : originColor;
    }

    private int getTheme(Context context) {
//        if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_STORM) {
//            return "blue";
//        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_HOPE) {
//            return "purple";
//        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_WOOD) {
//            return "green";
//        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_LIGHT) {
//            return "green_light";
//        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_THUNDER) {
//            return "yellow";
//        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_SAND) {
//            return "orange";
//        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_FIREY) {
//            return "red";
//        }
//        return null;

       return  ThemeHelper.getTheme(context);
    }


    @ColorRes
    private int getThemeColorId(Context context, int colorId, int  themeColor) {
        switch (colorId) {
            case R.color.theme_color_primary:
                return ThemeHelper.getTheme(context);
            case R.color.theme_color_primary_dark:
                return ThemeHelper.getThemeDarkColor(context);
            case R.color.theme_color_primary_trans:

                System.out.println("fddd");
                return ThemeHelper.getThemeLightColor(context);
        }
        return colorId;

    }


    @ColorRes
    private int getThemeColor(Context context, int color, int theme) {
        switch (color) {
            case 0xfffb7299:
                return ThemeHelper.getTheme(context);
            case 0xffb85671:
                return ThemeHelper.getThemeDarkColor(context);
            case 0x99f0486c:
                return ThemeHelper.getThemeLightColor(context);
        }
        return -1;
    }

    @ColorInt
    public  static int getCurrentThemeColor(Context ctx)
    {
        return ThemeUtils.getColorById(ctx, R.color.theme_color_primary);
    }




    public static int getThemeTextColorPrimary(Context context) {
        TypedValue textColorPrimary = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.textColorPrimary, textColorPrimary, true);
        return context.getResources().getColor(textColorPrimary.resourceId);
    }

    public static int getThemeTextColorSecondly(Context context) {
        TypedValue textColorSecondly = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.textColorSecondary, textColorSecondly, true);
        return context.getResources().getColor(textColorSecondly.resourceId);
    }


}
