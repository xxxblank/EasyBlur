package com.xpjun.library;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntRange;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by U-nookia on 2017/8/22.
 */

public class BgBlurEngine implements BlurEngine{

    private Activity holdActivity;
    private ImageView bgView;
    private int radius;
    private int police;
    private int reduce;
    private boolean dimming;

    public BgBlurEngine(Activity activity) {
        holdActivity = activity;
    }


    @Override
    public void onResume(boolean retainInstance) {
        if (bgView==null||retainInstance){
            if (holdActivity.getWindow().getDecorView().isShown()){

                return;
            }
            holdActivity.getWindow().getDecorView()
                    .getViewTreeObserver()
                    .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            if (holdActivity==null){
                                //TODO:异常处理
                                return false;
                            }
                            holdActivity.getWindow().getDecorView()
                                    .getViewTreeObserver()
                                    .removeOnPreDrawListener(this);

                            return true;
                        }
                    });
        }
    }

    @Override
    public void onDismiss() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void setRadius(@IntRange(from = 1, to = 25) int radius) {
        this.radius = radius;
    }

    @Override
    public void setBlurPolice(@BlurPolice int police) {
        this.police = police;
    }

    @Override
    public void setMultiReduce(@IntRange(from = 1, to = 25) int multi) {
        this.reduce = multi;
    }

    @Override
    public void setDimming(boolean dimming) {
        this.dimming = dimming;
    }

    protected static class BlurEngineHandler extends Handler{



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    protected static class BlurTask implements Runnable{

        private WeakReference<Activity> holdActivity;

        public BlurTask(Activity holdActivity) {
            this.holdActivity = new WeakReference<Activity>(holdActivity);
        }

        @Override
        public void run() {

        }
    }
}
