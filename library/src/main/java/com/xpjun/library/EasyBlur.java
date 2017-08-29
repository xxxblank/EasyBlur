package com.xpjun.library;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.xpjun.library.requestcreater.ImplRequestCreater;
import com.xpjun.library.requestcreater.RequestCreator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by U-nookia on 2017/8/20.
 */

public class EasyBlur {
    static volatile EasyBlur instance;

    public static EasyBlur getInstance(){
        if (instance==null){
            synchronized (EasyBlur.class){
                if (instance==null){
                    instance = new EasyBlur();
                }
            }
        }
        return instance;
    }

    public RequestCreator blur(@DrawableRes int resourceId){
        return new ImplRequestCreater(null,resourceId,null);
    }

    public RequestCreator blur(Bitmap bitmap){
        return new ImplRequestCreater(bitmap,0,null);
    }

    public RequestCreator blur(String path){
        File file = new File(path);
        return blur(file);
    }

    public RequestCreator blur(File file){
        if (!file.exists()){
            throw new RuntimeException("not found the file in the path");
        }
        return new ImplRequestCreater(null,0,file);
    }

    public static class BlurDialog{

        private WeakReference<Activity> activityRe;
        private BlurDialogBuilder builder;

        protected BlurDialog(BlurDialogBuilder builder,Activity activity) {
            this.builder = builder;
            activityRe = new WeakReference<Activity>(activity);
        }

        public void show(){
            if (activityRe==null||activityRe.get()==null){
                throw new ActivityNotFoundException("you must bind activity before build() method");
            }
            Activity activityBind = activityRe.get();
            if (activityBind instanceof FragmentActivity){
                SupportDialogFragment dialogFragment = SupportDialogFragment.getInstance(builder);
                dialogFragment.show(((FragmentActivity)activityBind)
                        .getSupportFragmentManager()
                        ,activityBind.getString(R.string.tag_support_dialog));
                return;
            }
            AppDialogFragment dialogFragment = AppDialogFragment.getInstance(builder);
            dialogFragment.show(activityBind.getFragmentManager()
                    ,activityBind.getString(R.string.tag_app_dialog));
        }
    }
}
