package com.connorchurch.james.churchapp.activities;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.connorchurch.james.churchapp.R;
import com.connorchurch.james.churchapp.activities.ui.main.SectionsPagerAdapter;
import com.connorchurch.james.churchapp.adapters.SectionsPageAdapter;
import com.connorchurch.james.churchapp.fragments.ChurchFragment;
import com.connorchurch.james.churchapp.fragments.DevotionalFragment;
import com.connorchurch.james.churchapp.fragments.PCINewsFragment;
import com.connorchurch.james.churchapp.fragments.VODFragment;


public class ResourcesActivity extends AppCompatActivity {
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.view_pager);
        setupViewPager(mViewPager);


    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new DevotionalFragment(), "Devotional");
        adapter.addFragment(new VODFragment(), "Verse of the Day");
        adapter.addFragment(new ChurchFragment(), "Church Resources");
        adapter.addFragment(new PCINewsFragment(), "PCI News");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
        Intent first = new Intent(ResourcesActivity.this, MainActivity.class);
        startActivity(first);

    }
}