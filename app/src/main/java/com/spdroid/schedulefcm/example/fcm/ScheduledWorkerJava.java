package com.spdroid.schedulefcm.example.fcm;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.spdroid.schedulefcm.example.util.NotificationUtilJava;

import org.jetbrains.annotations.NotNull;


public final class ScheduledWorkerJava extends Worker {
    private static final String TAG = "ScheduledWorkerJava";
    @NotNull
    public static final String NOTIFICATION_TITLE = "notification_title";
    @NotNull
    public static final String NOTIFICATION_MESSAGE = "notification_message";

//    public static final ScheduledWorkerJava.Companion Companion = new ScheduledWorkerJava.Companion((DefaultConstructorMarker)null);

    public ScheduledWorkerJava(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
//        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
//        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
//        super(appContext, workerParams);
    }

    @NotNull
    public Result doWork() {
        Log.d(TAG, "Work START");
        String title = this.getInputData().getString(NOTIFICATION_TITLE);
        String message = this.getInputData().getString(NOTIFICATION_MESSAGE);
        Context var10002 = this.getApplicationContext();

//        Intrinsics.checkExpressionValueIsNotNull(var10002, "applicationContext");
        Result var3;

        NotificationUtilJava var10000 = new NotificationUtilJava(var10002);
//            if (title == null) {
////            Intrinsics.throwNpe();
//
//            }else
//
//            if (message == null) {
////            Intrinsics.throwNpe();
//            }

        if (title != null) {
            if (message != null) {
                var10000.showNotification(title, message);
            }
        }
        Log.d(TAG, "Work DONE");
        var3 = Result.success();
        return var3;


//        Intrinsics.checkExpressionValueIsNotNull(var3, "Result.success()");

    }


//    private void showNotification(String task, String desc) {
//
//        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//
//
//        String channelId = "task_channel";
//        String channelName = "task_name";
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            NotificationChannel channel = new
//                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
//            if (manager != null) {
//                manager.createNotificationChannel(channel);
//            }
//        }
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
//                .setContentTitle(task)
//                .setContentText(desc)
//                .setSmallIcon(R.mipmap.ic_launcher);
//
//        manager.notify(1, builder.build());
//
//    }


}
