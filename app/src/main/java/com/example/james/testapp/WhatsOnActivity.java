package com.example.james.testapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;


public class WhatsOnActivity extends AppCompatActivity {


    private static final String TAG = "What's On";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

  @Override
    protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);

      setContentView(R.layout.whats_on);


    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
      Log.d(TAG, "onCreate: Starting");


      mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
      mViewPager = findViewById(R.id.container);
      setupViewPager(mViewPager);


      TabLayout tabLayout = findViewById(R.id.tabs);
      tabLayout.setupWithViewPager(mViewPager);
  }


    private void setupViewPager(ViewPager viewPager){
    SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

    adapter.addFragment(new Tab1Fragment(), "Young People");
    adapter.addFragment(new Tab2Fragment(), "Children");
    adapter.addFragment(new Tab3Fragment(), "Men and Women");

    viewPager.setAdapter(adapter);
}

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main_activity_navigation, menu);
    return true;
  }

  @Override
  public void onBackPressed(){
    Intent first = new Intent(WhatsOnActivity.this,MainActivity.class);
    startActivity(first);

  }
}
