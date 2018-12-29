package com.connorchurch.james.churchapp;
//courtesy https://android--examples.blogspot.com/2017/08/android-play-audio-from-url-http.html

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class AudioStreamActivity extends AppCompatActivity {
    TextView internetConnnection;
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
        Configuration config = getResources().getConfiguration();

        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.audio_stream_tablet);
        } else {
            setContentView(R.layout.audio_stream);

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = getApplicationContext();
        mActivity = AudioStreamActivity.this;

        /*
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
*/


        try {
            ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cManager.getActiveNetworkInfo();
            WebView webView = findViewById(R.id.webView);

            if (nInfo != null && nInfo.isConnected()) {
                webView.getSettings().setJavaScriptEnabled(true);

                webView.setWebViewClient(new WebViewClient());
                webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

                String customHTML =
                        "<html>" +
                        "<head>" +
                        "</head>" +
                        "<style>" +
                        "body{background-color:#1e73be; color:white} a {color:#1fcc94;}" +
                        "</style>" +
                        "<iframe width=\"100%\" height=\"100%\" scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/users/539015169&color=%230066cc&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true\"></iframe> \n"
                        ;
                webView.loadDataWithBaseURL(null, customHTML,"text/html", "UTF-8", null);
                webView.getSettings().setDomStorageEnabled(true);

            }else if (nInfo == null) {

                internetConnnection.setVisibility(View.VISIBLE);

            }

        } catch (Exception e) {
            Toast.makeText(AudioStreamActivity.this, "No Internet connection!", Toast.LENGTH_LONG).show();
        }


        }


    /*
    private String millisecondsToString(int milliseconds)  {
        long minutes = TimeUnit.MILLISECONDS.toMinutes((long) milliseconds);
        long seconds =  TimeUnit.MILLISECONDS.toSeconds((short) milliseconds) ;
        return minutes+":"+ seconds;
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // launch settings activity
            startActivity(new Intent(AudioStreamActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        WebView webView = findViewById(R.id.webView);
        webView.destroy();
        Intent first = new Intent(AudioStreamActivity.this, MainActivity.class);
        startActivity(first);

    }
}


