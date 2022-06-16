package com.example.fastkafoodappandroid.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.fastkafoodappandroid.ApiClient;
import com.example.fastkafoodappandroid.Model.CartData;
import com.example.fastkafoodappandroid.Model.Recommended;
import com.example.fastkafoodappandroid.R;
import com.example.fastkafoodappandroid.retrofit.ApiInterface;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, totalPriceTxt, starTxt, timeTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private Recommended object;

    private int numberOrder = 1;
    private RatingBar ratingBar;
    private ApiInterface apiInterface;


    String title, price, rating, imageUrl, number;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        title = intent.getStringExtra("title");
        price = intent.getStringExtra("price");
        rating = intent.getStringExtra("rating");
        number = intent.getStringExtra("numberOrder");
        imageUrl = intent.getStringExtra("imageUrl");

        setDataFromIntentExtra();


//        calo = intent.getStringExtra("calo");
//        imageUrl = intent.getStringExtra("image");
//
//        imageView = findViewById(R.id.foodPic);
//        itemName = findViewById(R.id.titleTxt);
//        itemPrice = findViewById(R.id.totalPriceTxt);
//        itemRating = findViewById(R.id.rating);
//        itemCalo = findViewById(R.id.calo);
//        ratingBar = findViewById(R.id.ratingBar);
//
//        Glide.with(getApplicationContext()).load(imageUrl).into(imageView);
//        itemName.setText(name);
//        itemPrice.setText("$ "+price);
//        itemRating.setText(rating);
//        itemCalo.setText(calo);
//        ratingBar.setRating(Float.parseFloat(rating));


        iniView();
        getBundle();
        addToCart();
    }

    private void setDataFromIntentExtra() {
    }


    private void getBundle() {

        object = (Recommended) getIntent().getSerializableExtra("dataRec");

        Glide.with(picFood.getContext()).load("http://172.20.10.13/Crdu_php_pdo/uploads/" + object.getPicture()).into(this.picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$" + object.getPrice());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        starTxt.setText(object.getRating() + "");
        timeTxt.setText(object.getTimeDelivery() + " minutes");

        totalPriceTxt.setText("$" + Math.round(numberOrder * object.getPrice()));

        ratingBar.setRating(Float.parseFloat(String.valueOf(object.getRating())));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText("$" + Math.round(numberOrder * object.getPrice()));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText("$" + Math.round(numberOrder * object.getPrice()));
            }
        });


    }

    private void addToCart() {
        object = (Recommended) getIntent().getSerializableExtra("dataRec");

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (object != null) {
                     CartActivity.myCarts.add(new CartData(object.getTitle(),object.getPicture(),numberOrder,object.getPrice()));
                    Intent intent = new Intent(ShowDetailActivity.this,CartActivity.class);
//                    startActivity(intent);
                   // postData("insert");
                     Toast.makeText(ShowDetailActivity.this,"Add to cart",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void postData(final String key) {
        object = (Recommended) getIntent().getSerializableExtra("dataRec");
        String title = object.getTitle().toString().trim();
        String price = object.getPrice().toString().trim();
        String amount = numberOrderTxt.getText().toString().trim();
        String picture = object.getPicture().toString().trim();


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Recommended> call = apiInterface.insertFood(key, title, price, amount, picture);

        call.enqueue(new Callback<Recommended>() {
            @Override
            public void onResponse(Call<Recommended> call, Response<Recommended> response) {
                    Toast.makeText(ShowDetailActivity.this, "Add to cart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Recommended> call, Throwable t) {
                Toast.makeText(ShowDetailActivity.this, "Error:"+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void iniView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plusCardBtn);
        minusBtn = findViewById(R.id.minusCardBtn);
        picFood = findViewById(R.id.foodPic);
        totalPriceTxt = findViewById(R.id.totalPriceTxt);
        starTxt = findViewById(R.id.rating);
//        caloryTxt = findViewById(R.id.VicaloriesTxt);
        timeTxt = findViewById(R.id.timeTxt);
        ratingBar = findViewById(R.id.ratingBar1);
    }
}