package com.example.fastkafoodappandroid.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastkafoodappandroid.Interface.ItemClickListener;
import com.example.fastkafoodappandroid.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView categoryName;
    public ImageView categoryPic;
    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView){
        super(itemView);

        categoryName = (TextView) itemView.findViewById(R.id.categoryName);
        categoryPic = (ImageView)  itemView.findViewById(R.id.categoryPic);

        itemView.setOnClickListener(this);
    }

    public void  setItemClickListener (ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
    itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
