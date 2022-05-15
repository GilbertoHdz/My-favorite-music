package com.ghg.favmusicapp.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghg.favmusicapp.domain.interactor.GetSearchInteractor
import com.ghg.favmusicapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val getSearchInteractor: GetSearchInteractor
) : BaseViewModel<SearchViewData>() {

  override val viewData by lazy {
    SearchViewData(
      onclicked = { getSearchResult() }
    )
  }

  fun getSearchResult(query: String = "jack johnson") {
    getSearchInteractor.execute(query)
      .catch {
        Log.i("GIL", "Error")
      }
      .mapLatest {
        Log.i("GIL", "Result: ${it.size}")
      }
      .onCompletion {
        Log.i("GIL", "Complete")
      }
      .launchIn(viewModelScope)
  }
}