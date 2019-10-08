package com.connorchurch.james.churchapp.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.connorchurch.james.churchapp.R;

/**
 * Fragment for the Gorilla tab.
 */
public class TestFragment extends Fragment {
    private static final String TAG = "GorillaFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container,false);
    }
}
