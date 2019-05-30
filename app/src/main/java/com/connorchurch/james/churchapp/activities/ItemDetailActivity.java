package com.connorchurch.james.churchapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.connorchurch.james.churchapp.R;


public class ItemDetailActivity extends FragmentActivity {
        ItemDetailFragment fragmentItemDetail;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_item_detail);
            Item item = (Item) getIntent().getSerializableExtra("item");
            if (savedInstanceState == null) {
                fragmentItemDetail = ItemDetailFragment.newInstance(item);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flDetailContainer, fragmentItemDetail);
                ft.commit();
            }


            //final WebView view = getView().findViewById(R.id.webViewFacebook);
            final WebView view = findViewById(R.id.webViewFacebook);

            view.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return false;
                }
            });
            view.getSettings().setJavaScriptEnabled(true);
            view.setVisibility(View.VISIBLE);
            view.loadUrl("https://www.facebook.com/connorpci/");

            view.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
                        view.clearCache(true);
                        view.setVisibility(View.INVISIBLE);
                        return true;
                    }
                    return false;
                }
            });










        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.item_detail, menu);
            return true;
        }



    }


