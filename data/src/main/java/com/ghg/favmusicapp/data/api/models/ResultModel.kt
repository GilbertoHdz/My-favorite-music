package com.ghg.favmusicapp.data.api.models

data class ResultModel(
  val collectionId: Int,
  val trackId: Int,
  val kind: String?,
  val artistName: String,
  val trackName: String?,
  val collectionName: String?,
  val artworkUrl100: String,
  val primaryGenreName: String?,
  val shortDescription: String?,
  val longDescription: String?,
  val collectionViewUrl: String?,
  val previewUrl: String?
)