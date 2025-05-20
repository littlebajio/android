package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;

public final class UiPreferences {

    private static final String PREF = "ui_prefs";
    private static final String KEY_ACCENT = "accent_idx";
    private static final String KEY_FONT   = "font_idx";

    private static final int[] ACCENTS = {
            0xffb28ee6,0xffc2d4fd,0xffd3ecdd,0xfffadadd
    };
    private static final float[] SCALES = {0.9f, 1.0f, 1.15f};

    private static SharedPreferences sp(Context c){
        return c.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static int getAccentIdx(Context c){ return sp(c).getInt(KEY_ACCENT,0); }
    public static int getAccentColor(Context c){ return ACCENTS[getAccentIdx(c)]; }
    public static void setAccentIdx(Context c,int i){ sp(c).edit().putInt(KEY_ACCENT,i).apply(); }

    public static int getFontIdx(Context c){ return sp(c).getInt(KEY_FONT,1); }
    public static float getFontScale(Context c){ return SCALES[getFontIdx(c)]; }
    public static void setFontIdx(Context c,int i){ sp(c).edit().putInt(KEY_FONT,i).apply(); }

    private UiPreferences(){}
}
