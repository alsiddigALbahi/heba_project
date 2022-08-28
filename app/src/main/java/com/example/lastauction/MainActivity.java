package com.example.lastauction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.lastauction.Adapter.CategoryAdapter;
import com.example.lastauction.Domain.CategoryDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     private RecyclerView.Adapter adapter;
     private   RecyclerView recyclerViewCategoryList;
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
    }
}