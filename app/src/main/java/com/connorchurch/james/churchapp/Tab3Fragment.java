package com.connorchurch.james.churchapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        Configuration config = getResources().getConfiguration();

        if (config.smallestScreenWidthDp >= 600) {
            View view = inflater.inflate(R.layout.tab3_fragment_tablet, container, false);
            return view;
        } else {
            View view = inflater.inflate(R.layout.tab3_fragment, container, false);
            return view;
        }

    }
}
