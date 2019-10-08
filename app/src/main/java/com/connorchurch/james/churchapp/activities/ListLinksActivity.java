package com.connorchurch.james.churchapp.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.connorchurch.james.churchapp.R;
import com.connorchurch.james.churchapp.adapters.AlbumsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListLinksActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    private CardView churchCard;

    //private List<CarRecyclerViewItem> carItemList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_links);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        initCollapsingToolbar();

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList);


        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.church_foyer).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
        final CardView btnChurchWebsite = findViewById(R.id.btnChurchWebsite);
        final CardView btnChurch = findViewById(R.id.btnChurch);


        btnChurch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnChurchWebsite.setVisibility(View.INVISIBLE);
                btnChurch.setVisibility(View.INVISIBLE);
                final WebView view = findViewById(R.id.webView);
                view.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return false;
                    }
                });
                view.getSettings().setJavaScriptEnabled(true);
                view.setVisibility(View.VISIBLE);
                view.loadUrl("https://www.facebook.com/connorpci/");
                getSupportActionBar().hide();
                view.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            view.clearCache(true);
                            view.setVisibility(View.INVISIBLE);
                            btnChurch.setVisibility(View.VISIBLE);
                            btnChurchWebsite.setVisibility(View.VISIBLE);

                            return true;
                        }
                        return false;
                    }
                });
            }
        });


        btnChurchWebsite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnChurchWebsite.setVisibility(View.INVISIBLE);
                btnChurch.setVisibility(View.INVISIBLE);
                final WebView view = findViewById(R.id.webView);
                view.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return false;
                    }
                });
                view.getSettings().setJavaScriptEnabled(true);
                view.setVisibility(View.VISIBLE);
                btnChurchWebsite.setVisibility(View.INVISIBLE);
                view.loadUrl("https://connorpresbyterianchurch.org/");

                view.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            view.clearCache(true);
                            view.setVisibility(View.INVISIBLE);
                            btnChurch.setVisibility(View.VISIBLE);
                            btnChurchWebsite.setVisibility(View.VISIBLE);
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {
            case R.id.btnChurch:
                i = new Intent(this, FacebookChurchNewsActivity.class);
                startActivity(i);
                break;
            default: break;
        }
    }


    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.church1,
                R.drawable.pci_logo_286};

        Album a = new Album("Connor Church Facebook", covers[0]);
        albumList.add(a);

        a = new Album("Presbyterian Church in Ireland", covers[1]);
        albumList.add(a);


        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onBackPressed(){

        Intent first = new Intent(ListLinksActivity.this, MainActivity.class);
        startActivity(first);

    }
}

