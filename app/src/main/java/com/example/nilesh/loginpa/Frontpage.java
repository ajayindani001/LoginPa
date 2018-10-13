package com.example.nilesh.loginpa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.IDNA;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.signin.SignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Frontpage extends AppCompatActivity {
    private Button btn;
    private EditText uid;
    private EditText pass;
    private TextView tvatt;
    private int counter = 3;
    private TextView registration;
    private FirebaseAuth firebaseauth;
    private ProgressDialog dialog;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);
        btn = (Button) findViewById(R.id.btnlog);
        uid = (EditText) findViewById(R.id.etuid);
        pass = (EditText) findViewById(R.id.etpass);
        tvatt = (TextView) findViewById(R.id.tvatt);
        registration = (TextView) findViewById(R.id.registrations);
        btn2 =(Button) findViewById(R.id.btnadmin);
        firebaseauth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseauth.getCurrentUser();
        dialog = new ProgressDialog(this);
        if(user != null){
            finish();
            startActivity(new Intent(Frontpage.this,Accountpage.class));
        }

        tvatt.setText("Attempts Remaining : 3");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(uid.getText().toString(),pass.getText().toString());
            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Frontpage.this,RegistrationPage.class));
            }
        });
        /*btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Frontpage.this,Adminpage.class));
            }
        });*/
    }

    private void validate(final String uid, final String pass)
    {
        dialog.setMessage("Please Wait");
        dialog.show();

        firebaseauth.signInWithEmailAndPassword(uid,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                dialog.dismiss();
                if(task.isSuccessful()){
                    if((uid.equals("admin@gmail.com")) && (pass.equals("admin1234")))
                        startActivity(new Intent(Frontpage.this,Adminhome.class));


                    else {

                        Toast.makeText(Frontpage.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Frontpage.this, Accountpage.class));
                    }
                }else{
                    Toast.makeText(Frontpage.this,"LOGIN FAILED",Toast.LENGTH_SHORT).show();
                    counter--;
                    tvatt.setText("ATTEMPTS REMAINING :  " + counter);
                    dialog.dismiss();
                    if(counter == 0)
                    {
                        Toast.makeText(Frontpage.this,"YOU ARE NOT AUTHORISED USER",Toast.LENGTH_SHORT).show();
                        btn.setEnabled(false);
                    }
                }

            }
        });
    }

}
