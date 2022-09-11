package com.example.lastauction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class my_auction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_auction);

        BottomNavigationView bottomNavigationView = findViewById(R.id.boot);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.my_auctions);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.my_auctions:
                        return true;

                    case R.id.favorite:
                        startActivity(new Intent(getApplicationContext()
                                ,favorite.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.bsmt:
                        startActivity(new Intent(getApplicationContext()
                                ,bsmt_khier.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }
}