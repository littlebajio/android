package com.example.project;

import android.content.Context;
import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context base){
        Configuration cfg = base.getResources().getConfiguration();
        if (cfg.fontScale != 1f){
            Configuration c = new Configuration(cfg);
            c.fontScale = 1f;
            super.attachBaseContext(base.createConfigurationContext(c));
        } else super.attachBaseContext(base);
    }
}
