package com.connorchurch.james.churchapp.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.connorchurch.james.churchapp.R;

public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        Configuration config = getResources().getConfiguration();


            View view = inflater.inflate(R.layout.tab3_fragment, container, false);
            return view;
        }


    }

