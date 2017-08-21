package com.xpjun.library;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.DrawableRes;

import java.io.File;

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
        Uri uri = Uri.parse(path);
        return blur(uri);
    }

    public RequestCreator blur(Uri uri){
        return new ImplRequestCreater(null,0,uri);
    }

    public RequestCreator blur(File file){
        Uri uri = Uri.fromFile(file);
        return blur(uri);
    }

    public static class BlurDialog{

    }
}
