package com.example.lastauction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bsmt_khier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsmt_khier);
        getSupportActionBar().setTitle("  منظمة بسمة خير  ");

        BottomNavigationView bottomNavigationView = findViewById(R.id.boot);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.bsmt);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.bsmt:
                        return true;

                    case R.id.favorite:
                        startActivity(new Intent(getApplicationContext()
                                ,favorite.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.my_auctions:
                        startActivity(new Intent(getApplicationContext()
                                ,my_auction.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }
}