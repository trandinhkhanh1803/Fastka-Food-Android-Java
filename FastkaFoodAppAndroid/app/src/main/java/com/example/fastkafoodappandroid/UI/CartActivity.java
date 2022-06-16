package com.example.fastkafoodappandroid.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fastkafoodappandroid.Adapter.MyCartRecycleViewAdapter;

import com.example.fastkafoodappandroid.Interface.ChangeNumberItemsListener;
import com.example.fastkafoodappandroid.MainActivity;
import com.example.fastkafoodappandroid.Model.CartData;
import com.example.fastkafoodappandroid.R;
import com.example.fastkafoodappandroid.account.LoginActivity;
import com.example.fastkafoodappandroid.account.User;
import com.example.fastkafoodappandroid.email.ConfigEmail;
import com.example.fastkafoodappandroid.email.FormEmailCustomHtml;
import com.example.fastkafoodappandroid.email.GMailSender;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    public static RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;

    private ScrollView scrollView;
    private Button btn_checkout;

   public static TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
   public static double tax;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    public String email;
    public static TextView txtSubTotal,txtFeeShipping,txtEstimasteTotal;
    public static ArrayList<CartData> myCarts = new ArrayList<>();
    private DatabaseReference mDatabase;
    public static CartData cartData;


    public static MyCartRecycleViewAdapter myCartRecycleViewAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ImageView backCart = findViewById(R.id.backCart);
        backCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,MainActivity.class));
            }
        });

//        foodData = new ManagementCart(this).getListCart();
//        mToolbar = findViewById(R.id.toolBarCart);
//        setSupportActionBar(mToolbar);
//
//        mActionBar = getSupportActionBar();
//        mActionBar.setDisplayHomeAsUpEnabled(true);
//        mActionBar.setDisplayShowTitleEnabled(false);

//        managementCart = new ManagementCart(this);



        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_cart);
        NavigationView navigationView = findViewById(R.id.navigation_cart);
        View headerView = navigationView.getHeaderView(0);
        TextView userName1 = (TextView) headerView.findViewById(R.id.userName1);
        TextView userMod1 = (TextView) headerView.findViewById(R.id.userMod1);
        ShapeableImageView userImg1 = (ShapeableImageView) headerView.findViewById(R.id.userImg1);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id){
                    case R.id.home_main:
                        startActivity(new Intent(CartActivity.this,MainActivity.class));break;
                    case R.id.cart:
                        startActivity(new Intent(CartActivity.this,CartActivity.class));break;
                    case R.id.profile:
                        startActivity(new Intent(CartActivity.this, ProfileActivity.class));break;
                    case R.id.notif:
                        startActivity(new Intent(CartActivity.this, NotificationActivity.class));break;
                    case R.id.support:
                        startActivity(new Intent(CartActivity.this, SupportActivity.class));break;
                    case R.id.policy:
                        startActivity(new Intent(CartActivity.this, PolicyActivity.class));break;
                    case R.id.logout:
                        goToLoginActivity();
                        break;

                    default:
                        return true;
                }
                return true;
            }

        });


        SharedPreferences preferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userName = preferences.getString("username","");
        String userEmail = preferences.getString("useremail", "");
        String userImageUrl = preferences.getString("userPhoto","");

        userName1.setText(userName);
        userMod1.setText(userEmail);
        Glide.with(this).load(userImageUrl).into(userImg1);
         LinearLayout ln_checkout = findViewById(R.id.ln_checkout);
         LinearLayout no_cart = findViewById(R.id.no_cart);
   if (myCarts.size() == 0){
       ln_checkout.setVisibility(View.INVISIBLE);
       no_cart.setVisibility(View.VISIBLE);
   }else {
       ln_checkout.setVisibility(View.VISIBLE);
       no_cart.setVisibility(View.GONE);
   }

        initView();
        setupRecycleview();
        calculateCard();
        bottomNavigation();
        clickToPayment();

    }

    public void clickToPayment(){

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(CartActivity.this,CheckOutActivity.class));
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                mDatabase = FirebaseDatabase.getInstance().getReference("Users");



                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_cart);
                NavigationView navigationView = findViewById(R.id.navigation_cart);
                View headerView = navigationView.getHeaderView(0);
                TextView userName1 = (TextView) headerView.findViewById(R.id.userName1);

                String name = userName1.getText().toString().trim();
                 String total = totalTxt.getText().toString().trim();
                 String total2 = totalFeeTxt.getText().toString().trim();
                 String delivery = deliveryTxt.getText().toString().trim();

                Intent i = new Intent(CartActivity.this, CheckOutActivity.class);
                i.putExtra("email",user.getEmail());
                i.putExtra("total",total);
                i.putExtra("price",total2);
                i.putExtra("delivery",delivery);
                i.putExtra("name",name);
                i.putExtra("phone","0915368545");
                i.putExtra("address","470 Tran Dai Nghia - NHS - Da Nang");


                startActivity(i);













