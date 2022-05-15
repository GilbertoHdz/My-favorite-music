package com.ghg.favmusicapp.presentation.search

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.ghg.favmusicapp.presentation.R
import com.ghg.favmusicapp.presentation.base.BaseFragment
import com.ghg.favmusicapp.presentation.databinding.SearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchBinding, SearchViewData, SearchViewModel>() {

  override val layoutId = R.layout.search

  override val viewModelClass: KClass<SearchViewModel> = SearchViewModel::class

  override fun bindViewModel(
    viewModel: SearchViewModel,
    savedInstanceState: Bundle?
  ) {
    super.bindViewModel(viewModel, savedInstanceState)
  }

  override fun initView(
    binding: SearchBinding,
    viewData: SearchViewData,
    savedInstanceState: Bundle?,
    viewLifecycleOwner: LifecycleOwner
  ) {
    super.initView(binding, viewData, savedInstanceState, viewLifecycleOwner)
  }
}