package com.ghg.favmusicapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.LifecycleOwner
import com.ghg.favmusicapp.presentation.BR
import kotlin.reflect.KClass

abstract class BaseFragment<TBinding : ViewDataBinding, TViewData : Any, TViewModel : BaseViewModel<TViewData>> : Fragment() {

  @get:LayoutRes
  abstract val layoutId: Int

  abstract val viewModelClass: KClass<TViewModel>

  /**
   * Use this to bind to ViewModel events
   */
  open fun bindViewModel(viewModel: TViewModel, savedInstanceState: Bundle?) = Unit

  /**
   * Use this to do any UI initialization.
   */
  open fun initView(
    binding: TBinding,
    viewData: TViewData,
    savedInstanceState: Bundle?,
    viewLifecycleOwner: LifecycleOwner
  ) = Unit

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding = DataBindingUtil.inflate<TBinding>(inflater, layoutId, container, false)
    try {
      val viewModel = createViewModelLazy(
        viewModelClass,
        { viewModelStore },
        { defaultViewModelProviderFactory }).value
      binding.setVariable(BR.model, viewModel.viewData)
      bindViewModel(viewModel, savedInstanceState)
      binding.lifecycleOwner = viewLifecycleOwner

      initView(binding, viewModel.viewData, savedInstanceState, viewLifecycleOwner)
    }catch (e: Throwable){
      e.printStackTrace()
      Toast.makeText(requireContext(), "Include error", Toast.LENGTH_LONG).show()
    }

    return binding.root
  }
}