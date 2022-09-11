package com.example.lastauction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

public class details extends AppCompatActivity {
    TextView price,overprice,overauction,name,des,addboton;
    EditText interAuc;
    ShapeableImageView detailimge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name=findViewById(R.id.nameDetailTextView);
        des=findViewById(R.id.descriptionDetailTextView);
        detailimge=findViewById(R.id.detailImageView);

        name.setText(getIntent().getExtras().getString("title"));
        des.setText(getIntent().getExtras().getString("des"));
        int img = getIntent().getIntExtra("image",0);
         detailimge.setImageResource(img);


    }
}