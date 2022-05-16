package com.ghg.favmusicapp.presentation.common.extensions.edittext

import androidx.appcompat.widget.SearchView

fun SearchView.onQueryTextChange(onQueryTextChange: (String) -> Unit) {
  this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
      return false
    }
    override fun onQueryTextChange(newText: String?): Boolean {
      onQueryTextChange.invoke(newText ?: "")
      return true
    }
  })
}