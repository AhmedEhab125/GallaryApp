package com.example.nwavetask.downloadImg

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context).load(imageUrl).into(view)
}
@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: Uri?) {
    Glide.with(view.context).load(imageUrl).into(view)
}