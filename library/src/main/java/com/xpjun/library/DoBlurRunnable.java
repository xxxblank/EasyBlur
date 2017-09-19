package com.xpjun.library;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.xpjun.library.blurhelper.BlurHelperFactory;
import com.xpjun.library.requestcreater.ImplRequestCreater;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by U-nookia on 2017/8/31.
 */

public class DoBlurRunnable implements Runnable{

    private static ExecutorService blurThreadPool = Executors.newCachedThreadPool();
    private Bitmap temp;
    private int reduce,radius,police;
    private WeakReference<ImageView> image;
    private ImplRequestCreater.ObtainImageHandler handler;

    public DoBlurRunnable(Bitmap temp, int reduce, int radius, int police, ImageView image) {
        this.temp = temp;
        this.reduce = reduce;
        this.radius = radius;
        this.image = new WeakReference<ImageView>(image);
        this.police = police;
        handler = new ImplRequestCreater.ObtainImageHandler();
    }

    public void execute(){
        blurThreadPool.execute(this);
    }

    @Override
    public void run() {
        Log.e("newThread",Thread.currentThread().getName());
        Bitmap scaleBitmap = null;
        Bitmap result = null;
        try {
            Bitmap cache = temp.copy(Bitmap.Config.ARGB_8888,false);
            scaleBitmap = Bitmap.createScaledBitmap(cache,cache.getWidth()/reduce
                    ,cache.getHeight()/reduce,false);
            result = BlurHelperFactory.getBlurHelper(image.get().getContext(),police)
                    .doBlur(scaleBitmap,radius,true);
            Bundle bundle = new Bundle();
            bundle.putParcelable("result",result.copy(Bitmap.Config.ARGB_8888,false));
            Message msg = Message.obtain();
            msg.obj = image.get();
            msg.setData(bundle);
            handler.sendMessage(msg);
        }finally {
            scaleBitmap.recycle();
            result.recycle();
            if (temp!=null) temp.recycle();
        }
    }
}
