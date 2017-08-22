package com.xpjun.library.requestcreater;

import android.widget.ImageView;

import com.xpjun.library.BlurPolice;

/**
 * Created by U-nookia on 2017/8/20.
 */

public interface RequestCreator {

    void into(ImageView imageView);

    RequestCreator radius(int radius);

    RequestCreator police(@BlurPolice int BlurPolice);

    RequestCreator reduce(int multi);
}
