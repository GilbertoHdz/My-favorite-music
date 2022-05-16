package com.ghg.favmusicapp.presentation.search

import androidx.lifecycle.MutableLiveData
import com.ghg.favmusicapp.domain.models.itunes.ResultDetail
import com.ghg.favmusicapp.presentation.common.handler.error.ErrorType

class SearchViewData(
  val onSearch: (query: String) -> Unit,
  val navigateToDetail: (detail: ResultDetail) -> Unit
) {
  val searchResult: MutableLiveData<List<ResultDetail>> = MutableLiveData(emptyList())
  val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
  val isVisibleErrorView: MutableLiveData<Boolean> = MutableLiveData(false)
  val errorMessage: MutableLiveData<ErrorType> = MutableLiveData()
}