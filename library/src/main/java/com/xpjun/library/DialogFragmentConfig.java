package com.xpjun.library;

import android.app.Dialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by U-nookia on 2017/8/30.
 */

public class DialogFragmentConfig {

    public static void dimmingConfig(Dialog dialog,boolean dimming) {
        if (!dimming)
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public static void animationConfig(Dialog dialog) {
        int currentAnimation = dialog.getWindow().getAttributes().windowAnimations;
        if (currentAnimation == 0) {
            dialog.getWindow().getAttributes().windowAnimations
                    = R.style.BlurDialogFragment_Default_Animation;
        }
    }

    public static void dialogShowPoliceConfig(Dialog dialog, int showPolice) {
        switch (showPolice){
            case BlurDialogShowPolice.bottom:
                Window window = dialog.getWindow();
                window.getDecorView().setPadding(0,0,0,0);
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.BlurDialogFragment_Bottom_Animation);
                window.setBackgroundDrawableResource(R.color.transparent);
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                params.y = 0;
                window.setAttributes(params);
                break;
        }
    }
}
