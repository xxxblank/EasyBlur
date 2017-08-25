package com.xpjun.library;

import android.support.annotation.IntRange;

/**
 * Created by U-nookia on 2017/8/24.
 */

public interface BlurEngine {
    void onResume(boolean retainInstance);
    void onDismiss();
    void onDetach();
    void setRadius(@IntRange(from = 1,to = 25) int radius);
    void setBlurPolice(@BlurPolice int police);
    void setMultiReduce(@IntRange(from = 1,to = 25)int multi);
    void setDimming(boolean dimming);
}
