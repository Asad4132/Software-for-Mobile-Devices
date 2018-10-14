package com.example.android.project;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database= Room.databaseBuilder(getApplicationContext(),Database.class,"userdb").allowMainThreadQueries().build();

        final Button write = (Button) findViewById(R.id.writeButton);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent writeIntent = new Intent(MainActivity.this,write.class);
                startActivity(writeIntent);
            }
        });


        final Button read = (Button) findViewById(R.id.readButton);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent readIntent = new Intent(MainActivity.this,read.class);
                startActivity(readIntent);
            }
        });

        final Button update = (Button) findViewById(R.id.updateButton);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateIntent = new Intent(MainActivity.this,update.class);
                startActivity(updateIntent);
            }
        });
    }
}
