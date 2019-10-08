package com.connorchurch.james.churchapp.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.connorchurch.james.churchapp.R;


public class ChatLoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration config = getResources().getConfiguration();

        if (config.smallestScreenWidthDp >= 600) {
            setContentView(R.layout.login_chat_tablet);
        } else {
            setContentView(R.layout.login_chat);
        }




        Button login = findViewById(R.id.btnLogin);
        final TextView error = findViewById(R.id.txtError);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                EditText username = findViewById(R.id.txtUsername);
                EditText password = findViewById(R.id.txtPassword);
                username.requestFocus();

                if (username.getText().toString().equals("")) {
                    error.setText("Incorrect username!");
                }
                if (password.getText().toString().equals("")){
                    error.setText("Incorrect password!");
                }
                if (!username.getText().toString().equals("Connor")){
                    error.setText("Incorrect username!");
                }
                if (!password.getText().toString().equals("connorchurch")){
                    error.setText("Incorrect password!");
                }

                else if(username.getText().toString().equals("Connor") & password.getText().toString().equals("connorchurch")){
                Intent myIntent = new Intent(ChatLoginActivity.this, MainChatActivity.class);
                startActivity(myIntent);
                finish();
            }

        }

        });

    }

    @Override
    public void onBackPressed(){
        Intent first = new Intent(ChatLoginActivity.this, MainActivity.class);
        startActivity(first);

    }

}
