package com.xpjun.library;

import android.content.Context;

/**
 * Created by U-nookia on 2017/8/21.
 */

public class BlurHelperFactory {
    public static BlurHelper getBlurHelper(Context context,@BlurPolice int police){
        switch (police){
            case BlurPolice.javaBlur:
                return new FastBlurHelper();
            case BlurPolice.rsBlur:
                return new RenderScriptBlurHelper(context);
            default:
                return null;
        }
    }
}
