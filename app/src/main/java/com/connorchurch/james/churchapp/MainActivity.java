package com.connorchurch.james.churchapp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.support.v7.app.ActionBarDrawerToggle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class MainActivity extends AppCompatActivity  {
    private DrawerLayout mDrawerLayout;
    List<String> ChildList;
    Map<String, List<String>> ParentListItems;
    ExpandableListView expandablelistView;

    // Assign Parent list items here.
    List<String> ParentList = new ArrayList<String>();
    {
        ParentList.add("Around our church");
        ParentList.add("Bible Resources");
        ParentList.add("Media");

    }

    // Assign children list element using string array.
    String[] AndroidName = { "Who We Are", "Announcements","Rotas","Chat Login" };
    String[] BibleResources = {"The Bible", "Verse of the Day"};
    String[] MediaName = {"Gallery"};
    String[] ByDefalutMessage = {"Items Loading"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        ParentListItems = new LinkedHashMap<String, List<String>>();

        for ( String HoldItem : ParentList) {
            if (HoldItem.equals("Around our church")) {
                loadChild(AndroidName);
            } else if(HoldItem.equals("Bible Resources")){
                loadChild(BibleResources);
            }

            else if (HoldItem.equals("Media"))
                loadChild(MediaName);

            else
                loadChild(ByDefalutMessage);

            ParentListItems.put(HoldItem, ChildList);
        }

        expandablelistView = (ExpandableListView) findViewById(R.id.navigationmenu);
        final ExpandableListAdapter expListAdapter = new ListAdapter(
                this, ParentList, ParentListItems);
        expandablelistView.setAdapter(expListAdapter);

        expandablelistView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);

                // Switch case to open selected child element activity on child element selection.

                Intent intent;
                switch(selected){
                    case "Who We Are":
                        intent = new Intent(MainActivity.this, TabbedActivity.class);
                        startActivity(intent);
                        break;
                    case "Announcements":
                        intent = new Intent(MainActivity.this, AnnouncementsActivity.class);
                        startActivity(intent);
                        break;

                    case "Rotas":
                        intent = new Intent(MainActivity.this, RotasActivity.class);
                        startActivity(intent);
                        break;

                    case "Chat Login":
                        intent = new Intent(MainActivity.this, ChatLoginActivity.class);
                        startActivity(intent);
                        break;

                    case "Gallery":
                        intent = new Intent(MainActivity.this, GalleryTest.class);
                        startActivity(intent);
                        break;
                    case "The Bible":
                        intent = new Intent(MainActivity.this, TheBibleActivity.class);
                        startActivity(intent);
                        break;
                    case "Verse of the Day":
                        intent = new Intent(MainActivity.this, VerseActivity.class);
                        startActivity(intent);
                        break;

                }

                return true;
            }
        });

        Button About = findViewById(R.id.btnAboutUs);
        Button Contact = findViewById(R.id.btnContactiUs);
        Button Find = findViewById(R.id.btnFindUs);
        Button Calendar = findViewById(R.id.btnCalendar);
        Button Sermons = findViewById(R.id.btnSermons);
        Button WhatsOn = findViewById(R.id.btnWhatsOn);

        About.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        Contact.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    // Start NewActivity.class
                    Intent myIntent = new Intent(MainActivity.this, ContactActivity.class);
                    startActivity(myIntent);
                    finish();
                }

        });
        Find.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, FindUsActivity.class);
                startActivity(myIntent);
                finish();
            }

        });
        Calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(myIntent);
                finish();
            }

        });
        Sermons.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, AudioStreamActivity.class);
                startActivity(myIntent);
                finish();
            }

        });
        WhatsOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, WhatsOnActivity.class);
                startActivity(myIntent);
                finish();
            }

        });

    }

    private void loadChild(String[] ParentElementsName) {
        ChildList = new ArrayList<String>();
        for (String model : ParentElementsName)
            ChildList.add(model);
    }

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
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

}