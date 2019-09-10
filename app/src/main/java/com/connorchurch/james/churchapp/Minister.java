package com.connorchurch.james.churchapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.connorchurch.james.churchapp.activities.AudioStreamActivity;
import com.connorchurch.james.churchapp.activities.MainActivity;

public class Minister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minister);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onBackPressed(){

        Intent first = new Intent(Minister.this, MainActivity.class);
        startActivity(first);

    }
}
