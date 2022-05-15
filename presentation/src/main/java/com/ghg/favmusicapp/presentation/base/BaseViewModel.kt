package com.ghg.favmusicapp.presentation.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<View : Any> : ViewModel() {

  abstract val viewData: View
}