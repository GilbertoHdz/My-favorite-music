package com.ghg.favmusicapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ghg.favmusicapp.domain.models.itunes.ResultDetail
import com.ghg.favmusicapp.presentation.databinding.SearchItemBinding

class SearchAdapter : ListAdapter<ResultDetail, SearchAdapter.SongItemVH>(DiffUtilCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemVH {
    val inflater = LayoutInflater.from(parent.context)
    return SongItemVH(SearchItemBinding.inflate(inflater))
  }

  override fun onBindViewHolder(holder: SongItemVH, position: Int) {
    holder.binding.item = getItem(position)
  }

  class SongItemVH(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root)
}

internal typealias SongItemClicks = (Long) -> Unit

private object DiffUtilCallback : DiffUtil.ItemCallback<ResultDetail>() {
  override fun areItemsTheSame(oldItem: ResultDetail, newItem: ResultDetail): Boolean {
    return oldItem.trackId == newItem.trackId
  }

  override fun areContentsTheSame(oldItem: ResultDetail, newItem: ResultDetail): Boolean {
    return oldItem == newItem
  }
}