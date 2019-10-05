package com.connorchurch.james.churchapp.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.connorchurch.james.churchapp.R;

import static android.content.Context.CONNECTIVITY_SERVICE;


public class VODFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.vod, container, false);

       final WebView VOD = view.findViewById(R.id.webViewVerse);
        VOD.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return false;
                }
            });

                VOD.getSettings().setJavaScriptEnabled(true);
                VOD.setVisibility(View.VISIBLE);

                String customHTML = "<html>" +
                        "<style>" +
                        "body{background-color:#ffff; color:#1e73be} a {color:#1e73be;}" +
                        "</style>" +
                        "<script src=\"https://www.biblegateway.com/votd/votd.write.callback.js\"></script>\n" + " " +
                        "<script src=\"https://www.biblegateway.com/votd/get/?format=json&version=NIV&callback=BG.votdWriteCallback\"></script>\n" +
                        "<!-- alternative for no javascript -->\n" +
                        "<noscript>\n" +
                        "<iframe framespacing=\"0\" frameborder=\"no\" src=\"https://www.biblegateway.com/votd/get/?format=html&version=NIV\">View Verse of the Day</iframe> \n" +
                        "</noscript></html>";
                VOD.loadData(customHTML, "text/html", "UTF-8");



        ImageView YouVersion =  view.findViewById(R.id.btnYouVersion);
        YouVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(
                        "https://play.google.com/store/apps/details?id=com.sirma.mobile.bible.android"));
                intent.setPackage("com.android.vending");
                startActivity(intent);

            }
        });

        return view;

    }

}