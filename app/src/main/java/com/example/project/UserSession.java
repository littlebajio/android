package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {
    private static final String PREF = "user_session_pref";
    private static final String KEY  = "username";

    public static void loginUser(Context ctx, String username){
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY, username)
                .apply();
    }

    public static String getUsername(Context ctx){
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .getString(KEY, "Гость");
    }
}
