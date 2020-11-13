package com.af.upworktasks.utils;

import android.content.Context;

import java.util.Locale;

public class Constant {
    public static String API_FREAKS_KEY = "8f90dfe86f40461ab6e21ddf21048d33";
    public static String getLocal(Context context){
        return Locale.getDefault().getISO3Country();
    }
}
