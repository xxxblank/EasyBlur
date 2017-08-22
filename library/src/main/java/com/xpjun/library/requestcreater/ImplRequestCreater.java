package com.xpjun.library.requestcreater;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.IntRange;
import android.widget.ImageView;

import com.xpjun.library.BlurPolice;
import com.xpjun.library.blurhelper.BlurHelper;
import com.xpjun.library.blurhelper.BlurHelperFactory;

/**
 * Created by U-nookia on 2017/8/21.
 */

public class ImplRequestCreater implements RequestCreator {

    @BlurPolice
    private int police = BlurPolice.javaBlur;
    private int radius = 8;
    private int multiReduce = 8;

    private Bitmap original;
    private int resourseId;
    private Uri uri;

    private BlurHelper blurHelper;

    public ImplRequestCreater(Bitmap original, int resourseId, Uri uri) {
        this.original = original;
        this.resourseId = resourseId;
        this.uri = uri;
    }

    @Override
    public void into(ImageView imageView) {

        blurHelper = BlurHelperFactory.getBlurHelper(imageView.getContext(),police);

        Bitmap scaleBitmap,temp = null;

        if (original!=null){
            temp = original;
            return;
        }

        if (resourseId!=0){
            temp = BitmapFactory
                    .decodeResource(imageView.getContext().getResources(),resourseId);
        }

        if (uri!=null){
            //TODO:
        }

        if (temp == null){
            //TODO:抛出异常
            return;
        }
        scaleBitmap = Bitmap.createScaledBitmap(temp,temp.getWidth()/multiReduce
                ,temp.getHeight()/multiReduce,false);
        Bitmap result = blurHelper.doBlur(scaleBitmap,radius,true);
        imageView.setImageBitmap(result);
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
