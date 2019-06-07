package com.connorchurch.james.churchapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.connorchurch.james.churchapp.adapters.ImageUrl;
import com.connorchurch.james.churchapp.R;
import com.connorchurch.james.churchapp.adapters.DataAdapter;

import java.util.ArrayList;
public class GalleryActivity extends AppCompatActivity {
    private ImageView imageView;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        ArrayList imageUrlList = prepareData();
        DataAdapter dataAdapter = new DataAdapter(getApplicationContext(), imageUrlList);
        recyclerView.setAdapter(dataAdapter);

    }
/*
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
            startActivity(new Intent(GalleryActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

*/

    private ArrayList prepareData() {

        String[] imageUrls = {
                "http://connorpresbyterianchurch.org/wp-content/uploads/Image-50.png",
                "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1502-resized-image-600x450.jpg",
                "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1503-resized-image-600x450.jpg",
                "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1504-resized-image-600x450.jpg",
                "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1505-resized-image-600x450.jpg",
                "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1506-resized-image-600x450.jpg",
                "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1507-resized-image-600x450.jpg",
                "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1508-resized-image-600x450.jpg",
                "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1510-resized-image-600x450.jpg",

        };

        ArrayList imageUrlList = new ArrayList<>();
        for (int i = 0; i < imageUrls.length; i++) {
            ImageUrl imageUrl = new ImageUrl();
            imageUrl.setImageUrl(imageUrls[i]);
            imageUrlList.add(imageUrl);
        }

        Log.d("GalleryActivity", "List count: " + imageUrlList.size());
        return imageUrlList;
    }

    @Override
    public void onBackPressed(){
        Intent first = new Intent(GalleryActivity.this,GalleryConnorActivity.class);
        startActivity(first);

    }
}
