package com.example.android.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button constraint = (Button) findViewById(R.id.ConstrainLayout);
        Button relative = (Button) findViewById(R.id.RelativeLayout);

        relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent constrainIntent = new Intent(MainActivity.this, signup_relative.class);
                startActivity(constrainIntent);
            }
        });

        constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent relativeIntent = new Intent(MainActivity.this,signup_constraint.class);
                startActivity(relativeIntent);
            }
        });
    }
}
