package com.ghg.favmusicapp.presentation.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ghg.favmusicapp.domain.interactor.GetSearchInteractor
import com.ghg.favmusicapp.domain.models.itunes.ResultDetail
import com.ghg.favmusicapp.presentation.base.BaseViewModel
import com.ghg.favmusicapp.presentation.common.Event
import com.ghg.favmusicapp.presentation.common.postEvent
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
      onclicked = { getSearchResult() },
      navigateToDetail = {  navToResultDetail(it) }
    )
  }

  val navigation = MutableLiveData<Event<NavigationTarget>>()

  init {
    getSearchResult()
  }

  private fun getSearchResult(query: String = "jack johnson") {
    getSearchInteractor.execute(query)
      .catch {
        it.printStackTrace()
        Log.i("GIL", "Error")
      }
      .mapLatest {
        viewData.searchResult.postValue(it)
        Log.i("GIL", "Result: ${it.size}")
      }
      .onCompletion {
        Log.i("GIL", "Complete")
      }
      .launchIn(viewModelScope)
  }

  private fun navToResultDetail(result: ResultDetail) {
    navigation.postEvent(NavigationTarget.GoResultDetail(result))
  }

  sealed class NavigationTarget {
    class GoResultDetail(val detail: ResultDetail) : NavigationTarget()
  }
}