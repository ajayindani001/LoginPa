package com.example.nilesh.loginpa;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Userslist extends ArrayAdapter <UserProfile> {
    private Activity context;
    private List<UserProfile> userlist;
    ArrayList<String> selected = new ArrayList<String>();
    public Userslist(Activity context,List<UserProfile> userlist){
        super(context,R.layout.list_layout,userlist);
        this.context =context;
        this.userlist = userlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        final TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        final TextView textViewemail = (TextView) listViewItem.findViewById(R.id.textViewemail);
        CheckBox chk = (CheckBox) listViewItem.findViewById(R.id.checked);


        final UserProfile userProfile = userlist.get(position);

        textViewName.setText(userProfile.getName());
        textViewemail.setText(userProfile.getEmail());

        /*chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    textViewName.setText(userProfile.getName());
                    }
                 else
                {
                    textViewemail.setText(userProfile.getEmail());
                }
            }
        });*/
        return listViewItem;
    }


}
