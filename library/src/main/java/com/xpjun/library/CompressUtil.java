package com.xpjun.library;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by U-nookia on 2017/8/25.
 */

public class CompressUtil {

    public static Bitmap getCompressBitmap(Resources resources
            ,int resId,int width,int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//don't get bitmap
        BitmapFactory.decodeResource(resources,resId,options);
        options.inSampleSize = getSampleSize(width,height,options); //图像像素的缩放比例
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources,resId,options);
    }

    private static int getSampleSize(int width, int height
            , BitmapFactory.Options options) {
        int sample = 1;
        if (options.outWidth>width||options.outHeight>height){
            int widthRatio = Math.round(((float)options.outWidth)/((float) width));
            int heightRatio = Math.round(((float)options.outHeight)/((float)height));
            sample = Math.max(widthRatio,heightRatio);
        }
        return sample;
    }
}
