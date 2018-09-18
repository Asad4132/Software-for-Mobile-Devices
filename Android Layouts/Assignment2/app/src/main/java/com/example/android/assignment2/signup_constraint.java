package com.example.android.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class signup_constraint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_constraint);

        final EditText email=(EditText) findViewById(R.id.email);
        final EditText password=(EditText) findViewById(R.id.password);
        final RadioGroup gender=(RadioGroup) findViewById((R.id.gender));


        final CheckBox termsAndConditions=(CheckBox) findViewById((R.id.termsAndConditions));
        Button register=(Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(termsAndConditions.isChecked())
                {
                    final int genderID=gender.getCheckedRadioButtonId();
                    if(genderID!=-1)
                    {
                        final RadioButton selectedGender=(RadioButton)findViewById(genderID);
                        Toast.makeText(getBaseContext(), "Email: "+email.getText().toString()+"\nPassword: "+password.getText().toString()+
                                "\nGender: "+selectedGender.getText()+"\nTerms and Conditions: Checked" , Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(), "Email: "+email.getText().toString()+"\nPassword: "+password.getText().toString()+
                                "\nGender: "+"\nTerms and Conditions: Checked" , Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Accept Terms and Conditions to continue", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
