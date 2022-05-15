package com.ghg.favmusicapp.presentation.main

import com.ghg.favmusicapp.presentation.R
import com.ghg.favmusicapp.presentation.base.BaseActivity
import com.ghg.favmusicapp.presentation.databinding.MainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class MainActivity : BaseActivity<MainBinding, MainViewData, MainViewModel>() {
  override val layoutId = R.layout.main
  override val viewModelClass: KClass<MainViewModel> = MainViewModel::class
}