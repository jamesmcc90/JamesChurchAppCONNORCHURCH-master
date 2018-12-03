package com.connorchurch.james.churchapp;

import android.os.Bundle;

public class HelpActivity extends AppCompatPreferenceActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_help);
    }
}