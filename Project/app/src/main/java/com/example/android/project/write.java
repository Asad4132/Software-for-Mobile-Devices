package com.example.android.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class write extends AppCompatActivity {

    private EditText name,email,password;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        name=(EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);

        add=(Button) findViewById(R.id.register);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = name.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();


                User user=new User();
                user.setName(userName);
                user.setEmail(userEmail);
                user.setPassword(userPassword);

                MainActivity.database.myDAO().addUser(user);
                Toast.makeText(getApplication(),"Registration Successful",Toast.LENGTH_SHORT).show();

                userName="";
                userEmail="";
                userPassword="";
            }
        });
    }
}
