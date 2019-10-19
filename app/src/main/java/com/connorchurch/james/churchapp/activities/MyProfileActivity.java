package com.connorchurch.james.churchapp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.connorchurch.james.churchapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyProfileActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private ImageView profile;
    private TextView Username;
    private ImageView backToChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        profile = findViewById(R.id.profileImageContact);
        Glide.with(this).load(mFirebaseUser.getPhotoUrl().toString()).into(profile);

        Username = findViewById(R.id.nameProfileContact);
        Username.setText(mFirebaseUser.getDisplayName());


        backToChat = findViewById(R.id.imgBack);
        backToChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(MyProfileActivity.this, MainChatActivity.class);
                startActivity(back);
            }
        });
    }

    public void onBackPressed() {
        Intent first = new Intent(MyProfileActivity.this, MainChatActivity.class);
        startActivity(first);

    }
}
