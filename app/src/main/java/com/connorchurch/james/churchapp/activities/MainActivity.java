package com.connorchurch.james.churchapp.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.connorchurch.james.churchapp.Minister;
import com.connorchurch.james.churchapp.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;

    int currentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button About = findViewById(R.id.btnAboutUs);
        Button Find = findViewById(R.id.btnFindUs);
        Button Sermons = findViewById(R.id.btnSermons);
        Button WhatsOn = findViewById(R.id.btnWhatsOn);

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



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_chat:
                        Intent signIn = new Intent(MainActivity.this, SignInActivity.class);
                        startActivity(signIn);
                        finish();
                        break;
                    case R.id.action_calendar:
                        Intent calendar = new Intent(MainActivity.this, CalendarActivity.class);
                        startActivity(calendar);
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

   @SuppressWarnings("StatementWithEmptyBody")
   @Override
    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.nav_minster){
            Intent i = new Intent();
            i.setClass(MainActivity.this, Minister.class);
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
        }else if(id == R.id.nav_external_links){
            Intent i = new Intent();
            i.setClass(MainActivity.this, ExternalLinksActivity.class);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

}