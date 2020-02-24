package com.spdroid.schedulefcm.example.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.spdroid.schedulefcm.example.R;
import com.spdroid.schedulefcm.example.ui.Main2Activity;

import org.jetbrains.annotations.NotNull;

import kotlin.TypeCastException;
import kotlin.random.Random;

/**
 * Created by rogergcc on 22/02/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class NotificationUtilJava {

    private final Context context;

    public NotificationUtilJava(Context context) {
        //super();
        this.context = context;
    }


    public final void showNotification(@NotNull String title, @NotNull String message) {
//        Intrinsics.checkParameterIsNotNull(title, "title");
//        Intrinsics.checkParameterIsNotNull(message, "message");
        Intent intent = new Intent(this.context, Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this.context, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String var10000 = this.context.getString(R.string.default_notification_channel_id);

//        Intrinsics.checkExpressionValueIsNotNull(var10000, "context.getString(R.stri…_notification_channel_id)");
        String channelId = var10000;
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(2);
        NotificationCompat.Builder notificationBuilder =(new NotificationCompat.Builder(this.context, channelId))
                        .setColor(ContextCompat.getColor(this.context, android.R.color.holo_red_dark)).setSmallIcon(-700129).setContentTitle((CharSequence)title).setContentText((CharSequence)message).setAutoCancel(true).setPriority(1).setSound(defaultSoundUri).setContentIntent(pendingIntent);
        Object var10 = this.context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (var10 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
        } else {
            NotificationManager notificationManager = (NotificationManager)var10;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId, (CharSequence)"Default Channel", 4);
                notificationManager.createNotificationChannel(channel);
            }

            notificationManager.notify(Random.Default.nextInt(), notificationBuilder.build());
        }
    }

}
