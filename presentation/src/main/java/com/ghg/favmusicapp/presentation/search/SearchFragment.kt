package com.ghg.favmusicapp.presentation.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ghg.favmusicapp.presentation.R
import com.ghg.favmusicapp.presentation.base.BaseFragment
import com.ghg.favmusicapp.presentation.common.extensions.edittext.onQueryTextChange
import com.ghg.favmusicapp.presentation.common.observeEvent
import com.ghg.favmusicapp.presentation.databinding.SearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.search.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchBinding, SearchViewData, SearchViewModel>() {

  private val adapter by lazy { SearchAdapter() }
  private var mActionModeCompat: ActionMode? = null

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
    setupActionBar(binding, viewData)
  }

  override fun onDestroyView() {
    mActionModeCompat?.finish()
    super.onDestroyView()
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

  private fun setupActionBar(binding: SearchBinding, viewData: SearchViewData) {
    (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
    (requireActivity() as AppCompatActivity).supportActionBar?.show()
    binding.searchToolbarLayout.searchToolbarContainer.setOnClickListener {
      if (mActionModeCompat == null) {
        (requireActivity() as AppCompatActivity).startSupportActionMode(actionModeCallbackCompat(viewData))
      } else {
        mActionModeCompat?.finish()
      }
    }
  }

  private fun actionModeCallbackCompat(viewData: SearchViewData) = object : ActionMode.Callback {

    override fun onCreateActionMode(
      mode: ActionMode?,
      menu: Menu?
    ): Boolean {
      (requireActivity() as AppCompatActivity).menuInflater.inflate(R.menu.menu_search, menu)
      mActionModeCompat = mode
      lifecycleScope.launch {
        delay(ANIM_TOOLBAR_DISPLAYED_MIN)
        searchAppBar.setExpanded(false, true)
      }
      return true
    }

    override fun onPrepareActionMode(
      mode: ActionMode?,
      menu: Menu?
    ): Boolean {
      val searchView: SearchView? = menu?.findItem(R.id.searchMenuItem)?.actionView as SearchView?
      searchView?.let { search ->
        search.onActionViewExpanded()
        search.queryHint = getString(R.string.search_toolbar_search)
        search.onQueryTextChange { query ->
          if (query.isNotBlank() && query.isNotEmpty()) {
            viewData.onSearch.invoke(query)
          }
        }
      }

      return false
    }

    override fun onActionItemClicked(
      mode: ActionMode?,
      item: MenuItem?
    ): Boolean {
      return false
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
      mActionModeCompat = null
      lifecycleScope.launch {
        delay(ANIM_TOOLBAR_DISPLAYED_MAX)
        searchAppBar?.setExpanded(true)
      }
    }
  }

  companion object {
    private const val ANIM_TOOLBAR_DISPLAYED_MAX = 200L
    private const val ANIM_TOOLBAR_DISPLAYED_MIN = 50L
  }
}