package com.example.fastkafoodappandroid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastkafoodappandroid.Model.MyOrder;
import com.example.fastkafoodappandroid.Model.Recommended;
import com.example.fastkafoodappandroid.R;


import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder>{
    List<MyOrder> myOrderList;

    public MyOrderAdapter(List<MyOrder> myOrderList) {
        this.myOrderList = myOrderList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order,parent,false);

        return new MyOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder holder, int position) {
        MyOrder myOrder = myOrderList.get(position);
        holder.numberOrder.setText(myOrderList.get(position).getNumberOrder());
        holder.address.setText(myOrderList.get(position).getAddress());
        holder.total.setText(myOrderList.get(position).getTotal());
    }

    @Override
    public int getItemCount() {

            return myOrderList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView numberOrder,address,total;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numberOrder = itemView.findViewById(R.id.numberOrder);
            address = itemView.findViewById(R.id.address);
            total = itemView.findViewById(R.id.total);
        }
    }
}
