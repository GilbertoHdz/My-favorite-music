package com.ghg.favmusicapp.presentation.resultdetail

import androidx.lifecycle.MutableLiveData
import com.ghg.favmusicapp.domain.models.itunes.ResultDetail

class ResultDetailViewData(
  val detail: MutableLiveData<ResultDetail>,
  val goToWebsite: (url: String) -> Unit
) {

}