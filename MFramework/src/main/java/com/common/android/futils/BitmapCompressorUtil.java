package com.common.android.futils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

/**
* 图片压缩工具类
* @author touch_ping
* 2015-1-5 下午1:29:59
*/
public class BitmapCompressorUtil {
   /**
    * 质量压缩
    * @author ping 2015-1-5 下午1:29:58
    * @param image
    * @param maxkb
    * @return
    */
   public static Bitmap compressBitmap(Bitmap image,int maxkb) {
       //L.showlog(压缩图片);
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
       int options = 100;
       Log.i("IMAGE_ZIP","原始大小 "+ baos.toByteArray().length/1024);
       while (baos.toByteArray().length / 1024 > maxkb) { // 循环判断如果压缩后图片是否大于(maxkb)50kb,大于继续压缩
//         Log.i(test,压缩一次!);
           baos.reset();// 重置baos即清空baos
           options -= 10;// 每次都减少10
           image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
       }
        Log.i("IMAGE_ZIP","压缩后大小 "+ baos.toByteArray().length/1024);
       ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
       Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
       return bitmap;
   }
    
   /**
    * http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
    * 官网：获取压缩后的图片
    * 
    * @param res
    * @param resId
    * @param reqWidth
    *            所需图片压缩尺寸最小宽度
    * @param reqHeight
    *            所需图片压缩尺寸最小高度
    * @return
    */
   public static Bitmap decodeSampledBitmapFromResource(Resources res,
           int resId, int reqWidth, int reqHeight) {
       final BitmapFactory.Options options = new BitmapFactory.Options();
       options.inJustDecodeBounds = true;
       BitmapFactory.decodeResource(res, resId, options);
        
       options.inSampleSize = calculateInSampleSize(options, reqWidth,
               reqHeight);
       options.inJustDecodeBounds = false;
       return BitmapFactory.decodeResource(res, resId, options);
   }

   /**
    * 官网：获取压缩后的图片
    * 
    * @param res
    * @param resId
    * @param reqWidth
    *            所需图片压缩尺寸最小宽度
    * @param reqHeight
    *            所需图片压缩尺寸最小高度
    * @return
    */
   public static Bitmap decodeSampledBitmapFromFile(String filepath,
           int reqWidth, int reqHeight) {
       final BitmapFactory.Options options = new BitmapFactory.Options();
       options.inJustDecodeBounds = true;
       BitmapFactory.decodeFile(filepath, options);

       options.inSampleSize = calculateInSampleSize(options, reqWidth,
               reqHeight);
       options.inJustDecodeBounds = false;
       return BitmapFactory.decodeFile(filepath, options);
   }

   public static Bitmap decodeSampledBitmapFromBitmap(Bitmap bitmap,
           int reqWidth, int reqHeight) {
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.PNG, 90, baos);
       byte[] data = baos.toByteArray();
        
       final BitmapFactory.Options options = new BitmapFactory.Options();
       options.inJustDecodeBounds = true;
       BitmapFactory.decodeByteArray(data, 0, data.length, options);
       options.inSampleSize = calculateInSampleSize(options, reqWidth,
               reqHeight);
       options.inJustDecodeBounds = false;
       return BitmapFactory.decodeByteArray(data, 0, data.length, options);
   }

   /**
    * 计算压缩比例值(改进版 by touch_ping)
    * 
    * 原版2>4>8...倍压缩
    * 当前2>3>4...倍压缩
    * 
    * @param options
    *            解析图片的配置信息
    * @param reqWidth
    *            所需图片压缩尺寸最小宽度O
    * @param reqHeight
    *            所需图片压缩尺寸最小高度
    * @return
    */
   public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth, int reqHeight) {
        
       final int picheight = options.outHeight;
       final int picwidth = options.outWidth;
       //Log.i(test, 原尺寸: +  picwidth + * +picheight);
        
       int targetheight = picheight;
       int targetwidth = picwidth;
       int inSampleSize = 1;
        
       if (targetheight > reqHeight || targetwidth > reqWidth) {
           while (targetheight  >= reqHeight
                   && targetwidth>= reqWidth) {
               //Log.i(test,压缩: +inSampleSize + 倍);
               inSampleSize += 1;
               targetheight = picheight/inSampleSize;
               targetwidth = picwidth/inSampleSize;
           }
       }
        
      // Log.i(test,最终压缩比例: +inSampleSize + 倍);
      // Log.i(test, 新尺寸: +  targetwidth + * +targetheight);
       return inSampleSize;
   
}

    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    public static Bitmap  getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;//压缩好比例大小后再进行质量压缩
    }

    public static Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩

    }



    public static void writeUri(Context context,Bitmap bitmap,Uri uri)
    {

        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            OutputStream outputStream = context.getContentResolver().openOutputStream(uri);
            outputStream.write(baos.toByteArray());
            outputStream.close();
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }


    }

}