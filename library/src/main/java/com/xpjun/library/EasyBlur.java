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

/**
 * Created by U-nookia on 2017/8/20.
 */

public class EasyBlur {
    static volatile EasyBlur instance;
    //private Activity activity;

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

    /*public EasyBlur bind(Activity activity){
        this.activity = activity;
        return this;
    }*/

    public RequestCreator blur(@DrawableRes int resourceId){
        return new ImplRequestCreater(null,resourceId,null);
    }

    public RequestCreator blur(Bitmap bitmap){
        return new ImplRequestCreater(bitmap,0,null);
    }

    public RequestCreator blur(String path) throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists()){
            Log.e("EasyBlur","the file path is not exists");
            throw new FileNotFoundException();
        }
        return blur(file);
    }

    /*public RequestCreator blur(Uri uri) throws Exception {
        if (activity==null){
            Log.e("EasyBlur","must bind activity before blur the pic from uri and after the getInstance()");
            throw new ActivityNotFoundException();
        }
        return blur(getImagePath(uri,null));
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = activity.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }

            cursor.close();
        }
        return path;
    }*/

    public RequestCreator blur(File file){
        return new ImplRequestCreater(null,0,file);
    }

    public static class BlurDialog{

        private WeakReference<Activity> activityRe;
        private BlurDialogBuilder builder;

        protected BlurDialog(BlurDialogBuilder builder) {
            this.builder = builder;
        }

        public BlurDialog bind(Activity activity){
            activityRe = new WeakReference<Activity>(activity);
            return this;
        }

        public void show(){
            if (activityRe==null||activityRe.get()==null){
                Log.e("EasyBlurError","you must call bind() method before show()");
                //TODO:异常处理
                return;
            }
            Activity activityBind = activityRe.get();
            if (activityBind instanceof FragmentActivity){
                SupportDialogFragment dialogFragment = SupportDialogFragment.getInstance(builder);
                dialogFragment.show(((FragmentActivity)activityBind)
                        .getSupportFragmentManager(),"dialog");
                return;
            }
            AppDialogFragment dialogFragment = AppDialogFragment.getInstance(builder);
            dialogFragment.show(activityBind.getFragmentManager(),"appdialog");
        }
    }
}
