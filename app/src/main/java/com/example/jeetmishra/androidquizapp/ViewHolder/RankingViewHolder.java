package com.example.jeetmishra.androidquizapp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jeetmishra.androidquizapp.Interface.ItemClickListener;
import com.example.jeetmishra.androidquizapp.R;

public class RankingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txt_name , txt_score;

    private ItemClickListener itemClickListener;
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }

    public RankingViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_name =(TextView) itemView.findViewById(R.id.txt_name);
        txt_score = (TextView) itemView.findViewById(R.id.txt_score);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    }
