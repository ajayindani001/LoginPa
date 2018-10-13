/*package com.example.nilesh.loginpa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nilesh.loginpa.Adminhome;
import com.example.nilesh.loginpa.R;

public class Adminpage extends AppCompatActivity {
    private Button logbtn;
    private EditText email;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);
        setupUIViews();


        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Adminpage.this,Adminhome.class));
            }
        });


    }
    private void validate(String email,String password)
    {

    if((email.equals("admin@gmail.com")) && (password.equals("admin1234")))
        startActivity(new Intent(Adminpage.this,Adminhome.class));

    }
    void setupUIViews()
    {
    logbtn = (Button) findViewById(R.id.btnlog);
    email = (EditText) findViewById(R.id.etem);
    password = (EditText) findViewById(R.id.etpas);
    }
}
*/