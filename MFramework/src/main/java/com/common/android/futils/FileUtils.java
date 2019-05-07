package com.common.android.futils;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by linux on 2017/6/30.
 */

public class FileUtils {


    public static void createDir(String dir)
    {

        File file = new File(dir);
        if(!file.getParentFile().exists())
        {
            createDir(file.getParent());

        }
        file.mkdir();
    }


    /**
     * 判断是否有扩展村粗
     * @return
     */
    public static boolean getExistStorage()
    {
        return Environment.isExternalStorageEmulated();
    }


    /**
     * 创建文件
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String createFileName(String fileName) throws IOException
    {
        File file  = new File(fileName);
        if(file.exists())
        {
            file.createNewFile();
        }
        return file.getPath();
    }


}
