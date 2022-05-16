package com.ghg.favmusicapp.presentation.common.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ghg.favmusicapp.presentation.common.handler.error.ErrorType

@BindingAdapter("errorText")
fun setErrorText(textView: TextView, errorType: ErrorType?) {
  errorType?.let {
    textView.setText(it.resId)
  }
}
