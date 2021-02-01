package com.mytask.util;

import android.content.Context;
import android.provider.Settings;

public class DeviceData {

    public static String GetDeviceId(Context context)
    {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}