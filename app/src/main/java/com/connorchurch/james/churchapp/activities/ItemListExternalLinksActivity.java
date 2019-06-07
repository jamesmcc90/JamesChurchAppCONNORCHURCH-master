package com.connorchurch.james.churchapp.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.FrameLayout;

import com.connorchurch.james.churchapp.R;
import com.connorchurch.james.churchapp.activities.ItemsListFragment.OnItemSelectedListener;

public class ItemListExternalLinksActivity extends FragmentActivity implements OnItemSelectedListener {
    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            //setContentView(R.layout.activity_items_tablet);
        } else {
            setContentView(R.layout.activity_items_external_links);

        }
        determinePaneLayout();
    }

    private void determinePaneLayout() {
        FrameLayout fragmentItemDetail = findViewById(R.id.flDetailContainer);
        if (fragmentItemDetail != null) {
            isTwoPane = true;
            ItemsListFragment fragmentItemsList =
                    (ItemsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_items_list);
            fragmentItemsList.setActivateOnItemClick(true);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public void onItemSelected(Item item) {
        if (isTwoPane) { // single activity with list and detail
            // Replace frame layout with correct detail fragment
            ItemDetailFragment fragmentItem = ItemDetailFragment.newInstance(item);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentItem);
            ft.commit();
        } else { // separate activities
            // launch detail activity using intent
            Intent i = new Intent(this, ItemDetailActivity.class);
            i.putExtra("item", item);
            startActivity(i);
        }
    }
    @Override
    public void onBackPressed(){
        Intent first = new Intent(ItemListExternalLinksActivity.this, MainActivity.class);
        startActivity(first);

    }
}
