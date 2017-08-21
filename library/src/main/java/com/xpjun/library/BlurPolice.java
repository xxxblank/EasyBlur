package com.xpjun.library;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by U-nookia on 2017/8/21.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD})
@IntDef({BlurPolice.javaBlur, BlurPolice.rsBlur})
public @interface BlurPolice {
    int rsBlur = 1;
    int javaBlur = 2;
}
