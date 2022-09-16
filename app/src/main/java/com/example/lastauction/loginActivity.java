package com.example.lastauction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    private EditText loginemail,loginpass;
    private ProgressBar loginpressbar;
    private FirebaseAuth authProfile;
    Button login;
    TextView register;
    private static final String TAG = "loginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("  تسجيل الدخول ...");

        loginemail = findViewById(R.id.loginemail);
        loginpass = findViewById(R.id.loginpassword);
        loginpressbar = findViewById(R.id.loginprogressBar);
        login = findViewById(R.id.loginreg);
        authProfile = FirebaseAuth.getInstance();
        register= findViewById(R.id.registertext);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String textemail= loginemail.getText().toString();
               String textpass = loginpass.getText().toString();

               if (TextUtils.isEmpty(textemail)){
                   Toast.makeText(loginActivity.this,"أرجوك بريدك الألكتروني ...",Toast.LENGTH_LONG).show();
                   loginemail.setError("من فضلك البريد الإلكتروني مطلوب !");
                   loginemail.requestFocus();
               }else  if (!Patterns.EMAIL_ADDRESS.matcher(textemail).matches()){
                   Toast.makeText(loginActivity.this,"أرجوك أعد إدخال البريد الإلكتروني ...",Toast.LENGTH_LONG).show();
                   loginemail.setError("البريد الإلكتروني غير صالح !");
                   loginemail.requestFocus();
               }else  if (TextUtils.isEmpty(textpass)){
                   Toast.makeText(loginActivity.this,"أرجوك أدخل كلمة السر ...",Toast.LENGTH_LONG).show();
                   loginpass.setError("من فضلك كلمة السر مطلوبه!");
                   loginpass.requestFocus();
               }else  {
                   loginpressbar.setVisibility(View.VISIBLE);
                   loginUser(textemail,textpass);
               }
           }
       });



    }

    private void loginUser(String email, String pass) {
        authProfile.signInWithEmailAndPassword(email,pass).addOnCompleteListener(loginActivity.this, new  OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = authProfile.getCurrentUser();
                    if(firebaseUser.isEmailVerified()){
                        Toast.makeText(loginActivity.this, "  تم الدخول بنجاح ...", Toast.LENGTH_LONG).show();
                        startActivity( new Intent(loginActivity.this,userProfileActivity.class));
                        finish();
                    } else {
                        firebaseUser.sendEmailVerification();
                        authProfile.signOut();
                        /*showAlertDialog();*/
                    }

                } else {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        loginemail.setError("من فضلك البريد الإلكتروني مطلوب !");
                        loginemail.requestFocus();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        loginemail.setError("من فضلك البريد الإلكتروني مطلوب !");
                        loginemail.requestFocus();
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(loginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                loginpressbar.setVisibility(View.GONE);
            }
        });
    }

   /* private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
        builder.setTitle("البريد الإلكتروني غير متحقق");
        builder.setMessage("من فضلك ....تفقد بريك الإلكتروني لتحقق !");

        builder.setPositiveButton("إستمر لتفقد بريدك الإلكتروني", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        if (authProfile.getCurrentUser()!=null) {
            Toast.makeText(loginActivity.this, "  تم الدخول مسبقاً ...", Toast.LENGTH_LONG).show();
            startActivity( new Intent(loginActivity.this,userProfileActivity.class));
            finish();
        } else  {
            Toast.makeText(loginActivity.this, " يمكنك تسجيل الدخول الأن ...", Toast.LENGTH_LONG).show();
        }
        }
}