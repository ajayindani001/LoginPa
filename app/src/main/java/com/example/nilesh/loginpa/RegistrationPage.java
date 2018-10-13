package com.example.nilesh.loginpa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationPage extends AppCompatActivity {
    private EditText name,age;
    private EditText emailid;
    private EditText password;
    private Button register;
    private TextView logged;
    private boolean result;
    private FirebaseAuth firebaseauth;
    private FirebaseDatabase db;
    private DatabaseReference myref;
    String nam,email,pass,uage,currentuserID,users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        setupUIViews();
        validate();

        firebaseauth = FirebaseAuth.getInstance();
        myref = FirebaseDatabase.getInstance().getReference("users");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {

                    String user_email = emailid.getText().toString().trim();
                    String user_password = password.getText().toString().trim();

                        firebaseauth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    sendUserData();
                                    Toast.makeText(RegistrationPage.this, "Registration Successful And Data upload Successfully", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(RegistrationPage.this, Frontpage.class));
                                } else {
                                    Toast.makeText(RegistrationPage.this, "Registration Problem is Occured", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                }

        });

        logged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationPage.this,Frontpage.class));
            }
        });

    }
    private void setupUIViews()
    {
        name = (EditText) findViewById(R.id.etname);
        emailid = (EditText) findViewById(R.id.etmailid);
        password = (EditText) findViewById(R.id.etrpassword);
        register = (Button) findViewById(R.id.btnreg);
        logged = (TextView) findViewById(R.id.tvlogg);
        age =  (EditText) findViewById(R.id.etage);
    }
    private boolean validate()
    {
        result = false;
         nam = name.getText().toString();
         email = emailid.getText().toString();
         pass = password.getText().toString();
         uage = age.getText().toString();

        if((nam.isEmpty()) || (email.isEmpty()) || (pass.isEmpty())){
            Toast.makeText(this, "Please Enter All Details",Toast.LENGTH_LONG).show();
        }
        else
        {
            result = true;
        }
        return result;
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //DatabaseReference myref = firebaseDatabase.getReference(firebaseauth.getUid());
        String id = myref.push().getKey();
        UserProfile userProfile = new UserProfile(nam,email,uage);
        myref.child(id).setValue(userProfile);
    }

}
/*
firebaseauth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        currentuserID = firebaseauth.getCurrentUser().getUid();
        //myref = db.getReference(firebaseauth.getUid()).child("Users");
        myref = FirebaseDatabase.getInstance().getReference().child("Users");
// db = FirebaseDatabase.getInstance();
//myref = db.getReference().child("Users");*/