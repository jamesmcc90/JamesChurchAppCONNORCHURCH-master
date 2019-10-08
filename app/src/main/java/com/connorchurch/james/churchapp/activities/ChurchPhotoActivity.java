package com.connorchurch.james.churchapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.connorchurch.james.churchapp.R;

public class ChurchPhotoActivity extends AppCompatActivity {

    public static final String EXTRA_SPACE_PHOTO = "ChurchPhotoActivity.SPACE_PHOTO";

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageView = findViewById(R.id.image);
        ChurchPhoto churchPhoto = getIntent().getParcelableExtra(EXTRA_SPACE_PHOTO);

        Glide.with(this)
                .load(churchPhoto.getUrl())
                .into(mImageView);

    }
}
