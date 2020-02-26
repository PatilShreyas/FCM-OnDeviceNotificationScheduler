package com.spdroid.schedulefcm.example.fcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import static com.spdroid.schedulefcm.example.fcm.ScheduledWorker.NOTIFICATION_MESSAGE;
import static com.spdroid.schedulefcm.example.fcm.ScheduledWorker.NOTIFICATION_TITLE;


/**
 * Created by rogergcc on 22/02/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class NotificationBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        if (intent != null) {
            if (intent.getExtras() == null) {
                return;
            }
            String title = intent.getStringExtra(NOTIFICATION_TITLE);
            String message = intent.getStringExtra(NOTIFICATION_MESSAGE);

            // Create Notification Data
            Data notificationData  = (new Data.Builder()).putString(NOTIFICATION_TITLE, title).putString(NOTIFICATION_MESSAGE, message).build();
            if (notificationData  == Data.EMPTY) {
                return;
            }
            // Init Worker
            OneTimeWorkRequest work = ((new OneTimeWorkRequest.Builder(ScheduledWorker.class)).setInputData(notificationData )).build();

            // Start Worker
            WorkManager.getInstance().beginWith(work).enqueue();
            Log.d(this.getClass().getName(), "WorkManager is Enqueued.");
        }
    }
}