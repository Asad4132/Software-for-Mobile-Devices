package com.example.android.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {

    private EditText userid,name,email,password;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        userid=(EditText) findViewById(R.id.id);
        name=(EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);

        add=(Button) findViewById(R.id.register);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(userid.getText().toString());
                String userName = name.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();


                User user=new User();
                user.setId(id);
                user.setName(userName);
                user.setEmail(userEmail);
                user.setPassword(userPassword);

                MainActivity.database.myDAO().updateUser(user);
                Toast.makeText(getApplication(),"Updation Successful",Toast.LENGTH_SHORT).show();

                userName="";
                userEmail="";
                userPassword="";
            }
        });
    }
}
