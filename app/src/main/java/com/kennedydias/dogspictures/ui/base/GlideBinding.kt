package com.kennedydias.dogspictures.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kennedydias.dogspictures.R

object GlideBinding {
    @JvmStatic
    @BindingAdapter("bind:imageUrlCenterCrop")
    fun loadRemoteImageCenterCrop(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.shape_placeholder)
            .centerCrop()
            .error(R.mipmap.ic_image_break)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("bind:imageUrlFitCenter")
    fun loadRemoteImageFitCenter(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.shape_placeholder)
            .fitCenter()
            .error(R.mipmap.ic_image_break)
            .into(view)
    }
}