package com.example.lastauction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
      private String name,email,datee,genderr,mobilee;
      ImageView imageView;
      FirebaseAuth authprofile;
      Button logout;
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
        logout = findViewById(R.id.logout);

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
                if (readUserDetails != null)   {
                    name =  firebaseUser.getDisplayName();
                    email =  firebaseUser.getEmail();
                    genderr =  readUserDetails.genderr;
                    mobilee =   readUserDetails.mobilee;
                    datee =   readUserDetails.datee;
                    textViewWelcocm.setText(" مرحبا , "+ name +" ! ");
                    textViewName.setText(name);
                    textViewEmail.setText(email);
                    textViewGender.setText(genderr);
                    textViewDate.setText(datee);
                    textViewPhone.setText(mobilee);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(userProfileActivity.this," حدث خطأ ما.... بياناتك غير متوفرة",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authprofile.signOut();
                Toast.makeText(userProfileActivity.this," تم تسجيل الخروج بنجاح .... ",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(userProfileActivity.this,MainActivity2.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }


}