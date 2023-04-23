package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {

    EditText editBeds,editDoctors;
    Button saveButton;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editBeds=findViewById(R.id.editBeds);
        editDoctors = findViewById(R.id.editDoctors);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String beds=editBeds.getText().toString();
                String doctors=editDoctors.getText().toString();
                if(beds.isEmpty() || doctors.isEmpty()){
                    Toast.makeText(EditProfile.this,"Enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth=FirebaseAuth.getInstance();
                    FirebaseUser user = mAuth.getCurrentUser();
                    String mail = user.getEmail();
                    String idForData=mail.substring(0,mail.indexOf('@'));
                    reference=FirebaseDatabase.getInstance().getReference().child("Hospitals").child(idForData);
                    reference.child("doctorsCount").setValue(doctors);
                    reference.child("bedsCount").setValue(beds);
                    Toast.makeText(EditProfile.this,"Uploaded Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditProfile.this,ProfileActivity.class));
                }
            }
        });
    }
    /*private boolean isNameChanged() {
        if (!nameUser.equals(editName.getText().toString())){
            reference.child(usernameUser).child("name").setValue(editName.getText().toString());
            nameUser = editName.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmailChanged() {
        if (!emailUser.equals(editEmail.getText().toString())){
            reference.child(usernameUser).child("email").setValue(editEmail.getText().toString());
            emailUser = editEmail.getText().toString();
            return true;
        } else {
            return false;
        }
    }


    private boolean isPasswordChanged() {
        if (!passwordUser.equals(editPassword.getText().toString())){
            reference.child(usernameUser).child("password").setValue(editPassword.getText().toString());
            passwordUser = editPassword.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public void showData(){

        Intent intent = getIntent();

        nameUser = intent.getStringExtra("name");
        emailUser = intent.getStringExtra("email");
        usernameUser = intent.getStringExtra("username");
        passwordUser = intent.getStringExtra("password");

        editName.setText(nameUser);
        editEmail.setText(emailUser);
        editUsername.setText(usernameUser);
        editPassword.setText(passwordUser);
    }*/
}