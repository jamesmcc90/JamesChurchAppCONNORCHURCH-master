package com.connorchurch.james.churchapp.fragments;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.connorchurch.james.churchapp.models.ImageModel;
import com.connorchurch.james.churchapp.R;
import com.connorchurch.james.churchapp.adapters.GalleryPagerAdapter;

import java.util.ArrayList;

public class GalleryViewerPagerFragment extends Fragment {

    private static final String EXTRA_INITIAL_POS = "initial_pos";
    private static final String EXTRA_IMAGES = "images";

    public GalleryViewerPagerFragment() {
    }

    public static GalleryViewerPagerFragment newInstance(int current, ArrayList<ImageModel> images) {
        GalleryViewerPagerFragment fragment = new GalleryViewerPagerFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_INITIAL_POS, current);
        args.putParcelableArrayList(EXTRA_IMAGES, images);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery_view_pager, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int currentItem = getArguments().getInt(EXTRA_INITIAL_POS);
        ArrayList<ImageModel> images = getArguments().getParcelableArrayList(EXTRA_IMAGES);
        GalleryPagerAdapter galleryPagerAdapter = new GalleryPagerAdapter(getChildFragmentManager(), images);

        ViewPager viewPager = view.findViewById(R.id.animal_view_pager);
        viewPager.setAdapter(galleryPagerAdapter);
        viewPager.setCurrentItem(currentItem);
    }


}
