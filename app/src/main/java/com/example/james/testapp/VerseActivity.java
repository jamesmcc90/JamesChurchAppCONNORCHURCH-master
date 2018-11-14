package com.example.james.testapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VerseActivity extends AppCompatActivity {
    TextView internetConnnection;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verse);
        Spinner spinner = findViewById(R.id.spinner);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select...");
        categories.add("About");
        categories.add("Version");


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


                if(position == 1) {
                    Intent i = new Intent();
                    i.setClass(VerseActivity.this, AboutAppActivity.class);
                    finish();
                    startActivity(i);
                }
                if(position == 2)
                {
                    Intent i = new Intent();
                    i.setClass(VerseActivity.this, VersionActivity.class);
                    finish();
                    startActivity(i);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cManager.getActiveNetworkInfo();
        WebView webView = findViewById(R.id.webViewVerse);

        if(nInfo != null && nInfo.isConnected()) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());

            String customHTML = "<html><head><h1>Verse of the Day</h1></head><style>body{background-color:#1e73be; color:white}</style><script src=\"https://www.biblegateway.com/votd/votd.write.callback.js\"></script>\n" +
                    "<script src=\"https://www.biblegateway.com/votd/get/?format=json&version=NIV&callback=BG.votdWriteCallback\"></script>\n" +
                    "<!-- alternative for no javascript -->\n" +
                    "<noscript>\n" +
                    "<iframe framespacing=\"0\" frameborder=\"no\" src=\"https://www.biblegateway.com/votd/get/?format=html&version=NIV\">View Verse of the Day</iframe> \n" +
                    "</noscript></html>";
            webView.loadData(customHTML, "text/html", "UTF-8");

        }else if(nInfo == null){
            internetConnnection = findViewById(R.id.internetInfo);
            internetConnnection.setVisibility(View.VISIBLE);

        }


    }


    @Override
    public void onBackPressed(){
        Intent first = new Intent(VerseActivity.this,MainActivity.class);
        startActivity(first);

    }



}
