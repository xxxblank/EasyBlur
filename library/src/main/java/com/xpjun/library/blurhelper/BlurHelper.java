package com.xpjun.library.blurhelper;

import android.graphics.Bitmap;

/**
 * Created by U-nookia on 2017/8/20.
 */

public interface BlurHelper {

    Bitmap doBlur(Bitmap original, int radius, boolean cache);
}
