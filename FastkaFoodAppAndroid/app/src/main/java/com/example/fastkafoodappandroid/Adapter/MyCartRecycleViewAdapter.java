package com.example.fastkafoodappandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastkafoodappandroid.MainActivity;
import com.example.fastkafoodappandroid.Model.CartData;
import com.example.fastkafoodappandroid.R;
import com.example.fastkafoodappandroid.UI.CartActivity;


import java.util.ArrayList;

public class MyCartRecycleViewAdapter extends RecyclerView.Adapter<MyCartRecycleViewAdapter.MyCartViewHoler> {
    private Context context;
    private ArrayList<CartData> myCartArrayList;

    public MyCartRecycleViewAdapter(Context context, ArrayList<CartData> myCartArrayList) {
        this.context = context;
        this.myCartArrayList = myCartArrayList;
    }

    @NonNull
    @Override
    public MyCartViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new MyCartViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartViewHoler holder, int position) {
        CartData myCart = myCartArrayList.get(position);
        holder.cart_amount.setText(myCart.getAmount()+"");
        holder.cart_price.setText(myCart.getPrice()+"$");
        holder.cart_total.setText(myCart.getPrice() * myCart.getAmount()+"$");
        holder.cart_title.setText(myCart.getTitle());
        Glide.with(context).load("http://192.168.1.66/Crdu_php_pdo/uploads/"+myCart.getImageUrl()).into(holder.cart_image);
       // Glide.with(context).load(myCart.getImageUrl()).into(holder.cart_image);

        holder.mycart_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCartArrayList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                CartActivity.setupForPayment();
                Toast.makeText(context.getApplicationContext(), "Delete Success", Toast.LENGTH_SHORT).show();
            }
        });
        holder.plus_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCart.setAmount(myCart.getAmount() +1);
                holder.cart_amount.setText(myCart.getAmount()+"");
                holder.cart_total.setText(myCart.getPrice() * myCart.getAmount()+"$");
                CartActivity.calculateCard();
            }
        });

        holder.minus_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(myCart.getAmount() > 1) {
                    myCart.setAmount(myCart.getAmount() - 1);
                    holder.cart_amount.setText(myCart.getAmount() + "");
                    holder.cart_total.setText(myCart.getPrice() * myCart.getAmount()+"$");
                    CartActivity.calculateCard();
                }else {
                   //amount = 1
                   myCartArrayList.remove(holder.getAdapterPosition());
                   notifyItemRemoved(holder.getAdapterPosition());
                   CartActivity.setupForPayment();
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return myCartArrayList.size();
    }

    class MyCartViewHoler extends RecyclerView.ViewHolder {
        private TextView cart_title,cart_price,cart_amount,cart_total;
        private ImageView cart_image,plus_cart,minus_Cart, mycart_delete;
        public MyCartViewHoler(@NonNull View itemView) {
            super(itemView);
            mycart_delete = itemView.findViewById(R.id.mycart_delete);
            cart_title = itemView.findViewById(R.id.titleTxt);
            cart_price = itemView.findViewById(R.id.priceItemCart);
            cart_total = itemView.findViewById(R.id.totalEachItem);
            cart_amount = itemView.findViewById(R.id.numberItem);
            cart_image = itemView.findViewById(R.id.picCart);
            plus_cart = itemView.findViewById(R.id.plusCardBtn);
            minus_Cart = itemView.findViewById(R.id.minusCardBtn);
        }
    }
}
