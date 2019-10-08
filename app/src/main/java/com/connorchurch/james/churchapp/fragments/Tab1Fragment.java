package com.connorchurch.james.churchapp.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.connorchurch.james.churchapp.R;

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        Configuration config = getResources().getConfiguration();

        if (config.smallestScreenWidthDp >= 600) {
            View view  = inflater.inflate(R.layout.tab1_fragment_tablet, container, false);
            return view;
        } else {
            View view  = inflater.inflate(R.layout.tab1_fragment, container, false);
            return view;
        }



    }


}
