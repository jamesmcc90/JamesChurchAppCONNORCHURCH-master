package com.connorchurch.james.churchapp.activities;

import android.app.Notification;
import android.app.NotificationManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import com.connorchurch.james.churchapp.R;


public class VerseNotificationActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verse_notification);

        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);
        ed3 = findViewById(R.id.editText3);
        Button b1 = findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = ed2.getText().toString().trim();
                String body = ed3.getText().toString().trim();

                NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify = new Notification.Builder
                        (getApplicationContext()).setContentText(body).
                        setContentTitle(subject).setSmallIcon(R.drawable.ic_notifications_black_24dp).build();

                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);

            }
        });



    }


    @Override
    public void onBackPressed(){
        Intent first = new Intent(VerseNotificationActivity.this, MainActivity.class);
        startActivity(first);

    }
}