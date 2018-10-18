package com.example.jeetmishra.androidquizapp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jeetmishra.androidquizapp.R;

public class ScoreDetailViewHolder extends RecyclerView.ViewHolder {

    public TextView txt_name,txt_score;
    public ScoreDetailViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_name =(TextView) itemView.findViewById(R.id.txt_name);
        txt_score =(TextView) itemView.findViewById(R.id.txt_score);
    }
}
