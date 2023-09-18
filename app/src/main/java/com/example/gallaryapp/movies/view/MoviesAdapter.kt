package com.example.gallaryapp.movies.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gallaryapp.R
import com.example.gallaryapp.databinding.VideoItemBinding

class MoviesAdapter(var imgList: List<String>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: VideoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.video_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imageUrl = imgList[position]
    }

    fun updateList(imgList: List<String>) {
        this.imgList = imgList
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: VideoItemBinding) : RecyclerView.ViewHolder(binding.root)
}