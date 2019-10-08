package com.connorchurch.james.churchapp.activities;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.connorchurch.james.churchapp.R;

public class AnnouncementsActivity extends AppCompatActivity {

    TextView internetConnnection;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verse);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cManager.getActiveNetworkInfo();

            if (nInfo != null && nInfo.isConnected()) {

                webView = new WebView(this);
                webView = new WebView(this);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());

                webView.loadUrl("https://drive.google.com/open?id=1ie92MfxCJA3UQNHS_WOgaK9kZoF4Jw30");

                setContentView(webView);

            } else if (nInfo == null) {

                internetConnnection.setVisibility(View.VISIBLE);

            }
        } catch (Exception e) {
            Toast.makeText(AnnouncementsActivity.this, "No Internet connection!", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_navigation, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent first = new Intent(AnnouncementsActivity.this, MainActivity.class);
        startActivity(first);

    }
}




