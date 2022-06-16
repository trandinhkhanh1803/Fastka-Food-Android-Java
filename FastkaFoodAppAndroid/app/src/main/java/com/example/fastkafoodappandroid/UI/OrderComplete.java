package com.example.fastkafoodappandroid.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fastkafoodappandroid.MainActivity;
import com.example.fastkafoodappandroid.R;

public class OrderComplete extends AppCompatActivity {

    private ImageView img_continue_buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        img_continue_buy = findViewById(R.id.img_continue_buy);

        img_continue_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderComplete.this, MainActivity.class));
            }
        });
    }
}