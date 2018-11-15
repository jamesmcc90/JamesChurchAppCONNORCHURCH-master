package com.example.james.testapp;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;



public class MainActivity extends AppCompatActivity {


    private TextView mTextMessage;
int currentItem = 0;

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

    EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.spinner);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select...");
        categories.add("About");
        categories.add("Version");
        categories.add("Verse of the Day");
        categories.add("Announcements");
        categories.add("Church Chat (experimental)");
        categories.add("The Bible (experimental)");
        categories.add("Audio Streaming for Sermons/Talks - experimental");

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
                    i.setClass(MainActivity.this, AboutAppActivity.class);
                    finish();
                    startActivity(i);
                }
                if(position == 2)
                {
                    Intent i = new Intent();
                    i.setClass(MainActivity.this, VersionActivity.class);
                    finish();
                    startActivity(i);
                }
                if(position == 3)
                {
                    Intent i = new Intent();
                    i.setClass(MainActivity.this, VerseActivity.class);
                    finish();
                    startActivity(i);
                }
                if(position == 4)
                {
                    Intent i = new Intent();
                    i.setClass(MainActivity.this, AnnouncementsActivity.class);
                    finish();
                    startActivity(i);
                }
                if(position == 5)
                {
                    Intent i = new Intent();
                    i.setClass(MainActivity.this, ChatLoginActivity.class);
                    finish();
                    startActivity(i);
                }
                if(position == 6)
                {
                    Intent i = new Intent();
                    i.setClass(MainActivity.this, TheBibleActivity.class);
                    finish();
                    startActivity(i);
                }
                if(position == 7)
                {
                    Intent i = new Intent();
                    i.setClass(MainActivity.this, AudioStreamActivity.class);
                    finish();
                    startActivity(i);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });


Button About = findViewById(R.id.btnAboutUs);
Button Contact = findViewById(R.id.btnContactiUs);
Button Find = findViewById(R.id.btnFindUs);
Button Calendar = findViewById(R.id.btnCalendar);
Button Sermons = findViewById(R.id.btnSermons);
Button WhatsOn = findViewById(R.id.btnWhatsOn);

        About.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, AboutConnorActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        Contact.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        Find.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, FindUsActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        Calendar.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        Sermons.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, Sermons_Activity_TEST.class);
                startActivity(myIntent);
                finish();
            }
        });

        WhatsOn.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, WhatsOnActivity.class);
                startActivity(myIntent);
                finish();
            }
        });



    }

    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

}