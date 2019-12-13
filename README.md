**Support This Repository**

![Github Followers](https://img.shields.io/github/followers/PatilShreyas?label=Follow&style=social)
![GitHub stars](https://img.shields.io/github/stars/PatilShreyas/FCM-OnDeviceNotificationScheduler?style=social)
![GitHub forks](https://img.shields.io/github/forks/PatilShreyas/FCM-OnDeviceNotificationScheduler?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/PatilShreyas/FCM-OnDeviceNotificationScheduler?style=social)
![Twitter Follow](https://img.shields.io/twitter/follow/imShreyasPatil?label=Follow&style=social)

# 🔔 FCM - Push Notification Scheduler⏰ (On Device 📱) 
This is demo app to implement FCM On Device Push Notification Scheduling using `AlarmManager` and `WorkManager`.

## Introduction
- We can use [***Cloud Pub/Sub***](https://firebase.google.com/docs/functions/schedule-functions) with [***Firebase Cloud Functions***](https://firebase.google.com/docs/functions) to send FCM Push Notifications. But this solution is costly to implement.
- Here, We are using regular FCM Push Notifications and scheduling it *on device*.

In this demo app, We are subscribing to a FCM Channel *`discount-offers`*. We'll receive **Data** payload from FCM to this subscribed channel and we will process and schedule it.

## Dependencies
- [**`Firebase Messaging`**](https://firebase.google.com/docs/cloud-messaging/android/client) - Firebase Cloud Messaging Library.
- **`Firebase IID`** - Firebase Instance ID Library.
- [**`WorkManager`**](https://developer.android.com/topic/libraries/architecture/workmanager) - Used for Background Work Processing.

## What's Happening?
- Subscribe to *`discount-offers`* FCM Channel from Android Device.
- **Data Payload** will be as follows
```json
  { 
    "to": "/topics/discount-offers", 
    "priority": "high",
    "data" : {
      "title" : "TITLE_HERE",
      "message" : "MESSAGE_HERE",
      "isScheduled" : "true",
      "scheduledTime" : "2019-12-13 09:41:00"
    }
}
```
  *Format of `scheduledTime`: **YYYY-MM-DD HH:MM:SS***
- Receive FCM on device and `onMessageReceived()` in [`MyFirebaseMessagingService`](https://github.com/PatilShreyas/FCM-OnDeviceNotificationScheduler/blob/master/app/src/main/java/com/spdroid/schedulefcm/example/fcm/MyFirebaseMessagingService.kt) will be invoked. In `onMessageReceived()` following operations will be done-
  - If `isScheduled` parameter received is `false` then notifications is displayed in system tray instantly.
  - If `isScheduled` is `true` then `scheduledTime` is parsed from payload and `AlarmManager` is used to set *one-time* alarm at that time and [`NotificationBroadcastReceiver`](https://github.com/PatilShreyas/FCM-OnDeviceNotificationScheduler/blob/master/app/src/main/java/com/spdroid/schedulefcm/example/fcm/NotificationBroadcastReceiver.kt) implementation will be executed on that time.
  - In `onReceive()`, we have scheduled a `WorkManager` [`NotificationScheduler`](https://github.com/PatilShreyas/FCM-OnDeviceNotificationScheduler/blob/master/app/src/main/java/com/spdroid/schedulefcm/example/fcm/NotificationScheduler.kt) for background work processing. There in `doWork()`, we're finally we're displaying Notification on system tray. Do any background proessing and return status from `WorkManager`.
  
Hurrah!😍 we have successfully implemented On-Device Scheduling of FCM Push Notification👍.

### Connect With Me
Visit [My Profile](https://patilshreyas.github.io).
