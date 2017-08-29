package com.xpjun.library;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by U-nookia on 2017/8/22.
 */

public class AppDialogFragment extends DialogFragment {

    private BlurDialogBuilder builder;
    private BlurEngine engine;

    public static AppDialogFragment getInstance(BlurDialogBuilder builder){
        AppDialogFragment fragment = new AppDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("builder",builder);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        builder = (BlurDialogBuilder) getArguments().get("builder");
        engine = new BgBlurEngine(getActivity());
        engine.setRadius(builder.radius);
        engine.setBlurPolice(builder.police);
        engine.setMultiReduce(builder.multiReduce);
    }

    @Override
    public void onStart() {
        Log.e("dialog","start");
        Dialog dialog = getDialog();
        if (dialog!=null){
            if (!builder.dimming){
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }

            int currentAnimation = dialog.getWindow().getAttributes().windowAnimations;
            if (currentAnimation == 0) {
                dialog.getWindow().getAttributes().windowAnimations
                        = R.style.BlurDialogFragment_Default_Animation;
            }
        }
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("dialog","resume");
        engine.onResume(getRetainInstance());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder =
                new AlertDialog
                        .Builder(getActivity())
                        .setTitle(builder.title)
                        .setMessage(builder.message)
                        .setAdapter(builder.adapter,builder.mOnClickListener)
                        .setCancelable(builder.cancelable)
                        .setIcon(builder.mIcon)
                        .setIcon(builder.iconId)
                        .setItems(builder.items,builder.mOnClickListener)
                        .setMultiChoiceItems(builder.items,builder.onCheckedItems,builder.mOnCheckboxClickListener)
                        .setNegativeButton(builder.negativeBtText,builder.mOnClickListener)
                        .setPositiveButton(builder.positiveBtText,builder.positiveListener)
                        .setNeutralButton(builder.neutralBtText,builder.neutralListener)
                        .setOnKeyListener(builder.keyListener)
                        .setOnDismissListener(builder.dismissListener)
                        .setSingleChoiceItems(builder.items,builder.mCheckedItem,builder.mOnClickListener);

        return dialogBuilder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        engine.onDismiss();
        builder.recycle();
        Log.e("dialog","dismiss");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        engine.onDetach();
        Log.e("dialog","detach");
    }
}
