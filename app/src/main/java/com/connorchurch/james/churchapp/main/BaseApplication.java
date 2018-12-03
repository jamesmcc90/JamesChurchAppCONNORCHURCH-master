package com.connorchurch.james.churchapp.main;


import android.app.Application;

import com.connorchurch.james.churchapp.utils.PreferenceUtils;
import com.crashlytics.android.Crashlytics;
import com.sendbird.android.SendBird;

import io.fabric.sdk.android.Fabric;

public class BaseApplication extends Application {

    private static final String APP_ID = "95C57E8FA-A024-42A1-A2BE-18FAEE3AD1F5"; // US-1 Demo
    public static final String VERSION = "3.0.40";

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        PreferenceUtils.init(getApplicationContext());

        SendBird.init(APP_ID, getApplicationContext());
    }
}
