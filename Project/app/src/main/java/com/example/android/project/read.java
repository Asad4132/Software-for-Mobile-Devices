package com.example.android.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.sql.BatchUpdateException;
import java.util.List;

public class read extends AppCompatActivity {

    private TextView usersinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        usersinfo=(TextView) findViewById(R.id.users);
        List<User> users = MainActivity.database.myDAO().getUsers();

        String info="";
        for(User usr:users)
        {
            int id=usr.getId();
            String name=usr.getName();
            String email=usr.getEmail();
            String password=usr.getPassword();
            info=info+"\n\n"+id+"\n"+name+"\n"+email+"\n"+password;
            usersinfo.setText(info);
        }

    }
}
