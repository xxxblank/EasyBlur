package com.xpjun.library;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import java.io.Serializable;

/**
 * Created by U-nookia on 2017/8/21.
 */

public class BlurDialogBuilder implements Serializable{

    protected CharSequence title;
    protected CharSequence message;
    protected Drawable mIcon;
    protected int iconId;
    protected CharSequence positiveBtText;
    protected CharSequence negativeBtText;
    protected CharSequence neutralBtText;
    protected DialogInterface.OnClickListener positiveListener
            ,negativeListener,neutralListener,mOnClickListener;
    protected boolean cancelable;
    protected DialogInterface.OnCancelListener cancelListener;
    protected DialogInterface.OnDismissListener dismissListener;
    protected DialogInterface.OnKeyListener keyListener;
    protected CharSequence[] items;
    protected ListAdapter adapter;
    protected DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
    protected boolean[] onCheckedItems;
    protected boolean isMultiChoice;
    protected int mCheckedItem;
    protected boolean isSingleChoice;
    protected AdapterView.OnItemClickListener mItemClickListener;
    protected Activity activity;
    @BlurPolice
    protected int police = BlurPolice.rsBlur;
    protected int radius = 4;
    protected int multiReduce = 4;
    protected boolean dimming = true;   //background become black or no change

    public BlurDialogBuilder setDimming(boolean dimming) {
        this.dimming = dimming;
        return this;
    }

    public BlurDialogBuilder setPolice(@BlurPolice int police) {
        this.police = police;
        return this;
    }

    public BlurDialogBuilder setRadius(@IntRange(from = 1,to = 25) int radius) {
        this.radius = radius;
        return this;
    }

    public BlurDialogBuilder setMultiReduce(@IntRange(from = 1,to = 25) int multiReduce) {
        this.multiReduce = multiReduce;
        return this;
    }

    public BlurDialogBuilder bind(Activity activity){
        this.activity = activity;
        return this;
    }

    public BlurDialogBuilder setTitle(CharSequence title){
        this.title = title;
        return this;
    }

    public BlurDialogBuilder setMessage(CharSequence message){
        this.message = message;
        return this;
    }

    public BlurDialogBuilder setmIcon(Drawable mIcon) {
        this.mIcon = mIcon;
        return this;
    }

    public BlurDialogBuilder setIconId(int iconId) {
        this.iconId = iconId;
        return this;
    }

    public BlurDialogBuilder setPositiveBtText(CharSequence positiveBtText) {
        this.positiveBtText = positiveBtText;
        return this;
    }

    public BlurDialogBuilder setNegativeBtText(CharSequence negativeBtText) {
        this.negativeBtText = negativeBtText;
        return this;
    }

    public BlurDialogBuilder setNeutralBtText(CharSequence neutralBtText) {
        this.neutralBtText = neutralBtText;
        return this;
    }

    public BlurDialogBuilder setPositiveListener(DialogInterface.OnClickListener positiveListener) {
        this.positiveListener = positiveListener;
        return this;
    }

    public BlurDialogBuilder setNegativeListener(DialogInterface.OnClickListener negativeListener) {
        this.negativeListener = negativeListener;
        return this;
    }

    public BlurDialogBuilder setNeutralListener(DialogInterface.OnClickListener neutralListener) {
        this.neutralListener = neutralListener;
        return this;
    }

    public BlurDialogBuilder setmOnClickListener(DialogInterface.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
        return this;
    }

    public BlurDialogBuilder setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public BlurDialogBuilder setCancelListener(DialogInterface.OnCancelListener cancelListener) {
        this.cancelListener = cancelListener;
        return this;
    }

    public BlurDialogBuilder setDismissListener(DialogInterface.OnDismissListener dismissListener) {
        this.dismissListener = dismissListener;
        return this;
    }

    public BlurDialogBuilder setKeyListener(DialogInterface.OnKeyListener keyListener) {
        this.keyListener = keyListener;
        return this;
    }

    public BlurDialogBuilder setItems(CharSequence[] items) {
        this.items = items;
        return this;
    }

    public BlurDialogBuilder setAdapter(ListAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public BlurDialogBuilder setmOnCheckboxClickListener(DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener) {
        this.mOnCheckboxClickListener = mOnCheckboxClickListener;
        return this;
    }

    public BlurDialogBuilder setOnCheckedItems(boolean[] onCheckedItems) {
        this.onCheckedItems = onCheckedItems;
        return this;
    }

    public BlurDialogBuilder setMultiChoice(boolean multiChoice) {
        isMultiChoice = multiChoice;
        return this;
    }

    public BlurDialogBuilder setmCheckedItem(int mCheckedItem) {
        this.mCheckedItem = mCheckedItem;
        return this;
    }

    public BlurDialogBuilder setSingleChoice(boolean singleChoice) {
        isSingleChoice = singleChoice;
        return this;
    }

    public BlurDialogBuilder setmItemClickListener(AdapterView.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
        return this;
    }

    public EasyBlur.BlurDialog build(){
        return new EasyBlur.BlurDialog(this,activity);
    }

    public void recycle() {
        title = null;
        message = null;
        mIcon = null;
        positiveBtText = null;
        negativeBtText = null;
        neutralBtText = null;
        positiveListener = null;
        negativeListener = null;
        neutralListener = null;
        mOnClickListener = null;
        cancelListener = null;
        dismissListener = null;
        keyListener = null;
        items = null;
        adapter = null;
        onCheckedItems = null;
        mOnCheckboxClickListener = null;
        mItemClickListener = null;
        activity = null;
    }
}
