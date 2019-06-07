package com.connorchurch.james.churchapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.se.omapi.Session;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.connorchurch.james.churchapp.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration config = getResources().getConfiguration();

        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.contact_us_tablet);
        } else {
            setContentView(R.layout.contact_us);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        send = findViewById(R.id.btnSendMessage);
        final EditText subject = findViewById(R.id.txtSubject);
        final EditText name = findViewById(R.id.txtName);
        final EditText message = findViewById(R.id.txtMessageSend);
        final EditText senderEmail = findViewById(R.id.txtEmail);

        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

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

                                }
                            });
                        } catch (Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ImageView error = findViewById(R.id.imgExclamation);
                                    TextView messageError = findViewById(R.id.lblError_messageEmail);
                                    error.setVisibility(View.VISIBLE);
                                    messageError.setTextColor(getResources().getColor(R.color.lblError_messageEmail));
                                    messageError.setText("Sorry! There was a problem sending your message.");
                                }
                            });
                        }

                    }
                }).start();
            }

        });

    }
    @Override
    public void onBackPressed(){
        Intent first = new Intent(ContactActivity.this, MainActivity.class);
        startActivity(first);

    }

}