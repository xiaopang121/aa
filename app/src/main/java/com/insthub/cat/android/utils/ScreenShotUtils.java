package com.insthub.cat.android.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by linux on 2017/10/13.
 */

public class ScreenShotUtils {

    public static void saveCurrentImage(Activity ctx)
    {
        //获取当前屏幕的大小
        int width = ctx.getWindow().getDecorView().getRootView().getWidth();
        int height = ctx.getWindow().getDecorView().getRootView().getHeight();
        //生成相同大小的图片
        Bitmap temBitmap = Bitmap.createBitmap( width, height, Bitmap.Config.ARGB_8888 );
        //找到当前页面的跟布局
        View view =  ctx.getWindow().getDecorView().getRootView();
        //设置缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //从缓存中获取当前屏幕的图片
        temBitmap = view.getDrawingCache();


        //输出到sd卡
        if (getExistStorage()) {
            File file = new File(getShortFileName(ctx));
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream foStream = new FileOutputStream(file);
                temBitmap.compress(Bitmap.CompressFormat.PNG, 100, foStream);
                foStream.flush();
                foStream.close();
            } catch (Exception e) {
                Log.i("Show", e.toString());
            }
        }
    }



    /**
     * 判断是否有扩展村粗
     * @return
     */
    public static boolean getExistStorage()
    {
        return Environment.isExternalStorageEmulated();
    }



    public static String  getShortFileName(Context context)
    {
        String dir = "/sdcard/" + context.getPackageName().toString() + "/temp.png";
        return dir;

    }

}
