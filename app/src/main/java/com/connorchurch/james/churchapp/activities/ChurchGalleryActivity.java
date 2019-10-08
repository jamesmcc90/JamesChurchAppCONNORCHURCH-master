package com.connorchurch.james.churchapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.connorchurch.james.churchapp.R;

public class ChurchGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration config = getResources().getConfiguration();

        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.activity_church_gallery_tablet);
        } else {
            setContentView(R.layout.activity_church_gallery);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ChurchGalleryActivity.ImageGalleryAdapter adapter = new ChurchGalleryActivity.ImageGalleryAdapter(this, ChurchPhoto.getChurchPhotos());
        recyclerView.setAdapter(adapter);

    }

    private class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>  {

        @Override
        public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the layout
            View photoView = inflater.inflate(R.layout.item_photo, parent, false);

            ImageGalleryAdapter.MyViewHolder viewHolder = new ImageGalleryAdapter.MyViewHolder(photoView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ImageGalleryAdapter.MyViewHolder holder, int position) {

            ChurchPhoto churchPhoto = mChurchPhotos[position];
            ImageView imageView = holder.mPhotoImageView;

            Glide.with(mContext)
                    .load(churchPhoto.getUrl())
                    .into(imageView);
        }

        @Override
        public int getItemCount() {
            return (mChurchPhotos.length);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ImageView mPhotoImageView;

            public MyViewHolder(View itemView) {

                super(itemView);
                mPhotoImageView = itemView.findViewById(R.id.iv_photo);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {

                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    ChurchPhoto ChurchPhoto = mChurchPhotos[position];

                    Intent intent = new Intent(mContext, ChurchPhotoActivity.class);
                    intent.putExtra(ChurchPhotoActivity.EXTRA_SPACE_PHOTO, ChurchPhoto);
                    startActivity(intent);
                }
            }
        }

        private ChurchPhoto[] mChurchPhotos;
        private Context mContext;

        public ImageGalleryAdapter(Context context, ChurchPhoto[] churchPhotos) {
            mContext = context;
            mChurchPhotos = churchPhotos;
        }
    }

    @Override
    public void onBackPressed(){
        Intent first = new Intent(ChurchGalleryActivity.this, MainActivity.class);
        startActivity(first);

    }
}
