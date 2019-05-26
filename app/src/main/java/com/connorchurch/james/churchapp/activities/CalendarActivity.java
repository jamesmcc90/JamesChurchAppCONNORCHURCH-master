package com.connorchurch.james.churchapp.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.connorchurch.james.churchapp.R;


public class CalendarActivity extends AppCompatActivity {
    TextView internetConnnection;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Configuration config = getResources().getConfiguration();

        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.calendar_tablet);
        } else {
            setContentView(R.layout.calendar);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // launch settings activity
            startActivity(new Intent(CalendarActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @Override
    public void onBackPressed(){
        Intent first = new Intent(CalendarActivity.this, MainActivity.class);
        startActivity(first);

    }
}
