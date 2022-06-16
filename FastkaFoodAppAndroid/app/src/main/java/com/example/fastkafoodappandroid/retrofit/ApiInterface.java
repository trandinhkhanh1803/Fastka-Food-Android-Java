package com.example.fastkafoodappandroid.retrofit;

import com.example.fastkafoodappandroid.Model.MyOrder;
import com.example.fastkafoodappandroid.Model.Recommended;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface
{

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setLenient().create();
    ApiInterface retrofit = new Retrofit.Builder()
            .baseUrl("http://172.20.10.13/apiFastkaFood/")
            .client(new OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiInterface.class);



    @GET("recommended.php")
    Call<List<Recommended>> getdata();

    @GET("get_order.php")
    Call<List<MyOrder>> getOrder();



    @FormUrlEncoded
    @POST("add_cart.php")
    Call<Recommended> insertFood(
            @Field("key") String key,
            @Field("title") String name,
            @Field("price") String species,
            @Field("amount") String amount,
            @Field("picture") String picture);


    @FormUrlEncoded
    @POST("add_order.php")
    Call<String> addOrders(
            @Field("email") String email,
            @Field("numberOrder") String numberOrder,
            @Field("date") String date,
            @Field("total") String total,
            @Field("address") String address);
}
