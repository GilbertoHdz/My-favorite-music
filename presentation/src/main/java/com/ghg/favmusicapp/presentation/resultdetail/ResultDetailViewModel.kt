package com.ghg.favmusicapp.presentation.resultdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ghg.favmusicapp.domain.models.itunes.ResultDetail
import com.ghg.favmusicapp.presentation.base.BaseViewModel
import com.ghg.favmusicapp.presentation.common.Event
import com.ghg.favmusicapp.presentation.common.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultDetailViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<ResultDetailViewData>() {

  override val viewData by lazy {
    ResultDetailViewData(
      detail = MutableLiveData(savedStateHandle.get<ResultDetail>("detail")),
      goToWebsite = { navToResultDetail(it) }
    )
  }

  val navigation = MutableLiveData<Event<NavigationTarget>>()

  private fun navToResultDetail(url: String) {
    navigation.postEvent(NavigationTarget.GoToWebsite(url))
  }

  sealed class NavigationTarget {
    class GoToWebsite(val url: String) : NavigationTarget()
  }
}