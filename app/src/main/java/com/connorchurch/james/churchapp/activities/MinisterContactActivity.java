package com.connorchurch.james.churchapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.connorchurch.james.churchapp.R;

public class MinisterContactActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minister);

        ImageView phone = findViewById(R.id.imgPhone);
        final ImageView email = findViewById(R.id.imgEmail);

        phone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialContactPhone("02825898364");
            }

        });


        String mailTo = "connorrev@icloud.com";
        final Intent email_intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",mailTo, null));
        email_intent.setData(Uri.parse("mailto:"));
        email_intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"connorrev@icloud.com"});
        //email_intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject text here");
        //email_intent.putExtra(android.content.Intent.EXTRA_TEXT,"Body text here");

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Intent.createChooser(email_intent, "Send email..."));
            }
        });
    }


    private void dialContactPhone(final String phoneNumber){

        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));

    }


    public void onBackPressed(){
        Intent first = new Intent(MinisterContactActivity.this, MainActivity.class);
        startActivity(first);

    }
}
