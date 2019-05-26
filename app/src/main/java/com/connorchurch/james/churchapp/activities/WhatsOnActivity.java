package com.connorchurch.james.churchapp.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.connorchurch.james.churchapp.R;
import com.connorchurch.james.churchapp.adapters.SectionsPageAdapter;
import com.connorchurch.james.churchapp.fragments.Tab1Fragment;
import com.connorchurch.james.churchapp.fragments.Tab2Fragment;
import com.connorchurch.james.churchapp.fragments.Tab3Fragment;
import com.connorchurch.james.churchapp.fragments.Tab4Fragment;


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
    adapter.addFragment(new Tab3Fragment(), "Women");
    adapter.addFragment(new Tab4Fragment(), "Men");

    viewPager.setAdapter(adapter);
}
/*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main_activity_navigation, menu);
    return true;
  }
*/
  @Override
  public void onBackPressed(){
    Intent first = new Intent(WhatsOnActivity.this, MainActivity.class);
    startActivity(first);

  }
}
