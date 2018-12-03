package com.connorchurch.james.churchapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VerseNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verse_notification);


        NotificationManager notification =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification.Builder
                (getApplicationContext()).setContentTitle("Verse of the Day").setContentText("").build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.notify(0, notify);

    }

}
