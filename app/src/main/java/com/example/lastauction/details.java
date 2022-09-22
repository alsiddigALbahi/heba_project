package com.example.lastauction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class details extends AppCompatActivity {
    TextView orgprice,lastprice,overauction,name,des,addboton,aucnum;
    EditText interAuc;
    ShapeableImageView detailimge;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setTitle("   التفاصيل   ");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        name=findViewById(R.id.nameDetailTextView);
        des=findViewById(R.id.auctionnum1);
        orgprice=findViewById(R.id.orgpri);
        lastprice=findViewById(R.id.lastprice);
        orgprice=findViewById(R.id.orgpri);
        overauction=findViewById(R.id.overauction);
        aucnum=findViewById(R.id.auctionnum1);
        detailimge=findViewById(R.id.detailImageView);
        addboton=findViewById(R.id.addboton);
        interAuc=findViewById(R.id.interAuc);

        getSelectedData();


        addboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String abc = interAuc.getText().toString();

                SharedPreferences pref = getApplicationContext().getSharedPreferences("UserDate", 0);
                String email = pref.getString("email", null); // getting String
                String aucnum = getIntent().getExtras().getString("aucnum");

                int lastprice = Integer.valueOf(getIntent().getExtras().getString("orgpri")) + Integer.valueOf(abc) ;
                String overauc = abc;
                String uId = email;
                String des = String.valueOf((Integer.valueOf(getIntent().getExtras().getString("des")) +1));

                Member member = new Member(
                        getIntent().getExtras().getString("title"),
                        getIntent().getExtras().getString("image"),
                        des,
                        String.valueOf(lastprice),
                        getIntent().getExtras().getString("orgpri"),
                        overauc,
                        aucnum,
                        uId,
                        getIntent().getExtras().getInt("fav")
                );

                if(Integer.valueOf(abc) <= Integer.valueOf(getIntent().getExtras().getString("overauc"))){
                    Toast.makeText(details.this,"القيمة ليست صحيحة",Toast.LENGTH_SHORT).show();
                }else {
                    mDatabase.child("auction").child(aucnum).setValue(member);
                    finish();
                }
            }
        });


        name.setText(getIntent().getExtras().getString("title"));
        des.setText(getIntent().getExtras().getString("des"));
        orgprice.setText(getIntent().getExtras().getString("orgpri"));
        overauction.setText(getIntent().getExtras().getString("overauc"));
        aucnum.setText(getIntent().getExtras().getString("aucnum"));
        lastprice.setText(getIntent().getExtras().getString("lastpri"));
        int img = getIntent().getIntExtra("image",0);
         detailimge.setImageResource(img);
    }

    private void getSelectedData(){
        mDatabase.child("auction").child("3").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
    }
}