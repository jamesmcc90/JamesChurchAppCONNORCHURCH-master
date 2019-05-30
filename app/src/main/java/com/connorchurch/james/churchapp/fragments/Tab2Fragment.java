package com.connorchurch.james.churchapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.connorchurch.james.churchapp.R;

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){


/*
        Configuration config = getResources().getConfiguration();

        if (config.smallestScreenWidthDp >= 600) {
            View view  = inflater.inflate(R.layout.tab1_fragment_tablet, container, false);
            return view;
        } else {
            View view  = inflater.inflate(R.layout.tab1_fragment, container, false);
            return view;
        }
*/

        View view  = inflater.inflate(R.layout.tab2_fragment, container, false);

        ImageView btnBB = view.findViewById(R.id.imgBB);
        btnBB.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View v) {
                final WebView view = getView().findViewById(R.id.webViewFacebook);
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

        ImageView btnGB = view.findViewById(R.id.imgGB);
        btnGB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final WebView view = getView().findViewById(R.id.webViewFacebook);
                view.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return false;
                    }
                });
                view.getSettings().setJavaScriptEnabled(true);
                view.setVisibility(View.VISIBLE);
                view.loadUrl("https://www.facebook.com/connor.gb.3");

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
        return view;
    }
}