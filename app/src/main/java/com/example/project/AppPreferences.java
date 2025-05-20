package com.example.project;

import android.content.*;

public final class AppPreferences {
    private static final String PREF="prefs";
    private static final String K_DARK="dark";
    private static final String K_ACC ="acc";

    private static final int[] ACCENTS={ 0xffb28ee6,0xffc2d4fd,0xffd3ecdd,0xfffadadd };

    private static SharedPreferences sp(Context c){
        return c.getSharedPreferences(PREF,Context.MODE_PRIVATE);
    }

    public static boolean isDark(Context c){ return sp(c).getBoolean(K_DARK,false); }
    public static void setDark(Context c,boolean d){ sp(c).edit().putBoolean(K_DARK,d).apply(); }

    public static int  accIdx(Context c){ return sp(c).getInt(K_ACC,0);}
    public static void setAccIdx(Context c,int i){ sp(c).edit().putInt(K_ACC,i).apply();}
    public static int  accColor(Context c){ return ACCENTS[accIdx(c)]; }

    private AppPreferences(){}
}
