package com.spdroid.schedulefcm.example.fcm;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.spdroid.schedulefcm.example.util.NotificationUtil;


public final class ScheduledWorker extends Worker {
    private static final String TAG = "ScheduledWorker";
    static final String NOTIFICATION_TITLE = "notification_title";
    static final String NOTIFICATION_MESSAGE = "notification_message";

    public ScheduledWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "Work START");

        // Get Notification Data
        String title = this.getInputData().getString(NOTIFICATION_TITLE);
        String message = this.getInputData().getString(NOTIFICATION_MESSAGE);
        Context context = this.getApplicationContext();

        // Show Notification
        NotificationUtil notificationUtil = new NotificationUtil(context);
        if (title != null) {
            if (message != null) {
                notificationUtil.showNotification(title, message);
            }
        }
        // TODO Do your other Background Processing
        Log.d(TAG, "Work DONE");
        // Return result
        return Result.success();

    }
}
