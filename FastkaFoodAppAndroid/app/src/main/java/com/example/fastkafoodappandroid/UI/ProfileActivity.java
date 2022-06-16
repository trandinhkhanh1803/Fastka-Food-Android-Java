package com.example.fastkafoodappandroid.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.fastkafoodappandroid.MainActivity;
import com.example.fastkafoodappandroid.R;
import com.example.fastkafoodappandroid.UI.OTP.SendOTPActivity;

public class ProfileActivity extends AppCompatActivity {

    private LinearLayout toMyOrder,toSupport,veryEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        toAct();


        bottomNavigation();
    }

    private void toAct() {
        toMyOrder = findViewById(R.id.toMyOrder);
        toSupport = findViewById(R.id.toSupport);
        veryEmail = findViewById(R.id.veryEmail);

        toMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,MyOrderActivity.class));
            }
        });

        toSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,SupportActivity.class));
            }
        });
        veryEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, SendOTPActivity.class));
            }
        });
    }

    //----------------bottomNavigation----------------------
    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn1);
        LinearLayout cartBtn = findViewById(R.id.cartBtn1);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        LinearLayout supportBtn = findViewById(R.id.supportBtn);
        LinearLayout settingBtn = findViewById(R.id.settingBtn);


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, CartActivity.class));
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, SupportActivity.class));
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,NotificationActivity.class));
            }
        });
    }
}