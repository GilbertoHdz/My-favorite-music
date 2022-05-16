package com.ghg.favmusicapp.presentation.common.handler.error

import androidx.annotation.StringRes
import com.ghg.favmusicapp.presentation.R

enum class ErrorType(@StringRes val resId: Int) {
  NO_INTERNET_CONNECTION(R.string.error_internet_connection),
  HTTP_EXCEPTION(R.string.error_something_went_wrong),
  UNKNOWN(R.string.error_something_went_wrong),
  NO_RESULT(R.string.search_no_results)
}