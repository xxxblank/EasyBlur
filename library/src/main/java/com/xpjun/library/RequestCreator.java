package com.xpjun.library;

import android.widget.ImageView;

/**
 * Created by U-nookia on 2017/8/20.
 */

public interface RequestCreator {

    void into(ImageView imageView);

    RequestCreator radius(int radius);

    RequestCreator police(@BlurPolice int BlurPolice);

    RequestCreator reduce(int multi);
}
