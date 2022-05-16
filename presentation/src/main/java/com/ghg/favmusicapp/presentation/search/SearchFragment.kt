package com.ghg.favmusicapp.presentation.search

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.ghg.favmusicapp.presentation.R
import com.ghg.favmusicapp.presentation.base.BaseFragment
import com.ghg.favmusicapp.presentation.common.observeEvent
import com.ghg.favmusicapp.presentation.databinding.SearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchBinding, SearchViewData, SearchViewModel>() {

  private val adapter by lazy { SearchAdapter() }

  override val layoutId = R.layout.search

  override val viewModelClass: KClass<SearchViewModel> = SearchViewModel::class

  override fun bindViewModel(
    viewModel: SearchViewModel,
    savedInstanceState: Bundle?
  ) {
    super.bindViewModel(viewModel, savedInstanceState)
    viewModel.navigation.observeEvent(this, ::handleNavigation)
  }

  override fun initView(
    binding: SearchBinding,
    viewData: SearchViewData,
    savedInstanceState: Bundle?,
    viewLifecycleOwner: LifecycleOwner
  ) {
    super.initView(binding, viewData, savedInstanceState, viewLifecycleOwner)
    viewData.searchResult.observe(viewLifecycleOwner) { result -> adapter.submitList(result) }
    binding.searchResultList.adapter = adapter
    adapter.handleSongItemClicks(viewData.navigateToDetail)
  }

  private fun handleNavigation(target: SearchViewModel.NavigationTarget) {
    when (target) {
      is SearchViewModel.NavigationTarget.GoResultDetail -> {
        findNavController().navigate(
          SearchFragmentDirections.actionToResultDetail(target.detail)
        )
      }
    }
  }
}