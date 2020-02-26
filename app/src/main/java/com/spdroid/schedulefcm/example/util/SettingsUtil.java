package com.spdroid.schedulefcm.example.util;

import android.content.Context;
import android.provider.Settings;


/**
 * Created by rogergcc on 22/02/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public final class SettingsUtil {
    public static boolean isTimeAutomatic( Context context) {
//        Intrinsics.checkParameterIsNotNull(context, "context");
        return Settings.Global.getInt(context.getContentResolver(), "auto_time", 0) == 1;
    }

}
