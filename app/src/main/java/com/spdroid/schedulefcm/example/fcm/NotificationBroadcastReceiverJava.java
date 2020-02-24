package com.spdroid.schedulefcm.example.fcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


import androidx.annotation.Nullable;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import static com.spdroid.schedulefcm.example.fcm.ScheduledWorkerJava.NOTIFICATION_MESSAGE;
import static com.spdroid.schedulefcm.example.fcm.ScheduledWorkerJava.NOTIFICATION_TITLE;


/**
 * Created by rogergcc on 22/02/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class NotificationBroadcastReceiverJava extends BroadcastReceiver {
    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        if (intent != null) {
            boolean var4 = false;
            boolean var5 = false;
//            int var7 = false;
            String title = intent.getStringExtra(NOTIFICATION_TITLE);
            String message = intent.getStringExtra(NOTIFICATION_MESSAGE);
            Data var10000 = (new Data.Builder()).putString(NOTIFICATION_TITLE, title).putString(NOTIFICATION_MESSAGE, message).build();
//            Intrinsics.checkExpressionValueIsNotNull(var10000, "Data.Builder()\n         …\n                .build()");

            WorkRequest var12 = ((new OneTimeWorkRequest.Builder(ScheduledWorkerJava.class)).setInputData(var10000)).build();
//            Intrinsics.checkExpressionValueIsNotNull(var12, "OneTimeWorkRequest.Build…\n                .build()");

            OneTimeWorkRequest work = (OneTimeWorkRequest) var12;
            WorkManager.getInstance().beginWith(work).enqueue();
            Log.d(this.getClass().getName(), "WorkManager is Enqueued.");
        }

    }
}