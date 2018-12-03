package com.connorchurch.james.churchapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class AnnouncementsActivity extends AppCompatActivity {

    TextView internetConnnection;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verse);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cManager.getActiveNetworkInfo();

            if (nInfo != null && nInfo.isConnected()) {

                webView = new WebView(this);
                webView = new WebView(this);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());

                webView.loadUrl("https://drive.google.com/file/d/1WTA91W2Aj-dbkbvo6i3_vEu2nkXh6CWA/view?usp=sharing");

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




