package com.xpjun.library.requestcreater;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.IntRange;
import android.widget.ImageView;

import com.xpjun.library.BlurPolice;
import com.xpjun.library.CompressUtil;
import com.xpjun.library.blurhelper.BlurHelper;
import com.xpjun.library.blurhelper.BlurHelperFactory;

import java.io.File;
import java.io.InputStream;

/**
 * Created by U-nookia on 2017/8/21.
 */

public class ImplRequestCreater implements RequestCreator {

    @BlurPolice
    private int police = BlurPolice.javaBlur;
    private int radius = 4;
    private int multiReduce = 4;

    private Bitmap original;
    private int resourseId;
    private File file;

    private BlurHelper blurHelper;

    public ImplRequestCreater(Bitmap original, int resourseId, File file) {
        this.original = original;
        this.resourseId = resourseId;
        this.file = file;
    }

    @Override
    public void into(ImageView imageView) {

        blurHelper = BlurHelperFactory.getBlurHelper(imageView.getContext(),police);

        Bitmap scaleBitmap,temp = null;

        if (original!=null){
            temp = original;
        }

        if (resourseId!=0){
            temp = CompressUtil.getCompressBitmap(imageView.getContext().getResources(),
                    resourseId,imageView.getWidth(),imageView.getHeight());
        }

        if (file!=null){
            temp = CompressUtil.getCompressBitmap(file,imageView.getWidth(),imageView.getHeight());
        }

        if (temp == null){
            //TODO:抛出异常
            return;
        }
        scaleBitmap = Bitmap.createScaledBitmap(temp,temp.getWidth()/multiReduce
                ,temp.getHeight()/multiReduce,false);
        Bitmap result = blurHelper.doBlur(scaleBitmap,radius,true);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageDrawable(new BitmapDrawable(result));
    }

    @Override
    public RequestCreator radius(@IntRange(from = 1,to = 25)int radius) {
        this.radius = radius;
        return this;
    }

    @Override
    public RequestCreator police(@BlurPolice int police) {
        this.police = police;
        return this;
    }

    @Override
    public RequestCreator reduce(@IntRange(from = 1,to = 25)int multi){
        multiReduce = multi;
        return this;
    }
}
