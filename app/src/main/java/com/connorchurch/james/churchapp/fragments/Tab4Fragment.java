package com.connorchurch.james.churchapp.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.connorchurch.james.churchapp.R;

public class Tab4Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.tab4_fragment, container, false);

        return view;

    }
}
