package com.example.fastkafoodappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fastkafoodappandroid.Adapter.CategoryAdapter;
import com.example.fastkafoodappandroid.Adapter.RecommendedAdapter;
import com.example.fastkafoodappandroid.Adapter.SliderAdapter;
import com.example.fastkafoodappandroid.Model.CategoryData;
//import com.example.fastkafoodappandroid.Model.FoodDataa;
import com.example.fastkafoodappandroid.Model.Recommended;
import com.example.fastkafoodappandroid.Model.SliderData;
import com.example.fastkafoodappandroid.UI.CartActivity;
import com.example.fastkafoodappandroid.UI.NotificationActivity;
import com.example.fastkafoodappandroid.UI.PolicyActivity;
import com.example.fastkafoodappandroid.UI.ProfileActivity;
import com.example.fastkafoodappandroid.UI.SeeMoreActivity;
import com.example.fastkafoodappandroid.UI.SupportActivity;
import com.example.fastkafoodappandroid.account.LoginActivity;
import com.example.fastkafoodappandroid.account.User;


import com.example.fastkafoodappandroid.retrofit.ApiInterface;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategotyList, recycleview_recom;
    private FirebaseDatabase database;
    private DatabaseReference category;
    private RecommendedAdapter recommendedAdapter;

    ApiInterface apiInterface;

    // RecommendedAdapter recommendedAdapter;

    public static final String TAG = "GoogleSignIn";
    private GoogleSignInClient mGoogleSignInClient;

    SliderView sliderView;

    String url1 = "https://s3-ap-southeast-1.amazonaws.com/storage.adpia.vn/affiliate_document/multi/giftpop-lotte-special-sale-2021.jpg";
    String url2 = "https://cdn-www.vinid.net/2020/02/20200218_Voucher-Lotteria_TopBannerWeb_1920x1080.jpg";
    String url3 = "https://blog.utop.vn/uploads/125060732118012021.jpg";
    String url4 = "https://i.ibb.co/Mnx6dmr/4095-detail-2.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        //get user from database
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        String userId = user.getUid();
        //get api retrofit

        recycleview_recom = findViewById(R.id.recycleview_recom);
        recycleview_recom.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
        recommendedData();
        TextView see_more = findViewById(R.id.see_more);
        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SeeMoreActivity.class));
            }
        });

        //  set textview nameUser
        TextView txtWelcome = (TextView) findViewById(R.id.txt_Welcome);
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    String name = userProfile.username;
                    String email = userProfile.email;

                    DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                    NavigationView navigationView = findViewById(R.id.navigation_view);

                    View headerView = navigationView.getHeaderView(0);
                    TextView userName2 = (TextView) headerView.findViewById(R.id.userName1);
                    TextView userMod2 = (TextView) headerView.findViewById(R.id.userMod1);
                    ShapeableImageView userImg2 = (ShapeableImageView) headerView.findViewById(R.id.userImg1);
                    txtWelcome.setText("Hi, " + name + " !");
                    userName2.setText(name);
                    userMod2.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, " ", Toast.LENGTH_SHORT).show();
            }
        });

        //  ---------set name,email,image from firebase in navigation drawer----------

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        View headerView = navigationView.getHeaderView(0);
        TextView userName1 = (TextView) headerView.findViewById(R.id.userName1);
        TextView userMod1 = (TextView) headerView.findViewById(R.id.userMod1);
        ShapeableImageView userImg1 = (ShapeableImageView) headerView.findViewById(R.id.userImg1);
        SharedPreferences preferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userName = preferences.getString("username", "");
        String userEmail = preferences.getString("useremail", "");
        String userImageUrl = preferences.getString("userPhoto", "");

        txtWelcome.setText("Hi, " + userName + "!");
