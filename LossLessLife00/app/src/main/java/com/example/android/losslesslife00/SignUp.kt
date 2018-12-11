package com.example.android.losslesslife00

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUp : AppCompatActivity() {

    var mauth : FirebaseAuth? = null
    var mDatabaseReference: DatabaseReference? = null
    var mDatabase: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mauth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()

        val signUp=findViewById<Button>(R.id.signUp) as Button


        signUp.setOnClickListener(){
            val name=findViewById<EditText>(R.id.name) as EditText
            val email=findViewById<EditText>(R.id.email) as EditText
            val password=findViewById<EditText>(R.id.password) as EditText

            val string_email=email.getText().toString().trim()
            val string_password=password.getText().toString().trim()

            if(TextUtils.isEmpty(string_email))
                Toast.makeText(this,"Please Enter Name",Toast.LENGTH_SHORT).show()
            else if(TextUtils.isEmpty(string_email))
                Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show()
            else if(TextUtils.isEmpty(string_password))
                Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show()
            else
                mauth!!.createUserWithEmailAndPassword(string_email, string_password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if(task.isSuccessful){
//                        val userId = mauth!!.currentUser!!.uid
//                        val currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userId)
//                        currentUserDb.setValue(name)
                        saveUserToFirebaseDatabase()
                        var intent = Intent(this, LogIn::class.java)
                        intent.putExtra("id", mauth!!.currentUser?.email)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this,"Sorry, Registration Unsuccessful",Toast.LENGTH_SHORT).show()
                    }
                })

        }



    }

    private fun saveUserToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, name.text.toString())

        ref.setValue(user)
                .addOnSuccessListener {
                    Toast.makeText(this,"User is registered in firebase database",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Sorry, User is not registered in firebase database",Toast.LENGTH_SHORT).show()
                }
    }
}
class User(val uid: String, val username: String)
