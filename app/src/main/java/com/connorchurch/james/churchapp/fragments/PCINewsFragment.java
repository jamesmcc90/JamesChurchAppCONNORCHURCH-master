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

public class PCINewsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view  = inflater.inflate(R.layout.pci_news, container, false);

        final WebView PCI = view.findViewById(R.id.webViewPCI);
        PCI.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        PCI.getSettings().setJavaScriptEnabled(true);
        PCI.setVisibility(View.VISIBLE);
        PCI.loadUrl("https://www.presbyterianireland.org/News.aspx");

        PCI.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK)
                {
                    PCI.clearCache(true);
                    PCI.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });

        return view;
    }
}
