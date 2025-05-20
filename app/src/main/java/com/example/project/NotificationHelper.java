package com.example.project;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.*;

public final class NotificationHelper {

    private static final String CH_ID = "film_day_channel";
    private static final int NOTIF = 777;

    private NotificationHelper() {}

    @SuppressLint("MissingPermission")
    public static void push(Context ctx, String filmTitle) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel ch = new NotificationChannel(
                    CH_ID, "Фильм дня", NotificationManager.IMPORTANCE_LOW);
            ch.setDescription("Ежедневная рекомендация фильма");
            ctx.getSystemService(NotificationManager.class)
                    .createNotificationChannel(ch);
        }

        Intent i = new Intent(ctx, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi = PendingIntent.getActivity(
                ctx, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Notification n = new NotificationCompat.Builder(ctx, CH_ID)
                .setSmallIcon(R.drawable.ic_push_bell)
                .setContentTitle("Фильм дня: " + filmTitle)
                .setContentText("Сегодняшний фильм!")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(filmTitle))
                .setOngoing(true)
                .setAutoCancel(false)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pi)
                .build();

        NotificationManagerCompat.from(ctx).notify(NOTIF, n);
    }
}
