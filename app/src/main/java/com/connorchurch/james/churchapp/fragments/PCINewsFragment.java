package com.connorchurch.james.churchapp.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

        return view;
    }
}
