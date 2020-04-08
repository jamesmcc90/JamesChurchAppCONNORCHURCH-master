package com.connorchurch.james.churchapp.activities;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.connorchurch.james.churchapp.R;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseRemoteConfig fbRemoteConfig;
    private DrawerLayout mDrawerLayout;
    private BottomAppBar bar;
    int currentItem = 0;
    private boolean fbModeCenter = true;
    private FloatingActionButton fab;
    TextView myWebView;
    boolean loadingFinished = true;
    boolean redirect = false;
    String url = "";
    TextView textView;
    long cacheExpiration = 43200;
    String imgUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        fbRemoteConfig = FirebaseRemoteConfig.getInstance();
        fbRemoteConfig.activateFetched();
        fbRemoteConfig.fetch(0);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        // BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        AppBarLayout appBarLayout = findViewById(R.id.appBarLayout);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView About = findViewById(R.id.btnAbout);
        ImageView Find = findViewById(R.id.btnFindUs);
        ImageView Sermons = findViewById(R.id.btnSermons);
        ImageView WhatsOn = findViewById(R.id.btnWhatsOn);
        ImageView Calendar = findViewById(R.id.btnCalendar);
        FloatingActionButton Info = findViewById(R.id.fltInfo);
        FloatingActionButton HideInfo = findViewById(R.id.fltInfoHide);

        textView = findViewById(R.id.textView18);
        //textView.setText(fbRemoteConfig.getString("welcome_message"));
        textView.setVisibility(View.INVISIBLE);

        loadHomePage();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        About.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        Find.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, FindUsActivity.class);
                startActivity(myIntent);
                finish();
            }

        });

        Sermons.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, AudioStreamActivity.class);
                startActivity(myIntent);
                finish();
            }

        });
        WhatsOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, WhatsOnActivity.class);
                startActivity(myIntent);
                finish();
            }

        });

        Calendar.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View arg0) {
                                            Intent myIntent = new Intent(MainActivity.this, CalendarActivity.class);
                                            startActivity(myIntent);
                                            finish();
                                        }
                                    }

        );

        Info.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            public void onClick(View v) {
                About.setVisibility(View.INVISIBLE);
                Sermons.setVisibility(View.INVISIBLE);
                WhatsOn.setVisibility(View.INVISIBLE);
                Find.setVisibility(View.INVISIBLE);
                Calendar.setVisibility(View.INVISIBLE);
                HideInfo.setVisibility(View.VISIBLE);
                bottomNavigationView.setVisibility(View.INVISIBLE);
               // appBarLayout.setVisibility(View.INVISIBLE);
                Info.setVisibility(View.INVISIBLE);
                HideInfo.setVisibility(View.VISIBLE);
               // toolbar.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
            }

        });

        HideInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                About.setVisibility(View.VISIBLE);
                Sermons.setVisibility(View.VISIBLE);
                WhatsOn.setVisibility(View.VISIBLE);
                Find.setVisibility(View.VISIBLE);
                Calendar.setVisibility(View.VISIBLE);
                Info.setVisibility(View.VISIBLE);
                HideInfo.setVisibility(View.INVISIBLE);
                bottomNavigationView.setVisibility(View.VISIBLE);
                appBarLayout.setVisibility(View.VISIBLE);
                Info.setVisibility(View.VISIBLE);
                toolbar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_chat:
                        Intent signIn = new Intent(MainActivity.this, SignInActivity.class);
                        startActivity(signIn);
                        finish();
                        break;
                    case R.id.action_bible:
                        Intent bible = new Intent(MainActivity.this, ResourcesActivity.class);
                        startActivity(bible);
                        finish();
                        break;
                }
                return true;
            }
        });


    }

    private void loadHomePage() {
        if (isConnected()) {
            textView.setText(fbRemoteConfig.getString("welcome_message"));
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No Internet Access", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void displayWelcomeMessage() {

    }


    private void init() {

        this.fab = findViewById(R.id.fab);
    }

    private void navigateToURL(String welcome_message) {

        textView.setText("jjh");

    }

    private boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public void updateConfig(View view) {
        fbRemoteConfig.fetch(0).addOnCompleteListener(this,
                new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            fbRemoteConfig.activateFetched();
                            loadHomePage();
                        } else {

                            Toast toast = Toast.makeText(getApplicationContext(), "Configuration Error !", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
    }


@SuppressWarnings("StatementWithEmptyBody")
   @Override
    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.nav_minster){
            Intent i = new Intent();
            i.setClass(MainActivity.this, MinisterContactActivity.class);
            finish();
            startActivity(i);
        }else if(id == R.id.nav_gallery){
            Intent i = new Intent();
            i.setClass(MainActivity.this, ChurchGalleryActivity.class);
            finish();
            startActivity(i);
        }else if(id == R.id.nav_news){
            Intent i = new Intent();
            i.setClass(MainActivity.this, ListLinksActivity.class);
            finish();
            startActivity(i);

        }else if(id == R.id.nav_announcements){
            Intent i = new Intent();
            i.setClass(MainActivity.this, AnnouncementsActivity.class);
            finish();
            startActivity(i);
        }else if(id == R.id.nav_rotas){
            Intent i = new Intent();
            i.setClass(MainActivity.this, RotasActivity.class);
            finish();
            startActivity(i);
        }else if(id == R.id.nav_settings){
            Intent i = new Intent();
            i.setClass(MainActivity.this, SettingsActivity.class);
            finish();
            startActivity(i);
        }
        else if(id == R.id.nav_vr){
            Intent i = new Intent();
            i.setClass(MainActivity.this, MainVRActivity.class);
            finish();
            startActivity(i);
        }
        else if(id == R.id.nav_youtube){
            Intent i = new Intent();
            i.setClass(MainActivity.this, YoutubeActivity.class);
            finish();
            startActivity(i);
        }
        else if(id == R.id.nav_donate){
            Intent i = new Intent();
            i.setClass(MainActivity.this, DonateActivity.class);
            finish();
            startActivity(i);
        }



       DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

}