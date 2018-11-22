package com.example.james.testapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementsActivity extends AppCompatActivity {

    TextView internetConnnection;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verse);

        // TODO Error-catch when no Internet connection available (crashes when not connected to Internet)
        try {
            ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cManager.getActiveNetworkInfo();

            if (nInfo != null && nInfo.isConnected()) {

                webView = new WebView(this);
                webView = new WebView(this);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());

                webView.loadUrl("https://drive.google.com/file/d/1H4Glf0EzgOIMc4P_OJoTG_5mIywYGaOS/view?usp=sharing");

                setContentView(webView);

            } else if (nInfo == null) {

                internetConnnection.setVisibility(View.VISIBLE);

            }
        } catch (Exception e) {
            Toast.makeText(AnnouncementsActivity.this, "No Internet connection!", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onBackPressed() {
        Intent first = new Intent(AnnouncementsActivity.this, MainActivity.class);
        startActivity(first);

    }
}




