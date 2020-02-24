package com.spdroid.schedulefcm.example.fcm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.spdroid.schedulefcm.example.util.NotificationUtilJava;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import static com.spdroid.schedulefcm.example.util.SettingsUtilJava.isTimeAutomatic;

/**
 * Created by rogergcc on 22/02/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class MyFirebaseMessagingServiceJava extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Intrinsics.checkParameterIsNotNull(remoteMessage, "remoteMessage");
        //        Intrinsics.checkExpressionValueIsNotNull(var10000, "remoteMessage.data");
        Map var2 = remoteMessage.getData();
        boolean var3 = false;
        boolean var16 = !var2.isEmpty();
        var3 = false;
        boolean var4 = false;
//        int var6 = false;
        Log.d("MyFirebaseMsgService", "Message data payload: " + remoteMessage.getData());
        String title = (String) remoteMessage.getData().get("title");
        String message = (String) remoteMessage.getData().get("message");
        Context var17 = this.getApplicationContext();
//        Intrinsics.checkExpressionValueIsNotNull(var17, "applicationContext");
        if (!isTimeAutomatic(var17)) {
            Log.d("MyFirebaseMsgService", "`Automatic Date and Time` is not enabled");
        } else {
            String var18 = (String) remoteMessage.getData().get("isScheduled");
            boolean var10;
            Boolean var19;
            if (var18 != null) {
                String var9 = var18;
                var10 = false;
                var19 = Boolean.parseBoolean(var9);
            } else {
                var19 = null;
            }

            Boolean isScheduled = var19;
            if (isScheduled != null) {
                var10 = false;
                boolean var12 = false;
                boolean it = isScheduled;
//                int var14 = false;
                if (it) {
                    String scheduledTime = (String) remoteMessage.getData().get("scheduledTime");
                    this.scheduleAlarm(scheduledTime, title, message);
                } else {
                    if (title == null) {
//                        Intrinsics.throwNpe();
                    }

                    if (message == null) {
//                        Intrinsics.throwNpe();
                    }

                    this.showNotification(title, message);
                }
            }

        }
    }

    private void scheduleAlarm(String scheduledTimeString, String title, String message) {
        Object var10000 = this.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        if (var10000 == null) {
//            throw new TypeCastException("null cannot be cast to non-null type android.app.AlarmManager");
        } else {
            AlarmManager alarmMgr = (AlarmManager) var10000;
            Intent var6 = new Intent(this.getApplicationContext(), NotificationBroadcastReceiverJava.class);
//            boolean var7 = false;
//            boolean var8 = false;
//            int var10 = false;
            var6.putExtra("notification_title", title);
            var6.putExtra("notification_message", message);
            PendingIntent alarmIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, var6, 0);
            SimpleDateFormat var13 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            if (scheduledTimeString == null) {
//                Intrinsics.throwNpe();
            }

            Date scheduledTime = null;
            try {
                if (scheduledTimeString != null) {
                    scheduledTime = var13.parse(scheduledTimeString);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (scheduledTime != null) {
//                var8 = false;
//                boolean var9 = false;
////                int var11 = false;
                alarmMgr.set(AlarmManager.RTC_WAKEUP, scheduledTime.getTime(), alarmIntent);
            }

        }
    }

    private void showNotification(String title, String message) {
        Context var10002 = this.getApplicationContext();
//        Intrinsics.checkExpressionValueIsNotNull(var10002, "applicationContext");
        (new NotificationUtilJava(var10002)).showNotification(title, message);
    }


    @Override
    public void onNewToken(@NotNull String token) {
        super.onNewToken(token);
        Log.d("MyFirebaseMsgService", "Refreshed token: " + token);
    }

}
