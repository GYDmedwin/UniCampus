package com.tpboys.unicampus.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    public static boolean getBoolean(Context context,String key,boolean defValue){
        SharedPreferences config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return config.getBoolean(key,defValue);
    }
    public static void setBoolean(Context context,String key,boolean value){
        SharedPreferences config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        config.edit().putBoolean(key,value).apply();
    }
}
