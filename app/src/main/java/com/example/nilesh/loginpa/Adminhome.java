package com.example.nilesh.loginpa;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Adminhome extends AppCompatActivity {
    FirebaseAuth firebaseauth;
    DatabaseReference mref;
    Button btn;
    Switch sw;
    ArrayList <String> selected = new ArrayList<>();

    List<UserProfile> userlist;
    ListView allusers;
    CheckBox chk;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);
        mref = FirebaseDatabase.getInstance().getReference("users");
        btn = (Button) findViewById(R.id.btnsub);
        chk = (CheckBox) findViewById(R.id.checked);
        allusers = (ListView) findViewById(R.id.lvusers);
        //allusers.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        userlist = new ArrayList<>();
        selected = new ArrayList<>();
        //final String slected = allusers.toString();

        }


    @Override
    protected void onStart() {
        super.onStart();

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userlist.clear();
                for (DataSnapshot userprofileSnapshot : dataSnapshot.getChildren()){
                    UserProfile userProfile = userprofileSnapshot.getValue(UserProfile.class);

                    userlist.add(userProfile);

                }

                Userslist adapter = new Userslist(Adminhome.this,userlist);

                allusers.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}








