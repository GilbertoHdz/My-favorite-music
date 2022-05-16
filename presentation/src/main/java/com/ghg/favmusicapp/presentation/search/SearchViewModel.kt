package com.ghg.favmusicapp.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ghg.favmusicapp.domain.interactor.GetSearchInteractor
import com.ghg.favmusicapp.domain.models.itunes.ResultDetail
import com.ghg.favmusicapp.presentation.base.BaseViewModel
import com.ghg.favmusicapp.presentation.common.Event
import com.ghg.favmusicapp.presentation.common.handler.error.ErrorHandler
import com.ghg.favmusicapp.presentation.common.handler.error.ErrorType
import com.ghg.favmusicapp.presentation.common.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val getSearchInteractor: GetSearchInteractor,
  private val errorHandler: ErrorHandler
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
      .onStart { viewData.isLoading.postValue(true) }
      .catch {
        viewData.errorMessage.postValue(errorHandler.process(it))
        viewData.isVisibleErrorView.postValue(true)
      }
      .mapLatest {
        if (it.isEmpty()) {
          viewData.errorMessage.postValue(ErrorType.NO_RESULT)
        }
        viewData.searchResult.postValue(it)
        viewData.isVisibleErrorView.postValue(it.isEmpty())
      }
      .onCompletion {
        viewData.isLoading.postValue(false)
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