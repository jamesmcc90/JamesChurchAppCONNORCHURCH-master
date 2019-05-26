package com.connorchurch.james.churchapp.adapters;

import android.widget.ImageView;

import com.connorchurch.james.churchapp.models.ImageModel;

public interface GalleryItemClickListener {
    void onGalleryItemClickListener(int position, ImageModel imageModel, ImageView imageView);
}
