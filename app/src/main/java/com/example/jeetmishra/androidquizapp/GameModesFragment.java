package com.example.jeetmishra.androidquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jeetmishra.androidquizapp.Common1.Common;
import com.example.jeetmishra.androidquizapp.Interface.ItemClickListener;
import com.example.jeetmishra.androidquizapp.Model.GameModes;
import com.example.jeetmishra.androidquizapp.ViewHolder.GameModesViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class GameModesFragment extends Fragment {
    View myFragment;
    RecyclerView listGameModes;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<GameModes,GameModesViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference gamemodes;


    public static GameModesFragment newInstance(){
        GameModesFragment gameModesFragment = new GameModesFragment();
        return gameModesFragment;
        }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        gamemodes = database.getReference("GameModes");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_game_modes,container,false);
        listGameModes = (RecyclerView) myFragment.findViewById(R.id.listGameModes);
        listGameModes.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        listGameModes.setLayoutManager(layoutManager);
        loadGameModes();

        return myFragment;
    }

    private void loadGameModes() {
      adapter = new FirebaseRecyclerAdapter<GameModes, GameModesViewHolder>(
              GameModes.class,
              R.layout.gamemodes_layout,
              GameModesViewHolder.class,
              gamemodes
      ) {
          @Override
          protected void populateViewHolder(GameModesViewHolder viewHolder, final GameModes model, int position) {
              viewHolder.gamemodes_name.setText(model.getName());
              Picasso.with(getActivity()).load(model.getImage()).into(viewHolder.gamemodes_image);
              viewHolder.setItemClickListener(new ItemClickListener() {
                  @Override
                  public void onClick(View view, int position, boolean isLongClick) {

                      Common.gameModesNo= adapter.getRef(position).getKey();
                      int j = Integer.parseInt(Common.gameModesNo);
                      Log.i("No",Common.gameModesNo);
                      if(j==01) {
                          Intent intent = new Intent(getActivity(), Start.class);
                          startActivity(intent);
                      }
                      else if(j==02)
                      {
                          Intent intent = new Intent(getActivity(),Online.class);
                          startActivity(intent);
                      }
                      else
                      {
                            Intent intent = new Intent(getActivity(),HeadOn.class);
                            startActivity(intent);
                      }

                   //   Toast.makeText(getActivity(), String.format("%s|%s",adapter.getRef(position).getKey(),model.getName()),Toast.LENGTH_SHORT).show();
                  }
              });


          }
      };
        adapter.notifyDataSetChanged();
        listGameModes.setAdapter(adapter);
      }
    }





