package com.ghg.favmusicapp.presentation.main

import com.ghg.favmusicapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : BaseViewModel<MainViewData>() {

  override val viewData by lazy {
    MainViewData()
  }
}