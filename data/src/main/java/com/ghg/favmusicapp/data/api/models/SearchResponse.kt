package com.ghg.favmusicapp.data.api.models

data class SearchResponse(
  val resultCount: Int,
  val results: List<ResultModel>
)