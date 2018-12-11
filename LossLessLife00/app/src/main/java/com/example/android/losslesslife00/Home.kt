package com.example.android.losslesslife00

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import com.github.clans.fab.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import android.webkit.MimeTypeMap
import android.content.ContentResolver
import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.util.Log
import java.io.ByteArrayOutputStream
import android.provider.MediaStore.Images
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.google.firebase.database.*

class Home : AppCompatActivity() {

    val IMAGE_PERMISSION_REQUEST_CODE = 10
    val EXTERNAL_STORAGE_READ_PERMISSION_REQUEST_CODE = 11
    var filePath: Uri? = null
    internal var storage: FirebaseStorage? = null
    internal var storageReference: StorageReference? = null
    internal var adapter: MyRecyclerViewAdapter ?= null

    private var mRecyclerView : RecyclerView ?= null
    private var mAdapter :Adapter ?= null

    private var mProgressCircle : ProgressBar ?= null

    private var mDatabaseRef : DatabaseReference ?= null
    private var mUploads : List<Upload> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

/*
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView!!.setHasFixedSize(true);
        mRecyclerView!!.setLayoutManager(LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("/users/$uid/files")
        var valueEventListner : ValueEventListener ?= null
        mDatabaseRef!!.addValueEventListener(valueEventListner){
            override

        }

        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference
*/

        //choose image from gallery. take permission if permission us not provided
        val chooseFromGallery = (findViewById<FloatingActionButton>(R.id.menu_item_3)) as FloatingActionButton
        chooseFromGallery.setOnClickListener{view ->
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                if (intent.resolveActivity(packageManager) != null) {
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), EXTERNAL_STORAGE_READ_PERMISSION_REQUEST_CODE)
                }
            }else{
                //permission failed. request
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        EXTERNAL_STORAGE_READ_PERMISSION_REQUEST_CODE)
            }

        }

        //take picture using camera . if camera permission is not provided then ask for permission
        val takePicture = (findViewById<FloatingActionButton>(R.id.menu_item_4)) as (FloatingActionButton)
        takePicture.setOnClickListener {view ->
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if(callCameraIntent.resolveActivity(packageManager)!=null){
                    startActivityForResult(callCameraIntent,IMAGE_PERMISSION_REQUEST_CODE)

                }
            }else{
                //permission failed. request
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CAMERA),
                        IMAGE_PERMISSION_REQUEST_CODE)
            }

        }
    }

    //asking for permissions
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            IMAGE_PERMISSION_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, Home::class.java)
                    startActivity(intent)

                } else {
                    val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    if(callCameraIntent.resolveActivity(packageManager)!=null){
                        startActivityForResult(callCameraIntent,IMAGE_PERMISSION_REQUEST_CODE)
                    }
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()

                }
            }

            EXTERNAL_STORAGE_READ_PERMISSION_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, Home::class.java)
                    startActivity(intent)

                } else {
                    val intent = Intent(Intent.ACTION_GET_CONTENT)
                    intent.type = "image/*"
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), EXTERNAL_STORAGE_READ_PERMISSION_REQUEST_CODE)
                    }
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

/*    private fun getFileExtension(uri: Uri): String? {
        val cR = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            IMAGE_PERMISSION_REQUEST_CODE ->{
                if(resultCode== Activity.RESULT_OK && data !=null){
                    val imageView = (findViewById<ImageView>(R.id.iv)) as (ImageView)
                    imageView.setImageBitmap(data.extras.get("data") as Bitmap)
                    if(resultCode== Activity.RESULT_OK && data !=null) {
                        filePath = data.data
                        //uploadImageToFirebaseStorage()
                    }
                }
            }
            EXTERNAL_STORAGE_READ_PERMISSION_REQUEST_CODE ->{
                val imageView = (findViewById<ImageView>(R.id.iv)) as (ImageView)
                imageView.setImageURI(data?.data)
                if(resultCode== Activity.RESULT_OK && data !=null) {
                    filePath = data.data
                    uploadImageToFirebaseStorage()
                }
            }
            else -> {
                Toast.makeText(this,"Unrecognized request code",Toast.LENGTH_SHORT)
            }


        }


    }

/*    public fun getImageUri(inContext: Context, inImage:Bitmap) : Uri{
        var bytes = ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        var path = Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }*/

    private fun uploadImageToFirebaseStorage() {
        if (filePath == null) return
        val progressDialog = ProgressDialog (this)
        progressDialog.setTitle("Uploading...")
        progressDialog.show()
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val ref1 = FirebaseDatabase.getInstance().getReference("/users/$uid/files")
        ref.putFile(filePath!!)
                .addOnSuccessListener {
                    Toast.makeText(this,"Successfully uploaded image",Toast.LENGTH_SHORT).show()

                    ref.downloadUrl.addOnSuccessListener {
                        ref1.push().setValue(filename)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Failed to upload image to storage",Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener {taskSnapshot ->
                    val progress = 100.0 * taskSnapshot.bytesTransferred/taskSnapshot.totalByteCount
                    progressDialog.setMessage("Uploaded " + progress.toInt())
                }
    }



}
