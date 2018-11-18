package com.example.james.testapp;
//courtesy https://android--examples.blogspot.com/2017/08/android-play-audio-from-url-http.html

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.SeekBar;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class AudioStreamActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;
    private Button mButtonPlay;
    private Button mButtonPause;
    private Button mButtonStop;
    private SeekBar seekBar;
    MediaPlayer mPlayer;
    private Handler threadHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_stream);
        this.seekBar = this.findViewById(R.id.seekBar);
        mContext = getApplicationContext();
        mActivity = AudioStreamActivity.this;
        mButtonPlay = findViewById(R.id.btnPlay);
        mButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonPlay.setEnabled(true);

                String audioUrl = "http://www.james-mcconnell.co.uk/Sermons/30-09-18pm.mp3";

                mPlayer = new MediaPlayer();

                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                try {

                    mPlayer.setDataSource(audioUrl);
                    mPlayer.prepare();
                    mPlayer.getCurrentPosition();
                    mPlayer.start();
                    mButtonPlay.setEnabled(false);
                } catch (IOException e) {
                    // Catch the exception
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }

            }
        });

        mButtonStop = findViewById(R.id.btnStop);
        mButtonStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mButtonPlay.setEnabled(true);
                mPlayer.stop();
                }
            });


    }



    // Convert millisecond to string.
    private String millisecondsToString(int milliseconds)  {
        long minutes = TimeUnit.MILLISECONDS.toMinutes((long) milliseconds);
        long seconds =  TimeUnit.MILLISECONDS.toSeconds((short) milliseconds) ;
        return minutes+":"+ seconds;
    }


    @Override
    public void onBackPressed(){
        Intent first = new Intent(AudioStreamActivity.this, MainActivity.class);
        startActivity(first);

    }
}


