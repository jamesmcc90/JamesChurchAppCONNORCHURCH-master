package com.connorchurch.james.churchapp;

import android.os.Handler;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.util.ArrayList;

public class CustomSermonAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<SermonsActivity_TEST> arrayList;
    private MediaPlayer mediaPlayer;
    private Boolean flag = true;
    private SeekBar seekBar;
    Handler handler;

    public CustomSermonAdapter(Context context, int layout, ArrayList<SermonsActivity_TEST> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtName, txtSpeaker;
        ImageView ivPlay, ivStop;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtName = convertView.findViewById(R.id.txtName);
            viewHolder.txtSpeaker = convertView.findViewById(R.id.txtSpeaker);
            viewHolder.ivPlay = convertView.findViewById(R.id.ivPlay);
            viewHolder.ivStop = convertView.findViewById(R.id.ivStop);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        final SermonsActivity_TEST sermon = arrayList.get(position);

        viewHolder.txtName.setText(sermon.getName());
        viewHolder.txtSpeaker.setText(sermon.getSpeaker());

        viewHolder.ivPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag) {
                    mediaPlayer = MediaPlayer.create(context, sermon.getSermon());
                    flag = false;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    viewHolder.ivPlay.setImageResource(R.drawable.ic_play);
                } else {
                    mediaPlayer.start();
                    viewHolder.ivPlay.setImageResource(R.drawable.ic_pause);
                }
            }

        });


        viewHolder.ivStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    mediaPlayer = MediaPlayer.create(context, sermon.getSermon());
                    mediaPlayer.stop();
                    viewHolder.ivPlay.setImageResource(R.drawable.ic_play);
                    flag = false;
                }

                if (!flag) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    flag = true;
                }
                viewHolder.ivPlay.setImageResource(R.drawable.ic_play);
            }

        });


        return convertView;
    }

}



