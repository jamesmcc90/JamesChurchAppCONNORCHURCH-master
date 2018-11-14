package com.example.james.testapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    TextView internetConnnection;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
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
                    i.setClass(CalendarActivity.this, AboutAppActivity.class);
                    finish();
                    startActivity(i);
                }
                if(position == 2)
                {
                    Intent i = new Intent();
                    i.setClass(CalendarActivity.this, VersionActivity.class);
                    finish();
                    startActivity(i);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cManager.getActiveNetworkInfo();

        if(nInfo != null && nInfo.isConnected()) {
            internetConnnection = findViewById(R.id.internetInfo);
            internetConnnection.setVisibility(View.INVISIBLE);

            webView = findViewById(R.id.webView);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("https://calendar.google.com/calendar/htmlembed?title=Connor+Presbyterian+Church&showPrint=0&showCalendars=0&showTz=0&height=500&wkst=1&bgcolor=%23FFFFFF&src=info@connorpresbyterianchurch.org&color=%23853104&ctz=Europe/London&mode=MONTH");

        }else if(nInfo == null){
            internetConnnection = findViewById(R.id.internetInfo);
            internetConnnection.setVisibility(View.VISIBLE);

        }



    }

    @Override
    public void onBackPressed(){
        Intent first = new Intent(CalendarActivity.this,MainActivity.class);
        startActivity(first);

    }


}
