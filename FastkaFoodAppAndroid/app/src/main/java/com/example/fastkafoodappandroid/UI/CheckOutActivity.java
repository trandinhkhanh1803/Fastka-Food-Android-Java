package com.example.fastkafoodappandroid.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastkafoodappandroid.MainActivity;
import com.example.fastkafoodappandroid.R;
import com.example.fastkafoodappandroid.account.LoginActivity;
import com.example.fastkafoodappandroid.account.RegisterActivity;
import com.example.fastkafoodappandroid.account.User;
import com.example.fastkafoodappandroid.email.ConfigEmail;
import com.example.fastkafoodappandroid.email.FormEmailCustomHtml;
import com.example.fastkafoodappandroid.email.GMailSender;
import com.example.fastkafoodappandroid.retrofit.ApiInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutActivity extends AppCompatActivity {

    private EditText ed_email;
    private TextView txtPrice,txtDelivery,txtTotal,ed_address,ed_name,auto_complete_txt,ed_phone;
    private Button payment;
    private ProgressBar progressBar;
    private ApiInterface apiInterface;
    public String email1;

    private FirebaseUser user;
    private DatabaseReference reference;

    String[] items =  {"Payment on delivery","Payment by other form"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        inview();




        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            ed_name.setText(name);
            String total = extras.getString("total");
            txtTotal.setText(total);
            String email = extras.getString("email");
            ed_email.setText(email);
            String total_price = extras.getString("price");
            txtPrice.setText(total_price);
            String delivery = extras.getString("delivery");
            txtDelivery.setText(delivery);
            String phone = extras.getString("phone");
            ed_phone.setText(phone);
            String address = extras.getString("address");
            ed_address.setText(address);
        }



        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckInput();



            }
        });



    }

    private void postOrder() {
                payment.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

        String email = ed_email.getText().toString().trim();
        String total = txtTotal.getText().toString().trim();
        String address = ed_address.getText().toString().trim();
        Date currentTime = Calendar.getInstance().getTime();
        String date = currentTime.toString();


        int min = 1000;
        int max = 9999;
        int number_random = new Random().nextInt((max - min) + 1)+min;
        String numberOrder = ("#"+number_random).toString();


        ApiInterface.retrofit.addOrders(email,numberOrder,date,total,address).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

//              String  email = ed_email.getText().toString().trim();
//                String address =ed_address.getText().toString().trim();
//                String total = txtTotal.getText().toString().trim();
//                String messenge = new FormEmailCustomHtml().emailCustom(address,total);
//                sendEmail(ConfigEmail.EMAIL, ConfigEmail.PASSWORD,email,"Order confirmation successful",messenge);

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        email1 = user.getEmail();
                        String total = txtTotal.getText().toString().trim();
                        String address = ed_address.getText().toString().trim();
                        String messenge = new FormEmailCustomHtml().emailCustom(total, address);
                        sendEmail(ConfigEmail.EMAIL, ConfigEmail.PASSWORD, email1, "Order confirmation successful", messenge);
                        CartActivity.clearCard();
                        startActivity(new Intent(CheckOutActivity.this,OrderComplete.class));
                    } else {
                        return;
                    }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });


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

                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();
    }

    private void makeAlert(){
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(CheckOutActivity.this, "Mail Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void CheckInput() {
        String name = ed_name.getText().toString().trim();
        String email=ed_email.getText().toString().trim();
        String phone=ed_phone.getText().toString().trim();
        String address=ed_address.getText().toString().trim();
        String payment = auto_complete_txt.getText().toString().trim();

        if(name.isEmpty()){
            ed_name.setError("Please input username");
            ed_name.requestFocus();
            return;
        }
        if(email.isEmpty()){
            ed_email.setError("Please input email");
            ed_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ed_email.setError("Email not value ");
            ed_email.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            ed_phone.setError("Please input phone");
            ed_phone.requestFocus();
            return;
        }


        if(payment.isEmpty()){
            auto_complete_txt.setError("Please select payment");
            auto_complete_txt.requestFocus();
            return;
        }

        if(address.isEmpty()){
            ed_address.setError("Please input address");
            ed_address.requestFocus();
            return;
        }

        postOrder();

    }


    private void inview() {
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        ed_email = findViewById(R.id.ed_email);
        txtPrice = findViewById(R.id.txt_total_price);
        txtDelivery = findViewById(R.id.txt_fee_delivery);
        txtTotal = findViewById(R.id.txt_total);
        payment = findViewById(R.id.payment);
        progressBar = findViewById(R.id.progressBar2);
        ed_address = findViewById(R.id.ed_address);
        ed_name = findViewById(R.id.ed_name);
        ed_phone = findViewById(R.id.ed_phone);
        auto_complete_txt = findViewById(R.id.auto_complete_txt);
    }
}






















//                    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

//                    Call<Orders> call =  apiInterface.addOrders(email,numberOrder,date,total,address);
//                    // ApiInterface.retrofit.order(email,numberOrder,date,total,address)
//                    call.enqueue(new Callback<Orders>() {
//                        @Override
//                        public void onResponse(Call<Orders> call, Response<Orders> response) {
//                            CartActivity.clearCard();
//                            startActivity(new Intent(CheckOutActivity.this,OrderComplete.class));
//                        }
//
//                        @Override
//                        public void onFailure(Call<Orders> call, Throwable t) {
//                            Toast.makeText(CheckOutActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                            ed_email.setText(t.getMessage().toString());
//                        }
//                    });


//                       enqueue(new Callback<String>() {
//                   @Override
//                   public void onResponse(Call<String> call, Response<String> response) {
//                       CartActivity.clearCard();
//                       startActivity(new Intent(CheckOutActivity.this,OrderComplete.class));
//                   }
//
//                   @Override
//                   public void onFailure(Call<String> call, Throwable t) {
//                       Toast.makeText(CheckOutActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
////                       ed_email.setText(t.getMessage().toString());
//                   }
//               });