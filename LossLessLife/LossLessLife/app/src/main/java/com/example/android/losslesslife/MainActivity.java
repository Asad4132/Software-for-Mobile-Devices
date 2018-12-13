package com.example.android.losslesslife;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    static String[] items = {"Item 0", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10", "Item 11", "Item 12", "Item 13", "Item 14", "Item 15", "Item 16", "Item 17", "Item 18", "Item 19", "Item 20"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
////////////////////////////////////////////////////////
        String URL = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this );
        //actual request json Object

        JsonObjectRequest objectRequest =  new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                             Log.e( "Rest Response" , response.toString());
                    }
                },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "Rest Response" , error.toString());
            }
        }

        );

        requestQueue.add(objectRequest);
/////////////////////////////////////////////////////////
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adaptor(items));

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListner(getBaseContext(), recyclerView ,new RecyclerItemClickListner.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                        startActivity(new Intent(MainActivity.this,Reminders.class));
                    }
                })
        );

        FloatingActionButton createNewFolder=(FloatingActionButton) findViewById(R.id.createNewFolder);

        createNewFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CreateFolderPopUp.class));
            }
        });

        FloatingActionButton camera=(FloatingActionButton)(findViewById(R.id.takePhoto));
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
        }

    }
}
