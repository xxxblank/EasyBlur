package com.xpjun.library;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by U-nookia on 2017/8/30.
 */
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@IntDef({BlurDialogShowPolice.common,BlurDialogShowPolice.bottom,BlurDialogShowPolice.top})
public @interface BlurDialogShowPolice {
    int common = 0;
    int bottom = 1;
    int top = 2;
}
