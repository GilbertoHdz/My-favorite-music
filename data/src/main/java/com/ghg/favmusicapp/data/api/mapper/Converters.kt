package com.ghg.favmusicapp.data.api.mapper

import com.ghg.favmusicapp.data.api.models.SearchResponse
import com.ghg.favmusicapp.domain.models.itunes.ResultDetail

fun SearchResponse.toDomain() : List<ResultDetail> {
  return this.results.map { result ->
    ResultDetail(result.artistName)
  }
}