package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName, profileEmail, profileUsername, profilePassword;
    TextView doctorsProfile, bedsProfile;
    Button editProfile;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference ref;
    TextView logout;
    String mail,idForData;
    String name,phone,email,password,doctors_count,beds_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileUsername = findViewById(R.id.profilePhone);
        profilePassword = findViewById(R.id.profilePassword);
        doctorsProfile = findViewById(R.id.profileDoctorsCount);
        bedsProfile = findViewById(R.id.profilenoofbeds);
        editProfile = findViewById(R.id.editButton);

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mail = user.getEmail();
        idForData = mail.substring(0,mail.indexOf('@'));
        logout=findViewById(R.id.logout);
        db=FirebaseDatabase.getInstance();
        showAllUserData();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,EditProfile.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(ProfileActivity.this,signUpActivity.class));
            }
        });

    }

    public void showAllUserData(){

        ref=FirebaseDatabase.getInstance().getReference().child("Hospitals").child(idForData);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("name").getValue(String.class);
                phone = snapshot.child("phone").getValue(String.class);
                email = mAuth.getCurrentUser().getEmail();
                password = snapshot.child("password").getValue(String.class);
                doctors_count = snapshot.child("doctorsCount").getValue(String.class);
                beds_count = snapshot.child("bedsCount").getValue(String.class);
                profileName.setText(name);
                profileEmail.setText(email);
                profileUsername.setText(phone);
                profilePassword.setText(password);
                doctorsProfile.setText(doctors_count);
                bedsProfile.setText(beds_count);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(ProfileActivity.this,signUpActivity.class));
        }
    }
}