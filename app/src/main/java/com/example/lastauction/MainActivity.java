package com.example.lastauction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastauction.Domain.AuctionAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory();
        profile = (FloatingActionButton) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,loginActivity.class);
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

        boton1 = findViewById(R.id.bottom1);
        boton2 = findViewById(R.id.bottom2);
        boton3 = findViewById(R.id.bottom3);
        boton4 = findViewById(R.id.bottom4);
        boton5 = findViewById(R.id.bottom5);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Member b = dataSnapshot1.getValue(Member.class);
                    list1.add(b);
                }
                adapter2 = new AuctionAdapter(MainActivity.this,list1);
                recyclerView2.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"نأسف ....حدث خطأ ما....",Toast.LENGTH_SHORT).show();
            }
        });

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list1.clear();
                        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            Member b = dataSnapshot1.getValue(Member.class);
                            list1.add(b);
                        }
                        adapter2 = new AuctionAdapter(MainActivity.this,list1);
                        recyclerView2.setAdapter(adapter2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this,"نأسف ....حدث خطأ ما....",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list1.clear();
                        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            Member b = dataSnapshot1.getValue(Member.class);
                            list1.add(b);
                        }
                        adapter2 = new AuctionAdapter(MainActivity.this,list1);
                        recyclerView2.setAdapter(adapter2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this,"نأسف ....حدث خطأ ما....",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list1.clear();
                        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            Member b = dataSnapshot1.getValue(Member.class);
                            list1.add(b);
                        }
                        adapter2 = new AuctionAdapter(MainActivity.this,list1);
                        recyclerView2.setAdapter(adapter2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this,"نأسف ....حدث خطأ ما....",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list1.clear();
                        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            Member b = dataSnapshot1.getValue(Member.class);
                            list1.add(b);
                        }
                        adapter2 = new AuctionAdapter(MainActivity.this,list1);
                        recyclerView2.setAdapter(adapter2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this,"نأسف ....حدث خطأ ما....",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference4.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list1.clear();
                        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            Member b = dataSnapshot1.getValue(Member.class);
                            list1.add(b);
                        }
                        adapter2 = new AuctionAdapter(MainActivity.this,list1);
                        recyclerView2.setAdapter(adapter2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this,"نأسف ....حدث خطأ ما....",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.boot);
        bottomNavigationView.setBackground(null);
        AppBarConfiguration appBarConfiguration;
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.my_auctions:
                        startActivity(new Intent(getApplicationContext()
                                ,my_auction.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
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
