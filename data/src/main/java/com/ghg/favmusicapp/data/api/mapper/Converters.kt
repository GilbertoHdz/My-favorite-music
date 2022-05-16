package com.ghg.favmusicapp.data.api.mapper

import com.ghg.favmusicapp.data.api.models.SearchResponse
import com.ghg.favmusicapp.domain.models.itunes.ResultDetail

fun SearchResponse.toDomain() : List<ResultDetail> {
  return this.results.map { result ->
    ResultDetail(
      result.trackId,
      result.kind ?: "",
      result.artistName,
      result.trackName ?: "No track name",
      result.collectionName ?: "No collection",
      result.artworkUrl100,
      result.primaryGenreName ?: "No genre",
      result.shortDescription ?: "No description",
      result.longDescription ?: "No description",
      result.collectionViewUrl ?: "No view url",
      result.previewUrl ?: ""
    )
  }
}