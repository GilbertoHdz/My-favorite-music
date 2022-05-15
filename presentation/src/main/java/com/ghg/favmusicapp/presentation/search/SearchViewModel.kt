package com.ghg.favmusicapp.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghg.favmusicapp.domain.interactor.GetSearchInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val getSearchInteractor: GetSearchInteractor
) : ViewModel() {

  fun getSearchResult(query: String = "jack johnson") {
    viewModelScope.launch {
      val result = getSearchInteractor.executeSuspend(query)
      Log.i("GIL", "Result")
    }
  }
}