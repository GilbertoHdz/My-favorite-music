package com.ghg.favmusicapp.presentation.resultdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.ghg.favmusicapp.presentation.R
import com.ghg.favmusicapp.presentation.base.BaseFragment
import com.ghg.favmusicapp.presentation.common.observeEvent
import com.ghg.favmusicapp.presentation.databinding.ResultDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class ResultDetailFragment : BaseFragment<ResultDetailBinding, ResultDetailViewData, ResultDetailViewModel>() {

  override val layoutId = R.layout.result_detail

  override val viewModelClass: KClass<ResultDetailViewModel> = ResultDetailViewModel::class

  override fun bindViewModel(
    viewModel: ResultDetailViewModel,
    savedInstanceState: Bundle?
  ) {
    super.bindViewModel(viewModel, savedInstanceState)
    viewModel.navigation.observeEvent(this, ::handleNavigation)
  }

  private fun handleNavigation(target: ResultDetailViewModel.NavigationTarget) {
    when (target) {
      is ResultDetailViewModel.NavigationTarget.GoToWebsite -> {
        startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(target.url)))
      }
    }
  }
}