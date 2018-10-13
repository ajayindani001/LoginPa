package com.example.nilesh.loginpa;

import android.widget.CheckBox;

import java.util.ArrayList;

public class UserProfile {
    public String name;
    public String email;
    public String age;
    public CheckBox chk;

    public UserProfile(CheckBox chk) {
        this.chk = chk;
    }

    public UserProfile(){

    }

    public UserProfile(String name, String email, String age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }



    @Override

    public String toString(){
        return this.name + ":" + this.age;
    }
}
