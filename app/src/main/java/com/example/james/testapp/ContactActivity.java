package com.example.james.testapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

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

        send =  findViewById(R.id.btnSendMessage);
        final EditText subject = findViewById(R.id.txtSubject);
        final EditText name = findViewById(R.id.txtName);
        final EditText message = findViewById(R.id.txtMessage);
        final EditText senderEmail = findViewById(R.id.txtEmail);


        send.setOnClickListener(new View.OnClickListener() {
            TextView messageSend = findViewById(R.id.txtMessageSend);
            public void onClick(View v) {


                // TODO Error-catch when no Internet connection available (crashes when not connected to Internet)

                new Thread(new Runnable() {

                    public void run() {

                        try {

                            GMailSender sender = new GMailSender(

                                    "james.mcc90@gmail.com",
                                    "64lislunnan");

                            sender.sendMail(
                                    name.getText().toString(),
                                    subject.getText().toString(),
                                    message.getText().toString(),
                                    senderEmail.getText().toString(),
                                    "james.mcc90@gmail.com");

                            messageSend.setText("Thank you for your message!");

                        } catch (Exception e) {
                            messageSend.setText("Please fill in all fields!");

                        }
                    }

                }).start();


            }

        });

    }
    @Override
    public void onBackPressed(){
        Intent first = new Intent(ContactActivity.this,MainActivity.class);
        startActivity(first);

    }

}
