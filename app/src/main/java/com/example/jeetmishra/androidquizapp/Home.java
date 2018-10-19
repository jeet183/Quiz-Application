package com.example.jeetmishra.androidquizapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jeetmishra.androidquizapp.Model.GameModes;

class Home extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;
     DrawerLayout mDrawerLayout;
     ActionBarDrawerToggle mToggle;
    android.support.v7.widget.Toolbar mToolbar;
    RankingFragment rankingFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);

//            bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                Fragment selectedFragment = null;
//                switch (menuItem.getItemId())
//                {
//                    case R.id.action_gamemodes:
//                        selectedFragment = GameModesFragment.newInstance();
//                        break;
//                    case R.id.action_leaderboard:
//                        selectedFragment = RankingFragment.newInstance();
//                        break;
//
//
//                }
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout,selectedFragment);
//                transaction.commit();
//                return true;
//            }
//
//        });
//      //  setNavigationViewListner();
//        Fragment fragment = new RankingFragment();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.leaderboard, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
//        //setDefaultFragment();
        swapFragment(new GameModesFragment());
    }

    private void swapFragment(Fragment fragment) {
        if(fragment==null){
            Toast.makeText(this, "Error fragment", Toast.LENGTH_SHORT).show();
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).addToBackStack(null).commit();
    }

//    private void setDefaultFragment() {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_layout,GameModesFragment.newInstance());
//        transaction.commit();
//
//
//
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
          Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            return true;

        }
     return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Fragment selectedFragment = null;
                switch (id)
                {
                    case R.id.profile:
                        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                        selectedFragment =  GameModesFragment.newInstance();
                        swapFragment(selectedFragment);
                        break;
                    case R.id.leaderboard:

                            selectedFragment =  RankingFragment.newInstance();
                        swapFragment(selectedFragment);
                        break;
                    case R.id.logout: ProgressDialog dialog;
                        dialog = new ProgressDialog(Home.this);
                        dialog.setTitle("User");
                        dialog.setMessage("Please Wait");
                        dialog.show();


                        Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),com.example.jeetmishra.androidquizapp.SignUp.class);
                        startActivity(intent);
                        break;

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

    }
   /* private void setNavigationViewListner() {
        NavigationView navigationView =findViewById(R.id.profile);
        navigationView.setNavigationItemSelectedListener(this);
    }*/


