package com.example.jeetmishra.androidquizapp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeetmishra.androidquizapp.Interface.ItemClickListener;
import com.example.jeetmishra.androidquizapp.R;

public class GameModesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView gamemodes_name;
    public ImageView gamemodes_image;
    private ItemClickListener itemClickListener;

    public GameModesViewHolder(@NonNull View itemView) {
        super(itemView);
        gamemodes_image =(ImageView) itemView.findViewById(R.id.gamemodes_image);
        gamemodes_name = (TextView) itemView.findViewById(R.id.gamemodes_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
