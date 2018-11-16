package com.example.james.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class ExternalLinksActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_links);

        ImageView ChristianInstitute = findViewById(R.id.btnCI);
        ImageView PCI = findViewById(R.id.btnPCI);
        ImageView DesiringGod =  findViewById(R.id.btnDesiring);

        ChristianInstitute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final WebView view = (findViewById(R.id.webExternalLinks));
                view.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return false;
                    }
                });
                view.getSettings().setJavaScriptEnabled(true);
                view.setVisibility(View.VISIBLE);
                view.loadUrl("https://www.christian.org.uk/");

                view.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if( keyCode == KeyEvent.KEYCODE_BACK)
                        {
                            view.setVisibility(View.INVISIBLE);
                            return true;
                        }
                        return false;
                    }
                });


            }


        });

        PCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final WebView view = (findViewById(R.id.webExternalLinks));
                view.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return false;
                    }
                });
                view.getSettings().setJavaScriptEnabled(true);
                view.setVisibility(View.VISIBLE);
                view.loadUrl("http://www.presbyterianireland.org/");

                view.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if( keyCode == KeyEvent.KEYCODE_BACK)
                        {
                            view.setVisibility(View.INVISIBLE);
                            return true;
                        }
                        return false;
                    }
                });


            }


        });

        DesiringGod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final WebView view = (findViewById(R.id.webExternalLinks));
                view.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return false;
                    }
                });
                view.getSettings().setJavaScriptEnabled(true);
                view.setVisibility(View.VISIBLE);
                view.loadUrl("https://www.desiringgod.org/");

                view.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if( keyCode == KeyEvent.KEYCODE_BACK)
                        {
                            view.setVisibility(View.INVISIBLE);
                            return true;
                        }
                        return false;
                    }
                });


            }


        });

    }


    @Override
    public void onBackPressed(){
        Intent first = new Intent(ExternalLinksActivity.this, MainActivity.class);
        startActivity(first);

    }
}

