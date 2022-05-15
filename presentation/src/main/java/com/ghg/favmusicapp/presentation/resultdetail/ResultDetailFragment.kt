package com.ghg.favmusicapp.presentation.resultdetail

import com.ghg.favmusicapp.presentation.R
import com.ghg.favmusicapp.presentation.base.BaseFragment
import com.ghg.favmusicapp.presentation.databinding.ResultDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class ResultDetailFragment : BaseFragment<ResultDetailBinding, ResultDetailViewData, ResultDetailViewModel>() {

  override val layoutId = R.layout.result_detail

  override val viewModelClass: KClass<ResultDetailViewModel> = ResultDetailViewModel::class
}