package com.connorchurch.james.churchapp.activities;

import androidx.annotation.NonNull;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import com.connorchurch.james.churchapp.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private BottomAppBar bar;
    int currentItem = 0;
    private boolean fbModeCenter = true;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
       // TextView floatInfo = findViewById(R.id.txtFloatInfo);
        final WebView updates = findViewById(R.id.webInfo);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        About.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
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

        Calendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                Intent myIntent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(myIntent);
                finish();
            }
        }

        );

        Info.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            public void onClick(View v) {

               // floatInfo.setVisibility(View.VISIBLE);
                About.setVisibility(View.INVISIBLE);
                Sermons.setVisibility(View.INVISIBLE);
                WhatsOn.setVisibility(View.INVISIBLE);
                Find.setVisibility(View.INVISIBLE);
                Calendar.setVisibility(View.INVISIBLE);
                HideInfo.setVisibility(View.VISIBLE);
                bottomNavigationView.setVisibility(View.INVISIBLE);
                appBarLayout.setVisibility(View.INVISIBLE);
                Info.setVisibility(View.INVISIBLE);
                HideInfo.setVisibility(View.VISIBLE);

                updates.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return false;
                    }
                });

                updates.getSettings().setJavaScriptEnabled(true);
                WebSettings settings = updates.getSettings();
                settings.setDomStorageEnabled(true);
                updates.setVisibility(View.VISIBLE);
                updates.loadUrl("https://ourdailybread.org/read/");

                Info.setVisibility(View.INVISIBLE);
                HideInfo.setVisibility(View.VISIBLE);
    }

});

        HideInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              //  floatInfo.setVisibility(View.INVISIBLE);
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
                updates.setVisibility(View.INVISIBLE);

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

    private void init() {

        this.fab = findViewById(R.id.fab);
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


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

}