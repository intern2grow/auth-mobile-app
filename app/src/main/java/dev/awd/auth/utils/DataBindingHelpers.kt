package dev.awd.auth.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import dev.awd.auth.R

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun setImageUrl(imageView: ImageView, url: String?, placeHolder: Drawable?) {
    if (url == null) {
        imageView.setImageDrawable(placeHolder)
    } else {

        Glide
            .with(imageView.context)
            .asBitmap()
            .load(url)
            .placeholder(R.drawable.downloading)
            .error(R.drawable.intern_logo)
            .into(imageView)
    }
}