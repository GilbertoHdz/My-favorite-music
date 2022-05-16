package com.ghg.favmusicapp.domain.models.itunes

import java.io.Serializable

data class ResultDetail(
  val trackId: Int,
  val kind: String,
  val artistName: String,
  val trackName: String,
  val collectionName: String,
  val artworkUrl60: String,
  val primaryGenreName: String,
  val shortDescription: String,
  val longDescription: String,
  val collectionViewUrl: String,
  val previewUrl: String
): Serializable