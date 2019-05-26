package com.connorchurch.james.churchapp.activities;

import android.os.Bundle;

import com.connorchurch.james.churchapp.R;
import com.connorchurch.james.churchapp.activities.AppCompatPreferenceActivity;

public class HelpActivity extends AppCompatPreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_help);
    }
}