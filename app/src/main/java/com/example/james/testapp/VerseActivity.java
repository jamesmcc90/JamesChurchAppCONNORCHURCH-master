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

public class VerseActivity extends AppCompatActivity {
    TextView internetConnnection;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verse);


        try {
            ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cManager.getActiveNetworkInfo();
            WebView webView = findViewById(R.id.webViewVerse);

            if (nInfo != null && nInfo.isConnected()) {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());

                String customHTML = "<html><head><h1>Verse of the Day</h1></head>" +
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

            @Override
            public void onBackPressed () {
                Intent first = new Intent(VerseActivity.this, MainActivity.class);
                startActivity(first);

            }

    }