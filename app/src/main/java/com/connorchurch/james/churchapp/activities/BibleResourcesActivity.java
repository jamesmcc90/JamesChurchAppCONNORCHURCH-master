package com.connorchurch.james.churchapp.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.connorchurch.james.churchapp.R;

import java.util.Calendar;

import BroadcastReceiver.VerseReceiver;

public class BibleResourcesActivity extends AppCompatActivity {
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cManager.getActiveNetworkInfo();
            WebView webView = findViewById(R.id.webViewVerse);

            if (nInfo != null && nInfo.isConnected()) {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());

                String customHTML = "<html>" +
                        "<style>" +
                        "body{background-color:#ffff; color:#1e73be} a {color:#1e73be;}" +
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
            Toast.makeText(BibleResourcesActivity.this, "No Internet connection!", Toast.LENGTH_LONG).show();
        }


        ImageView BibleApp = findViewById(R.id.btnYouVersion);

        BibleApp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.sirma.mobile.bible.android"));
                intent.setPackage("com.android.vending");
                startActivity(intent);
            }
        });


    }




    private void registerAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 8);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(BibleResourcesActivity.this, VerseReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(BibleResourcesActivity.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT );
        AlarmManager am = (AlarmManager)this.getSystemService(ALARM_SERVICE);
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
            startActivity(new Intent(BibleResourcesActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @Override
    public void onBackPressed () {
         Intent first = new Intent(BibleResourcesActivity.this, MainActivity.class);
         startActivity(first);

         }

    }