package com.example.lastauction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class MainActivity3 extends AppCompatActivity {
    DatabaseReference mdataref;
    FirebaseDatabase mdatabase;
    FirebaseStorage mstorage;
    ImageButton imageButton;
    EditText name , orgpri;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        name = findViewById(R.id.nameEditText);
        orgpri = findViewById(R.id.orgpriEditText);
        upload = findViewById(R.id.upLoadBtn);
        imageButton = findViewById(R.id.chooseImageView);

        mdatabase=FirebaseDatabase.getInstance();
        mstorage=FirebaseStorage.getInstance();
        mdataref=mdatabase.getReference().child("project");

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                //startActivityForResult(intent,Gallery_Code);
            }
        });

    }
}