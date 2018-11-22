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


        try {
            ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cManager.getActiveNetworkInfo();
            WebView webView = findViewById(R.id.webView);

            if (nInfo != null && nInfo.isConnected()) {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());

                webView.loadUrl("https://calendar.google.com/calendar/htmlembed?title=Connor+Presbyterian+Church&showPrint=0&showCalendars=0&showTz=0&height=500&wkst=1&bgcolor=%23FFFFFF&src=info@connorpresbyterianchurch.org&color=%23853104&ctz=Europe/London&mode=MONTH");

            }

            }
            catch(Exception e){
                Toast.makeText(CalendarActivity.this, "No Internet connection!", Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public void onBackPressed(){
        Intent first = new Intent(CalendarActivity.this,MainActivity.class);
        startActivity(first);

    }
}
