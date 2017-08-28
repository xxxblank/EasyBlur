package com.xpjun.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.IntRange;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;



/**
 * Created by U-nookia on 2017/8/22.
 */

public class BgBlurEngine implements BlurEngine{

    private Activity holdActivity;
    private View backgroundView;
    private ImageView blurBgView;
    private Bitmap bgBitmap;
    private int radius;
    private int police;
    private int reduce;

    public BgBlurEngine(Activity activity) {
        Log.e("BgBlurEngine","engine contact");
        holdActivity = activity;
    }


    @Override
    public void onResume(boolean retainInstance) {
        Log.e("BgBlurEngine","engine resume");
        backgroundView = holdActivity.getWindow().getDecorView();
        Rect frame = new Rect();
        backgroundView.getWindowVisibleDisplayFrame(frame);
        backgroundView.setDrawingCacheEnabled(true);
        bgBitmap = backgroundView.getDrawingCache();
        blurBgView = new ImageView(holdActivity);
        blurBgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        EasyBlur.getInstance()
                .blur(bgBitmap)
                .reduce(reduce)
                .police(police)
                .radius(radius)
                .into(blurBgView);
        int top = (int) Math.ceil((double) frame.top/3.8);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                frame.width(),frame.height()+frame.top/2);
        params.setMargins(0,0,0,0);
        holdActivity.getWindow().addContentView(blurBgView,params);
        bgBitmap.recycle();
        backgroundView.setDrawingCacheEnabled(false);
    }

    @Override
    public void onDismiss() {
        Log.e("BgBlurEngine","engine dismiss");
        if (blurBgView==null){
            Log.e("BgBlurEngine","background blur view has not be shown");
            return;
        }
        blurBgView.animate()
                .alpha(0)
                .setDuration(300)
                .setInterpolator(new LinearInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        removeBgView();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                        removeBgView();
                    }
                })
                .start();

    }

    private void removeBgView() {
        if (blurBgView!=null){
            ViewGroup parent = (ViewGroup) blurBgView.getParent();
            if (parent!=null){
                parent.removeView(blurBgView);
            }
            blurBgView = null;
        }
    }

    @Override
    public void onDetach() {
        Log.e("BgBlurEngine","engine detach");
        holdActivity = null;
        backgroundView = null;
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
}
