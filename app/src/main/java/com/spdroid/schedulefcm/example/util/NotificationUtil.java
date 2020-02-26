package com.spdroid.schedulefcm.example.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.spdroid.schedulefcm.example.R;
import com.spdroid.schedulefcm.example.ui.MainActivity;

import java.util.Random;


/**
 * Created by rogergcc on 22/02/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class NotificationUtil {

    private final Context context;

    public NotificationUtil(Context context) {
        //super();
        this.context = context;
    }


    public final void showNotification(@NonNull String title, @NonNull String message) {

        Intent intent = new Intent(this.context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this.context, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = this.context.getString(R.string.default_notification_channel_id);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(2);
        NotificationCompat.Builder notificationBuilder = (new NotificationCompat.Builder(this.context, channelId))
                .setColor(ContextCompat.getColor(this.context, android.R.color.holo_red_dark))
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle(title)
                .setContentText(message).setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(defaultSoundUri).setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) this.context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager == null) {
            return;
        }

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Default Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(new Random().nextInt(), notificationBuilder.build());

    }

}
