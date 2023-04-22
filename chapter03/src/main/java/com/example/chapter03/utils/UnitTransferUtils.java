package com.example.chapter03.utils;

import android.content.Context;

public class UnitTransferUtils {

    /**
     * 根据手机分辨率，从dp转px
     * @param context
     * @param dpVal
     * @return
     */
    public static int dip2px(Context context, float dpVal){
        //通过上下文获取手机的像素密度（1dp等于多少px)
        float density = context.getResources().getDisplayMetrics().density;
        //计算公式 px = dpi * dip（dp) / 160
        return (int) (dpVal * density + 0.5f);

    }
}
