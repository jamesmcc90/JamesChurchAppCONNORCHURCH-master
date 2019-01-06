package com.connorchurch.james.churchapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class FindUsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_us);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mapFragment.getMapAsync(this);


        ImageView Facebook = findViewById(R.id.btnFacebook);

        Facebook.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final WebView view = (findViewById(R.id.webSocialMedia));
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
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            view.setVisibility(View.INVISIBLE);
                            return true;
                        }
                        return false;
                    }
                });


            }


        });


       final Spinner spinner = findViewById(R.id.spnFind);

        // Spinner Drop down elements
        final List<String> categories = new ArrayList<String>();
        categories.add("NORMAL");
        categories.add("SATELLITE");
        categories.add("HYBRID");

        // Creating adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,categories);

        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // attaching data adapter to spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int position, long arg3) {

              if(position == 0) {
                  mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
              }else if (position == 1){
                  mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
              }else if (position == 2){
                  mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng connorPresbyterian = new LatLng(54.805873, -6.208884);
        mMap.addMarker(new MarkerOptions().position(connorPresbyterian).title("Connor Presbyterian Church"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(connorPresbyterian));
        mMap.setMinZoomPreference(15);

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }

/*
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
            startActivity(new Intent(FindUsActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;

        }
    };
*/


    @Override
    public void onBackPressed(){
        Intent first = new Intent(FindUsActivity.this,MainActivity.class);
        startActivity(first);

    }
}


