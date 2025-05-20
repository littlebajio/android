package com.example.project;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.*;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.work.*;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);


        setSupportActionBar(findViewById(R.id.topBar));


        nav = findViewById(R.id.bottomNavigation);
        nav.setOnItemSelectedListener(item -> {
            Fragment f = null;
            if (item.getItemId() == R.id.nav_home)      f = new HomeFragment();
            else if (item.getItemId() == R.id.nav_profile)  f = new ProfileFragment();
            else if (item.getItemId() == R.id.nav_settings) f = new SettingsFragment();
            if (f != null) { load(f); return true; }
            return false;
        });
        applyAccent();
        load(new HomeFragment());

        askNotificationPermission();
        scheduleDailyWorker();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem it) {
        int id = it.getItemId();

        if (id == R.id.action_customize) {
            CustomizationDialog.show(this, this::applyAccent);
            return true;
        }

        if (id == R.id.action_test_push) {
            WorkManager.getInstance(this)
                    .enqueue(new OneTimeWorkRequest
                            .Builder(FilmOfDayWorker.class).build());
            return true;
        }
        return super.onOptionsItemSelected(it);
    }

    private void load(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, f).commit();
    }

    private void applyAccent() {
        nav.setItemActiveIndicatorColor(
                android.content.res.ColorStateList.valueOf(
                        AppPreferences.accColor(this)));
    }

    private void askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= 33 &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS}, 77);
        }
    }

    private void scheduleDailyWorker() {
        PeriodicWorkRequest req = new PeriodicWorkRequest.Builder(
                FilmOfDayWorker.class, 24, TimeUnit.HOURS).build();

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "film_of_day", ExistingPeriodicWorkPolicy.KEEP, req);
    }
}
