package com.example.fastkafoodappandroid.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fastkafoodappandroid.Adapter.RecommendedAdapter;
import com.example.fastkafoodappandroid.ApiClient;
import com.example.fastkafoodappandroid.MainActivity;
import com.example.fastkafoodappandroid.Model.Recommended;
import com.example.fastkafoodappandroid.R;
import com.example.fastkafoodappandroid.account.LoginActivity;
import com.example.fastkafoodappandroid.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeMoreActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private RecyclerView rcv_see_more;
    private ImageView backMain, ac_toCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more);

        backMain = findViewById(R.id.backMain);
        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SeeMoreActivity.this, MainActivity.class));
            }
        });

        ac_toCart = findViewById(R.id.ac_toCart);
        ac_toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SeeMoreActivity.this, CartActivity.class));
            }
        });


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        rcv_see_more = findViewById(R.id.rcv_see_more);
        rcv_see_more.setHasFixedSize(true);

        Call<List<Recommended>> call = apiInterface.getdata();


        call.enqueue(new Callback<List<Recommended>>() {
            @Override
            public void onResponse(Call<List<Recommended>> call, Response<List<Recommended>> response) {
                List<Recommended> data = response.body();
                rcv_see_more.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                RecommendedAdapter recAdapter = new RecommendedAdapter(data);
                rcv_see_more.setAdapter(recAdapter);

            }


            @Override
            public void onFailure(Call<List<Recommended>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "server not connect", Toast.LENGTH_SHORT).show();
            }
        });


    }


}