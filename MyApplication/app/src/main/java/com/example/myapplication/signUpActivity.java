package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUpActivity extends AppCompatActivity {

    EditText signupName, signupUsername, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    double latitude=0.0, longitude=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);

        mAuth = FirebaseAuth.getInstance();


        signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String mail=signupEmail.getText().toString();
                    String password = signupPassword.getText().toString();
                    if(mail.isEmpty()||password.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Fill up all the fields",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(signUpActivity.this,"User registered successfully",Toast.LENGTH_SHORT).show();

                                    database = FirebaseDatabase.getInstance();

                                    String idForData = mail.substring(0,mail.indexOf('@'));
                                    reference = database.getReference().child("Hospitals");

                                    String name = signupName.getText().toString();
                                    String email = signupEmail.getText().toString();
                                    String phone = signupUsername.getText().toString();
                                    String password = signupPassword.getText().toString();

                                    HelperClass helperClass = new HelperClass(name, phone, email, password,"Did not set yet","Did not set yet",latitude,longitude);
                                    reference.child(idForData).setValue(helperClass);
                                    Toast.makeText(signUpActivity.this,"entered the details",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(signUpActivity.this,ProfileActivity.class));
                                }
                                else{
                                    Toast.makeText(signUpActivity.this, "Registration Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }


                }
            });
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();
        if(mUser!=null){
            finish();
            startActivity(new Intent(signUpActivity.this,ProfileActivity.class));
        }
            loginRedirectText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(signUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
    }
}