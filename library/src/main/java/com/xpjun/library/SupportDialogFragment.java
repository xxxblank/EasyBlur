package com.xpjun.library;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by U-nookia on 2017/8/22.
 */

public class SupportDialogFragment extends DialogFragment {

    private BlurDialogBuilder builder;

    public static SupportDialogFragment getInstance(BlurDialogBuilder builder){
        SupportDialogFragment fragment = new SupportDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("builder",builder);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        builder = (BlurDialogBuilder) getArguments().get("builder");
    }

    @NonNull
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
                        .setNeutralButton(builder.negativeBtText,builder.neutralListener)
                        .setOnKeyListener(builder.keyListener)
                        .setOnDismissListener(builder.dismissListener)
                        .setSingleChoiceItems(builder.items,builder.mCheckedItem,builder.mOnClickListener);

        return dialogBuilder.create();
    }
}
