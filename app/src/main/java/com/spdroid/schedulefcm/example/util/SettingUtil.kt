package com.spdroid.schedulefcm.example.util

import android.content.Context
import android.provider.Settings

fun isTimeAutomatic(context: Context): Boolean {
    return Settings.Global.getInt(
        context.contentResolver,
        Settings.Global.AUTO_TIME,
        0
    ) == 1;
}