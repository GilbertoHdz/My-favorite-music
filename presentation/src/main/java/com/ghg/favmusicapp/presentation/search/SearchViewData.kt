package com.ghg.favmusicapp.presentation.search

import androidx.lifecycle.MutableLiveData
import com.ghg.favmusicapp.domain.models.itunes.ResultDetail

class SearchViewData(
  val onclicked: () -> Unit,
  val navigateToDetail: (detail: ResultDetail) -> Unit
) {
  val searchResult: MutableLiveData<List<ResultDetail>> = MutableLiveData()
}