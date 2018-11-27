package com.example.james.testapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText sub, msg;
    String rec, subject, textMessage;
    Button send;
    Intent email = new Intent(android.content.Intent.ACTION_SEND);

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        send = findViewById(R.id.btnSendMessage);
        final EditText subject = findViewById(R.id.txtSubject);
        final EditText name = findViewById(R.id.txtName);
        final EditText message = findViewById(R.id.txtMessage);
        final EditText senderEmail = findViewById(R.id.txtEmail);

        send.setOnClickListener(new View.OnClickListener() {
            ImageView error = findViewById(R.id.imgExclamation);
            TextView messageSend = findViewById(R.id.txtMessageSend);
            public void onClick(View v) {


                // TODO Error-catch when no Internet connection available (crashes when not connected to Internet)

                new Thread(new Runnable() {

                    public void run() {


                            GMailSender sender = new GMailSender(

                                    "james.mcc90@gmail.com",
                                    "64lislunnan");

                            try {
                            sender.sendMail(
                                    name.getText().toString(),
                                    subject.getText().toString(),
                                    message.getText().toString(),
                                    senderEmail.getText().toString(),
                                    "james.mcc90@gmail.com");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    error.setVisibility(View.INVISIBLE);
                                    messageSend.setTextColor(getResources().getColor(R.color.colorWhite));
                                    messageSend.setText("Thank you for your message!");
                                }
                            });
                        } catch (Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    error.setVisibility(View.VISIBLE);
                                    messageSend.setTextColor(getResources().getColor(R.color.lblError_messageEmail));
                                    messageSend.setText("Please fill in all fields!");
                                }
                            });
                        }

                    }
                }).start();
            }

        });

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
            startActivity(new Intent(ContactActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent first = new Intent(ContactActivity.this,MainActivity.class);
        startActivity(first);

    }

}
