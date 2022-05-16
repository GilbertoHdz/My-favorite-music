package com.ghg.favmusicapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ghg.favmusicapp.domain.models.itunes.ResultDetail
import com.ghg.favmusicapp.presentation.databinding.SearchItemBinding

/**
 * Using [ListAdapter] with DiffUtils since the list is dynamic.
 */
class SearchAdapter : ListAdapter<ResultDetail, SearchAdapter.SongItemVH>(DiffUtilCallback) {

  private var songItemClicks: SongItemClicks? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemVH {
    val inflater = LayoutInflater.from(parent.context)
    return SongItemVH(SearchItemBinding.inflate(inflater))
  }

  override fun onBindViewHolder(holder: SongItemVH, position: Int) {
    holder.binding.item = getItem(position)
    holder.binding.searchItemCardContainer.setOnClickListener {
      songItemClicks?.invoke(getItem(position))
    }
  }

  fun handleSongItemClicks(listener: SongItemClicks) {
    this.songItemClicks = listener
  }

  class SongItemVH(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root)
}

internal typealias SongItemClicks = (ResultDetail) -> Unit

private object DiffUtilCallback : DiffUtil.ItemCallback<ResultDetail>() {
  override fun areItemsTheSame(oldItem: ResultDetail, newItem: ResultDetail): Boolean {
    return oldItem.trackId == newItem.trackId
  }

  override fun areContentsTheSame(oldItem: ResultDetail, newItem: ResultDetail): Boolean {
    return oldItem == newItem
  }
}