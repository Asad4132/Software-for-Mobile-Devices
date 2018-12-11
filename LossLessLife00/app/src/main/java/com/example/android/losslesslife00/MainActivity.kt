package com.example.android.losslesslife00

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signUpButton = findViewById<Button>(R.id.signUp) as Button
        val signInButton = findViewById<Button>(R.id.signIn) as Button

        signUpButton.setOnClickListener{
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        signInButton.setOnClickListener{
            val intent = Intent(this,LogIn::class.java)
            startActivity(intent)
        }

    }
}
