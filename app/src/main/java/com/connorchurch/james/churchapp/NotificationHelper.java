package com.connorchurch.james.churchapp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;

public class NotificationHelper extends ContextWrapper {

    private static final String EDMT_CHANNEL_ID = "com.connorchurch.james.churchapp.EDMTDEV";
    private static final String EDMT_CHANNEL_NAME = "EDMTDEV Channel";
    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);
        createChannels();
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannels() {


        NotificationChannel edmtChannel = new NotificationChannel(EDMT_CHANNEL_ID, EDMT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        edmtChannel.enableLights(true);
        edmtChannel.enableVibration(true);
        edmtChannel.setLightColor(Color.GREEN);
        edmtChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(edmtChannel);
    }

    public NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

public Notification.Builder getEDMTChannelNotification(String title, String body){

        return new Notification.Builder(getApplicationContext(), EDMT_CHANNEL_ID)
                .setContentText(body)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true);
}
}
