package com.connorchurch.james.churchapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.MediaController;
import android.widget.VideoView;

public class FragmentOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_one, container, false);
    }

    class TheStory extends AppCompatActivity {
        private VideoView videoView;
        private MediaController mediaController;

        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            videoView = findViewById(R.id.videoView);

            String fullScreen =  getIntent().getStringExtra("fullScreenInd");
            if("y".equals(fullScreen)){
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getSupportActionBar().hide();
            }

            Uri videoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.youth_club);

            videoView.setVideoURI(videoUri);

            mediaController = new FullScreenMediaController(this);
            mediaController.setAnchorView(videoView);

            videoView.setMediaController(mediaController);
            videoView.start();

        }

    }
}





