package com.xpjun.library.blurhelper;

import android.content.Context;
import android.util.Log;

import com.xpjun.library.BlurPolice;

/**
 * Created by U-nookia on 2017/8/21.
 */

public class BlurHelperFactory {
    public static BlurHelper getBlurHelper(Context context, @BlurPolice int police){
        switch (police){
            case BlurPolice.javaBlur:
                return new FastBlurHelper();
            case BlurPolice.rsBlur:
                return new RenderScriptBlurHelper(context);
            default:
                throw new RuntimeException("this blurHelper can't do blur");
        }
    }
}
