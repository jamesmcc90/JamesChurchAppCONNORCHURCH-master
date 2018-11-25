package com.example.james.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class ExternalLinksActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_links);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                            view.clearCache(true);
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
                        {   view.clearCache(true);
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
                        {   view.clearCache(true);
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
            startActivity(new Intent(ExternalLinksActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent first = new Intent(ExternalLinksActivity.this, MainActivity.class);
        startActivity(first);

    }
}

