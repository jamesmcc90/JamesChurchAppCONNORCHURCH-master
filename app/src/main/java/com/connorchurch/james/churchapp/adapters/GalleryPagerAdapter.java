package com.connorchurch.james.churchapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.connorchurch.james.churchapp.fragments.ImageDetailFragment;
import com.connorchurch.james.churchapp.models.ImageModel;

import java.util.ArrayList;

public class GalleryPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<ImageModel> images;

    public GalleryPagerAdapter(FragmentManager fm, ArrayList<ImageModel> images) {
        super(fm);
        this.images = images;
    }

    @Override
    public Fragment getItem(int position) {
        ImageModel image = images.get(position);
        return ImageDetailFragment.newInstance(image, image.getName());
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
