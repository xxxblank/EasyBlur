package com.xpjun.library.requestcreater;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntRange;
import android.support.annotation.MainThread;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.xpjun.library.BlurPolice;
import com.xpjun.library.CheckUtil;
import com.xpjun.library.CompressUtil;
import com.xpjun.library.DoBlurRunnable;
import com.xpjun.library.blurhelper.BlurHelper;
import com.xpjun.library.blurhelper.BlurHelperFactory;

import java.io.File;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by U-nookia on 2017/8/21.
 */

public class ImplRequestCreater implements RequestCreator {

    @BlurPolice
    private int police = BlurPolice.rsBlur;
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

        if (imageView==null){
            throw new RuntimeException("the target imageView must not be null");
        }

        blurHelper = BlurHelperFactory.getBlurHelper(imageView.getContext(),police);

        Bitmap temp = null;

        if (original!=null){
            temp = original;
        }

        if (resourseId!=0){
            temp = BitmapFactory.decodeResource(imageView.getResources()
                    ,resourseId);
        }

        if (file!=null){
            temp = BitmapFactory.decodeFile(file.getAbsolutePath());
        }

        if (temp == null){
            throw new RuntimeException("have no bitmap to show");
        }
        try {
            new DoBlurRunnable(temp.copy(Bitmap.Config.ARGB_8888,false)
                    ,multiReduce,radius,police,imageView)
                    .execute();
        }finally {
            if (original!=null) original.recycle();
            temp.recycle();
        }
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

    public static class ObtainImageHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            ImageView imageView = (ImageView) msg.obj;
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Bitmap result = msg.getData().getParcelable("result");
            imageView.setImageDrawable(
                    new BitmapDrawable(result.copy(Bitmap.Config.ARGB_8888,false)));
            result.recycle();
        }
    }
}
