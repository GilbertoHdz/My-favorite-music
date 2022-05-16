package com.ghg.favmusicapp.presentation.common.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ghg.favmusicapp.presentation.R

@BindingAdapter(
  "urlGlide"
)
fun ImageView.loadBinding(
  url: String?
) {
  url ?: return

  val reqOption = RequestOptions()
  reqOption.placeholder(R.drawable.ic_image_placeholder)
  reqOption.error(R.drawable.ic_image_not_supported)
  reqOption.transform(RoundedCorners(8))

  Glide.with(this).load(url)
    .apply(reqOption)
    .into(this)
}