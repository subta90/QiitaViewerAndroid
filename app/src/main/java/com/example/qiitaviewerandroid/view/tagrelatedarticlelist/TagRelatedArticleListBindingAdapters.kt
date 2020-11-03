package com.example.qiitaviewerandroid.view.tagrelatedarticlelist

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageURL")
fun loadImageURL(imageView: ImageView, imageURLString: String?) {
    imageURLString ?: return
    val imageURL = Uri.parse(imageURLString)
    Glide.with(imageView).load(imageURL).into(imageView)

}
