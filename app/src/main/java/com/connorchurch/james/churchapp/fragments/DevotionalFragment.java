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

import com.connorchurch.james.churchapp.R;

public class DevotionalFragment extends Fragment {

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view  = inflater.inflate(R.layout.devotional, container, false);

        final WebView devotional = view.findViewById(R.id.webDevotional);
        devotional.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        devotional.getSettings().setJavaScriptEnabled(true);
        devotional.setVisibility(View.VISIBLE);
        devotional.loadUrl("https://ourdailybread.org/read/");

        devotional.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK)
                {
                    devotional.clearCache(true);
                    devotional.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });

        return view;

    }
}
