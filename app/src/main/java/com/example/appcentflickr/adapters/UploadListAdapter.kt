package com.example.appcentflickr.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentflickr.R
import com.example.appcentflickr.interfaces.RecyclerViewClickListener
import com.example.appcentflickr.models.Upload
import com.example.appcentflickr.utils.PhotoUrlHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_upload.view.*

class UploadListAdapter (val uploads : List<Upload>, itemListener: RecyclerViewClickListener, val context : Context) : RecyclerView.Adapter<UploadListAdapter.UploadsViewHolder>() {


    var itemListener: RecyclerViewClickListener

    init {
        this.itemListener = itemListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UploadListAdapter.UploadsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_upload,parent,false)
        return UploadsViewHolder(v)
    }

    override fun getItemCount(): Int {
        return uploads.size
    }

    override fun onBindViewHolder(holder: UploadListAdapter.UploadsViewHolder, position: Int) {
        val upload = uploads.get(position)
        holder.itemView.postTitle.text = upload.title
        Picasso.get().load(PhotoUrlHelper
            .getImageUrl(upload))
            .placeholder( R.drawable.loading_animation )
            .into(holder.itemView.postImage)

        holder.itemView.setOnClickListener {
            itemListener.recyclerViewListClicked(holder.itemView, position)
        }
    }

    class UploadsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}
