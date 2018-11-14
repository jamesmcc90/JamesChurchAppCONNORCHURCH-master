package com.example.james.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ChatLoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_chat);

        Button login = findViewById(R.id.btnLogin);
        final TextView error = findViewById(R.id.txtError);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                EditText username = findViewById(R.id.txtUsername);
                EditText password = findViewById(R.id.txtPassword);
                username.requestFocus();

                if (username.getText().toString().equals("")) {
                    error.setText("Incorrect username");
                }
                if (password.getText().toString().equals("")){
                    error.setText("Incorrect password");
                }
                if (!username.getText().toString().equals("James")){
                    error.setText("User not registered!");
                }
                if (!password.getText().toString().equals("password")){
                    error.setText("Incorrect password");
                }
                else if(username.getText().toString().equals("James") & password.getText().toString().equals("password")){
                Intent myIntent = new Intent(ChatLoginActivity.this, MainChatActivity.class);
                startActivity(myIntent);
                finish();
            }

        }

        });

    }

    @Override
    public void onBackPressed(){
        Intent first = new Intent(ChatLoginActivity.this,MainActivity.class);
        startActivity(first);

    }

}
