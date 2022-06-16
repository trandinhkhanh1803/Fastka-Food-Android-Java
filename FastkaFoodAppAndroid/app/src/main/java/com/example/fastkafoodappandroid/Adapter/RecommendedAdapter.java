package com.example.fastkafoodappandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastkafoodappandroid.Model.Recommended;
import com.example.fastkafoodappandroid.R;
import com.example.fastkafoodappandroid.UI.ShowDetailActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    List<Recommended> RecommendedDomains;

    public RecommendedAdapter(List<Recommended> recommendedDomains) {
        RecommendedDomains = recommendedDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recommended,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                Recommended recommended = RecommendedDomains.get(position);
        holder.title.setText(RecommendedDomains.get(position).getTitle());
        holder.price.setText(String.valueOf(RecommendedDomains.get(position).getPrice()));
        Glide.with(holder.pic.getContext()).load("http://172.20.10.13/Crdu_php_pdo/uploads/"+RecommendedDomains.get(position).getPicture()).into(holder.pic);


        holder.itemRec.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra("dataRec",RecommendedDomains.get(position));
            holder.itemView.getContext().startActivity(intent);
        });

    }


    @Override
    public int getItemCount() {
        return RecommendedDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;
        TextView price,title;
        ConstraintLayout itemRec;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.picrec);
            title = itemView.findViewById(R.id.titlerec);
            price = itemView.findViewById(R.id.pricerec);
            itemRec = itemView.findViewById(R.id.constraintRecome);
        }
    }
}


