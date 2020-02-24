package com.spdroid.schedulefcm.example.util;

import android.content.Context;
import android.provider.Settings;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

/**
 * Created by rogergcc on 22/02/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public final class SettingsUtilJava {
    public static boolean isTimeAutomatic(@NotNull Context context) {
//        Intrinsics.checkParameterIsNotNull(context, "context");
        return Settings.Global.getInt(context.getContentResolver(), "auto_time", 0) == 1;
    }

}
