package com.example.james.testapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SermonsActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private TextView textMaxTime;
    private TextView textCurrentPosition;
    private Button buttonPause;
    private Button buttonStart;
    private SeekBar seekBar;
    private Handler threadHandler = new Handler();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;


        }
    };

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sermons);

        Button previous = findViewById(R.id.btnPrevious);
        Button rewind = findViewById(R.id.btnRewind);
        Button play = findViewById(R.id.btnPlay);
        Button pause = findViewById(R.id.btnPause);
        Button next = findViewById(R.id.btnNext);


        previous.setText("|<");
        rewind.setText("<<");
        play.setText(">");
        pause.setText("||");
        next.setText(">>");

        Spinner spinner = findViewById(R.id.spinner);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select...");
        categories.add("About");
        categories.add("Version");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int position, long arg3) {


                if(position == 1) {
                    Intent i = new Intent();
                    i.setClass(SermonsActivity.this, AboutAppActivity.class);
                    finish();
                    startActivity(i);
                }
                if(position == 2)
                {
                    Intent i = new Intent();
                    i.setClass(SermonsActivity.this, VersionActivity.class);
                    finish();
                    startActivity(i);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        ImageButton Back = findViewById(R.id.btnBack);
        Back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(SermonsActivity.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        ImageButton audioList = findViewById(R.id.btnAudioList);
        audioList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(SermonsActivity.this, Sermons_Activity_TEST.class);
                startActivity(myIntent);
                finish();
            }
        });

        //write code to play selected audio track from Sermons_Activity_TEST.java






    }
}