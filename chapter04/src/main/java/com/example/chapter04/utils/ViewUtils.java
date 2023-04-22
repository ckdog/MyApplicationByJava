package com.example.chapter04.utils;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ViewUtils {

    public static void hideInputMethod(Activity act, View v){
        InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
        //隐藏软键盘
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
