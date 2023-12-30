package io.github.hadyahmed00.movieapp.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImageByUrl")
fun bindImagePictureOfDay(imageView: ImageView, data: String?) {

    data?.let {
        Glide.with(imageView.context)
            .load(it.toUri())
            .into(imageView)
    }
}
