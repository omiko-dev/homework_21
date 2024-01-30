package com.example.homework_21.presentation.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.homework_21.R

fun ImageView.loadImagesWithGlide(url: String, cornerRadius: Int = 0) {
    Glide.with(this)
        .load(url)
        .error(R.drawable.ic_launcher_foreground)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(cornerRadius)))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}