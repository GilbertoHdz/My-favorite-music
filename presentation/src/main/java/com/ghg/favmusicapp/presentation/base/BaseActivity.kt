package com.ghg.favmusicapp.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelLazy
import kotlin.reflect.KClass

/**
 * State management architecture approach
 * Fast development since we are using [viewbinding] instead of kotlin synthetics
 * [TBinding] as View
 * [TView] as ViewData
 * [TViewModel] as ViewModel
 */
abstract class BaseActivity<TBinding : ViewDataBinding, TView : Any, TViewModel : BaseViewModel<TView>> : AppCompatActivity() {

  @get:LayoutRes
  protected abstract val layoutId: Int
  protected abstract val viewModelClass: KClass<TViewModel>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val viewModel = ViewModelLazy(
      viewModelClass,
      { viewModelStore },
      { defaultViewModelProviderFactory }).value

    bindViewModel(viewModel, savedInstanceState)

    val binding = DataBindingUtil.setContentView<TBinding>(this, layoutId)
    // binding.setVariable(BR.model, viewModel.viewData) TODO use this to share model on layouts
    binding.lifecycleOwner = this

    initView(binding, viewModel.viewData, savedInstanceState)
  }

  open fun bindViewModel(
    viewModel: TViewModel,
    savedInstanceState: Bundle?
  ) = Unit

  open fun initView(
    binding: TBinding,
    viewData: TView,
    savedInstanceState: Bundle?
  ) = Unit
}