package com.ghg.favmusicapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ghg.favmusicapp.presentation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)
  }
}