//                if(managementCart.getListCart().size() == 0){
//                    Toast.makeText(CartActivity.this, "Nothing to pay, please add product to cart", Toast.LENGTH_SHORT).show();
//                }else if(managementCart.getListCart().size() >0){
//                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                    if (user != null) {
//                        email = user.getEmail();
//                        String total = txtSubTotal.getText().toString().trim();
//                        String estimastetotal = txtEstimasteTotal.getText().toString().trim();
//                        String messenge = new FormEmailCustomHtml().emailCustom(total,estimastetotal);
//                        sendEmail(ConfigEmail.EMAIL,ConfigEmail.PASSWORD,email,"Order confirmation successful",messenge);
//                        TinyDB.flag_CheckOut = 1;
//                        foodData.clear();
//                        initList(foodData);
//                        //setupForPayment();
//                    } else {
//                        return;
//                    }
//                }
           }

            private void calculateCard() {
                if(myCarts.size() == 0){

                    totalFeeTxt.setText("$" + 0);
                    taxTxt.setText("$" + 0);
                    deliveryTxt.setText("$" + 0);
                    totalTxt.setText("$" + 0);

                }
                if (myCarts.size() > 0){

                    double percentTax = 0;  //you can change this item for tax price
                    double delivery = 5;     //you can change this item you need price for delivery
                    double total = 0;

                    for(int i = 0 ; i< myCarts.size() ; i++){
                        total += myCarts.get(i).getPrice() * myCarts.get(i).getAmount();
                    }

                    totalFeeTxt.setText("$" + total);
                    taxTxt.setText("$" + tax);
                    deliveryTxt.setText("$" + delivery);
                    totalTxt.setText("$" + Math.round(total + delivery + percentTax));

                }
            }
        });
    }


    public static void setupForPayment() {
        if(myCarts.size() == 0){

            totalFeeTxt.setText("$" + 0);
            taxTxt.setText("$" + 0);
            deliveryTxt.setText("$" + 0);
            totalTxt.setText("$" + 0);
        }
    }

    public static void clearCard(){
        myCarts.clear();
    }


    public static void  calculateCard() {
        if(myCarts.size() == 0){

            totalFeeTxt.setText("$" + 0);
            taxTxt.setText("$" + 0);
            deliveryTxt.setText("$" + 0);
            totalTxt.setText("$" + 0);

        }
        if (myCarts.size() > 0){

            double percentTax = 0;  //you can change this item for tax price
            double delivery = 5;     //you can change this item you need price for delivery
            double total = 0;

            for(int i = 0 ; i< myCarts.size() ; i++){
                total += myCarts.get(i).getPrice() * myCarts.get(i).getAmount();
            }

            totalFeeTxt.setText("$" + total);
            taxTxt.setText("$" + tax);
            deliveryTxt.setText("$" + delivery);
           totalTxt.setText("$" + Math.round(total + delivery + percentTax));

        }
    }


    private void setupRecycleview(){
        myCartRecycleViewAdapter = new MyCartRecycleViewAdapter(this,myCarts);
        recyclerViewList.setAdapter(myCartRecycleViewAdapter);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        myCartRecycleViewAdapter.notifyDataSetChanged();
    }


    private void sendEmail(final String Sender,final String Password,final String Receiver,final String Title,final String Message)
    {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender(Sender,Password);
                    sender.sendMail(Title, "<b>"+Message+"</b>", Sender, Receiver);
                    makeAlert();
                    startActivity(new Intent(CartActivity.this,OrderComplete.class));
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();
    }

    private void makeAlert(){
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(CartActivity.this, "Mail Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }

//logout
    private void goToLoginActivity() {
        FirebaseAuth.getInstance().signOut();
//        mGoogleSignInClient.signOut().addOnCompleteListener(this,
//                new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }
//                });

        startActivity(new Intent(CartActivity.this, LoginActivity.class));
    }

//    private void bottomNavigation() {
//        LinearLayout homeBtn = findViewById(R.id.homeBtn);
//        LinearLayout cartBtn = findViewById(R.id.cartBtn);
//
//        homeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CartActivity.this, MainActivity.class));
//            }
//        });
//
//        cartBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CartActivity.this, CartActivity.class));
//            }
//        });
//    }

    private void initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        recyclerViewList = findViewById(R.id.rcv_cart);
        scrollView = findViewById(R.id.scrollView);
        emptyTxt = findViewById(R.id.emptyTxt);
        btn_checkout = findViewById(R.id.btn_checkout);

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
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CartActivity.class));
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, ProfileActivity.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, SupportActivity.class));
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,NotificationActivity.class));
            }
        });
    }
}