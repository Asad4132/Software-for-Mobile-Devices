package com.example.android.losslesslife00


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.nio.file.Files.size




/**
 * Created by Asad Ur Rehman on 12/11/2018.
 */

class MyRecyclerViewAdapter (val userList: ArrayList<File>) : RecyclerView.Adapter<MyRecyclerViewAdapter.ImageViewHolder>() {
    private var mContext: Context ?= null
    private var mUploads: List<Upload> ?= null

    fun ImageAdapter(context: Context, uploads: List<Upload>) {
        mContext = context
        mUploads = uploads
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_row, parent, false)
        return ImageViewHolder(v)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val uploadCurrent = mUploads!![position]
        holder.textViewName.text = uploadCurrent.getName()
        Picasso.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return mUploads!!.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView
        var imageView: ImageView

        init {

            textViewName = itemView.findViewById(R.id.text_view_name)
            imageView = itemView.findViewById(R.id.image_view_upload)
        }
    }
}

