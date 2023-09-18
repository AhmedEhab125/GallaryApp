package com.example.gallaryapp.gallery.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gallaryapp.R
import com.example.gallaryapp.databinding.GalleryItemBinding

class GalleryAdapter(var imgList: List<String>) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: GalleryItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.gallery_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.imageUrl = imgList[position]
    }
    fun updateList(imgList : List<String>){
        this.imgList = imgList
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: GalleryItemBinding) : RecyclerView.ViewHolder(binding.root)
}