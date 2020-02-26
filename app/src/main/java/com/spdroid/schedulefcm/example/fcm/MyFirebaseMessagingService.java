package com.spdroid.schedulefcm.example.fcm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.spdroid.schedulefcm.example.util.NotificationUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import static com.spdroid.schedulefcm.example.fcm.ScheduledWorker.NOTIFICATION_MESSAGE;
import static com.spdroid.schedulefcm.example.fcm.ScheduledWorker.NOTIFICATION_TITLE;
import static com.spdroid.schedulefcm.example.util.SettingsUtil.isTimeAutomatic;


/**
 * Created by rogergcc on 22/02/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Map remoteMessageData = remoteMessage.getData();
        // Check if message contains a data payload.
        if (remoteMessageData.isEmpty()) {
            return;
        }
        Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        // Get Message details
        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");
        Context context = this.getApplicationContext();

        // Check that 'Automatic Date and Time' settings are turned ON.
        // If it's not turned on, Return
        if (!isTimeAutomatic(context)) {
            Log.d(TAG, "`Automatic Date and Time` is not enabled");
            return;
        }
        // Check whether notification is scheduled or not
        String dataSchedule = remoteMessage.getData().get("isScheduled");

        if (dataSchedule == null) {
            return;
        }
        boolean it = Boolean.parseBoolean(dataSchedule);
        if (it) {
            String scheduledTime = remoteMessage.getData().get("scheduledTime");
            this.scheduleAlarm(scheduledTime, title, message);
        } else {
            if (title != null && message != null) {
                this.showNotification(title, message);
            }
        }
    }

    private void scheduleAlarm(String scheduledTimeString, String title, String message) {
        Context mcontext = this.getApplicationContext();
        if (mcontext != null) {
            AlarmManager alarmMgr = (AlarmManager) mcontext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this.getApplicationContext(), NotificationBroadcastReceiver.class);

            intent.putExtra(NOTIFICATION_TITLE, title);
            intent.putExtra(NOTIFICATION_MESSAGE, message);
            PendingIntent alarmIntent = PendingIntent.getBroadcast(mcontext, 0, intent, 0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

            Date scheduledTime = null;
            try {
                if (scheduledTimeString != null) {
                    scheduledTime = sdf.parse(scheduledTimeString);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (scheduledTime != null) {

                if (alarmMgr != null) {
                    alarmMgr.set(AlarmManager.RTC_WAKEUP, scheduledTime.getTime(), alarmIntent);
                }
            }
        }
    }

    private void showNotification(String title, String message) {
        Context mcontext = this.getApplicationContext();
        if (mcontext != null)
            new NotificationUtil(mcontext).showNotification(title, message);
    }


    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG, "Refreshed token: " + token);
    }
}
