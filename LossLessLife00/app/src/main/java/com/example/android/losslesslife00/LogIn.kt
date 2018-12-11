package com.example.android.losslesslife00

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        val signIn=findViewById<Button>(R.id.signIn) as Button


        signIn.setOnClickListener(){
            val email=findViewById<EditText>(R.id.email) as EditText
            val password=findViewById<EditText>(R.id.password) as EditText

            val fireBaseAuth= FirebaseAuth.getInstance()

            val string_email=email.getText().toString().trim()
            val string_password=password.getText().toString().trim()

            if(TextUtils.isEmpty(string_email))
                Toast.makeText(this,"Please Enter Email", Toast.LENGTH_SHORT).show()
            else if(TextUtils.isEmpty(string_password))
                Toast.makeText(this,"Please Enter Password", Toast.LENGTH_SHORT).show()
            else
                fireBaseAuth.signInWithEmailAndPassword(string_email, string_password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if(task.isSuccessful){
                        var intent = Intent(this, Home::class.java)
                        intent.putExtra("id", fireBaseAuth.currentUser?.email)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this,"Sorry, Login Unsuccessful", Toast.LENGTH_SHORT).show()
                    }
                })

        }

    }
}
