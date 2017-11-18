package com.xpjun.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
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

        builder = (BlurDialogBuilder) getArguments().get("builder");
        engine = new BgBlurEngine(getActivity());
        engine.setRadius(builder.radius);
        engine.setBlurPolice(builder.police);
        engine.setMultiReduce(builder.multiReduce);

        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {

        builder = (BlurDialogBuilder) getArguments().get("builder");
        engine = new BgBlurEngine(getActivity());
        engine.setRadius(builder.radius);
        engine.setBlurPolice(builder.police);
        engine.setMultiReduce(builder.multiReduce);
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        Log.e("dialog","start");
        Dialog dialog = getDialog();
        super.onStart();
        if (dialog!=null){
            DialogFragmentConfig.dimmingConfig(dialog,builder.dimming);
            DialogFragmentConfig.animationConfig(dialog);
            DialogFragmentConfig.dialogShowPoliceConfig(dialog,builder.showPolice);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("dialog","resume");
        engine.onResume(getRetainInstance());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /*onAttach(getActivity());
        onStart();
        onResume();*/
        AlertDialog.Builder dialogBuilder =
                new AlertDialog
                        .Builder(getActivity())
                        .setTitle(builder.title)
                        .setMessage(builder.message)
                        .setAdapter(builder.adapter,builder.mOnClickListener)
                        .setCancelable(builder.cancelable)
                        .setIcon(builder.mIcon)
                        .setView(builder.view)
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
