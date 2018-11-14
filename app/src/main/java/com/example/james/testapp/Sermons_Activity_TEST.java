package com.example.james.testapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View.OnClickListener;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Sermons_Activity_TEST extends AppCompatActivity {

    private ArrayList<SermonsActivity_TEST> arrayList;
    private CustomSermonAdapter adapter;
    private ListView sermonList;
    private Handler threadHandler = new Handler();
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_sermon_item);
        this.seekBar = this.findViewById(R.id.seekBar);
        ImageView note = findViewById(R.id.Sermon);
        TextView name = findViewById(R.id.txtName);
        TextView sermon = findViewById(R.id.txtSpeaker);
        ImageView play = findViewById(R.id.ivPlay);
        ImageView stop = findViewById(R.id.ivStop);


       // note.setVisibility(View.INVISIBLE);
        name.setVisibility(View.INVISIBLE);
        sermon.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);
        stop.setVisibility(View.INVISIBLE);

        sermonList = findViewById(R.id.sermonSelect);
        arrayList = new ArrayList<>();
        arrayList.add(new SermonsActivity_TEST("Philip Thompson", "Philip Thompson", R.raw.philip_thompson_evening));
        arrayList.add(new SermonsActivity_TEST("Immigrant Song", "Led Zepplin", R.raw.immigrant_song));
        arrayList.add(new SermonsActivity_TEST("Eraser", "Ed Sheeran", R.raw.eraser));
        arrayList.add(new SermonsActivity_TEST("Castle on The Hill", "Ed Sheeran", R.raw.castle_on_the_hill));
        arrayList.add(new SermonsActivity_TEST("Dive", "Ed Sheeran", R.raw.dive));
        arrayList.add(new SermonsActivity_TEST("Shape of You", "Ed Sheeran", R.raw.shape_of_you));
        arrayList.add(new SermonsActivity_TEST("Perfect", "Ed Sheeran", R.raw.perfect));
        arrayList.add(new SermonsActivity_TEST("Galway Girl", "Ed Sheeran", R.raw.galway_girl));
        arrayList.add(new SermonsActivity_TEST("Happier", "Ed Sheeran", R.raw.happier));
        arrayList.add(new SermonsActivity_TEST("New Man", "Ed Sheeran", R.raw.new_man));

        adapter = new CustomSermonAdapter(this, R.layout.custom_sermon_item, arrayList);
        sermonList.setAdapter(adapter);

        this.seekBar = this.findViewById(R.id.seekBar);


    }
    // Find ID of resource in 'raw' folder.
    public int getRawResIdByName(String resName)  {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        return resID;
    }

    // Convert millisecond to string.
    private String millisecondsToString(int milliseconds)  {
        long minutes = TimeUnit.MILLISECONDS.toMinutes((long) milliseconds);
        long seconds =  TimeUnit.MILLISECONDS.toSeconds((short) milliseconds) ;
        return minutes+":"+ seconds;
    }


    public void doStart(View view)  {
        // The duration in milliseconds
        int duration = this.mediaPlayer.getDuration();

        int currentPosition = this.mediaPlayer.getCurrentPosition();
        if(currentPosition == 0)  {
            this.seekBar.setMax(duration);
            String maxTimeString = this.millisecondsToString(duration);

        } else if(currentPosition == duration)  {
            // Resets the MediaPlayer to its uninitialized state.
            this.mediaPlayer.reset();
        }
        this.mediaPlayer.start();
        // Create a thread to update position of SeekBar.
        UpdateSeekBarThread updateSeekBarThread= new UpdateSeekBarThread();
        threadHandler.postDelayed(updateSeekBarThread,50);


    }

    // Thread to Update position for SeekBar.
    class UpdateSeekBarThread implements Runnable {

        public void run()  {
            int currentPosition = mediaPlayer.getCurrentPosition();
            String currentPositionStr = millisecondsToString(currentPosition);


            seekBar.setProgress(currentPosition);
            // Delay thread 50 milisecond.
            threadHandler.postDelayed(this, 50);
        }
    }

    // When user click to "Pause".
    public void doPause(View view)  {
        this.mediaPlayer.pause();

    }

    // When user click to "Go to start".
    public void doBack(View view)  {

        this.mediaPlayer.seekTo(0);
    }

    // When user click to "Rewind".
    public void doRewind(View view)  {
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        int duration = this.mediaPlayer.getDuration();
        // 5 seconds.
        int SUBTRACT_TIME = 5000;

        if(currentPosition - SUBTRACT_TIME > 0 )  {
            this.mediaPlayer.seekTo(currentPosition - SUBTRACT_TIME);
        }
    }

    // When user click to "Fast-Forward".
    public void doFastForward(View view)  {
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        int duration = this.mediaPlayer.getDuration();
        // 5 seconds.
        int ADD_TIME = 5000;

        if(currentPosition + ADD_TIME < duration)  {
            this.mediaPlayer.seekTo(currentPosition + ADD_TIME);
        }
    }
    @Override
    public void onBackPressed(){
        Intent first = new Intent(Sermons_Activity_TEST.this,MainActivity.class);
        startActivity(first);

    }

}
