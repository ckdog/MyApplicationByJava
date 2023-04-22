package com.example.chapter04.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtils {

    private static SharedUtils mUtils;
    private SharedPreferences preferences;


    public static SharedUtils getInstance(Context context){
        if(mUtils == null){
            mUtils = new SharedUtils();
            mUtils.preferences = context.getSharedPreferences("shopping", Context.MODE_PRIVATE);
        }
        return mUtils;
    }


    public void writeBoolean(String key, boolean value){
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public boolean readBoolean(String key, boolean defaultValue){
        return preferences.getBoolean(key, defaultValue);
    }
}
