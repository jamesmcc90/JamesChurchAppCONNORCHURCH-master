package com.connorchurch.james.churchapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;

import com.connorchurch.james.churchapp.activities.AudioStreamActivity;
import com.connorchurch.james.churchapp.activities.MainActivity;

public class Minister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minister);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView phone = findViewById(R.id.imgPhone);
        ImageView email = findViewById(R.id.imgEmail);


       phone.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent callIntent = new Intent(Intent.ACTION_CALL);
               callIntent.setData(Uri.parse("tel:07511947038"));

               startActivity(callIntent);
           }
       });




       email.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                       "mailto","james.mcc90@email.com", null));
               intent.putExtra(Intent.EXTRA_SUBJECT, "New email");
               intent.putExtra(Intent.EXTRA_TEXT, "");
               startActivity(Intent.createChooser(intent, "Choose an Email client :"));
           }
       });



    }

    @Override
    public void onBackPressed(){

        Intent first = new Intent(Minister.this, MainActivity.class);
        startActivity(first);

    }
}
