package com.xpjun.library;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Created by U-nookia on 2017/8/31.
 */

public class CheckUtil {
    public static boolean checkBitmap(Resources resources, int resId,int height,int width){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//don't get bitmap
        BitmapFactory.decodeResource(resources,resId,options);
        if (options.outHeight>2*height||options.outWidth>2*width){
            return false;
        }
        return true;
    }

    public static boolean checkBitmap(File file, int width, int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//don't get bitmap
        BitmapFactory.decodeFile(file.getAbsolutePath(),options);
        if (options.outHeight>2*height||options.outWidth>2*width){
            return false;
        }
        return true;
    }
}