//
        userName1.setText(userName);
        userMod1.setText(userEmail);
        Glide.with(this).load(userImageUrl).into(userImg1);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        //  navigation main
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id) {
                    case R.id.home_main:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                    case R.id.cart:
                        startActivity(new Intent(MainActivity.this, CartActivity.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        break;
                    case R.id.notif:
                        startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                        break;
                    case R.id.support:
                        startActivity(new Intent(MainActivity.this, SupportActivity.class));
                        break;
                    case R.id.policy:
                        startActivity(new Intent(MainActivity.this, PolicyActivity.class));
                        break;
                    case R.id.logout:
                        goToLoginActivity();
                        break;

                    default:
                        return true;
                }
                return true;
            }

        });


        //--------- goi ham-----------------
        slideImage();
        recyclerViewCategoty();
        // recyclerViewPopular();
        bottomNavigation();
        // mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void recommendedData() {
        Call<List<Recommended>> call = apiInterface.getdata();

        call.enqueue(new Callback<List<Recommended>>() {
            @Override
            public void onResponse(Call<List<Recommended>> call, Response<List<Recommended>> response) {
                List<Recommended> data = response.body();
                RecommendedAdapter recAdapter = new RecommendedAdapter(data);
                recycleview_recom.setAdapter(recAdapter);

            }


            @Override
            public void onFailure(Call<List<Recommended>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "server not connect", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void slideImage() {
        //-----------slide image------------
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        sliderView = findViewById(R.id.image_slider);
        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));
        sliderDataArrayList.add(new SliderData(url4));

        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);

        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }


    //logout
    private void goToLoginActivity() {
        FirebaseAuth.getInstance().signOut();

        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
    //


    //--------------recyclerViewPopular------------------
    private void recyclerViewPopular() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewPopularList = findViewById(R.id.view2);
//        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
//
//        ArrayList<FoodData> foodlist = new ArrayList<>();
//        foodlist.add(new FoodData("Pepperoni pizza", "pizza1", "slices pepperoni ,mozzarella cheese, fresh oregano,  ground black pepper, pizza sauce", 13.0, 5, 20, 1000));
//        foodlist.add(new FoodData("Chesse Burger", "burger", "beef, Gouda Cheese, Special sauce, Lettuce, tomato ", 15.20, 4, 18, 1500));
//        foodlist.add(new FoodData("Vagetable pizza", "pizza3", " olive oil, Vegetable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 11.0, 3, 16, 800));
//
//        adapter2 = new RecommendedAdapter(foodlist);
//        recyclerViewPopularList.setAdapter(adapter2);
    }

    //-----------recyclerViewCategoty--------------
    private void recyclerViewCategoty() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategotyList = findViewById(R.id.view1);
        recyclerViewCategotyList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryData> categoryList = new ArrayList<>();
        categoryList.add(new CategoryData("Pizza", "cat_1"));
        categoryList.add(new CategoryData("Burger", "cat_2"));
        categoryList.add(new CategoryData("Hotdog", "cat_3"));
        categoryList.add(new CategoryData("Drink", "cat_4"));
        categoryList.add(new CategoryData("Donut", "cat_5"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategotyList.setAdapter(adapter);

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
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SupportActivity.class));
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });
    }

    private void loadMenu() {

//        FirebaseRecyclerAdapter<CategoryData, MenuViewHolder> adapter =
//                new FirebaseRecyclerAdapter<CategoryData, MenuViewHolder>(CategoryData.class,R.layout.viewholder_category, MenuViewHolder.class,category){
//                    @Override
//                    protected void populateViewHolder(MenuViewHolder holder, CategoryData model, int i) {
//                        holder.categoryName.setText(model.getTitle());
//                        Picasso.with(getBaseContext()).load(model.getPic())
//                                .into(holder.categoryPic);
//
//                        CategoryData clickitem = model;
//                        holder.setItemClickListener(new ItemClickListener() {
//                            @Override
//                            public void onClick(View view, int position, boolean isLongClick) {
//                                Toast.makeText(MainActivity.this,""+clickitem.getTitle(),Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//        };
    }
}


