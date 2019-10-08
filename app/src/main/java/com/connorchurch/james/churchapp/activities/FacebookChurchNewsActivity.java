package com.connorchurch.james.churchapp.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.connorchurch.james.churchapp.R;

public class FacebookChurchNewsActivity extends AppCompatActivity {
    TextView internetConnnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_church_news);
        final WebView view = findViewById(R.id.webViewFacebookNews);



        view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        view.getSettings().setJavaScriptEnabled(true);
        view.setVisibility(View.VISIBLE);
        view.loadUrl("https://www.facebook.com/1stConnorBB/");

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    view.clearCache(true);
                    view.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed(){
        final WebView view = findViewById(R.id.webViewFacebookNews);
        view.setVisibility(View.INVISIBLE);
        Intent first = new Intent(FacebookChurchNewsActivity.this, ListLinksActivity.class);
        startActivity(first);

    }
}



