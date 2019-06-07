package com.connorchurch.james.churchapp.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.connorchurch.james.churchapp.R;
import com.connorchurch.james.churchapp.adapters.SectionsPageAdapter;
import com.connorchurch.james.churchapp.fragments.CommitteeAboutFragment;
import com.connorchurch.james.churchapp.fragments.EldersAboutFragment;
import com.connorchurch.james.churchapp.fragments.MinisterAboutFragment;

public class TabbedActivity extends AppCompatActivity {

    private static final String TAG = "What's On";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.people_tablet);
        } else {
            setContentView(R.layout.people);
        }



        Toolbar toolbar = findViewById(R.id.toolbar);
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

        adapter.addFragment(new MinisterAboutFragment(), "Our Minister");
        adapter.addFragment(new EldersAboutFragment(), "Our Elders");
        adapter.addFragment(new CommitteeAboutFragment(), "Our Committee");


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
        Intent first = new Intent(TabbedActivity.this, MainActivity.class);
        startActivity(first);

    }
}
