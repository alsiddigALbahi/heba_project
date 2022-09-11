package com.example.lastauction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerActivity extends AppCompatActivity {
    private EditText name,email,date,mobile,password1,password2;
    private ProgressBar progressBar;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button reg;
    private DatePickerDialog picker;
    private static final String TAG = "registerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("   إنشاء حساب جديد...  ");

        Toast.makeText(registerActivity.this,"يمكنك إنشاء حسابك الأن ...",Toast.LENGTH_LONG).show();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        date = findViewById(R.id.date);
        mobile = findViewById(R.id.mobile);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        progressBar = findViewById(R.id.progressBar);
        radioGroup = findViewById(R.id.radiogroup);
        reg = findViewById(R.id.reg);

        radioGroup.clearCheck();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(registerActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                          date.setText(day+"/"+(month+1)+"/"+year);
                    }
                }, year, month, day);
                  picker.show();
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectGenderRadio = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectGenderRadio);

                String textName = name.getText().toString();
                String textemail= email.getText().toString();
                String textdate = date.getText().toString();
                String textmobile = mobile.getText().toString();
                String textpass1 = password1.getText().toString();
                String textpass2 = password2.getText().toString();
                String textGender;
                String mobileReg = "";
                Matcher mobilematcher;
                Pattern mobilepat = Pattern.compile(mobileReg);
                mobilematcher = mobilepat.matcher(textmobile);

                if (TextUtils.isEmpty(textName)){
                    Toast.makeText(registerActivity.this,"أرجوك أدخل الإسم ...",Toast.LENGTH_LONG).show();
                    name.setError("من فضلك الإسم مطلوب!");
                    name.requestFocus();
                }else if (TextUtils.isEmpty(textemail)){
                    Toast.makeText(registerActivity.this,"أرجوك أدخل البريد الإلكتروني ...",Toast.LENGTH_LONG).show();
                    email.setError("من فضلك البريد الإلكتروني مطلوب!");
                    email.requestFocus();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(textemail).matches()){
                    Toast.makeText(registerActivity.this,"أرجوك أدخل بريد صالح ...",Toast.LENGTH_LONG).show();
                    email.setError("من فضلك البريد الإلكترني الصالح مطلوب!");
                    email.requestFocus();
                }else if (TextUtils.isEmpty(textdate)){
                    Toast.makeText(registerActivity.this,"أرجوك أدخل تاريخ ميلادك ...",Toast.LENGTH_LONG).show();
                    date.setError("من فضلك تاريخ الميلاد مطلوب!");
                    date.requestFocus();
                }else if (radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(registerActivity.this,"أرجوك إختر جنس ...",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(textmobile)){
                    Toast.makeText(registerActivity.this,"أرجوك أدخل رقم الهاتف ...",Toast.LENGTH_LONG).show();
                    mobile.setError("من فضلك رقم الهاتف مطلوب!");
                    mobile.requestFocus();
                }else if (textmobile.length() != 10 ){
                    Toast.makeText(registerActivity.this,"أرجوك أعد أدخال رقم الهاتف  ...",Toast.LENGTH_LONG).show();
                    mobile.setError("من فضلك  رقم الهاتف غير صحيح!");
                    mobile.requestFocus();
                }else if (!mobilematcher.find()){
                    Toast.makeText(registerActivity.this,"أرجوك أعد أدخال رقم الهاتف  ...",Toast.LENGTH_LONG).show();
                    mobile.setError("من فضلك  رقم الهاتف غير صالح!");
                    mobile.requestFocus();
                }else if (TextUtils.isEmpty(textpass1)){
                    Toast.makeText(registerActivity.this,"أرجوك أدخل كلمة السر ...",Toast.LENGTH_LONG).show();
                    password1.setError("من فضلك كلمة السر مطلوبة!");
                    password1.requestFocus();
                }else if (textpass1.length()<6){
                    Toast.makeText(registerActivity.this,"أرجوك يجب أن تكون كلمة السر مكونة من أكثر  6 أرقام...",Toast.LENGTH_LONG).show();
                    password1.setError("من فضلك كلمة السر ضعيفة!");
                    password1.requestFocus();
                }else if (TextUtils.isEmpty(textpass2)){
                    Toast.makeText(registerActivity.this,"أرجوك أكد كلمة السر ...",Toast.LENGTH_LONG).show();
                    password2.setError("من فضلك تأكيد كلمة السر مطلوب!");
                    password2.requestFocus();
                }else if (!textpass2.equals(textpass1)){
                    Toast.makeText(registerActivity.this,"أرجوك يسجب أن تكون متطابقة ...",Toast.LENGTH_LONG).show();
                    password2.setError("من فضلك كلمة السر غير متطابقة!");
                    password2.requestFocus();
                    password1.clearComposingText();
                    password2.clearComposingText();
                } else {
                    textGender = radioButton.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    registerRegister(textdate,textGender,textemail,textmobile,textName,textpass1);
                    
                }
            }
        });


    }

    private void registerRegister(String textdate, String textGender, String textemail, String textmobile, String textName, String textpass1) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textemail,textpass1).addOnCompleteListener(registerActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            UserProfileChangeRequest profileChangeRequest= new  UserProfileChangeRequest.Builder().setDisplayName(textName).build();
                            firebaseUser.updateProfile(profileChangeRequest);

                    ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(textdate,textGender,textmobile);

                            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Regesters Users");
                            referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){

                                        firebaseUser.sendEmailVerification();

                                        Toast.makeText(registerActivity.this,"تم التسجيل بنجاح ( قم بتفقد بريدك الإلكتروني )...",Toast.LENGTH_LONG).show();

                                      Intent intent= new Intent(registerActivity.this,userProfileActivity.class);
                                      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                              | Intent.FLAG_ACTIVITY_NEW_TASK);
                                      startActivity(intent);
                                      finish();

                                    } else {
                                        Toast.makeText(registerActivity.this," فشل التسجيل  , أعد المحاولة ...",Toast.LENGTH_LONG).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });

                            
                        } else  {
                            try {
                                throw task.getException();
                            }catch (FirebaseAuthWeakPasswordException e){
                             password1.setError("يجب ان تحتوي كلمة السر علي حروف وأرقام وعلامات خاصة...");
                             password1.requestFocus();
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                email.setError("البريد الإلكتروني غير صالح أو تم استخدامة من قبل...");
                                email.requestFocus();
                            }catch (FirebaseAuthUserCollisionException e){
                                email.setError("لقد تم التسجيل بواسطة هذا البريد الإلكتروني ...");
                                email.requestFocus();
                            } catch (Exception e){
                                Log.e(TAG,e.getMessage());
                                Toast.makeText(registerActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}