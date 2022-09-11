package com.example.lastauction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class favorite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        BottomNavigationView bottomNavigationView = findViewById(R.id.boot);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.favorite);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.favorite:
                        return true;

                    case R.id.my_auctions:
                        startActivity(new Intent(getApplicationContext()
                                ,my_auction.class));
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