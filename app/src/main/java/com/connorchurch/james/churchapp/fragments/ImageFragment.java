package com.connorchurch.james.churchapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.connorchurch.james.churchapp.R;


@SuppressLint("ValidFragment")
public class ImageFragment extends Fragment {

    public Context context;
    private int img;

    public ImageFragment(int image) {
        this.img = img;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        /*  ImageView imageView = new ImageView(context);
      LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(img);*/


       ImageView imageView = (ImageView)inflater.inflate(R.layout.image_view, container, false);
       imageView.setImageResource(img);
        return imageView;
    }
}
