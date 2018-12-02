package com.example.james.testapp;

import android.content.Intent;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class RotasActivity extends AppCompatActivity{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.rotas);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            Spinner spinner = findViewById(R.id.spnRotas);

            // Spinner Drop down elements
            List<String> categories = new ArrayList<String>();
            categories.add("Children's Church");
            categories.add("Creche");
            categories.add("Sound/PowerPoint");


            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                @Override
                public void onItemSelected(AdapterView<?> parent, View arg1,
                                           int position, long arg3) {

                    if(position == 0) {
                        final WebView view = (findViewById(R.id.webViewRotas));
                        view.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                view.loadUrl(url);
                                return false;
                            }
                        });
                        view.getSettings().setJavaScriptEnabled(true);
                        view.setVisibility(View.VISIBLE);
                        view.loadUrl("https://drive.google.com/file/d/1ai7KtwXQZV1Bf5vl0ZirNcY46ybCABWm/view?usp=sharing");

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
                    if(position == 1)
                    {
                        final WebView view = (findViewById(R.id.webViewRotas));
                        view.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                view.loadUrl(url);
                                return false;
                            }
                        });
                        view.getSettings().setJavaScriptEnabled(true);
                        view.setVisibility(View.VISIBLE);
                        view.loadUrl("https://drive.google.com/file/d/1WTA91W2Aj-dbkbvo6i3_vEu2nkXh6CWA/view?usp=sharing");

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


                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

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
            startActivity(new Intent(RotasActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent first = new Intent(RotasActivity.this,MainActivity.class);
        startActivity(first);

    }
}