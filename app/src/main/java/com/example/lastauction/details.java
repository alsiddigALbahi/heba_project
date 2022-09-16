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
    TextView orgprice,lastprice,overauction,name,des,addboton,aucnum;
    EditText interAuc;
    ShapeableImageView detailimge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setTitle("   التفاصيل   ");

        name=findViewById(R.id.nameDetailTextView);
        des=findViewById(R.id.auctionnum1);
        orgprice=findViewById(R.id.orgpri);
        lastprice=findViewById(R.id.lastprice);
        orgprice=findViewById(R.id.orgpri);
        overauction=findViewById(R.id.overauction);
        aucnum=findViewById(R.id.auctionnum1);
        detailimge=findViewById(R.id.detailImageView);

        name.setText(getIntent().getExtras().getString("title"));
        des.setText(getIntent().getExtras().getString("des"));
        orgprice.setText(getIntent().getExtras().getString("orgpri"));
        overauction.setText(getIntent().getExtras().getString("overauc"));
        aucnum.setText(getIntent().getExtras().getString("aucnum"));
        lastprice.setText(getIntent().getExtras().getString("lastpri"));

        int img = getIntent().getIntExtra("image",0);
         detailimge.setImageResource(img);


    }
}