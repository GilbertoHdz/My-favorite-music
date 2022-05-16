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
      onSearch = { getSearchResult(it) },
      navigateToDetail = {  navToResultDetail(it) }
    )
  }

  val navigation = MutableLiveData<Event<NavigationTarget>>()

  private fun getSearchResult(query: String) {
    getSearchInteractor.execute(query)
      .catch {
        it.printStackTrace()
      }
      .mapLatest {
        viewData.searchResult.postValue(it)
      }
      .onCompletion {
        Log.i(TAG, "Complete")
      }
      .launchIn(viewModelScope)
  }

  private fun navToResultDetail(result: ResultDetail) {
    navigation.postEvent(NavigationTarget.GoResultDetail(result))
  }

  sealed class NavigationTarget {
    class GoResultDetail(val detail: ResultDetail) : NavigationTarget()
  }

  companion object {
    private const val TAG = "SearchViewModel"
  }
}