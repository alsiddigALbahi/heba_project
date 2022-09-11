package com.example.lastauction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userProfileActivity extends AppCompatActivity {
      private TextView textViewWelcocm,textViewName,textViewEmail,textViewDate,textViewGender,textViewPhone;
      private ProgressBar progressBar;
      private String name,email,date,gender,phone;
      ImageView imageView;
      FirebaseAuth authprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setTitle(" حسابي ...");

        textViewWelcocm = findViewById(R.id.show_welcom);
        textViewName= findViewById(R.id.show_name);
        textViewEmail = findViewById(R.id.show_email);
        textViewDate = findViewById(R.id.show_date);
        textViewGender = findViewById(R.id.show_gender);
        textViewPhone = findViewById(R.id.show_phone);
        progressBar = findViewById(R.id.prfileprogressBar);

        authprofile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authprofile.getCurrentUser();
        if (firebaseUser == null){
            Toast.makeText(userProfileActivity.this," حدث خطأ ما.... بياناتك غير متوفرة",Toast.LENGTH_LONG).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);
        }
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userId = firebaseUser.getUid();
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Regesters Users");
        referenceProfile.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}