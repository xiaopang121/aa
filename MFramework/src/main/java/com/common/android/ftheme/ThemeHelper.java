/*
 * Copyright (C) 2016 Bilibili
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.common.android.ftheme;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.ColorRes;

import com.common.android.R;


public class ThemeHelper {
   // private static final String CURRENT_THEME = "theme_current";
    private static final String THEME_COLOR_PRIMARIY="theme_color_primary";
    private static final String THEME_COLOR_PRIMARIY_DARK="theme_color_primary_dark";
    private static final String THEME_COLOR_PRIMARIY_LIGHT="theme_color_primary_trans";

    public static final int CARD_SAKURA = 0x1;
    public static final int CARD_HOPE = 0x2;
    public static final int CARD_STORM = 0x3;
    public static final int CARD_WOOD = 0x4;
    public static final int CARD_LIGHT = 0x5;
    public static final int CARD_THUNDER = 0x6;
    public static final int CARD_SAND = 0x7;
    public static final int CARD_FIREY = 0x8;

    public static SharedPreferences getSharePreference(Context context) {
        return context.getSharedPreferences("multiple_theme", Context.MODE_PRIVATE);
    }

    public static void setTheme(Context context, int themeColorId,int second,int  thrid) {
        getSharePreference(context).edit()
                .putInt(THEME_COLOR_PRIMARIY, themeColorId)
                .putInt(THEME_COLOR_PRIMARIY_DARK, second)
                .putInt(THEME_COLOR_PRIMARIY_LIGHT, thrid)
                .commit();


    }



    public static int getTheme(Context context) {
        return getSharePreference(context).getInt(THEME_COLOR_PRIMARIY, context.getResources().getColor(R.color.theme_color_primary));
    }


    public static int getThemeDarkColor(Context context) {
        return getSharePreference(context).getInt(THEME_COLOR_PRIMARIY_DARK, context.getResources().getColor(R.color.theme_color_primary_dark));
    }



    public static int getThemeLightColor(Context context) {
        return getSharePreference(context).getInt(THEME_COLOR_PRIMARIY_LIGHT, context.getResources().getColor(R.color.theme_color_primary_trans));
    }


    public static boolean isDefaultTheme(Context context) {
        return getTheme(context) == Color.TRANSPARENT;
    }

    public static String getName(int currentTheme) {
        switch (currentTheme) {
            case CARD_SAKURA:
                return "THE SAKURA";
            case CARD_STORM:
                return "THE STORM";
            case CARD_WOOD:
                return "THE WOOD";
            case CARD_LIGHT:
                return "THE LIGHT";
            case CARD_HOPE:
                return "THE HOPE";
            case CARD_THUNDER:
                return "THE THUNDER";
            case CARD_SAND:
                return "THE SAND";
            case CARD_FIREY:
                return "THE FIREY";
        }
        return "THE RETURN";
    }
}
