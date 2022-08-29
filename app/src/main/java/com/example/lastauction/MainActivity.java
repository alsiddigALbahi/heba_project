package com.example.lastauction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lastauction.Adapter.CategoryAdapter;
import com.example.lastauction.Domain.AuctionAdapter;
import com.example.lastauction.Domain.CategoryDomain;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     private RecyclerView.Adapter adapter;
     private   RecyclerView recyclerViewCategoryList,recyclerView2;
     ArrayList<Member> list1;
     AuctionAdapter adapter2;
     FirebaseDatabase firebaseDatabase;
     FirebaseStorage mStorage;
     DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory();
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category= new ArrayList<>();
        category.add(new CategoryDomain("فستان","cat_1"));
        category.add(new CategoryDomain("احذية","cat_2"));
        category.add(new CategoryDomain("حقيبة","cat_3"));
        category.add(new CategoryDomain("جواهر","cat_4"));
        category.add(new CategoryDomain("بنطلون","cat_5"));

        adapter = new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);


        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        list1 = new ArrayList<Member>();

        mStorage = FirebaseStorage.getInstance();
        reference= firebaseDatabase.getInstance().getReference("auctions");
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

    }
