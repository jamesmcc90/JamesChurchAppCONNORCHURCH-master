package com.connorchurch.james.churchapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import android.view.View.OnClickListener;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import BroadcastReceiver.VerseReceiver;

public class VerseActivity extends AppCompatActivity {
    TextView internetConnnection;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Configuration config = getResources().getConfiguration();

        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.verse_tablet);
        } else {
            setContentView(R.layout.verse);
        }

        registerAlarm();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cManager.getActiveNetworkInfo();
            WebView webView = findViewById(R.id.webViewVerse);

            if (nInfo != null && nInfo.isConnected()) {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());

                String customHTML = "<html>" +
                        "<style>" +
                        "body{background-color:#1e73be; color:white} a {color:#1fcc94;}" +
                        "</style>" +
                        "<script src=\"https://www.biblegateway.com/votd/votd.write.callback.js\"></script>\n" + " " +
                        "<script src=\"https://www.biblegateway.com/votd/get/?format=json&version=NIV&callback=BG.votdWriteCallback\"></script>\n" +
                        "<!-- alternative for no javascript -->\n" +
                        "<noscript>\n" +
                        "<iframe framespacing=\"0\" frameborder=\"no\" src=\"https://www.biblegateway.com/votd/get/?format=html&version=NIV\">View Verse of the Day</iframe> \n" +
                        "</noscript></html>";
                webView.loadData(customHTML, "text/html", "UTF-8");

            } else if (nInfo == null) {

                internetConnnection.setVisibility(View.VISIBLE);

            }

        } catch (Exception e) {
            Toast.makeText(VerseActivity.this, "No Internet connection!", Toast.LENGTH_LONG).show();
        }


    }

    private void registerAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 8);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(VerseActivity.this, VerseReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(VerseActivity.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT );
        AlarmManager am = (AlarmManager)this.getSystemService(this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
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
            startActivity(new Intent(VerseActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @Override
    public void onBackPressed () {
         Intent first = new Intent(VerseActivity.this, MainActivity.class);
         startActivity(first);

         }

    }