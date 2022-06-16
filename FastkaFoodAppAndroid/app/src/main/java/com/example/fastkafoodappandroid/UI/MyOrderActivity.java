package com.example.fastkafoodappandroid.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fastkafoodappandroid.Adapter.MyOrderAdapter;
import com.example.fastkafoodappandroid.Adapter.RecommendedAdapter;
import com.example.fastkafoodappandroid.ApiClient;
import com.example.fastkafoodappandroid.Model.MyOrder;
import com.example.fastkafoodappandroid.Model.Recommended;
import com.example.fastkafoodappandroid.R;
import com.example.fastkafoodappandroid.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private RecyclerView rcv_my_order;
    private MyOrderAdapter myOrderAdapter;
    private ImageView ac_cat, backPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ac_cat = findViewById(R.id.ac_cat);
        ac_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyOrderActivity.this, CartActivity.class));
            }
        });
        backPro = findViewById(R.id.backPro);
        backPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyOrderActivity.this, ProfileActivity.class));
            }
        });

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        rcv_my_order = findViewById(R.id.rcv_my_order);


        Call<List<MyOrder>> call = apiInterface.getOrder();

        call.enqueue(new Callback<List<MyOrder>>() {
            @Override
            public void onResponse(Call<List<MyOrder>> call, Response<List<MyOrder>> response) {
                List<MyOrder> myOrders = response.body();
                rcv_my_order.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrders);
                rcv_my_order.setAdapter(myOrderAdapter);


            }

            @Override
            public void onFailure(Call<List<MyOrder>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "server not connect", Toast.LENGTH_SHORT).show();
            }
        });
    }
}