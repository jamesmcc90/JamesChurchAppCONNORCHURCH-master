package com.example.james.testapp.main;


import android.app.Application;

import com.sendbird.android.SendBird;
import com.example.james.testapp.utils.PreferenceUtils;

public class BaseApplication extends Application {

    private static final String APP_ID = "95C57E8FA-A024-42A1-A2BE-18FAEE3AD1F5"; // US-1 Demo
    public static final String VERSION = "3.0.40";

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceUtils.init(getApplicationContext());

        SendBird.init(APP_ID, getApplicationContext());
    }
}
