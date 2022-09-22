package com.example.lastauction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.lastauction.Domain.AuctionAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class my_auction extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private   RecyclerView recyclerViewCategoryList,recyclerView2;
    FloatingActionButton profile;
    ConstraintLayout boton1;
    ConstraintLayout boton2;
    ConstraintLayout boton3;
    ConstraintLayout boton4;
    ConstraintLayout boton5;

    ArrayList<Member> list1;
    AuctionAdapter adapter2;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage mStorage;
    DatabaseReference reference;
    DatabaseReference reference1;
    DatabaseReference reference2;
    DatabaseReference reference3;
    DatabaseReference reference4;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_auction);

        drawerLayout=findViewById(R.id.drawerlayout);
        //toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.nav_view);


        getSupportActionBar().setTitle("   مزاداتي  ");

        //toolbar=findViewById(R.id.toolbar);


        recyclerViewCategory();
        profile = (FloatingActionButton) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(my_auction.this,loginActivity.class);
                startActivity(intent);
            }
        });

    }
    private void recyclerViewCategory() {

        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView2.setBackground(null);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        list1 = new ArrayList<Member>();

        mStorage = FirebaseStorage.getInstance();
        reference= firebaseDatabase.getInstance().getReference("auction");
        reference1= firebaseDatabase.getInstance().getReference("auction1");
        reference2= firebaseDatabase.getInstance().getReference("auction2");
        reference3= firebaseDatabase.getInstance().getReference("auction3");
        reference4= firebaseDatabase.getInstance().getReference("auction4");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1.clear();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("UserDate", 0);
                String email = pref.getString("email", null); // getting String

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Member b = dataSnapshot1.getValue(Member.class);

                    if(b.getuId().equalsIgnoreCase(email)){
                        list1.add(b);
                    }
                }


                adapter2 = new AuctionAdapter(my_auction.this,list1);
                recyclerView2.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(my_auction.this,"نأسف ....حدث خطأ ما....",Toast.LENGTH_SHORT).show();
            }
        });




        BottomNavigationView bottomNavigationView = findViewById(R.id.boot);
        bottomNavigationView.setBackground(null);
        AppBarConfiguration appBarConfiguration;
        bottomNavigationView.setSelectedItemId(R.id.my_auctions);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.my_auctions:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
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