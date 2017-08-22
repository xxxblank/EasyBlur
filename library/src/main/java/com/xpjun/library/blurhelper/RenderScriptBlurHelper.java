package com.xpjun.library.blurhelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.Type;
import android.support.annotation.IntRange;

import com.xpjun.library.blurhelper.BlurHelper;

/**
 * Created by U-nookia on 2017/8/21.
 */

public class RenderScriptBlurHelper implements BlurHelper {

    private Context context;

    public RenderScriptBlurHelper(Context context) {
        this.context = context;
    }

    @Override
    public Bitmap doBlur(Bitmap original, @IntRange(from = 1, to = 25) int radius, boolean cache) {

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象，第二个参数Element相当于一种像素处理的算法，高斯模糊的话用这个就好
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        Allocation input = Allocation.createFromBitmap(rs, original);
        // 创建相同类型的Allocation对象用来输出
        Type type = input.getType();
        Allocation output = Allocation.createTyped(rs, type);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(radius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(input);
        // 将输出数据保存到输出内存中
        blurScript.forEach(output);
        // 将数据填充到bitmap中
        output.copyTo(original);

        // 销毁它们释放内存
        input.destroy();
        output.destroy();
        blurScript.destroy();
        rs.destroy();
        type.destroy();

        return original;
    }
}
